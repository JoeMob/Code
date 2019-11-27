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
        :task="task"
        :taskDetailVisible="taskDetailVisible"
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
                  v-for="(Data,index) in mockData"
                  :key="index"
                >
                  <div v-if="Data.state==0||Data.state==1">
                    <el-col
                      :span=8
                      class="card"
                    >
                      <el-card
                        v-if="Data.priority==2"
                        class="normal"
                      >
                        <div
                          slot="header"
                          class="unstarted"
                        >
                          <span
                            :class="states[Data.state].icon"
                            style="margin-right:15px"
                          ></span>
                          <el-button
                            circle
                            icon="el-icon-edit"
                            style="float:right;"
                            @click="editTask(Data)"
                          ></el-button>
                          <span>{{Data.taskName}}</span>
                        </div>
                        <span
                          :span=22
                          v-for="(data,name,index) in Data"
                          :key="index"
                        >
                          {{name+":"+data}}
                        </span>
                        <el-button
                          style="float:right;margin-bottom:16px"
                          @click="deleteTask"
                          circle
                          icon="el-icon-delete"
                          type="danger"
                        ></el-button>
                      </el-card>
                      <el-card
                        v-if="Data.priority==1"
                        class="highPriority"
                      >
                        <div slot="header">
                          <span
                            :class="states[Data.state].icon"
                            style="margin-right:15px"
                          ></span>
                          <el-button
                            circle=""
                            icon="el-icon-edit"
                            style="float:right"
                            @click.native="editTask(Data)"
                          ></el-button>
                          <span>{{Data.taskName}}</span>
                        </div>
                        <span
                          :span=22
                          v-for="(data,name,index) in Data"
                          :key="index"
                        >
                          {{name+":"+data}}
                        </span>
                        <el-button
                          style="float:right;margin-bottom:16px"
                          @click="deleteTask"
                          circle
                          icon="el-icon-delete"
                          type="danger"
                        ></el-button>
                      </el-card>
                      <el-card
                        v-if="Data.priority==0"
                        class="emergency"
                      >
                        <div slot="header">
                          <span
                            :class="states[Data.state].icon"
                            style="margin-right:15px"
                          ></span>
                          <el-button
                            circle=""
                            icon="el-icon-edit"
                            style="float:right"
                            @click.native="editTask(Data)"
                          ></el-button>
                          <span>{{Data.taskName}}</span>
                        </div>
                        <span
                          :span=22
                          v-for="(data,name,index) in Data"
                          :key="index"
                        >
                          {{name+":"+data}}
                        </span>
                        <el-button
                          style="float:right;margin-bottom:16px"
                          @click="deleteTask"
                          circle
                          icon="el-icon-delete"
                          type="danger"
                        ></el-button>
                      </el-card>
                    </el-col>
                  </div>
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
              <el-table
                :data="mockData"
                :default-sort="{prop: 'uid', order:'ascending'}"
              >
                <el-table-column
                  prop="uid"
                  label="编号"
                  sortable
                ></el-table-column>
                <el-table-column
                  prop="taskName"
                  label="任务名"
                  sortable
                >
                </el-table-column>
                <el-table-column
                  prop="priority"
                  label="优先级"
                  sortable
                ></el-table-column>
                <el-table-column
                  prop="startTime"
                  label="开始时间"
                  sortable
                >
                </el-table-column>
                <el-table-column
                  prop="endTime"
                  label="结束时间"
                  sortable
                >
                </el-table-column>
                <el-table-column>
                  <template slot-scope="scope">
                    <el-button
                      icon="el-icon-edit"
                      circle
                      type="success"
                      @click="editTask(scope.row)"
                    ></el-button>
                  </template>
                </el-table-column>
                <el-table-column>
                  <template slot-scope="scope">
                    <el-button
                      icon="el-icon-delete"
                      circle
                      type="danger"
                      @click="deleteTask(scope.row)"
                    ></el-button>
                  </template>
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

<script lang="js">
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
          uid: 1,
          taskName: "JoJo",
          startTime: "2019-11-11",
          endTime: "2020-1-1",
          describe: "OlaOlaOlaOla",
          state: 0,
          priority: 0
        },
        {
          uid: 2,
          taskName: "Dio",
          startTime: "2019-11-11",
          endTime: "2020-1-1",
          describe: "MudaMudaMudaMuda",
          state: 1,
          priority: 1
        },
        {
          uid: 3,
          taskName: "Zepplin",
          startTime: "2019-11-11",
          endTime: "2020-1-1",
          describe: "满门忠烈",
          state: 2,
          priority: 2
        },
        {
          uid: 4,
          taskName: "Evangelion",
          startTime: "2019-11-11",
          endTime: "2020-1-1",
          describe: "The beast.",
          state: 3,
          priority: 0
        },
        {
          uid:5,
          taskName:"JoeMob",
          startTime:"1998-6-26",
          endTime:"unknown",
          describe:"Long may the sun shine.",
          state:1,
          priority:2
        }
      ],
      task: {
        taskName: "",
        priority: "",
        startTime: "",
        endTime: "",
        describe: "",
        state: ""
      },
      states: [
        {
          value: 0,
          label: "未开始",
          icon: "el-icon-switch-button"
        },
        {
          value: 1,
          label: "进行中",
          icon: "el-icon-time"
        },
        {
          value: 2,
          label: "已完成",
          icon: "el-icon-circle-check"
        },
        {
          value: 3,
          label: "已超时",
          icon: "el-icon-circle-close"
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
    editTask(task) {
      Object.keys(this.task).forEach(key => this.task[key] = task[key])
      this.openTaskDetail();
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
.el-icon-switch-button {
  color: yellow;
  box-shadow: insert, 0px, 0px, 5px, 5px, black;
}
.el-icon-time {
  color: blue;
}
.el-icon-circle-check {
  color: greenyellow;
}
.el-icon-circle-close {
  color: red;
}
.el-card__header {
  background: rgba(0, 0, 0, 0.15);
}
.el-card__body {
  background: rgba(255, 255, 255, 0.5);
}
.el-card.normal.is-always-shadow {
  border-left: 4px solid aqua;
}
.el-card.highPriority.is-always-shadow {
  border-left: 4px solid orange;
}
.el-card.emergency.is-always-shadow {
  border-left: 4px solid red;
}
</style>