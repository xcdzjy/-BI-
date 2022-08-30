import request from '@/utils/request'

export function getDatasourceList() {
  return request({
    url: '/api/v1/metadata/datasource/list',
    method: 'get'
  })
}

export function getDbList(data) {
  return request({
    url: '/api/v1/metadata/db/list/' + data,
    method: 'get'
  })
}

export function getTableList(data) {
  return request({
    url: '/api/v1/metadata/table/list/' + data.dataSourceType + '/' + data.dbName,
    method: 'get'
  })
}

export function getSchema(data) {
  return request({
    url: '/api/v1/metadata/table/schema/click_house/' + data.dbName + '/' + data.tableName,
    method: 'get'
  })
}
