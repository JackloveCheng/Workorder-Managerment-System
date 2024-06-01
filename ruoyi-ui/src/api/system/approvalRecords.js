import request from '@/utils/request'

// 查询审批记录列表
export function listApprovalRecords(query) {
  return request({
    url: '/system/approvalRecords/list',
    method: 'get',
    params: query
  })
}

// 查询审批记录详细
export function getApprovalRecords(approvalId) {
  return request({
    url: '/system/approvalRecords/' + approvalId,
    method: 'get'
  })
}

// 新增审批记录
export function addApprovalRecords(data) {
  return request({
    url: '/system/approvalRecords',
    method: 'post',
    data: data
  })
}

// 修改审批记录
export function updateApprovalRecords(data) {
  return request({
    url: '/system/approvalRecords',
    method: 'put',
    data: data
  })
}

// 删除审批记录
export function delApprovalRecords(approvalId) {
  return request({
    url: '/system/approvalRecords/' + approvalId,
    method: 'delete'
  })
}
