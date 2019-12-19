package main

import (
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

//MysqlDBConfig is the entity of config for connect to DB.
var MysqlDBConfig = DBConfig{"root", "Wyx19980626", "127.0.0.1", "3306", "interreaction_software_design"}

//DBConfig is the config for DB connect.
type DBConfig struct {
	Username string
	Password string
	IP       string
	Port     string
	DBname   string
}

//Reciver is the reciver of json when user create/update/delete a task.
type Reciver struct {
	User User `json:"user"`
	Task Task `json:"task"`
}

//User is the structure of user.
type User struct {
	ID         int    `gorm:"size:11;primary_key;AUTO_INCREMENT;not null" json:"id"`
	Username   string `gorm:"size:255;not null" json:"username"`
	Password   string `gorm:"size:255;not null" json:"password"`
	TaskAmount int    `gorm:"size11;DEFAULT 0;not null" json:"taskamount"`
}

//Task is the structure of task.
type Task struct {
	ID        int    `gorm:"size:11;primary_key;AUTO_INCREMENT;not null" json:"id"`
	TaskID    int    `gorm:"size:11;DEFAULT 0;not null" json:"taskid"`
	Taskname  string `gorm:"size:255;not null" json:"taskname"`
	StartTime string `gorm:"size:10;DEFAULT NULL" json:"startTime"`
	EndTime   string `gorm:"size:10;DEFAULT NULL" json:"endTime"`
	Describe  string `gorm:"size:255;DEFAULT NULL" json:"describe"`
	Priority  int    `gorm:"size:1;not null" json:"priority"`
	State     int    `gorm:"size:1;not null" json:"state"`
	UseridRef int    `gorm:"size:11;not null" json:"useridRef"`
}

//TaskAndInfo is the return from getAllTasksByUserid it contains info and tasks.
type TaskAndInfo struct {
	Info  string
	tasks []Task
}

//UserAndInfo the json for login to front.
type UserAndInfo struct {
	Info string `json:"info"`
	User User   `json:"user"`
}

func userRegister(user User) string {
	// MysqlDB, err := gorm.Open("mysql", path)
	// if err != nil {
	// 	fmt.Println("failed to connect database:", err)
	// 	return "Connet to DB failed."
	// }
	// MysqlDB.SingularTable(true)
	if MysqlDB.Where("username = ?", user.Username).Find(&User{}).RecordNotFound() {
		MysqlDB.Create(&user)
		return "User created."
	}
	return "User already registerd."

}

func userLogin(user *User) string {
	// MysqlDB, err := gorm.Open("mysql", path)
	// if err != nil {
	// 	fmt.Println("failed to connect database:", err)
	// 	return "Failed to connect database."
	// }
	// MysqlDB.SingularTable(true)
	if MysqlDB.Where("username = ? AND password = ?", user.Username, user.Password).First(user).RecordNotFound() {
		return "Username or password uncorrect."
	}
	return "Login success."
}

func findUserIDByUsername(username string) int {
	// MysqlDB, err := gorm.Open("mysql", path)
	// if err != nil {
	// 	fmt.Println("failed to connect database:", err)
	// 	return -1
	// }
	// MysqlDB.SingularTable(true)
	var user User
	if MysqlDB.Where("username = ?", username).First(&user).RecordNotFound() {
		return -2
	}
	return user.ID
}

func createTaskDAO(user User, task Task) string {
	// MysqlDB, err := gorm.Open("mysql", path)
	// if err != nil {
	// 	fmt.Println("failed to connect database:", err)
	// 	return "Failed to connect database."
	// }
	// MysqlDB.SingularTable(true)
	user.TaskAmount++
	task.TaskID = user.TaskAmount
	MysqlDB.Create(&task)
	MysqlDB.Save(&user)
	if MysqlDB.NewRecord(task) {
		return "Create failed."
	}
	return "Create success."
}

func getAllTasksByUserID(userid int) TaskAndInfo {
	// MysqlDB, err := gorm.Open("mysql", path)
	// if err != nil {
	// 	fmt.Println("failed to connect database:", err)
	// 	taskAndInfo.Info = "Failed to connect database."
	// 	return taskAndInfo
	// }
	// MysqlDB.SingularTable(true)
	var taskAndInfo TaskAndInfo
	MysqlDB.Where("userid_ref = ?", userid).Find(&taskAndInfo.tasks)
	taskAndInfo.Info = "Find success."
	return taskAndInfo
}

func deleteTaskDAO(task Task) string {
	// MysqlDB, err := gorm.Open("mysql", path)
	// if err != nil {
	// 	fmt.Println("failed to connect database:", err)
	// 	return "Failed to connect database."
	// }
	// MysqlDB.SingularTable(true)
	if MysqlDB.NewRecord(task) {
		return "No task exists."
	}
	MysqlDB.Delete(&task)
	return "Delete success."
}

func getTaskAmount(user User) int {
	// MysqlDB, err := gorm.Open("mysql", path)
	// if err != nil {
	// 	fmt.Println("failed to connect database:", err)
	// 	return -1
	// }
	// MysqlDB.SingularTable(true)
	MysqlDB.First(&user, user.ID)
	return user.TaskAmount
}

func updateTaskDAO(task Task) string {
	// MysqlDB, err := gorm.Open("mysql", path)
	// if err != nil {
	// 	fmt.Println("failed to connect database:", err)
	// 	return "Failed to connect database."
	// }
	// MysqlDB.SingularTable(true)
	MysqlDB.Save(&task)
	return "Update success."
}
