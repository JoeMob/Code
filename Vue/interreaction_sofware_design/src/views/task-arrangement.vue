<template>
  <div>
    <h1>Hi, {{user.username}}. Welcome to JoeMob's task arrangement system.</h1>
    <CreateTask
      :createTaskVisible="createTaskVisible"
      @closeCreateTask="closeCreateTask"
    ></CreateTask>
    <TaskDetail
      :taskDetailVisible="taskDetailVisible"
      @closeTaskDetail="closeTaskDetail"
    >
    </TaskDetail>
    <el-button
      icon="el-icon-close"
      type="danger"
      circle
      @click="logOut"
      class="LogOutButton"
    ></el-button>
    <el-col
      :span=16
      :offset=4
    >
      <el-row>
        <el-tabs
          v-model="tabCondition"
          type="border-card"
          class="wholeTab"
          @tab-click="clearSearch"
        >
          <el-input
            :span="8"
            v-model="searchContent"
            prefix-icon="el-icon-search"
            clearable=""
          ></el-input>
          <el-tab-pane
            name="tabWorking"
            class="panelContainer"
          >
            <span slot="label">
              <div class="el-icon-date"></div> Working
            </span>
            <el-row :gutter=30>
              <div
                v-for=" (Data,index) in mockData"
                :key="index"
              >
                <el-col
                  :span=8
                  class="card"
                >
                  <el-card
                    class="box-card"
                    @click.native="openTaskDetail"
                  >
                    <div slot="header">
                      <div>{{Data.name}}</div>
                      <div>Working</div>
                    </div>
                    <div
                      v-for="(data,name,index) in Data"
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
            <span slot="label">
              <div class="el-icon-check"></div> Finished
            </span>
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
            <span slot="label">
              <div class="el-icon-s-release"></div> Failed
            </span>
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
        icon="el-icon-plus"
        class="createTaskButton"
        @click="openCreateTask"
        type="primary"
        circle
      ></el-button>
      <br />
    </el-col>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import CreateTask from "@/components/create-task.vue";
import TaskDetail from "@/components/task-detail.vue";
export default Vue.extend({
  components: {
    CreateTask,
    TaskDetail
  },
  data() {
    return {
      createTaskVisible: false,
      taskDetailVisible: false,
      tabCondition: "tabWorking",
      searchContent: "",
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
    openCreateTask() {
      this.createTaskVisible = true;
    },
    closeCreateTask() {
      this.createTaskVisible = false;
    },
    openTaskDetail() {
      this.taskDetailVisible = true;
    },
    closeTaskDetail() {
      this.taskDetailVisible = false;
    },
    logOut() {},
    clearSearch() {
      this.searchContent = "";
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
  font-size: 50px;
  height: 100px;
  width: 100px;
  position: relative;
  top: -50px;
}
.search {
  position: relative;
}
.el-button.LogOutButton.el-button--danger.is-circle {
  position: relative;
  left: -160px;
  top: -20px;
  z-index: 10;
}
</style>