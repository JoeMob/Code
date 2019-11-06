import Vue from 'vue'
import Router from 'vue-router'
import userLogin from './views/user-login.vue'
import taskArrangement from './views/task-arrangement.vue'
import createTask from './components/create-task.vue'
import userRegister from './views/user-register.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path:'/user-login',
      name:'user-login',
      component: userLogin,
    },
    {
      path:'/task-arranegment',
      name:'task_arrangement',
      component: taskArrangement
    },
    {
      path:'/create-task',
      name:'create-task',
      component: createTask
    },
    {
      path:'/user-register',
      name:'user-register',
      component:userRegister
    }
  ]
})
