import request from '@/utils/request'

// 查询接收工单列表
export function listDealOrders(query) {
  return request({
    url: '/system/dealOrders/list',
    method: 'get',
    params: query
  })
}

// 查询接收工单详细
export function getDealOrders(orderId) {
  return request({
    url: '/system/dealOrders/' + orderId,
    method: 'get'
  })
}

// 新增接收工单
export function addDealOrders(data) {
  return request({
    url: '/system/dealOrders',
    method: 'post',
    data: data
  })
}

// 修改接收工单
export function updateDealOrders(data) {
  return request({
    url: '/system/dealOrders',
    method: 'put',
    data: data
  })
}

// 删除接收工单
export function delDealOrders(orderId) {
  return request({
    url: '/system/dealOrders/' + orderId,
    method: 'delete'
  })
}
