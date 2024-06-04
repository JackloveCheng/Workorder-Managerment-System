import request from '@/utils/request'

// 查询接收工单列表
export function listAcceptOrders(query) {
  return request({
    url: '/system/acceptOrders/list',
    method: 'get',
    params: query
  })
}

// 查询接收工单详细
export function getAcceptOrders(orderId) {
  return request({
    url: '/system/acceptOrders/' + orderId,
    method: 'get'
  })
}

// 新增接收工单
export function addAcceptOrders(data) {
  return request({
    url: '/system/acceptOrders',
    method: 'post',
    data: data
  })
}

// 修改接收工单
export function updateAcceptOrders(data) {
  return request({
    url: '/system/acceptOrders',
    method: 'put',
    data: data
  })
}

// 删除接收工单
export function delAcceptOrders(orderId) {
  return request({
    url: '/system/acceptOrders/' + orderId,
    method: 'delete'
  })
}
