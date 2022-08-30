import request from '@/utils/request'

export function getAllSetList() {
  return request({
    url: '/api/v1/dataset/list',
    method: 'get'
  })
}

export function getSetInfo(id) {
  return request({
    url: '/api/v1/dataset/info/' + id,
    method: 'get'
  })
}

export function queryDataInfo(data) {
  return request({
    url: '/api/v1/dataInfo/query',
    method: 'post',
    data
  })
}
