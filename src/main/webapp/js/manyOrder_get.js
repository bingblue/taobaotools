'use strict'
$(function () {
  var option = {
    type: 'GET',
    url: config.api.manyOrderGet,
    data: {
      orderId: $.getQueryString('orderId')
    }
  }
  $.muAjax(option, function(data){
    var html = ''
    for(var i of data.manyOrderBill.manyOrderDetails){
      html += '<tr>'+
                '<td>'+ i.keywords +'</td>'+
                '<td>'+ i.limitClickQuantity +'</td>'+
                '<td>'+ i.clickQuantity +'</td>'+
                '<td>'+ (i.limitClickQuantity - i.clickQuantity) +'</td>'+
                '<td><a href="'+ i.productUrl +'" target="_blank">查看宝贝</a></td>'
    }
    $('.table-content').html(html)
    $('.shortLink').text(data.manyOrderBill.shortLink)
    $('.ercode').qrcode({
      width   : 100,
      height  : 100,
      text    : data.manyOrderBill.shortLink
    })
  })
})
