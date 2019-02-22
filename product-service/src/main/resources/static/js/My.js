

//登录
function login() {
    var account = $("#memberAcct").val();
    var password = $("#memberPwd").val();
    $.ajax({
        url: '/shopping/login',
        type: 'post',
        dataType: "json",
        data: "account=" + account + "&password=" + password,
        success: function (data) {
            if (data.code == '100') {
                alert(data.msg);
                window.location.href = "index.html";
            } else {
                alert(data.msg);
            }
        }
    });
}



//注册
function register() {
    var account = $("#memberSignupAccount").val();
    var password = $("#memberSignupPwd").val();
    var repwd = $("#memberSignupRepwd").val();
    var email = $("#email").val();
    var verifycode = $("#memberSignupCaptcha").val();
    if (password != repwd){
        alert("两次密码输入不一致");
    }
    $.ajax({
        url: '/shopping/register',
        type: 'post',
        dataType: "json",
        data: "account=" + account + "&password=" + password+"&email="+email+"&code="+verifycode,
        success: function (data) {
            if (data.code == '100') {
                alert(data.msg);
                window.location.href = "index.html";
            } else {
                alert("注册失败");
            }
        }
    });
}

function getCode(n) {
    var all = "azxcvbnmsdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP0123456789";
    var b = "";
    for (var i = 0; i < n; i++) {
        var index = Math.floor(Math.random() * 62);
        b += all.charAt(index);

    }
    return b;
};

function change() {
    document.getElementById("memberSignupCaptchaImg").innerHTML = getCode(4);

}


function showOrders(ele, data) {
    $(ele).empty();
    $.ajax({
        url: 'querry/list',
        type: 'GET',
        data: data,
        success: function (data) {
            if (data.code == 100) {
                var list = data.map.pageInfo.list;
                console.log(list);
                var num=0;
                $(list).each(function (index, data) {
                    console.log(data);
                    var ProductInfo = '<div class="rcr"><div class="rcr-top"><img src="images/rc-'+data.productIcon+'" width="100%"></div><div class="rcr-bot"><div class="rb-top">' + data.productName + '</div><div class="second_P"><span class="fk-prop">￥</span><span class="fk-prop-price">' + data.productPrice + '</span> ' +
                        '<span class="second_Marketprice">￥0.00</span></div><div class="buy"><a class="second_mallBuy" href="orange.html?data='+data.productId+'"><span style="color: white;">购买</span></a></div></div></div>';
                    $(ele).append(ProductInfo);
                });
                var ele1 = $(".pages");
            } else {
                $(".pages").empty();
            }
        }
    });
}


