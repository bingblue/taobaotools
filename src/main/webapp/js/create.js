'use strict'
$(function () {
  var instance = $('form').parsley()
  /* 任意链接淘口令 */
  $(".save").click(function(){
    if(!instance.validate()){
      return false
    }
    var option = {
      url: config.api.create,
      data: {
        url: $('input[name=link]').val(),	                               // 待转链接
        title: $('input[name=title]').val(),                             // 淘口令分享标题
        logoUrl: ''                                                      // 淘口令分享图片地址       
      }
    }
    $.muAjax(option, function(data){
      var reuslt = {
        '淘口令': data.tpwd,
        '原Url': data.url
      }
      $.bomb(reuslt)
    })
  })
  
})
