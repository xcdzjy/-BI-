import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/v1/sys/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/api/v1/sys/user/logout',
    method: 'post'
  })
}

export function register(data) {
  return request({
    url: '/api/v1/sys/user/register',
    method: 'post',
    data
  })
}

export function getcaptcha(username) {
  return request({
    url: '/api/v1/sys/user/captcha',
    method: 'get',
    params: {
      username: username
    }
  })
}
