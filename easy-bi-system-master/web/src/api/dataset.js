import request from '@/utils/request'

export function getSetList(data) {
  return request({
    url: '/api/v1/dataset/list',
    method: 'post',
    data
  })
}

export function createDataSet(data) {
  return request({
    url: '/api/v1/dataset/create',
    method: 'post',
    data
  })
}

export function deleteDataset(id) {
  return request({
    url: '/api/v1/dataset/delete/' + id,
    method: 'delete'
  })
}

