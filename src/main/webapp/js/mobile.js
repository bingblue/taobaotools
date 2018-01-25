'use strict'
$(function () {
  /* 卡首屏手机端 */
  $(".save").click(function(){
    var option = {
      url: config.api.mobile,
      data: {
        taobaoProductId: $.getQueryString('id', $('input[name=link]').val()),	// 淘宝商品Id
        keywords: $('input[name=keyword]').val(),                             // 搜索的关键字
        productTitle: $('input[name=title]').val(),                           // 淘口令分享标题
        logoUrl: ''                                                           // 淘口令分享图片地址       
      }
    }
    $.muAjax(option, function(data){
      var reuslt = {
        '淘口令': data.tpwd //,
        //'卡首屏Url': data.url
      }
      $.bomb(reuslt)
    })
  })
  $('.menu li,.submenu li,.manage,.recharge span,.video').click(function(){
    $.tip('暂未开通，请稍后')
    return false
  })
  
})
