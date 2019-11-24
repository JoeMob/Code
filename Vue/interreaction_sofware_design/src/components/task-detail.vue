<template>
  <div>
    <el-dialog
      :visible.sync="taskDetailVisible"
      :before-close="closeTaskDetail"
    ></el-dialog>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  props: {
    taskDetailVisible: Boolean
  },
  methods: {
    closeTaskDetail() {
      this.$emit("closeTaskDetail");
    }
  }
});
</script>

<style scoped>
</style>