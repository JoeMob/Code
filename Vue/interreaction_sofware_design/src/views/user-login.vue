<template>
  <div Home>
    <img src='./../assets/E.jpeg'>
    <h1>This is JoeMob's task manager</h1>
    <el-col>
      <el-row>
        <el-button type="primary"> Login </el-button>
      </el-row>
      <el-row style="padding-top:20px">
        <el-button
          type="success"
          @click="goToRegister"
        > Register </el-button>
      </el-row>
    </el-col>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import router from "vue-router";
export default Vue.extend({
  methods: {
    goToRegister() {
      this.$router.push("user-register");
    }
  }
});
</script>

<style scoped>
div {
  font-family: "Sarasa Slab SC";
}
</style>