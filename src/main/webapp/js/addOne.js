'use strict'
$(function () {
  var instance = $('form').parsley()
  $.add({
    placeholder1 : '请输入关键词',
    placeholder2 : '请输入点击量上限'
  })
  /* 淘词补单多个关键词 */
  $(".save").click(function(){
    if(!instance.validate()){
      return false
    }
    var option = {
      url: config.api.addOne,
      data: {
        productUrl: $('input[name=link]').val(),	             // 淘宝商品Id
        remark: $('input[name=remark]').val()                  // 备注
      }
    }
    $('.add').each(function(i){
      var keywords = $('.add').eq(i).find('input[name=keywords]').val()
      var limitClickQuantity = $('.add').eq(i).find('input[name=limitClickQuantity]').val()
      option.data['manyOrderDetails['+ i +'].keywords'] = keywords
      option.data['manyOrderDetails['+ i +'].limitClickQuantity'] = limitClickQuantity
    })
    $.muAjax(option, function(data){
      $.tip('请到淘词补单列表查看',2000)
    })
  })
  
})
