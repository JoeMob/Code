<template>
  <div class="Todo">
    <div class="Tabs">
      <el-tabs
        :tab-position="'top'"
        style="margin:15px 0px 0px 5px;backgourd:#000000"
        overflow:scroll
      >
        <el-tab-pane>
          <span slot="label">
            <i class="el-icon-date"></i> 新建任务
          </span>
          <el-col span="12" offset="6">
            <el-form :model="form" :label-position="'top'" ref="form" label-width="80px">
              <el-form-item label="标题:">
                <el-input v-model="form.newTodoTitle"></el-input>
              </el-form-item>
              <el-form-item label="任务内容:">
                <el-input type="textarea" :autosize="{minRows:4}" v-model="form.newTodoContent"></el-input>
              </el-form-item>
              <el-form-item label="任务完成度描述:">
                <el-input
                  type="textarea"
                  :autosize="{minRows:3}"
                  v-model="form.newTodoCompleteDesc"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <span>起始时间:</span>
                <el-date-picker
                  v-model="form.newTodoBeginDate"
                  align="right"
                  type="date"
                  placeholder="选择日期"
                  format="yyyy 年 MM 月 dd 日"
                  :picker-options="pickerOptions1"
                ></el-date-picker>
                <span>截止时间:</span>
                <el-date-picker
                  v-model="form.newTodoDeadline"
                  align="right"
                  type="date"
                  placeholder="选择日期"
                  format="yyyy 年 MM 月 dd 日"
                  :picker-options="pickerOptions2"
                ></el-date-picker>
              </el-form-item>
              <el-form-item label="紧急程度:">
                <el-select v-model="form.newTodoProperty">
                  <el-option
                    v-for="item in PropertyOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="完成度:">
                <el-slider v-model="form.newTodoComplete" show-input></el-slider>
              </el-form-item>
              <el-form-item style="margin-top:10px">
                <el-button type="primary" @click="onNewTodo('form')">新建任务</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label">
            <i class="el-icon-info"></i> 正在执行
          </span>
          <el-row gutter="20">
            <el-col span="4">
              <el-input v-model="searchTitle" placeholder="根据标题搜索"></el-input>
            </el-col>
            <el-col span="1">
              <el-button icon="el-icon-search" circle @click="onSearchByTitle"></el-button>
            </el-col>
          </el-row>
          <el-row>
            <div v-for="item in todoItems" :key="item.id">
              <div v-if="item.complete != '100'">
                <div v-if="item.deadline >= getNowFormatDate()">
                  <todoitem
                    :item="item"
                    @onEditItem="onEditTodoItem"
                    @onFinishItem="onFinishTodoItem"
                    @onDeleteItem="onDeleteTodoItem"
                  ></todoitem>
                </div>
              </div>
            </div>
          </el-row>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label">
            <i class="el-icon-success"></i> 已完成任务
          </span>
          <el-row gutter="20">
            <el-col span="4">
              <el-input v-model="searchTitle" placeholder="根据标题搜索"></el-input>
            </el-col>
            <el-col span="1">
              <el-button icon="el-icon-search" circle @click="onSearchByTitle"></el-button>
            </el-col>
          </el-row>
          <el-row>
            <div v-for="item in todoItems" :key="item.id">
              <div v-if="item.complete=='100'">
                <todoitem
                  :item="item"
                  @onEditItem="onEditTodoItem"
                  @onFinishItem="onFinishTodoItem"
                  @onDeleteItem="onDeleteTodoItem"
                ></todoitem>
              </div>
            </div>
          </el-row>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label">
            <i class="el-icon-error"></i> 未完成任务
          </span>
          <el-row gutter="20">
            <el-col span="4">
              <el-input v-model="searchTitle" placeholder="根据标题搜索"></el-input>
            </el-col>
            <el-col span="1">
              <el-button icon="el-icon-search" circle @click="onSearchByTitle"></el-button>
            </el-col>
          </el-row>
          <el-row>
            <div v-for="item in todoItems" :key="item.id">
              <div v-if="item.complete != '100'">
                <div v-if="item.deadline < getNowFormatDate()">
                  <todoitem
                    :item="item"
                    @onEditItem="onEditTodoItem"
                    @onFinishItem="onFinishTodoItem"
                    @onDeleteItem="onDeleteTodoItem"
                  ></todoitem>
                </div>
              </div>
            </div>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import TodoItem from "./TodoItem.vue";

import axios from "axios";

export default {
  name: "Todo",
  components: {
    todoitem: TodoItem
  },
  data() {
    // push(item.id)
    return {
      todoItems: "",
      form: {
        newTodoTitle: "",
        newTodoContent: "",
        newTodoBeginDate: "",
        newTodoDeadline: "",
        newTodoProperty: "",
        newTodoComplete: "",
        newTodoCompleteDesc: ""
      },
      PropertyOptions: [
        {
          value: "1",
          label: "非常紧急"
        },
        {
          value: "2",
          label: "正常完成"
        },
        {
          value: "3",
          label: "不用着急"
        }
      ],
      pickerOptions1: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      },
      pickerOptions2: {
        disabledDate: time => {
          return time.getTime() < this.form.newTodoBeginDate;
        },
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "明天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周后",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      },
      searchTitle: ""
    };
  },
  mounted() {
    // 加载组件时从服务器获取数据
    this.getDataFromServer();
  },
  methods: {
    getNowFormatDate() {
      var date = new Date();
      var seperator1 = "-";
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate = year + seperator1 + month + seperator1 + strDate;
      return currentdate;
    },
    onNewTodo() {
      var self = this;

      var user = {
        userName: self.$store.state.user.userName,
        userPassword: self.$store.state.user.userPassword
      };
      var _beginDate = new Date(
        //  Date.parse(self.form.newTodoBeginDate.replace(/-/g, "/"))
        Date.parse(self.form.newTodoBeginDate)
      );
      var _deadline = new Date(
        //  Date.parse(self.form.newTodoDeadline.replace(/-/g, "/"))
        Date.parse(self.form.newTodoDeadline)
      );
      var todo = {
        title: self.form.newTodoTitle,
        content: self.form.newTodoContent,
        beginDate: _beginDate,
        deadline: _deadline,
        property: self.form.newTodoProperty,
        complete: self.form.newTodoComplete,
        completeDesc: self.form.newTodoCompleteDesc
      };

      this.form.newTodoTitle = "";
      this.form.newTodoContent = "";
      this.form.newTodoBeginDate = "";
      this.form.newTodoDeadline = "";
      this.form.newTodoProperty = "";
      this.form.newTodoComplete = "";
      this.form.newTodoCompleteDesc = "";

      var url = "http:// 123.57.45.31:9000/todo/v1/todo";
      //  url = 'http:// 127.0.0.1:9000/todo/v1/todo'
      // 使用post方法提交数据
      axios
        .post(url, {
          user,
          todo
        })
        .then(response => {
          if (response.data > 0) {
            self.getDataFromServer();
          }
          if (response.data < 0) {
            alert("Failed.");
          }
        })
        .catch(function(error) {
          console.log(error);
          self.$router.push({ path: "error" });
        });
    },
    getDataFromServer() {
      var self = this;

      var url = "http:// 123.57.45.31:9000/todo/v1/todos";
      //   url = 'http:// 127.0.0.1:9000/todo/v1/todos'

      axios
        .get(url, {
          params: {
            userName: self.$store.state.user.userName,
            userPassword: self.$store.state.user.userPassword
          }
        })
        .then(response => {
          this.todoItems = [];
          if (response.data != null && response.data != "") {
            console.log(response.data);
            self.todoItems = response.data;
          }
        })
        .catch(function(error) {
          console.log(error);
          //  self.$router.push({ path: "error" });
        });
    },
    onDeleteTodoItem(itemid) {
      var self = this;
      // 删除item，其id为itemid

      var url = "http:// 123.57.45.31:9000/todo/v1/todo/" + itemid;
      //    url = 'http:// 127.0.0.1:9000/todo/v1/todo/' + itemid

      // 使用delete方法删除数据,注意传参方式
      axios
        .delete(url, {
          data: {
            userName: self.$store.state.user.userName,
            userPassword: self.$store.state.user.userPassword
          }
        })
        .then(function(response) {
          // 执行成功
          if (response.data > 0) this.getDataFromServer();
        })
        .catch(function(error) {
          console.log(error);
          self.$router.push({ path: "error" });
        });
    },
    onEditTodoItem(itemid) {
      var url = "http:// 123.57.45.31:9000/todo/v1/todo/" + itemid;

      var todo = {
        title: item.form.newTodoTitle,
        content: item.form.newTodoContent,
        beginDate: Date.parse(item.form.newTodoDeadline),
        deadline: Date.parse(item.form.newTodoDeadline),
        property: item.form.newTodoProperty,
        complete: item.form.newTodoComplete,
        completeDesc: item.form.newTodoCompleteDesc
      };

      axios
        .put(url, {
          data: {
            userName: self.$store.state.user.userName,
            userPassword: self.$store.state.user.userPassword
          }
        })
        .then(function(response) {
          // 执行成功
          if (response.data > 0) this.getDataFromServer();
        })
        .catch(function(error) {
          console.log(error);
          self.$router.push({ path: "error" });
        });
    },
    onFinishTodoItem(itemid) {
      var url = "http:// 123.57.45.31:9000/todo/v1/todo/complete" + itemid;
      var user = {
        userName: self.$store.state.user.userName,
        userPassword: self.$store.state.user.userPassword
      };
      var _beginDate = new Date(
        //  Date.parse(self.form.newTodoBeginDate.replace(/-/g, "/"))
        Date.parse(self.itemid.form.newTodoBeginDate)
      );
      var _deadline = new Date(
        //  Date.parse(self.form.newTodoDeadline.replace(/-/g, "/"))
        Date.parse(self.itemid.form.newTodoDeadline)
      );
      var todo = {
        title: self.item.form.newTodoTitle,
        content: self.itemid.form.newTodoContent,
        beginDate: _beginDate,
        deadline: _deadline,
        property: self.itemid.form.newTodoProperty,
        complete: self.itemid.form.newTodoComplete,
        completeDesc: self.itemid.form.newTodoCompleteDesc
      };
      axios
        .put(url, {
          user,
          todo
        })
        .then(function(response) {
          // 执行成功
          if (response.data > 0) this.getDataFromServer();
        })
        .catch(function(error) {
          console.log(error);
          self.$router.push({ path: "error" });
        });
    },
    onSearchByTitle() {
      var self = this;
      this.todoItems = [];
      var url = "http:// 123.57.45.31:9000/todo/v1/todos/title";
      //    url = 'http:// 127.0.0.1:9000/todo/v1/todos/title'

      console.log(url);
      axios
        .get(url, {
          params: {
            userName: self.$store.state.user.userName,
            userPassword: self.$store.state.user.userPassword,
            todo_title: self.searchTitle
          }
        })
        .then(response => {
          if (response.data != null && response.data != "") {
            console.log(response.data);
            self.todoItems = response.data;
          }
        })
        .catch(function(error) {
          console.log(error);
          self.$router.push({ path: "error" });
        });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.el-tabs--left .el-tabs__item.is-left {
  text-align: left;
}
.container-tab >>> .el-tabs__content {
  flex-grow: 1;
  overflow-y: scroll;
}
</style>
