<template>
  <el-container>
    <el-header>
      <h1>Hi, {{user.username}}. Welcome to JoeMob's task arrangement system.</h1>
    </el-header>
    <el-main>
      <CreateTask
        :createTaskVisible="createTaskVisible"
        @closeCreateTask="closeCreateTask"
      ></CreateTask>
      <TaskDetail
        :taskDetailVisible="taskDetailVisible"
        :task="task"
        @closeTaskDetail="closeTaskDetail"
      >
      </TaskDetail>
      <el-button
        @click="logOut"
        type="text"
        class="LogOutButton"
      >Sign out <i class="el-icon-close" /></el-button>
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
                    <el-card class="box-card">
                      <div slot="header">
                        <el-button
                          circle=""
                          icon="el-icon-edit"
                          style="float:right"
                          @click.native="openTaskDetail"
                        ></el-button>
                        <el-select
                          v-model="Data.state"
                          style="float:left;width:100px;margin-right:40px"
                          :class="{}"
                        >
                          <el-option
                            v-for="item in states"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                          >
                          </el-option>
                        </el-select>
                        <span>{{Data.name}}</span>
                        <br />
                        <span>Working</span>
                      </div>
                      <el-button
                        style="float:right"
                        @click="deleteTask"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
                      <div
                        :span=22
                        v-for="(data,name,index) in Data"
                        :key="index"
                      >
                        {{name+":"+data}}
                      </div>
                      <br />
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
    </el-main>
  </el-container>
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
          name: "JoJo",
          age: 1,
          state: 0
        },
        {
          num: 2,
          name: "Dio",
          age: 1,
          state: 0
        },
        {
          num: 3,
          name: "Zepplin",
          age: 1,
          state: 0
        },
        {
          num: 4,
          name: "a",
          age: 1,
          state: 0
        },
        {
          num: 5,
          name: "b",
          age: 1,
          state: 0
        }
      ],
      task: {
        taskName: "",
        priority: "",
        startTime: "",
        endTime: "",
        discribe: ""
      },
      states: [
        {
          value: 0,
          label: "未开始",
          icon: "el-icon-close"
        },
        {
          value: 1,
          label: "进行中"
        },
        {
          value: 2,
          label: "已完成"
        },
        {
          value: 3,
          label: "已超时"
        }
      ]
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
    deleteTask() {},
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
.el-button.LogOutButton.el-button--text {
  position: relative;
  left: -210px;
  z-index: 10;
}
</style>