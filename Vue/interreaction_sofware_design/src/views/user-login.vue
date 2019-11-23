<template>
  <div>
    <img src='./../assets/E.jpeg'>
    <h1>This is JoeMob's task manager</h1>
    <h2>Login</h2>
    <router-view></router-view>
    <el-col
      span="6"
      offset="9"
    >
      <el-form
        v-ref="loginform"
        :label-position="top"
      >
        <el-form-item label="Username">
          <el-input
            v-model="loginform.username"
            prefix-icon="el-icon-user"
            clearable="true"
            icon="el-icon-user"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="Password">
          <el-input
            v-model="loginform.password"
            prefix-icon="el-icon-lock"
            type="password"
            clearable="true"
            show-password="true"
          >
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"> Login </el-button>
        </el-form-item>
        <el-form-item>
          <el-button
            type="success"
            @click="goToRegister"
          > Go to Register </el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import router from "vue-router";
export default Vue.extend({
  data() {
    return {
      loginform: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    userLogin() {},
    goToRegister() {
      this.$router.push("user-register");
    }
  }
});
</script>

<style scoped>
* {
  font-family: "Sarasa Slab SC";
}
button {
  width: 150px;
}
form {
  box-shadow: 0px 0px 10px 2px #aaaaaa;
  padding: 20px 20px 20px 20px;
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;
}
</style>

<style>
.el-form-item__label {
  font-size: 20px;
}
</style>