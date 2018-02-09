'use strict'
$(function () {
  var instance = $('form').parsley()
  /* 卡同行淘口令 */
  $(".save").click(function(){
    if(!instance.validate()){
      return false
    }
    var option = {
      url: config.api.competitor,
      data: {
        taobaoProductId: $.getQueryString('id', $('input[name=link]').val()),	                        // 淘宝商品Id
        keywords: $('input[name=keyword]').val(),                                                     // 搜索的关键字
        competitorProductId1: $.getQueryString('id', $('input[name=competitorProductId1]').val()),    // 同行淘宝商品Id1
        competitorProductId2: $.getQueryString('id', $('input[name=competitorProductId2]').val()),    // 同行淘宝商品Id2
        competitorProductId3: $.getQueryString('id', $('input[name=competitorProductId3]').val()),    // 同行淘宝商品Id3
        productTitle: $('input[name=title]').val(),                                                   // 淘口令分享标题
        logoUrl: ''                                                                                   // 淘口令分享图片地址       
      }
    }
    $.muAjax(option, function(data){
      var reuslt = {
        '淘口令': data.tpwd,
        '卡同行Url': data.url
      }
      $.bomb(reuslt)
    })
  })
  
})
