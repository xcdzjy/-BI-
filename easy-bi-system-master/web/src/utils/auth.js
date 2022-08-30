import Cookies from 'js-cookie'

const TokenKey = 'vue_admin_template_token'

export function getToken() {
  return localStorage.getItem('token')
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  localStorage.removeItem('token')
  return Cookies.remove(TokenKey)
}
