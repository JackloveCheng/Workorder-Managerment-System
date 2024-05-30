import request from '@/utils/request'

// 查询客户管理列表
export function listCustomer(query) {
  return request({
    url: '/system/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户管理详细
export function getCustomer(id) {
  return request({
    url: '/system/customer/' + id,
    method: 'get'
  })
}

// 新增客户管理
export function addCustomer(data) {
  return request({
    url: '/system/customer',
    method: 'post',
    data: data
  })
}

// 修改客户管理
export function updateCustomer(data) {
  return request({
    url: '/system/customer',
    method: 'put',
    data: data
  })
}

// 删除客户管理
export function delCustomer(id) {
  return request({
    url: '/system/customer/' + id,
    method: 'delete'
  })
}
