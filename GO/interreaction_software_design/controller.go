package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"strconv"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

func connectMySQL() {
	MysqlDB, err := gorm.Open("mysql", "root:Wyx19980626@(127.0.0.1:3306)/interreaction_software_design?charset=utf8&parseTime=True&loc=Local")
	if err != nil {
		fmt.Println("failed to connect database:", err)
	} else {
		fmt.Println("connect database success")
		MysqlDB.SingularTable(true)
		MysqlDB.AutoMigrate(&User{})                                                         //自动建表
		MysqlDB.AutoMigrate(&Task{})                                                         //自动建表
		MysqlDB.Model(&Task{}).AddForeignKey("userid_ref", "user(id)", "CASCADE", "CASCADE") //设置外键
		fmt.Println("create table success")
	}
}

func createTask(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE")
	w.Header().Set("Access-Control-Allow-Credentials", "true")
	w.Header().Set("Content-Type", "application/json")
	body, _ := ioutil.ReadAll(r.Body)
	var reciver Reciver
	json.Unmarshal(body, &reciver)
	fmt.Println(reciver)
	var task Task
	var user User
	task = reciver.Task
	user = reciver.User
	if task.Taskname == "" {
		w.Write([]byte("Taskname can't be empty."))
	} else {
		var createTaskResult = createTaskService(user, task)
		if createTaskResult == "Create success." {
			w.WriteHeader(201)
			w.Write([]byte(createTaskResult))
		} else {
			w.Write([]byte(createTaskResult))
		}
	}
}

func getAllTasks(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE")
	w.Header().Set("Access-Control-Allow-Credentials", "true")
	w.Header().Set("Content-Type", "application/json")
	vars := r.URL.Query()
	var ID int
	var id = vars["id"][0]
	ID, err := strconv.Atoi(id)
	if err == nil {
		var taskAndInfo TaskAndInfo
		taskAndInfo = getAllTasksService(ID)
		tasks, _ := json.Marshal(taskAndInfo.tasks)
		w.Write(tasks)
	}
}

func deleteTask(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS")
	w.Header().Set("Access-Control-Allow-Credentials", "true")
	w.Header().Set("Content-Type", "application/json")
	vars := r.URL.Query()
	var ID int
	var id = vars["id"][0]
	ID, err := strconv.Atoi(id)
	if err == nil {
		var task Task
		task.ID = ID
		fmt.Println("Delete task's id = ", task.ID)
		var deleteTaskResult = deleteTaskService(task)
		w.Write([]byte(deleteTaskResult))
	}
}

func register(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE")
	w.Header().Set("Access-Control-Allow-Credentials", "true")
	w.Header().Set("Content-Type", "application/json")
	var user User
	body, _ := ioutil.ReadAll(r.Body)
	json.Unmarshal(body, &user)
	fmt.Println(user)
	var createUserResult = registerService(user)
	if createUserResult == "User created." {
		w.WriteHeader(201)
		w.Write([]byte(createUserResult))
	} else {
		w.Write([]byte(createUserResult))
	}
}

func login(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE")
	w.Header().Set("Access-Control-Allow-Credentials", "true")
	w.Header().Set("Content-Type", "application/json")
	var user User
	body, _ := ioutil.ReadAll(r.Body)
	json.Unmarshal(body, &user)
	fmt.Print(user)
	var loginResult = loginService(user)
	if loginResult == "Login success." {
		createToken()
	} else {
		w.Write([]byte(loginResult))
	}
}

func updateTask(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS")
	w.Header().Set("Access-Control-Allow-Credentials", "true")
	w.Header().Set("Content-Type", "application/json")
	var task Task
	body, _ := ioutil.ReadAll(r.Body)
	json.Unmarshal(body, &task)
	fmt.Print(task)
	var updateResult = updateTaskService(task)
	w.Write([]byte(updateResult))
}

func dealOptions(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS")
	w.Header().Set("Access-Control-Allow-Credentials", "true")
	w.Header().Set("Content-Type", "application/json")
	w.Write([]byte("Access accepted."))
}

func main() {
	connectMySQL()
	router := NewRouter()
	http.ListenAndServe(":8081", router)
}
