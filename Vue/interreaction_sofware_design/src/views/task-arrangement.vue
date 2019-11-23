<template>
  <div>
    <h1>Hi, {{user.username}}. Welcome to JoeMob's task arrangement system.</h1>
    <CreateTask :createTaskVisible="createTaskVisible"></CreateTask>
    <el-col
      :span=16
      :offset=4
    >
      <el-row>
        <el-tabs
          v-model="tabCondition"
          type="border-card"
          class="wholeTab"
        >
          <el-tab-pane
            name="tabWorking"
            class="panelContainer"
          >
            <span slot="label"><i class="el-icon-date"></i> Working</span>
            <el-row :gutter=30>
              <div
                v-for="
          (Data,index)
          in
          mockData"
                :key="index"
              >
                <el-col
                  :span=8
                  class="card"
                >
                  <el-card class="box-card">
                    <div slot="header">
                      <span>{{Data.name}} Tasks</span>
                      <br />
                      <br />
                      <br />
                      <br />
                      <span>Working</span>
                    </div>
                    <div
                      v-for="(data ,name,index) in Data"
                      :key="index"
                    >
                      {{name+":"+data}}
                    </div>
                  </el-card>
                </el-col>
              </div>
            </el-row>
          </el-tab-pane>
          <el-tab-pane
            name="tabFinished"
            class="panelContainer"
          >
            <span slot="label"><i class="el-icon-check"></i> Finished</span>
            <el-table :data="mockData">
              <el-table-column
                prop="num"
                label="编号"
              ></el-table-column>
              <el-table-column
                prop="name"
                label="姓名"
              >
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane
            name="tabFailed"
            class="panelContainer"
          >
            <span slot="label"><i class="el-icon-close"></i> Failed</span>
            <el-table :data="mockData">
              <el-table-column
                prop="num"
                label="编号"
              ></el-table-column>
              <el-table-column
                prop="name"
                label="姓名"
              >
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-row>
      <el-button
        icon="el-icon-circle-plus"
        class="createTaskButton"
        @click="createTask"
        type="primary"
        circle
      ></el-button>
    </el-col>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import CreateTask from "@/components/create-task.vue";
export default Vue.extend({
  components: {
    CreateTask
  },
  data() {
    return {
      createTaskVisible: false,
      tabCondition: "tabWorking",
      user: {
        username: "Guest"
      },
      mockData: [
        {
          num: 1,
          name: "JoJo"
        },
        {
          num: 2,
          name: "Dio"
        },
        {
          num: 3,
          name: "Zepplin"
        },
        {
          num: 4,
          name: "a"
        },
        {
          num: 5,
          name: "b"
        }
      ],
      task: [{}]
    };
  },
  methods: {
    createTask() {
      this.createTaskVisible = true;
    },
    closeCreateTask() {
      this.createTaskVisible = false;
    }
  }
});
</script>

<style scoped>
</style>

<style>
.panelContainer {
  height: 620px;
  overflow-y: auto;
  overflow-x: hidden;
}
.wholeTab {
  height: 720px;
}
.card {
  margin-top: 10px;
  margin-bottom: 20px;
  text-align: left;
}
.el-card__header {
  font-size: 24px;
}
.el-card__body {
  font-size: 16px;
}
.el-button.createTaskButton {
  font-size: 64px;
  height: 128px;
  width: 128px;
  position: relative;
  top: -64px;
}
.search {
  position: relative;
}
</style>