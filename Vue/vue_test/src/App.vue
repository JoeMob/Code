<template>
  <div id="app">
    <!-- 路由出口 -->
    <!-- 路由匹配到的组件将渲染在这里 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
  computed: {
    // 用户是否登录
    isLogin: function () {
      if (this.$store.state.user == null) return false
      var userName = this.$store.state.user.userName
      var userPassword = this.$store.state.user.userPassword
      if (userName != null && userPassword != null) return true
      return false
    }
  }
}
</script>

<style scoped>
</style>
