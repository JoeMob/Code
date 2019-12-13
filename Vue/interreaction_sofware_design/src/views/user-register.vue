<template>
  <div>
    <img src='./../assets/E.jpeg'>
    <h1>This is JoeMob's task manager</h1>
    <h2>Register</h2>
    <el-col
      :span="6"
      :offset="9"
    >
      <el-form>
        <el-form-item label="Username">
          <el-input
            v-model="registerform.username"
            prefix-icon="el-icon-user"
            :clearable="true"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="Password">
          <el-input
            v-model="registerform.password"
            prefix-icon="el-icon-lock"
            type="password"
            :clearable="true"
            :show-password="true"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="Confirm passord">
          <el-input
            v-model="registerform.confirmpassword"
            prefix-icon="el-icon-lock"
            type="password"
            :clearable="true"
            :show-password="true"
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
<script lang="js">
import Vue from "vue";
import axios from "axios";
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
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
    },
    Register() {
      if (this.registerform.password.length >= 8 && this.registerform.password.length <= 22 && this.registerform.password == this.registerform.confirmpassword){
      var config = {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
      };
      var requestBody = {
        "username": this.registerform.username,
        "password": this.registerform.password
      };
      axios.post("http://127.0.0.1:8081/user", requestBody, config)
        .then((response) => {                                       //=>的this作用域为词法作用域，匿名函数function()为undifined。
          console.log(response);
          if(response.status == 201){
            alert(response.data)
            this.$router.push("user-login")
          }else{
            alert(response.data)
          }
        })
        .catch(function(error) {
          console.log(error);
        });
      } else if(this.registerform.password.length < 8) {
        alert("Password should be longer than (or equal to) 8 characters.")
      } else if(this.registerform.password.length > 22){
        alert("Password should be shorter than (or equal to) 22 characters.")
      } else if(this.registerform.password != this.registerform.confirmpassword){
        alert("Passwrod and confirmpassword are different.")
      }
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