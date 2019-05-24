import Vue from 'vue'

import Vuex from 'vuex'

Vue.use(Vuex)

// Vuex 使用单一状态树
const state = {
  user: null // 用户信息{userName,userPassword}
}

// 更改 Vuex 的 store 中的状态的唯一方法是提交 mutation。
// Vuex 中的 mutation 非常类似于事件：每个 mutation 都有一个字符串的 事件类型 (type) 和 一个 回调函数(handler)。
// 这个回调函数就是我们实际进行状态更改的地方，并且它会接受 state 作为第一个参数

const mutations = {
  setUser (state, value) {
    state.user = value
  }
}

// Action 类似于 mutation,，但是提交的是 mutation，而不是直接变更状态
const actions = {
  setUser ({ commit, state }, user) {
    commit('setUser', user)
  }
}

// getters are functions
const getters = {
  user: state => state.user
}

// A Vuex instance is created by combining the state, mutations, actions,
// and getters.

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations
})
