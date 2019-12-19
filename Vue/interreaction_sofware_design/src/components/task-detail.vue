<template>
  <div>
    <el-dialog
      title="Task detail."
      :visible.sync="taskDetailVisible"
      :before-close="closeTaskDetail"
      :close-on-click-modal="false"
      center
    >
      <el-form label-position="top">
        <el-row>
          <el-col :span="8">
            <el-select v-model="task.state" style="width:90px;margin-right:20px">
              <el-option
                v-for="item in states"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
            <el-form-item label="任务名">
              <el-input v-model="task.taskname"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="3" :offset="2">
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
          <el-col :span="9" :offset="2">
            <el-col :span="12">
              <el-form-item label="开始日期">
                <el-date-picker
                  value-format="yyyy-MM-dd"
                  v-model="task.startTime"
                  style="width:140px"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="结束日期">
                <el-date-picker
                  value-format="yyyy-MM-dd"
                  v-model="task.endTime"
                  style="width:140px"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="详细描述">
            <el-input type="textarea" :rows="12" v-model="task.describe"></el-input>
          </el-form-item>
        </el-row>
      </el-form>
      <span slot="footer">
        <el-button @click="confirmEdit" type="success">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script lang="js">
import Vue from "vue";
export default Vue.extend({
  data() {
    return {
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
      ],
      states: [
        {
          value: 0,
          label: "未开始",
        },
        {
          value: 1,
          label: "进行中",
        },
        {
          value: 2,
          label: "已完成",
        },
        {
          value: 3,
          label: "已失败",
        }
      ]
    };
  },
  props: {
    taskDetailVisible: Boolean,
    task: {}
  },
  methods: {
    closeTaskDetail() {
      this.$emit("closeTaskDetail");
    },
    confirmEdit() {
      this.$emit("confirmEdit",this.task);
      this.closeTaskDetail();
    }
  }
});
</script>

<style scoped>
</style>