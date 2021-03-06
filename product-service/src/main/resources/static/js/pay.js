window.onload=function(){
    vm.getCar();
}

/*模拟数据*/
var shopCartdatas = {
    shopcartdatas:[
        {
        }
    ]
}
var addressdatas = {
    addressdata:[
        {
            "name":"吴浩然1",
            "city":"北京市",
            "area":"丰台区",
            "minarea":"嘉园一里9号楼404",
        },
        {
            "name":"吴浩然2",
            "city":"北京市",
            "area":"丰台区",
            "minarea":"嘉园一里9号楼404",
        },
        {
            "name":"吴浩然3",
            "city":"北京市",
            "area":"丰台区",
            "minarea":"嘉园一里9号楼404",
        },
        {
            "name":"吴浩然4",
            "city":"北京市",
            "area":"丰台区",
            "minarea":"嘉园一里9号楼404",
        },
        {
            "name":"吴浩然5",
            "city":"北京市",
            "area":"丰台区",
            "minarea":"嘉园一里9号楼404",
        },
    ]
}
var payment = {
    paymentdata:[
        {
            "name":"货到付款",
        },
        {
            "name":"在线支付",
        },
        {
            "name":"银行汇款",
        },
    ]
}
var invoice = {
    invoicedata:[
        {
            "name":"不要发票",
        },
        {
            "name":"需要发票",
        },
    ]
}
var Coupon = {
    Coupondata:[
        {
            "price":500,
            "time":"2017-08-30",
            "type":"[ 店铺类 ]",
            "types":"[ 店铺类 ]",
        },
        {
            "price":100,
            "time":"2017-08-30",
            "type":"[ 店铺类 ]",
            "types":"[ 店铺类 ]",
        },
        {
            "price":200,
            "time":"2017-08-30",
            "type":"[ 店铺类 ]",
            "types":"[ 店铺类 ]",
        },
    ]
}
var deliverymode = {
    deliverymodeData:[
        {
            "type":"自提",
            "name":"选择自提时，请与卖家协商取货地址。",
        },
        {
            "type":"物流",
            "name":"由卖家发货。",
        },
    ]
}

var vm = new Vue({
    el: "#myVue",
    data: {
        /*数据源*/
        shopTableDatas:[],
        moreAddressData:addressdatas.addressdata,//地址数据
        paymentdatas:payment.paymentdata,//支付类型数据
        deliverymodedatas:deliverymode.deliverymodeData,//配送类型数据
        invoicedatas:invoice.invoicedata,//发票类型数据
        Coupondatas:Coupon.Coupondata,//优惠券数据
        userBuyData:[],//用户购买数据

        /*默认选择标签*/
        checkedAll:false, //全选状态
        limitNum:3,//默认显示几个地址
        currentIndex:1,//地址默认选择
        paymentIndex:1,//支付类型默认选择
        deliverymodeIndex:1,//配送类型默认选择
        invoiceIndex:1,//发票类型默认选择
        CouponIndex:-1,//优惠券默认选择
        stopDelete:"",//定时器id(用于清空定时器)
        activeName: '支付平台',

        /*关键字段初始化*/
        moreaddressName:"",//收货人姓名
        moreaddressCity:"",//收货人所在市
        moreaddressArea:"",//收货人所在区
        moreaddressMinarea:"",//收货人所在小区
        couponPrice:0,//优惠券默认金额
        goodNums:0,    //商品或者服务总数
        totalMoney:0, //总价格
        saveandremove:true,//收藏和取消收藏的状态
        goodsNum:0,//商品的数量
        serviceNum:0,//服务的数量

    },
    methods: {
        getCar:function(){
            // var ids = {};
            var ids = new Array();
            for(var i=0; i<localStorage.length;i++){
                console.log('localStorage里存储的第'+i+'条数据的名字为：'+localStorage.key(i)+',值为：'+localStorage.getItem(localStorage.key(i)));
                // ids[i] = localStorage.key(i);
                ids.push(localStorage.key(i));
            }
            console.log(JSON.stringify(ids));
            $.ajax({
                type: "POST",
                url: '/shoppingcar/listByIds',
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (data) {
                    if (data.code == '100') {
                        vm.shopTableDatas=data.map.shopcartdatas;
                        console.log(data.map.shopcartdatas);
                    } else {
                        alert("购物车无记录");
                    }
                }
            });
        },
        /*商品数量增加减少函数*/
        goodNum:function(item,way){
            if(way == 1){
                item.counts++;
                vm.countTotalMoney()
            }else{
                if(item.counts < 2){
                    item.counts =1;
                }else{
                    item.counts--;
                    vm.countTotalMoney()
                }

            }
        },
        /*单选函数*/
        checkedRadioBtn:function(tabledatas){
            this.countTotalMoney()
            /*单选计算商品或服务数量*/
            if(tabledatas.checked == true){
                this.goodNums += 1;
            }else if(tabledatas.checked == false){
                this.goodNums -= 1;
            }else{
                console.log("未知错误！")
            }
        },
        /*全选函数*/
        checkedAllBtn:function(checkedAll){
            this.goodNums = 0;
            var _this= this;
            /*全选计算商品或服务数量*/
            if(checkedAll == true){
                for(x in this.shopTableDatas){
                    this.shopTableDatas[x].checked = true;
                    _this.goodNums += 1;
                }
            }else{
                for(y in this.shopTableDatas){
                    this.shopTableDatas[y].checked = false;
                    this.goodNums = 0;
                }
            }
            vm.countTotalMoney();
        },
        /*删除单个选中函数*/
        deletegoods:function(index){
            console.log(index);
            $.ajax({
                url: '/shoppingcar/delete',
                type: 'post',
                dataType: "json",
                data: "id=" + index,
                success: function (data) {
                    if (data.code == '100') {
                        alert("删除成功");
                        vm.getCar();
                    } else {
                        alert("注册失败");
                    }
                }
            });
            vm.countTotalMoney();
        },
        /*删除多个选中函数*/
        deleteSelectAll:function(){
            for(var i = this.shopTableDatas.length-1 ; i >= 0 ; i--){
                if(this.shopTableDatas[i].checked  == true){
                    $.ajax({
                        url: '/shoppingcar/delete',
                        type: 'post',
                        dataType: "json",
                        data: "id=" + this.shopTableDatas[i].id,
                        success: function (data) {
                            if (data.code == '100') {
                                vm.getCar();
                            } else {
                                alert("删除选中失败");
                            }
                        }
                    });
                    vm.countTotalMoney();
                }
            }
            vm.countTotalMoney();
        },
        /*单个移到收藏*/
        movesSave:function(index){
            this.shopTableDatas.splice(index,1)
        },
        /*多个商品移动函数*/
        saveSelectAll:function(){
            for(var i = 0 ; i <= this.shopTableDatas.length ; i++){
                if(this.shopTableDatas[i].checked  == true){
                    console.log(this.shopTableDatas[i])
                    this.stopDelete = setTimeout(function(){
                        vm.deleteSelectAll();
                        clearInterval(this.stopDelete)
                    },10);
                }
            }
        },
        /*计算商品总价函数*/
        countTotalMoney:function(){
            var _this = this;
            _this.totalMoney = 0;
            this.shopTableDatas.forEach(function(item,index){
                if(item.checked == true){
                    _this.totalMoney += item.counts*item.productPrice
                }
            })
        },
        /*保存购买数据*/
        saveData:function(){
            var _this = this;
            _this.userBuyData.length = 0;
            this.shopTableDatas.forEach(function(item,index){
                if(item.checked == true){
                    var msg={
                        msgdata:[
                            {
                                productId:item.productId,
                                name:item.productName,
                                num:item.counts,
                                price:item.productPrice,
                                src:item.src,
                            },
                        ]
                    };
                    var time=new Date().getTime();
                    localStorage.setItem(item.productId,JSON.stringify(msg));
                    console.log(JSON.stringify(msg));
                    // window.location.href='../CivilMilitaryIntegration/ShoppingCartAdress.html'
                }
            })


        },
        /*loadCartAddress:function(){
            this.userBuyData = JSON.parse(localStorage.getItem("user"));
            console.log(this.userBuyData)

            if(localStorage.length!=0){

                for(var i=0;i<localStorage.length;i++){
                    var key=localStorage.key(i);
                    var jsonStr=localStorage.getItem(key);
                    this.userBuyData=JSON.parse(jsonStr);
                    console.log(this.userBuyData)
                }
            }
        },*/

        /*地址点击函数*/
        currentIndexClick:function(moreaddressData,index){
            this.currentIndex = index;
            this.moreaddressName = moreaddressData.name;
            this.moreaddressCity = moreaddressData.city;
            this.moreaddressArea = moreaddressData.area;
            this.moreaddressMinarea = moreaddressData.minarea;
        },
        /*优惠券点击函数*/
        CouponIndexClcik:function(index,price){
            this.CouponIndex = index;
            this.couponPrice = price;

        },
        /*提示删除单个商品*/
        alertRadio:function(index){
            console.log(index);
            vm.deletegoods(index);
        },
        /*提示多个删除函数*/
        alertMuch:function(){
            this.$confirm('此操作将永久删除已选择商品或服务, 是否继续?', '提示', {
                confirmButtonText: '确定删除',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                type: 'success',
                message: '删除成功!',
                callback : vm.deleteSelectAll()
            });
        }).catch(() => {
                this.$message({
                type: 'warning',
                message: '已取消删除'
            });
        });
        },

        /*提示单个商品移动到收藏函数*/
        alertmovesSavegoods:function(index){
            this.$confirm('此操作将已选择商品或服务移到我的收藏, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                type: 'success',
                message: '收藏成功!',
                callback : vm.movesSave(index)
            });
        }).catch(() => {
                this.$message({
                type: 'success',
                message: '收藏成功!',
            });
        });
        },
        /*提示收藏多个商品函数*/
        alertMuchgoods:function(){
            this.$confirm('此操作将已选择商品或服务移到我的收藏, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                type: 'success',
                message: '收藏成功!',
                callback : vm.saveSelectAll()
            });
        }).catch(() => {
                this.$message({
                type: 'success',
                message: '收藏成功!',
            });
        });
        }
    },
    /*金额过滤器*/
    filters:{
        moneyFiler:function(value){

            return "￥"+value.toFixed(2);
        }
    },
    /*用于过滤缓存(用于过滤地址显示几个)*/
    computed:{
        filterAddress:function(){
            return this.moreAddressData.slice(0,this.limitNum)
        }
    },


});


