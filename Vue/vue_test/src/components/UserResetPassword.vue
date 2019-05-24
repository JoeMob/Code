<template>
  <div class="Reset">
    <img src="./../assets/E=mc² 355x94 white background.jpeg">
    <h1>重置密码</h1>
    <el-col span="4" offset="10" type="flex" justify="center">
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        status-icon
        label-position="left"
        style="border-radius:20px ; border: 3px solid #d05010;padding:10px 20px 0px 20px"
      >
        <el-form-item label="Username" prop="username">
          <el-input v-model="ruleForm.username" type="username" @keyup.enter.native="onNewUser"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="ruleForm.password" type="password" @keyup.enter.native="onNewUser"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="warning"
            @click="onNewUser('ruleForm')"
            style="margin-top:5px;width:100px"
          >Reset</el-button>
        </el-form-item>
      </el-form>
      <p>Already remembered ?</p>
      <el-button type="primary" @click="onRemembered" style="width:100px">Login</el-button>
    </el-col>
  </div>
</template>
<script>
/* eslint-disable */
import axios from "axios";
import store from "./../store";
export default {
  name: "UserResetPassword",
  data() {
    return {
      ruleForm: { username: "", password: "" },
      rules: {
        username: [
          {
            required: true,
            message: "usrename could not be empty.",
            trigger: "blur"
          },
          {
            required: true,
            message: "usrename could not be empty.",
            trigger: "change"
          }
        ],
        password: [
          {
            required: true,
            message: "password could not be empty.",
            trigger: "blur"
          },
          {
            required: true,
            message: "password could not be empty.",
            trigger: "change"
          }
        ]
      }
    };
  },
  methods: {
    onNewUser() {
      var self = this;
      //var url = "";

      if (self.newUserName == "") {
        alert("Username could not be empty.");
      }
      if (self.newUserName != "" && self.newUserPwd == "") {
        alert("Password could not be empty.");
      }

      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          alert("Submit.");
          axios
            .get(url, {
              params: {
                userName: self.newUserName,
                userPassword: self.newUserPwd
              }
            })
            .then(function(response) {
              if (response.data != null && response.data != "") {
                if (response.data) self.$router.push({ path: "/" });
              }
            })
            .catch(function(error) {
              console.log(error);
              self.$router.push({ path: "error" });
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
      // 使用get方法提交数据
    },
    onRemembered() {
      this.$router.push({ path: "/" });
    }
  }
};
</script>
<style scoped>
.Reset {
  display: block;
  text-align: center;
  align-content: center;
  font-size: 12px;
}
</style>