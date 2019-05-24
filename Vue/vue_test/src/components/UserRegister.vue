<template>
  <div class='Register'>
    <img src='./../assets/E=mc² 355x94 white background.jpeg'>
    <h1>注册新用户</h1>
    <el-col
      span='4'
      offset='10'
      type='flex'
      justify='center'
    >
      <el-form
        :model='ruleForm'
        :rules='rules'
        ref='ruleForm'
        status-icon
        label-position='left'
        style='border-radius:20px ; border: 3px solid #10d050;padding:10px 20px 0px 20px'
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
            type='success'
            span='4'
            @click="onNewUser('ruleForm')"
            style='margin-top:5px;width:100px'
          >Register</el-button>
        </el-form-item>
      </el-form>
      <p>Already have an account ?</p>
      <el-button
        type='primary'
        span='4'
        @click='onHaveAccount'
        style='width:100px'
      >Login</el-button>
      <h2>{{serverMsg}}</h2>
    </el-col>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'UserRegister',
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
    }
  },
  methods: {
    onNewUser (formName) {
      var self = this

      var url = 'http:// 123.57.45.31:9000/user/v1/user'
      //   url = 'http:// 127.0.0.1:9000/user/v1/user'

      if (self.ruleForm.username === '') {
        alert('Username could not be empty.')
      }
      if (self.ruleForm.username !== '' && self.ruleForm.password === '') {
        alert('Password could not be empty.')
      }

      this.$refs['ruleForm'].validate(valid => {
        if (valid) {
          alert('Submit.')
          self.serverMsg = ''
          // 使用post方法提交数据
          axios
            .post(url, {
              userName: self.ruleForm.username,
              userPassword: self.ruleForm.password
            })
            .then(function (response) {
              // 注册成功，跳转到登录页面
              if (response.data) {
                alert('Register successed.')
                self.$router.push({ path: '/' })
              } else self.serverMsg = '参数错误，请重新输入'
            })
            .catch(function (error) {
              // 失败
              console.log(error)
              self.$router.push({ path: 'error' })
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    onHaveAccount () {
      this.$router.push({ path: '/' })
    }
  }
}
</script>
<style scoped>
.Register {
  display: block;
  text-align: center;
  align-content: center;
  font-size: 12px;
}
</style>
