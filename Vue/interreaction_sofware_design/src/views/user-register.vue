<template>
  <div Home>
    <img src='./../assets/E.jpeg'>
    <h1>This is JoeMob's task manager</h1>
    <el-col>
      <el-row>
        <el-button
          type="success"
          @click="Register"
        > Register </el-button>
      </el-row>
      <el-row style="padding-top:20px">
        <el-button
          type="primary"
          @click="goToLogin"
        > Go to Login </el-button>
      </el-row>
    </el-col>
  </div>
</template>

<style>
</style>
<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  methods: {
    goToLogin() {
      this.$router.push("user-login");
    }
  }
});
</script>

<style scoped>
* {
  font-family: "Sarasa Slab SC";
}
button{
    width:150px
}
</style>