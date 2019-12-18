<template>
  <el-container>
    <el-header>
      <h1>Hi, {{user.username}}. Welcome to JoeMob's task arrangement system.</h1>
    </el-header>
    <el-main>
      <CreateTask
        :createTaskVisible="createTaskVisible"
        @closeCreateTask="closeCreateTask"
        @createTask="(taskCreated)=>{createTask(taskCreated)}"
      ></CreateTask>
      <TaskDetail
        :task="task"
        :taskDetailVisible="taskDetailVisible"
        @closeTaskDetail="closeTaskDetail"
        @confirmEdit="(task)=>{confirmEdit(task)}"
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
              name="tabAll"
              class="panelContainer"
            >
              <span slot="label">
                <div class="el-icon-date"></div> All tasks
              </span>
              <el-row :gutter=30>
                <div
                  v-for="(Data,index) in allTasks"
                  :key="index"
                >
                  <el-col
                    :span=8
                    class="card"
                  >
                    <el-card
                      v-if="Data.priority==2"
                      class="normal"
                    >
                      <span
                        slot="header"
                        class="title"
                      >
                        <span
                          :class="states[Data.state].icon"
                          style="margin-right:15px"
                        ></span>
                        <span class="text">{{Data.taskname}}</span>
                        <el-button
                          circle
                          icon="el-icon-edit"
                          @click="editTask(Data)"
                        ></el-button>
                      </span>
                      <div>
                        编号:{{Data.taskid}}
                      </div>
                      <div>
                        <span>
                          开始时间:{{Data.startTime}}
                        </span>
                        <span>
                          结束时间:{{Data.endTime}}
                        </span>
                      </div>
                      <el-button
                        style="float:right;margin-bottom:16px"
                        @click="deleteTask(Data,index)"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
                    </el-card>
                    <el-card
                      v-if="Data.priority==1"
                      class="highPriority"
                    >
                      <div
                        slot="header"
                        class="title"
                      >
                        <span
                          :class="states[Data.state].icon"
                          style="margin-right:15px"
                        ></span>
                        <span class="text">{{Data.taskname}}</span>
                        <el-button
                          circle=""
                          icon="el-icon-edit"
                          @click.native="editTask(Data)"
                        ></el-button>
                      </div>
                      <div>
                        编号:{{Data.id}}
                      </div>
                      <div>
                        <span>
                          开始时间:{{Data.startTime}}
                        </span>
                        <span>
                          结束时间:{{Data.endTime}}
                        </span>
                      </div>
                      <el-button
                        style="float:right;margin-bottom:16px"
                        @click="deleteTask(Data,index)"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
                    </el-card>
                    <el-card
                      v-if="Data.priority==0"
                      class="emergency"
                    >
                      <div
                        slot="header"
                        class="title"
                      >
                        <span
                          :class="states[Data.state].icon"
                          style="margin-right:15px"
                        ></span>
                        <span class="text">{{Data.taskname}}</span>
                        <el-button
                          circle=""
                          icon="el-icon-edit"
                          @click.native="editTask(Data)"
                        ></el-button>
                      </div>
                      <div>
                        编号:{{Data.id}}
                      </div>
                      <div>
                        <span>
                          开始时间:{{Data.startTime}}
                        </span>
                        <span>
                          结束时间:{{Data.endTime}}
                        </span>
                      </div>
                      <el-button
                        style="float:right;margin-bottom:16px"
                        @click="deleteTask(Data,index)"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
                    </el-card>
                  </el-col>
                </div>
              </el-row>
            </el-tab-pane>
            <el-tab-pane
              name="tabUnstarted"
              class="panelContainer"
            >
              <span slot="label">
                <div
                  class="el-icon-switch-button"
                  style="color:black"
                ></div> Unstarted
              </span>
              <el-row :gutter=30>
                <div
                  v-for="(Data,index) in unstartedTasks"
                  :key="index"
                >
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
                        class="title"
                      >
                        <span
                          :class="states[Data.state].icon"
                          style="margin-right:15px"
                        ></span>
                        <span class="text">{{Data.taskname}}</span>
                        <el-button
                          circle
                          icon="el-icon-edit"
                          @click="editTask(Data)"
                        ></el-button>
                      </div>
                      <div>
                        编号:{{Data.id}}
                      </div>
                      <div>
                        <span>
                          开始时间:{{Data.startTime}}
                        </span>
                        <span>
                          结束时间:{{Data.endTime}}
                        </span>
                      </div>
                      <el-button
                        style="float:right;margin-bottom:16px"
                        @click="deleteTask(Data,index)"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
                    </el-card>
                    <el-card
                      v-if="Data.priority==1"
                      class="highPriority"
                    >
                      <div
                        slot="header"
                        class="title"
                      >
                        <span
                          :class="states[Data.state].icon"
                          style="margin-right:15px"
                        ></span>
                        <span class="text">{{Data.taskname}}</span>
                        <el-button
                          circle=""
                          icon="el-icon-edit"
                          @click.native="editTask(Data)"
                        ></el-button>
                      </div>
                      <div>
                        编号:{{Data.id}}
                      </div>
                      <div>
                        <span>
                          开始时间:{{Data.startTime}}
                        </span>
                        <span>
                          结束时间:{{Data.endTime}}
                        </span>
                      </div>
                      <el-button
                        style="float:right;margin-bottom:16px"
                        @click="deleteTask(Data,index)"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
                    </el-card>
                    <el-card
                      v-if="Data.priority==0"
                      class="emergency"
                    >
                      <div
                        slot="header"
                        class="title"
                      >
                        <span
                          :class="states[Data.state].icon"
                          style="margin-right:15px"
                        ></span>
                        <span class="text">{{Data.taskname}}</span>
                        <el-button
                          circle=""
                          icon="el-icon-edit"
                          @click.native="editTask(Data)"
                        ></el-button>
                      </div>
                      <div>
                        编号:{{Data.id}}
                      </div>
                      <div>
                        <span>
                          开始时间:{{Data.startTime}}
                        </span>
                        <span>
                          结束时间:{{Data.endTime}}
                        </span>
                      </div>
                      <el-button
                        style="float:right;margin-bottom:16px"
                        @click="deleteTask(Data,index)"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
                    </el-card>
                  </el-col>
                </div>
              </el-row>
            </el-tab-pane>
            <el-tab-pane
              name="tabWorking"
              class="panelContainer"
            >
              <span slot="label">
                <div
                  class="el-icon-time"
                  style="color:black"
                ></div> Working
              </span>
              <el-row :gutter=30>
                <div
                  v-for="(Data,index) in workingTasks"
                  :key="index"
                >
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
                        class="title"
                      >
                        <span
                          :class="states[Data.state].icon"
                          style="margin-right:15px"
                        ></span>
                        <span class="text">{{Data.taskname}}</span>
                        <el-button
                          circle
                          icon="el-icon-edit"
                          @click="editTask(Data)"
                        ></el-button>
                      </div>
                      <div>
                        编号:{{Data.id}}
                      </div>
                      <div>
                        <span>
                          开始时间:{{Data.startTime}}
                        </span>
                        <span>
                          结束时间:{{Data.endTime}}
                        </span>
                      </div>
                      <el-button
                        style="float:right;margin-bottom:16px"
                        @click="deleteTask(Data,index)"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
                    </el-card>
                    <el-card
                      v-if="Data.priority==1"
                      class="highPriority"
                    >
                      <div
                        slot="header"
                        class="title"
                      >
                        <span
                          :class="states[Data.state].icon"
                          style="margin-right:15px"
                        ></span>
                        <span class="text">{{Data.taskname}}</span>
                        <el-button
                          circle=""
                          icon="el-icon-edit"
                          @click.native="editTask(Data)"
                        ></el-button>
                      </div>
                      <div>
                        编号:{{Data.id}}
                      </div>
                      <div>
                        <span>
                          开始时间:{{Data.startTime}}
                        </span>
                        <span>
                          结束时间:{{Data.endTime}}
                        </span>
                      </div>
                      <el-button
                        style="float:right;margin-bottom:16px"
                        @click="deleteTask(Data,index)"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
                    </el-card>
                    <el-card
                      v-if="Data.priority==0"
                      class="emergency"
                    >
                      <div
                        slot="header"
                        class="title"
                      >
                        <span
                          :class="states[Data.state].icon"
                          style="margin-right:15px"
                        ></span>
                        <span class="text">{{Data.taskname}}</span>
                        <el-button
                          circle=""
                          icon="el-icon-edit"
                          @click.native="editTask(Data)"
                        ></el-button>
                      </div>
                      <div>
                        编号:{{Data.id}}
                      </div>
                      <div>
                        <span>
                          开始时间:{{Data.startTime}}
                        </span>
                        <span>
                          结束时间:{{Data.endTime}}
                        </span>
                      </div>
                      <el-button
                        style="float:right;margin-bottom:16px"
                        @click="deleteTask(Data,index)"
                        circle
                        icon="el-icon-delete"
                        type="danger"
                      ></el-button>
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
                <div
                  class="el-icon-circle-check"
                  style="color:black"
                ></div> Finished
              </span>
              <el-table
                :data="finishedTasks"
                :default-sort="{prop: 'id', order:'ascending'}"
                :row-class-name="tableRowClassName"
              >
                >
                <el-table-column
                  prop="id"
                  label="编号"
                  sortable
                ></el-table-column>
                <el-table-column
                  prop="taskname"
                  label="任务名"
                  sortable
                >
                </el-table-column>
                <el-table-column
                  prop="priority"
                  label="优先级"
                  :formatter="formatPriority"
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
                      @click="deleteTask(scope.row,scope.$index)"
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
                <div
                  class="el-icon-circle-close"
                  style="color:black"
                ></div> Failed
              </span>
              <el-table
                v-model="tasks"
                :data="failedTasks"
                :default-sort="{prop: 'id', order:'ascending'}"
                :row-class-name="tableRowClassName"
              >
                <el-table-column
                  prop="id"
                  label="编号"
                  sortable
                >
                </el-table-column>
                <el-table-column
                  prop="taskname"
                  label="任务名"
                  sortable
                >
                </el-table-column>
                <el-table-column
                  prop="priority"
                  label="优先级"
                  :formatter="formatPriority"
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
                      @click="deleteTask(scope.row,scope.$index)"
                    ></el-button>
                  </template>
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
import axios from "axios";
export default Vue.extend({
  components: {
    CreateTask,
    TaskDetail,
  },
  data() {
    return {
      createTaskVisible: false,
      taskDetailVisible: false,
      tabCondition: "tabAll",
      searchContent: "",
      user: {
        id:1,
        username: "JoeMob"
      },
      tasks:[{}],
      task: {},
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
          label: "已失败",
          icon: "el-icon-circle-close"
        }
      ],
      originTask:{},
      token:{
        refreshToken:"",
        token:""
      },
      config : {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
      }
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
      this.originTask = task;
      this.task = Object.assign({},task);
      this.openTaskDetail();
    },
    logOut() {
      this.tasks=[]
    },
    deleteTask(task,index) {
      axios.delete("http://127.0.0.1:8081/task?id=" + task.id, this.config)
      .then((response)=>{
        console.log(response)
        if(response.data == "Delete success."){
          this.$message({showClose:true,message:response.data,type:'success'})
            axios.get("http://127.0.0.1:8081/tasks?id="+this.user.id,this.config)
            .then((response)=>{
        this.tasks = response.data
      })
        } else {
          this.$message({showClose:true,message:response.data,type:'danger'})
        }
      })
    },
    clearSearch() {
      this.searchContent = "";
    },
    completed(value,row,col){
      console.log(row);
      return row.state === 2;
    },
    createTask(taskCreated){
      taskCreated.state=1;
      var sender={
        "user":this.user,
        "task":taskCreated
      }
      axios.post("http://127.0.0.1:8081/task",sender,this.config)
      .then((response)=>{
        console.log(response);
        if(response.status == 201){
          this.$message({showClose:true,message:response.data,type:'success'})
            axios.get("http://127.0.0.1:8081/tasks?id="+this.user.id,this.config)
            .then((response)=>{
        this.tasks = response.data
      })
      .catch(function(error) {
          console.log(error);
      });
        }else{
          this.$message({showClose:true,message:response.data,type:'danger'})
        }
      }
      )
    },
    confirmEdit(task){
      axios.put("http://127.0.0.1:8081/task",task,this.config)
      .then((response)=>{
        console.log(response)
          this.$message({showClose:true,message:response.data,type:'success'})
            axios.get("http://127.0.0.1:8081/tasks?id="+this.user.id,this.config)
            .then((response)=>{
        this.tasks = response.data
      })
    })
    },
    formatPriority: function (row, column) {
        return row.priority == 0 ? '加急' : row.priority == 1 ? '紧急': row.priority == 2?'普通':'';
    },
    tableRowClassName({row,rowIndex}) {
        if (row.priority === 0) {
          return 'emergency-row';
        } else if (row.priority === 1) {
          return 'highPriority-row';
        } else if (row.priority === 2){
          return 'normal-row';
        }
        return '';
      }
  },
  computed:{
        allTasks:function(){
          var search =  this.searchContent
          var tasks = this.tasks
          if(search){
            return tasks.filter(item=>{
              return item.taskname.indexOf(search) > -1
            })
          }
          return tasks
        },
        unstartedTasks:function(){
          var search = this.searchContent;
          var tasks = this.tasks.filter(item=>{
            return item.state==0
          })
          if(search){
            return tasks.filter(item=>{
              return item.taskname.indexOf(search) > -1
            })
          }
          return tasks
        },
        workingTasks:function(){
          var search = this.searchContent;
          var tasks = this.tasks.filter(item=>{
            return item.state==1
          })
          if(search){
            return tasks.filter(item=>{
              return item.taskname.indexOf(search) > -1
            })
          }
          return tasks
        },
        finishedTasks:function(){
        var search=this.searchContent;
        var tasks=this.tasks.filter(item=>{
          return item.state==2
        })
        if(search){
          return  tasks.filter(item=>{
              return item.taskname.indexOf(search) > -1
            })
        }
        return tasks
      },
      failedTasks:function(){
      var search=this.searchContent;
      var tasks=this.tasks.filter(item=>{
        return item.state==3
      })
      if(search){
        return  tasks.filter(function(item){
          return item.taskname.indexOf(search) > -1
        })
      }
      return tasks
    },
  },
  mounted:
    function(){
      axios.get("http://127.0.0.1:8081/tasks?id="+this.user.id,this.config)
      .then((response)=>{
        this.tasks = response.data
      })
      .catch(function(error) {
          console.log(error);
      });
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
  color: aqua;
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
.el-table__row.normal-row {
  background: rgba(0, 255, 255, 0.2);
}
.el-table__row.highPriority-row {
  background: rgba(255, 128, 0, 0.2);
}
.el-table__row.emergency-row {
  background: rgba(255, 0, 0, 0.2);
}
.title {
  white-space: nowrap;
  display: inline;
}
.text {
  width: 76%;
  display: inline-block;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: middle;
}
</style>