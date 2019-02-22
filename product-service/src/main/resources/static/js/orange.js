/**
 * Created by Administrator on 2017/5/11.
 */
//顶部导航
var navarr=['20px','130px','240px','350px','460px']
$('.headr-nav li').mouseover(function(){
    $('.headr-nav li a').eq($(this).index('li')).css('color','#4AB344')
    $('.spbottom:eq(0)').css('left',navarr[$(this).index()])
}).mouseout(function(){
    $('.headr-nav li a').eq($(this).index('li')).css('color','')
    $('.spbottom:eq(0)').css('left','20px')
})

$('.headr-right:eq(0)').mouseover(function(){
    $(this).css('overflow','visible')
}).mouseout(function(){
    $(this).css('overflow','hidden')
})
//顶部导航结束
$('.coll').mousemove(function(){
    $('.coll').eq($(this).index('.coll')).css('color','#4AB344')
}).mouseout(function(){
    $('.coll').eq($(this).index('.coll')).css('color','#666')
})
$('.coll:eq(1)').mouseover(function(){
    $(this).css({'overflow':'visible','border':'1px solid #D1D1D1'})
}).mouseout(function(){
    $(this).css({'overflow':'hidden','border':'1px solid #ffffff'})
})
$('.nt').mousemove(function(){
    $('.nt').eq($(this).index('.nt')).css('color','#4AB344')
}).mouseout(function(){
    $('.nt').eq($(this).index('.nt')).css('color','#666')
})

$('.webSiteQrCode:eq(0)').mouseover(function(){
    $(this).css('overflow','visible')
}).mouseout(function(){
    $(this).css('overflow','hidden')
})




//切换菜单
$('.hd li').click(function(){
    $('.bd1').css('display','none')
    $('.hd li').removeClass('active')
    $('.hd1').removeClass('acti')
    $('.bd1 ').eq($(this).index()).css('display','block')
    $('.hd li').eq($(this).index()).addClass('active')
    $('.hd1').eq($(this).index()).addClass('acti')

})
var navarrl=['70px','130px']
$('.lhd li').click(function(){
    $('.lbd1').css('display','none')
    $('.lhd li').removeClass('active')
    $('.lbd1 ').eq($(this).index()).css('display','block')
    $('.lhd li').eq($(this).index()).addClass('active')
    $('.lhdbottom:eq(0)').css('left',navarrl[$(this).index()])

})

