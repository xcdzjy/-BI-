import { login, logout, register } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const res = response
        localStorage.setItem('name', username.trim())
        localStorage.setItem('token', res.data)
        commit('SET_TOKEN', res.data)
        setToken(res.data)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  getInfo({
    commit,
    state
  }) {
    return new Promise((resolve, reject) => {
      /**
       * 这里请求用户信息和权限，目前接口没做，只注释了，data写死
       * */
      // user.getInfo(state.token).then(response => {
      // const {
      //   data
      // } = response
      const {
        data
      } = {
        data: {
          roles: ['admin'],
          introduction: 'Administrator',
          avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
          name: 'administrator'
        }
      }

      if (!data) {
        reject('Verification failed, please Login again.')
      }

      const {
        roles,
        name,
        avatar,
        token
      } = data

      // roles must be a non-empty array
      if (!roles || roles.length <= 0) {
        reject('getInfo: roles must be a non-null array!')
      }

      // commit('SET_ROLES', roles)
      commit('SET_NAME', name)
      commit('SET_AVATAR', avatar)
      // commit('SET_INTRODUCTION', introduction)
      commit('SET_TOKEN', token)
      resolve(data)
      // }).catch(error => {
      //   reject(error)
      // })
    })
  },

  register({ commit }, userInfo) {
    const { username, password, code } = userInfo
    return new Promise((resolve, reject) => {
      register({ username: username.trim(), password: password, code: code }).then(response => {
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

