<template>
  <div>
    <el-dialog
      :visible.sync="createTaskVisible"
      :before-close="closeCreateTask"
    >
      <span slot="footer">
        <el-button @click="closeCreateTask">Close</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  name: "CreateTask",
  props: {
    createTaskVisible: Boolean
  },
  data() {
    return {};
  },
  methods: {
    closeCreateTask() {
      this.$emit("closeCreateTask");
    }
  }
});
</script>

<style scoped>
</style>