
$(function(){
    var divlogin=$("#loginbox");
    $.ajax({
        url: '/shopping/checklogin',
        type: 'post',
        async:false,
        dataType: "json",
        success: function (data) {
            if (data.code == 100) {
                var text="您好！欢迎来到果然新鲜！";
                var href=$("<a></a>").text("个人中心");
                var logout_a=$("<a></a>").attr("href","").addClass("logout").text(" 退出");
                divlogin.empty();
                divlogin.append(text).append(href).attr("href","student/index").append("&nbsp;| ").append(logout_a);
                return true;
            } else {
                var href=$("<a></a>").text("注册   |   登录").addClass("buttno");
                divlogin.empty();
                divlogin.append(href);
                return false;
            }
        }
    });
});
$(document).on("click", ".logout", function () {
    $.ajax({
        url: '/shopping/logout',
        type: 'post',
        success: function () {
            window.location.reload();
        }
    });
});
$(document).on("click", ".buttno", function () {
    $('.popup:eq(0)').css('display','block')
});

$(document).on("click", ".del", function () {
    $('.popup:eq(0)').css('display','none')
});

$(function () {
    var ele = $(".headr-right");
    $.ajax({
        url: '/shoppingcar/totle',
        type: 'GET',
        dataType: "json",
        data: null,
        success: function (data) {
            if (data.code == '100') {
                var href=$("<span></span>").text("我的购物车("+data.map.totle+")");
                ele.append(href);
            }
        }
    });
});

$(document).on("click", ".headr-right", function () {
    window.location.href='shoppingcar.html';
});

