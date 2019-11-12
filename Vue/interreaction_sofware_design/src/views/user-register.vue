<head>
<title>Register</title>
</head>
<template>
  <div>
    <img src='./../assets/E.jpeg'>
    <h1>This is JoeMob's task manager</h1>
    <h2>Register</h2>
    <el-col
      span="6"
      offset="9"
    >
      <el-form
        v-ref="registerform"
        :label-position="top"
      >
        <el-form-item label="Username">
          <el-input
            v-model="registerform.username"
            prefix-icon="el-icon-user"
            clearable="true"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="Password">
          <el-input
            v-model="registerform.password"
            prefix-icon="el-icon-lock"
            type="password"
            clearable="true"
            show-password="true"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="Confirm passord">
          <el-input
            v-model="registerform.confirmpassword"
            prefix-icon="el-icon-lock"
            type="password"
            clearable="true"
            show-password="true"
          >
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-row>
            <el-button
              type="success"
              v-on:click="Register"
            > Register </el-button>
          </el-row>
        </el-form-item>
        <el-form-item>
          <el-row>
            <el-button
              type="primary"
              v-on:click="goToLogin"
            > Go to Login </el-button>
          </el-row>
        </el-form-item>
      </el-form>
    </el-col>
  </div>
</template>

<style>
</style>
<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  data() {
    return {
      registerform: {
        username: "",
        password: "",
        confirmpassword: ""
      }
    };
  },
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