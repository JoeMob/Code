package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"

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
		MysqlDB.AutoMigrate(&User{}) //自动建表
		MysqlDB.AutoMigrate(&Task{}) //自动建表
		MysqlDB.Model(&Task{}).AddForeignKey("userid_ref", "user(uid)", "CASCADE", "CASCADE")
		fmt.Println("create table success")
	}
}

func getWorkingItems(w http.ResponseWriter, r *http.Request) {
}

func getFinishedItems(w http.ResponseWriter, r *http.Request) {
}

func getFailedItems(w http.ResponseWriter, r *http.Request) {
}

func register(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
	w.Header().Set("Access-Control-Allow-Credentials", "true")
	w.Header().Set("Content-Type", "application/json")
	var user User
	body, _ := ioutil.ReadAll(r.Body)
	json.Unmarshal(body, &user)
	fmt.Println(user.Username)
	fmt.Println(user.Password)
	var createResult = registerService(user)
	if createResult == "User created." {
		w.WriteHeader(201)
		w.Write([]byte(createResult))
	} else {
		w.Write([]byte(createResult))
	}
}

func login(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
	w.Header().Set("Access-Control-Allow-Credentials", "true")
	w.Header().Set("Content-Type", "application/json")
	var user User
	body, _ := ioutil.ReadAll(r.Body)
	json.Unmarshal(body, &user)
	loginService(user)
}

func main() {
	connectMySQL()
	router := NewRouter()
	http.ListenAndServe(":8081", router)
}
