<template>
  <div>
    <el-dialog
      title="Create your new task."
      :visible.sync="createTaskVisible"
      :before-close="closeCreateTask"
      center=""
    >
      <span slot="footer">
        <el-button
          @click="createTask"
          type="success"
        >Confirm</el-button>
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
    },
    createTask() {}
  }
});
</script>

<style scoped>
</style>