import request from '@/utils/request'

// 查询工单系统列表
export function listTickets(query) {
  return request({
    url: '/system/tickets/list',
    method: 'get',
    params: query
  })
}

// 查询工单系统详细
export function getTickets(ticketId) {
  return request({
    url: '/system/tickets/' + ticketId,
    method: 'get'
  })
}

// 新增工单系统
export function addTickets(data) {
  return request({
    url: '/system/tickets',
    method: 'post',
    data: data
  })
}

// 修改工单系统
export function updateTickets(data) {
  return request({
    url: '/system/tickets',
    method: 'put',
    data: data
  })
}

// 删除工单系统
export function delTickets(ticketId) {
  return request({
    url: '/system/tickets/' + ticketId,
    method: 'delete'
  })
}
