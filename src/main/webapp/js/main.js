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
    },1000)
  }
  $.bomb = function(data) {
    var el = '<div class="bomb-mask">'
    for(var key in data){
      el +=   '<span>'+key+':</span><input type="text" value="'+ data[key] +'">'
    }
    el +=     '<span>二维码:</span><div id="ercode"></div>'
    el +=     '<a class="close" href="javascript:;">确定</a>' +
            '</div>'
    $(el).appendTo('body')
    $('.bomb-mask .close').click(function(){
      $('.bomb-mask').hide()
    })
  }
  $.muAjax = function(option, cb) {
    option.type = option.type ? option.type : 'POST'
    $.ajax({
      type: option.type,
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
  /* 增加输入框 */
  $.add = function(option) {
    $('.jq-add').click(function(){
      var item = '<div class="add">' +
                    '<div class="add-input">'+
                      '<input type="text" name="keywords" placeholder="'+ option.placeholder1 +'" required>' +
                    '</div>' +
                    '<div class="add-input">' +
                      '<input type="text" name="limitClickQuantity" placeholder="'+ option.placeholder2 +'" required>' +
                    '</div>' +
                  '</div>'
      $('.jq-add-wrap').append($(item))
    })
  }
  $.muChineseTF = function(isT) {
    return isT ? '是' : '否'
  }
  $.muChineseStatus = function(status) {
    switch (status) {
      case 'OVER':
        return '正常'
        break;
      case 'STAY_CREATE':
        return '待生成'
        break;
      case 'CREATING':
        return '生成中'
        break;
      case 'FAILURE':
        return '生成失败'
        break;
      default:
        return '正常'
        break;
    }
  }
  $.muNumToLocalDate = function(num) {
    return new Date(num).toLocaleDateString().replace(/\//g,'-')
  }
  /*
   * 分页方法
   * @param {count} 总条数
   * @param {page} 当前页
   * @param {limit=10} 每页条数
   */
  $.getPageArr = function(count, page, limit) {
    const pages = Math.ceil(count / limit)
    let result = []
    if (pages <= 3 || page <= 1) {
      for(let i = 1;i <= pages && i<= 3;i++){
        result[i-1] = i
      }
    } else if (page >= pages - 1) {
      result = [pages-2, pages - 1, pages]
    }else {
      result = [page - 1, page, page + 1]
    }
    return result
  }
  /*
   * 生成分页HTML
   * @param {pageArr} 分页数组
   * @param {page} 当前页
   */
  $.createPageHtml = function(count, page, limit) {
    let pageArr = $.getPageArr(count, page, limit)
    let pageHtml = `<ul class="pagination">
        <li><a data-page="1" href="javascript:;">«</a></li>`
    for (let item in pageArr) {
      let active = pageArr[item] === page ? 'active' : ''
      pageHtml += `<li class="${active}"><a data-page="${pageArr[item]}" href="javascript:;">${pageArr[item]}</a></li>`
    }
    pageHtml += `<li><a data-page="${Math.ceil(count / limit)}" href="javascript:;">»</a></li>
      </ul>`
    return pageHtml
  }
})
