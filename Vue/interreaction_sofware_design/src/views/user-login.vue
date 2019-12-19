<template>
  <div>
    <img src="./../assets/E.jpeg" />
    <h1>This is JoeMob's task manager</h1>
    <h2>Login</h2>
    <router-view></router-view>
    <el-col :span="6" :offset="9">
      <el-form>
        <el-form-item label="Username">
          <el-input v-model="loginform.username" prefix-icon="el-icon-user" :clearable="true"></el-input>
        </el-form-item>
        <el-form-item label="Password">
          <el-input
            v-model="loginform.password"
            prefix-icon="el-icon-lock"
            type="password"
            :clearable="true"
            :show-password="true"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="userLogin">Login</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click="goToRegister">Go to Register</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </div>
</template>

<script>
import Vue from 'vue'
import router from 'vue-router'
import Axios from 'axios'
import md5 from 'md5'
export default Vue.extend({
  data () {
    return {
      loginform: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    userLogin () {
      if (this.loginform.username != "" && this.loginform.password != "") {
        var requestBody = {
          username: this.loginform.username,
          password: md5(this.loginform.password)
        }
        var config = {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }
        Axios.post('http://127.0.0.1:8081/user/login', requestBody, config)
          .then(response => {
            if (response.data.info == 'Login success.') {
              localStorage.setItem('user', JSON.stringify(response.data.user))
              this.$message({ showClose: true, message: response.data.info, type: 'success' })
              this.$router.push('task-arrangement')
            } else {
              console.log(response)
              this.$message({
                showClose: true,
                message: response.data.info,
                type: 'error'
              })
            }
          })
          .catch(function (error) {
            console.log(error)
          })
      } else if (this.loginform.username == "") {
        this.$message({ showClose: true, message: "Username couldn't be empty.", type: 'error' })
      } else if (this.loginform.password == "") {
        this.$message({ showClose: true, message: "Password couldn't be empty.", type: 'error' })
      }
    },
    goToRegister () {
      this.$router.push('user-register')
    }
  },
  mounted: function () {
    if (localStorage.getItem('user')) {
      this.$router.push('task-arrangement')
    }
  }
})
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
