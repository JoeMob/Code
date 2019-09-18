import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import task_arrangement from './views/task_arrangement.vue'
import create_task from './components/create_task.vue'
import Home from './views/Home.vue'
import register from './views/register.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path:'/',
      name:'Home',
      component: Home,
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path:'/task_arranegment',
      name:'task_arrangement',
      component: task_arrangement
    },
    {
      path:'/create_task',
      name:'create_task',
      component: create_task
    },
    {
      path:'/register',
      name:'register',
      component:register
    }
  ]
})
