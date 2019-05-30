import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import VueMeta from 'vue-meta'
import Vuex from 'vuex'
import store from './store'
import axios from 'axios'

Vue.prototype.$axios = axios

Vue.config.productionTip = false
Vue.use(VueMeta)
Vue.use(Vuex)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
