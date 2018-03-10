'use strict'
$(function () {
  var option = {
    type: 'GET',
    url: config.api.manyOrderGet,
    data: {
      id: $.getQueryString('id')
    }
  }
  $.muAjax(option, function(data){
    var i = data.stuckFirstScreenBill
    $('.keywords').text(i.manyOrderDetails.keywords)
    $('.productUrl').text(i.productUrl)
    $('.clickCount').text(i.manyOrderDetails.clickCount)
    $('.clickQuantity').text(i.manyOrderDetails.clickQuantity)
    $('.limitClickQuantity').text(i.manyOrderDetails.limitClickQuantity)
    $('.remark').text($.muChineseTF(i.remark))
    $('.createDate').text($.muNumToLocalDate(i.createDate))
    $('.ercode').qrcode({
      width   : 100,
      height  : 100,
      text    : i.productUrl
    })
  })
})
