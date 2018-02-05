'use strict'
$(function () {
  $.getQueryString = function(name, url) {
    var url = url ? url.split('?')[1] : window.location.search.substr(1)
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
    if(!url) return null
    var r = url.match(reg)
    if (r != null) {
      return decodeURI(r[2])
    } else {
      return null
    }
  }
  $.tip = function(title) {
    var $el = $('<div class="bombsettime-wp">' +
                    '<div class="bombsettime">' +
                      '<p class="title">' + title + '</p>' +
                    '</div>' +
                  '</div>')
    $el.appendTo('body')
    setTimeout(function(){
      $('.bombsettime-wp').remove()
    },1800)
  }
  $.bomb = function(data) {
    var el = '<div class="bomb-mask">'
    for(var key in data){
      el += '<span>'+key+':</span><input type="text" value="'+ data[key] +'">'
    }
    el +=     '<a class="close" href="javascript:;">确定</a>' +
            '</div>'
    $(el).appendTo('body')
    $('.bomb-mask .close').click(function(){
      $('.bomb-mask').hide()
    })
  }
  $.muAjax = function(option, cb) {
    $.ajax({
      type: 'POST',
      url: option.url,
      async: true,
      dataType: 'json',
      data: option.data, 
      success: function(data) {
        console.log('muAjax-success:', data)
        if (data.code == 200) {
          cb(data.body)
        } else {
          var msg = data.msg ? data.msg : '请求失败，请重试！'
          $.tip(msg)
        }
      },
      error: function(data) {
        console.log('muAjax-error:', data)
      }
    })
  }
  $('.menu li,.submenu li,.manage,.recharge span,.video').click(function(){
    if($(this).find('a').attr('href') != '#') return
    $.tip('非一期项目，暂未开通，请稍后')
    return false
  })
})
