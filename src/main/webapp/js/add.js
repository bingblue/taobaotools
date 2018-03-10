'use strict'
$(function () {
  var instance = $('form').parsley()
  /* 卡首屏手机端 */
  $(".save").click(function(){
    if(!instance.validate()){
      return false
    }
    var option = {
      url: config.api.add,
      data: {
        shopUrl: $('input[name=shopUrl]').val()
      }
    }
    $.muAjax(option, function(data){
      $.tip('请按 分析店铺列表 按钮查看', 2000)
    })
  })
  
})
