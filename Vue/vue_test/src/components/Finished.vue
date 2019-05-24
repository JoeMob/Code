<template>
  <div class="Todo"></div>
</template>

<script>
/* eslint-disable */
import TodoItem from "./TodoItem.vue";

import axios from "axios";

export default {
  name: "Finished",
  components: {
    todoitem: TodoItem
  },
  data() {
    return {
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
        disabledDate(time) {
          return time.getTime() < Date.now();
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
      todoItems: [],
      newTodoTitle: "",
      newTodoContent: "",
      newTodoBeginDate: "",
      newTodoDeadline: "",
      newTodoProperty: 0,
      newTodoComplete: 0,
      newTodoCompleteDesc: "",
      searchTitle: ""
    };
  },
  mounted() {
    // 加载组件时从服务器获取数据
    this.getDataFromServer();
  },
  methods: {
    onNewTodo() {
      var self = this;

      var user = {
        userName: self.$store.state.user.userName,
        userPassword: self.$store.state.user.userPassword
      };
      var _beginDate = new Date(
        Date.parse(self.newTodoBeginDate.replace(/-/g, "/"))
      );
      var _deadline = new Date(
        Date.parse(self.newTodoDeadline.replace(/-/g, "/"))
      );
      var todo = {
        title: self.newTodoTitle,
        content: self.newTodoContent,
        beginDate: _beginDate,
        deadline: _deadline,
        property: self.newTodoProperty,
        complete: self.newTodoComplete,
        completeDesc: self.newTodoCompleteDesc
      };

      this.newTodoTitle = "";
      this.newTodoContent = "";
      this.newTodoBeginDate = "";
      this.newTodoDeadline = "";
      this.newTodoProperty = 0;
      this.newTodoComplete = 0;
      this.newTodoCompleteDesc = "";

      var url = "http:// 123.57.45.31:9000/todo/v1/todo";
      //  url = 'http:// 127.0.0.1:9000/todo/v1/todo'
      // 使用post方法提交数据
      axios
        .post(url, {
          user,
          todo
        })
        .then(response => {
          //  if (response.data != null && response.data != 1) {
          if (response.data > 0) {
            self.getDataFromServer();
          }
          //   }
        })
        .catch(function(error) {
          console.log(error);
          self.$router.push({ path: "error" });
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
          if (response.data > 0) self.getDataFromServer();
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
<style scoped>
</style>
