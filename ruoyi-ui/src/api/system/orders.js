import request from '@/utils/request'

// 查询工单信息列表
export function listOrders(query) {
  return request({
    url: '/system/orders/list',
    method: 'get',
    params: query
  })
}

// 查询工单信息详细
export function getOrders(orderId) {
  return request({
    url: '/system/orders/' + orderId,
    method: 'get'
  })
}

// 新增工单信息
export function addOrders(data) {
  return request({
    url: '/system/orders',
    method: 'post',
    data: data
  })
}

// 修改工单信息
export function updateOrders(data) {
  return request({
    url: '/system/orders',
    method: 'put',
    data: data
  })
}

// 删除工单信息
export function delOrders(orderId) {
  return request({
    url: '/system/orders/' + orderId,
    method: 'delete'
  })
}
