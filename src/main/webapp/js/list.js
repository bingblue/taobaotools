'use strict'
$(function () {
  var nowPage = 1   // 当前页
  var limit = 10     // 每页显示条数
  var today = new Date().toLocaleDateString().replace(/\//g,'-')
  var option = {
    type: 'GET',
    url: config.api.list,
    data: {
      startDate: '2018-01-01',	// 开始日期
      endDate: today            // 结束日期
    }
  }
  function setData(data) {
    $.muAjax(option, function(data){
      var html = ''
      for(let i of data.stuckFirstScreenBills){
        html += '<tr>'+
                    '<td>'+ i.productId +'</td>'+
                    '<td>'+ i.keywords +'</td>'+
                    '<td>'+ i.taobaoWord.text +'</td>'+
                    '<td>'+ i.taobaoWord.tpwd +'</td>'+
                    '<td>'+ $.muChineseStatus(i.status) +'</td>'+
                    '<td>'+ $.muChineseTF(i.competitor) +'</td>'+
                    '<td>'+ $.muNumToLocalDate(i.createDate) +'</td>'+
                    '<td><a href="/TaobaoTools/html/user/stuck/get.html?id=' + i.id +'">查看</a></td>'+
                  '</tr>'
      }
      $('tbody').html(html)
      $('.page').html($.createPageHtml(data.stuckFirstScreenBillCount, nowPage, limit))
    })
  }
  setData()
  $(document).on('click', 'a', function(){
    nowPage = $(this).data('page')
    option.data.page = nowPage
    setData()
  })
})
