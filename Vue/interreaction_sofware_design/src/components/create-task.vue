<template>
  <div>
    <el-dialog
      title="Create your new task."
      :visible.sync="createTaskVisible"
      :before-close="closeCreateTask"
      :close-on-click-modal=false
      center
    >
      <el-form label-position="top">
        <el-row>
          <el-col :span=8>
            <el-form-item label="任务名">
              <el-input v-model="task.taskName">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col
            :span=3
            :offset=2
          >
            <el-form-item label="优先级">
              <el-select v-model="task.priority">
                <el-option
                  v-for="item in priorities"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col
            :span=9
            :offset=2
          >
            <el-col :span=12>
              <el-form-item label="开始日期">
                <el-date-picker
                  v-model="task.startTime"
                  style="width:140px"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span=12>
              <el-form-item label="结束日期">
                <el-date-picker
                  v-model="task.endTime"
                  style="width:140px"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="详细描述">
            <el-input
              type="textarea"
              :rows=12
              v-model="task.describe"
            >
            </el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <div slot="footer">
        <el-button
          v-on:click="createTask"
          type="success"
        >Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>
     
<script lang="js">
import Vue from "vue";
export default Vue.extend({
  name: "CreateTask",
  props: {
    createTaskVisible: Boolean
  },
  data() {
    return {
      task: {
        taskName: "",
        priority: "",
        startTime:"",
        endTime:"",
        describe:""
      },
      priorities: [
        {
          value: 0,
          label: "加急"
        },
        {
          value: 1,
          label: "紧急"
        },
        {
          value: 2,
          label: "普通"
        }
      ]
    };
  },
  methods: {
    closeCreateTask() {
      this.$emit("closeCreateTask");
      Object.keys(this.task).forEach(key => this.task[key] = "")
    },
    createTask() {
      this.$emit("closeCreateTask");
      Object.keys(this.task).forEach(key => this.task[key] = "")
    }
  }
});
</script>

<style scoped>
</style>