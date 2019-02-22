package com.example.productservice.controller;

import com.example.productservice.domain.Msg;
import com.example.productservice.domain.ProductInfo;
import com.example.productservice.domain.ShoppingCar;
import com.example.productservice.domain.UserMain;
import com.example.productservice.service.IndexService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by zhanglh on 2018/3/29.
 */
@RestController
@RequestMapping("/querry")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    IndexService indexService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Msg showProduct(HttpSession session){
        UserMain userMain=(UserMain) session.getAttribute("userMain");
        List<ProductInfo> list = new ArrayList<ProductInfo>();
        ProductInfo productInfo = new ProductInfo();
        if (userMain == null){
            list=indexService.querryAll();
            if(!list.isEmpty()&&list.size()!=0){
                PageInfo<ProductInfo> pageInfo=new PageInfo<>(list);
                return Msg.success("").add("pageInfo", pageInfo);
            }
            return Msg.error("");
        }else {
            List<UserMain> userMainList = indexService.getAllUser();
            int[][] sparseMatrix = new int[userMainList.size()][userMainList.size()];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
            Map<String, Integer> userItemLength = new HashMap<>();//存储每一个用户对应的不同物品总数  eg: A 3
            Map<String, Set<String>> itemUserCollection = new HashMap<>();//建立物品到用户的倒排表 eg: a A B
            Set<String> items = new HashSet<>();//辅助存储物品集合
            Map<String, Integer> userID = new HashMap<>();//辅助存储每一个用户的用户ID映射
            Map<Integer, String> idUser = new HashMap<>();//辅助存储每一个ID对应的用户映射
            for(int i = 0; i < userMainList.size() ; i++){//依次处理N个用户 输入数据  以空格间隔
                List<ShoppingCar> shoppingCarList = indexService.getAllShoppingCarById(userMainList.get(i).getId());
                int length = shoppingCarList.size();
                userItemLength.put(userMainList.get(i).getId().toString(), length);//eg: A 3
                userID.put(userMainList.get(i).getId().toString(), i);//用户ID与稀疏矩阵建立对应关系
                idUser.put(i, userMainList.get(i).getId().toString());
                //建立物品--用户倒排表
                for(int j = 0; j < length; j ++){
                    if(items.contains(shoppingCarList.get(j).getProductId().toString())){//如果已经包含对应的物品--用户映射，直接添加对应的用户
                        itemUserCollection.get(shoppingCarList.get(j).getProductId().toString()).add(userMainList.get(i).getId().toString());
                    }else{//否则创建对应物品--用户集合映射
                        items.add(shoppingCarList.get(j).getProductId().toString());
                        itemUserCollection.put(shoppingCarList.get(j).getProductId().toString(), new HashSet<String>());//创建物品--用户倒排关系
                        itemUserCollection.get(shoppingCarList.get(j).getProductId().toString()).add(userMainList.get(i).getId().toString());
                    }
                }
            }
//            System.out.println(itemUserCollection.toString());
            //计算相似度矩阵【稀疏】
            Set<Map.Entry<String, Set<String>>> entrySet = itemUserCollection.entrySet();
            Iterator<Map.Entry<String, Set<String>>> iterator = entrySet.iterator();
            while(iterator.hasNext()){
                Set<String> commonUsers = iterator.next().getValue();
                for (String user_u : commonUsers) {
                    for (String user_v : commonUsers) {
                        if(user_u.equals(user_v)){
                            continue;
                        }
                        sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;//计算用户u与用户v都有正反馈的物品总数
                    }
                }
            }
//            System.out.println(userItemLength.toString());
//            System.out.println("Input the user for recommendation:<eg:A>");
            String recommendUser = userMain.getId().toString();
//            System.out.println(userID.get(recommendUser));
            //计算用户之间的相似度【余弦相似性】
            int recommendUserId = userID.get(recommendUser);
            for (int j = 0;j < sparseMatrix.length; j++) {
                if(j != recommendUserId){
                    System.out.println(idUser.get(recommendUserId)+"--"+idUser.get(j)+"相似度:"+sparseMatrix[recommendUserId][j]/Math.sqrt(userItemLength.get(idUser.get(recommendUserId))*userItemLength.get(idUser.get(j))));
                }
            }

            //计算指定用户recommendUser的物品推荐度
            for(String item: items){//遍历每一件物品
                Set<String> users = itemUserCollection.get(item);//得到购买当前物品的所有用户集合
                if(!users.contains(recommendUser)){//如果被推荐用户没有购买当前物品，则进行推荐度计算
                    double itemRecommendDegree = 0.0;
                    for(String user: users){
                        itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)]/Math.sqrt(userItemLength.get(recommendUser)*userItemLength.get(user));//推荐度计算
                    }
                    if (itemRecommendDegree>0.4235) {
                        productInfo = indexService.getProductById(item);
                        list.add(productInfo);
                    }
                    System.out.println("The item "+item+" for "+recommendUser +"'s recommended degree:"+itemRecommendDegree);
                }
            }
            if(!list.isEmpty()&&list.size()!=0){
                PageInfo<ProductInfo> pageInfo=new PageInfo<>(list);
                return Msg.success("").add("pageInfo", pageInfo);
            }else {
                list=indexService.querryAll();
                PageInfo<ProductInfo> pageInfo=new PageInfo<>(list);
                return Msg.success("").add("pageInfo", pageInfo);
            }
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Msg getProductById(@PathVariable String id) {
        ProductInfo productInfo=indexService.getProductById(id);
        if (productInfo!=null){
            return Msg.success("").add("productInfo", productInfo);
        }
        return Msg.error("");
    }
}
