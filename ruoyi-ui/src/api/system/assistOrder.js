import request from '@/utils/request'

// 查询工单协助列表
export function listAssistOrder(query) {
  return request({
    url: '/system/assistOrder/list',
    method: 'get',
    params: query
  })
}

// 查询工单协助详细
export function getAssistOrder(assistanceID) {
  return request({
    url: '/system/assistOrder/' + assistanceID,
    method: 'get'
  })
}

// 新增工单协助
export function addAssistOrder(data) {
  return request({
    url: '/system/assistOrder',
    method: 'post',
    data: data
  })
}

// 修改工单协助
export function updateAssistOrder(data) {
  return request({
    url: '/system/assistOrder',
    method: 'put',
    data: data
  })
}

// 删除工单协助
export function delAssistOrder(assistanceID) {
  return request({
    url: '/system/assistOrder/' + assistanceID,
    method: 'delete'
  })
}
