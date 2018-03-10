'use strict'
$(function () {
  var instance = $('form').parsley()
  /* 卡首屏手机端 */
  $(".save").click(function(){
    if(!instance.validate()){
      return false
    }
    var option = {
      url: config.api.book,
      type: 'GET',
      data: {
        code: 'utf-8',
        q: $('input[name=keyword]').val()                              // 淘口令分享图片地址       
      }
    }
    $.ajax({
      type: option.type,
      url: option.url,
      async: true,
      dataType: 'jsonp',
      data: option.data, 
      success: function(data) {
        var html = ''
        for(var i of data.result){
          html += '<a href="javascript:;">'+ i[0] +'</a>'
        }
        $('.book').html(html)
      }
    })
  })
})
