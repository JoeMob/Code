import Vue from 'vue'
import Router from 'vue-router'
import Todo from '@/components/Todo.vue'
import UserRegister from '@/components/UserRegister.vue'
import UserLogin from '@/components/UserLogin.vue'
import ServerError from '@/components/ServerError.vue'
import UserResetPassword from '@/components/UserResetPassword'
import CreateNewPlan from '@/components/CreateNewPlan'
import Finished from '@/components/Finished'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'UserLogin',
      component: UserLogin
    },
    {
      path: '/todo',
      name: 'Todo',
      component: Todo
    },
    {
      path: '/register',
      name: 'UserRegister',
      component: UserRegister
    },
    {
      path: '/reset',
      name: 'UserResetPassword',
      component: UserResetPassword
    },
    {
      path: '/error',
      name: 'ServerError',
      component: ServerError
    },
    {
      path: '/CreateNewPlan',
      name: 'CreateNewPlan',
      component: CreateNewPlan
    },
    {
      path: '/Finished',
      name: 'Finished',
      component: Finished
    }
  ]
})
