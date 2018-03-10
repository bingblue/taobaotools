'use strict'
$(function () {
  var nowPage = 1   // 当前页
  var limit = 10     // 每页显示条数
  var today = new Date().toLocaleDateString().replace(/\//g,'-')
  var option = {
    type: 'GET',
    url: config.api.manyOrderList,
    data: {
      startDate: '2018-01-01',	// 开始日期
      endDate: today            // 结束日期
    }
  }
  function getNum(arr) {
    var data = {
      clickQuantityAll: 0,
      limitClickQuantityAll: 0,
      remaining: 0
    }
    for(let i of arr){
      data.clickQuantityAll += i.clickQuantity
      data.limitClickQuantityAll += i.limitClickQuantity
    }
    data.remaining = data.limitClickQuantityAll - data.clickQuantityAll
    return data
  }
  function setData(data) {
    $.muAjax(option, function(data){
      var html = ''
      for(let i of data.manyOrderAndDetailsList){
        var result = getNum(i.manyOrderDetails)
        html += '<tr>'+
                    '<td><a target="_blank" href="'+ i.shortLink +'">查看商品</a></td>'+
                    '<td>'+ result.clickQuantityAll +'</td>'+
                    '<td>'+ result.limitClickQuantityAll +'</td>'+
                    '<td>'+ result.remaining +'</td>'+
                    '<td>'+ i.remark +'</td>'+
                    '<td>'+ $.muNumToLocalDate(i.createDate) +'</td>'+
                    '<td><a href="/html/user/manyOrder/get.html?orderId=' + i.id +'">详情</a></td>'+
                  '</tr>'
      }
      $('tbody').html(html)
      $('.page').html($.createPageHtml(data.manyOrderBillCount, nowPage, limit))
    })
  }
  setData()
  $(document).on('click', 'a', function(){
    nowPage = $(this).data('page')
    option.data.page = nowPage
    setData()
  })
})
