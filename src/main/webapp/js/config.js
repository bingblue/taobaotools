// var url = 'http://bingblue.com:8000/TaobaoTools'
// var url = 'http://localhost:8080'
var url = 'http://192.168.199.209:8084/TaobaoTools'
var config = {
  api : {
    'mobile': url + '/user/stuck/mobile.do',
    'competitor': url + '/user/stuck/competitor.do',
    'create': url + '/api/taobao/tpwd/create.do',
    'addOne': url + '/user/manyOrder/addOne.do',
    'addMore': url + '/user/manyOrder/addMore.do',
    'list': url + '/user/stuck/list.do',
    'get': url + '/user/stuck/get.do',
    'manyOrderList': url + '/user/manyOrder/list.do',
    'manyOrderGet': url + '/user/manyOrder/get.do',
    'book': 'https://suggest.taobao.com/sug'
  }
}
