<template>
  <div class='JoeWyx'>
    <img src='./../assets/E=mc² 355x94 white background.jpeg'>
    <h1>登录</h1>
    <el-col
      span=4
      offset=10
      type='flex'
      justify='center'
    >
      <el-form
        :model='ruleForm'
        :rules='rules'
        ref='ruleForm'
        status-icon
        style='border-radius:20px;border: 3px solid #1050d0;padding:10px 20px 0px 20px'
      >
        <el-form-item
          label='Username'
          prop='username'
        >
          <el-input
            v-model='ruleForm.username'
            type='username'
            @keyup.enter.native='onNewUser'
          ></el-input>
        </el-form-item>
        <el-form-item
          label='Password'
          prop='password'
        >
          <el-input
            v-model='ruleForm.password'
            type='password'
            @keyup.enter.native='onNewUser'
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type='primary'
            @click="onNewUser('ruleForm')"
            class='LoginButton'
            style='margin-top:5px;width:100px'
          >Login</el-button>
        </el-form-item>
      </el-form>
      <el-button
        type='success'
        @click='onNewVisitor'
        style='margin-top:20px;width:100px'
      >Register</el-button>
      <el-col>
        <el-button
          type='text'
          @click='onForgetPassword'
        >Forget password ?</el-button>
      </el-col>
    </el-col>
  </div>
</template>
<script>
/* eslint-disable */
import axios from 'axios';
import store from './../store';
export default {
  name: 'UserLogin',
  data () {
    return {
      ruleForm: { username: '', password: '' },
      rules: {
        username: [
          {
            required: true,
            message: 'usrename could not be empty.',
            trigger: 'blur'
          },
          {
            required: true,
            message: 'usrename could not be empty.',
            trigger: 'change'
          }
        ],
        password: [
          {
            required: true,
            message: 'password could not be empty.',
            trigger: 'blur'
          },
          {
            required: true,
            message: 'password could not be empty.',
            trigger: 'change'
          }
        ]
      }
    };
  },
  methods: {
    onNewUser (formName) {
      var self = this;
      var url = 'http:// 123.57.45.31:9000/user/v1/login';
      //  url = 'http:// 127.0.0.1:9000/user/v1/login'

      if (this.ruleForm.username == '') {
        alert('Username could not be empty.');
      }
      if (this.ruleForm.username != '' && this.ruleForm.password == '') {
        alert('Password could not be empty.');
      }

      this.$refs['ruleForm'].validate(valid => {
        if (valid) {
          alert('Submit.');
          axios
            .get(url, {
              params: {
                userName: this.ruleForm.username,
                userPassword: this.ruleForm.password
              }
            })
            .then(function (response) {
              if (response.data != null && response.data != '') {
                store.dispatch('setUser', {
                  userName: self.ruleForm.username,
                  userPassword: self.ruleForm.password
                });

                console.log(store.state.user);
                if (response.data) self.$router.push({ path: '/todo' });
              }
            })
            .catch(function (error) {
              console.log(error);
              self.$router.push({ path: 'error' });
            });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    onNewVisitor () {
      this.$router.push({ path: '/register' });
    },
    onForgetPassword () {
      this.$router.push({ path: '/reset' });
    }
  }
};
</script>
<style>
.JoeWyx {
  text-align: center;
  align-content: center;
  font-size: 12px;
}
</style>
