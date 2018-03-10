'use strict'
$(function () {
  var option = {
    type: 'GET',
    url: config.api.get,
    data: {
      id: $.getQueryString('id')
    }
  }
  $.muAjax(option, function(data){
    var i = data.stuckFirstScreenBill
    $('.productId').text(i.productId)
    $('.keywords').text(i.keywords)
    $('.text').text(i.taobaoWord.text)
    $('.tpwd').text(i.taobaoWord.tpwd)
    $('.status').text($.muChineseStatus(i.status))
    $('.competitor').text($.muChineseTF(i.competitor))
    $('.createDate').text($.muNumToLocalDate(i.createDate))
    $('.url').text(i.taobaoWord.url)
    $('.ercode').qrcode({
      width   : 100,
      height  : 100,
      text    : i.taobaoWord.url
    })
  })
})
