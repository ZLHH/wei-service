/**
 * Created by Administrator on 2017/5/13.
 */
//顶部导航
var navarr=['20px','130px','240px','350px','460px']
$('.headr-nav li').mouseover(function(){
    $('.headr-nav li a').eq($(this).index('li')).css('color','#4AB344')
    $('.spbottom:eq(0)').css('left',navarr[$(this).index("")])
}).mouseout(function(){
    $('.headr-nav li a').eq($(this).index('li')).css('color','')
    $('.spbottom:eq(0)').css('left','240px')
})

$('.headr-right:eq(0)').mouseover(function(){
    $(this).css('overflow','visible')
}).mouseout(function(){
    $(this).css('overflow','hidden')
})
//顶部导航结束

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
