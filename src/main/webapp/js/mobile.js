'use strict'
$(function () {
  var instance = $('form').parsley()
  /* 卡首屏手机端 */
  $(".save").click(function(){
    if(!instance.validate()){
      return false
    }
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
        '淘口令': data.tpwd,
        '卡首屏Url': data.url
      }
      $.bomb(reuslt)
    })
  })
  
})
