import request from '@/utils/request'

// 查询工单审批列表
export function listApprovalOrders(query) {
  return request({
    url: '/system/approvalOrders/list',
    method: 'get',
    params: query
  })
}

// 查询工单审批详细
export function getApprovalOrders(orderId) {
  return request({
    url: '/system/approvalOrders/' + orderId,
    method: 'get'
  })
}

// 新增工单审批
export function addApprovalOrders(data) {
  return request({
    url: '/system/approvalOrders',
    method: 'post',
    data: data
  })
}

// 修改工单审批
export function updateApprovalOrders(data) {
  return request({
    url: '/system/approvalOrders',
    method: 'put',
    data: data
  })
}

// 删除工单审批
export function delApprovalOrders(orderId) {
  return request({
    url: '/system/approvalOrders/' + orderId,
    method: 'delete'
  })
}
