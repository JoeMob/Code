package main

import (
	"fmt"
	"time"

	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

//MysqlDB 是链接数据库的实体。
var MysqlDB *gorm.DB

//User is the structure of user.
type User struct {
	ID       int    `gorm:"size:11;primary_key;AUTO_INCREMENT;not null" json:"id"`
	Username string `gorm:"size:255;not null" json:"username"`
	Password string `gorm:"size:255;not null" json:"password"`
}

//Task is the structure of task.
type Task struct {
	ID        int       `gorm:"size:11;primary_key;AUTO_INCREMENT;not null" json:"id"`
	Taskname  string    `gorm:"size:255;not null" json:"taskname"`
	StartTime time.Time `gorm:"size:10;DEFAULT NULL" json:"startTime"`
	EndTime   time.Time `gorm:"size:10;DEFAULT NULL" json:"endTime"`
	Describe  string    `gorm:"size:255;DEFAULT NULL" json:"describe"`
	Priority  int       `gorm:"size:1;not null" json:"priority"`
	State     int       `gorm:"size:1;not null" json:"state"`
	UseridRef int       `gorm:"size:11;not null" json:"useridRef"`
}

func userRegister(user User) string {
	MysqlDB, err := gorm.Open("mysql", "root:Wyx19980626@(127.0.0.1:3306)/interreaction_software_design?charset=utf8&parseTime=True&loc=Local")
	if err != nil {
		fmt.Println("failed to connect database:", err)
		return "Connet to DB failed."
	}
	MysqlDB.SingularTable(true)
	if MysqlDB.Where("username = ?", user.Username).Find(&User{}).RecordNotFound() {
		MysqlDB.Create(&user)
		return "User created."
	}
	return "User already registerd."

}

func userLogin(user User) string {
	MysqlDB, err := gorm.Open("mysql", "root:Wyx19980626@(127.0.0.1:3306)/interreaction_software_design?charset=utf8&parseTime=True&loc=Local")
	if err != nil {
		fmt.Println("failed to connect database:", err)
		return "Failed to connect database."
	}
	MysqlDB.SingularTable(true)
	if MysqlDB.Where("username = ? AND password = ?", user.Username, user.Password).Find(&User{}).RecordNotFound() {
		return "Username or password uncorrect."
	}
	return "Login sucess."
}

func findUserID(user User) int {
	return 0
}

func createTaskByUserID(userID int, task Task) string {
	return "Create success."
}

func getAllTasksByUserID() {
}
