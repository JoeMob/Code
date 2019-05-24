/* eslint-disable */
import Vue from 'vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import store from './store'
import router from './router'
Vue.use(VueRouter)
Vue.use(ElementUI)
Vue.config.productionTip = false

// const routes = [
//   { path: '/', component: UserLogin },
//   { path: '/todo', component: Todo },
//   { path: '/register', component: UserRegister },
//   { path: '/reset', component: UserResetPassword },
//   { path: '/error', component: ServerError }
// ]


// const router = new VueRouter({
//   routes 
// })

new Vue({
  el:'#app',
  store,
  router,
  components:{App},
  template:'<App/>'
})
