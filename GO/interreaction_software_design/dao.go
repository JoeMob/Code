package main

import (
	"fmt"
	"time"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

var MysqlDB *gorm.DB

type User struct{
	Uid      int    `gorm:"size:11;primary_key;AUTO_INCREMENT;not null" json:"uid"`
	Username string  `gorm:"size:255;DEFAULT NULL;not null" json:"username"`
	Password string  `gorm:"size:255;DEFAULT NULL;not null" json:"password"`
}

type Task struct{
	Uid       int       `gorm:"size:11;primary_key;AUTO_INCREMENT;not null" json:"uid"`
	Taskname  string    `gorm:"size:255;DEFAULT NULL;not null" json:"taskname"`
	StartTime time.Time `gorm:"size:10;DEFAULT NULL" json:"startTime"`
	EndTime   time.Time `gorm:"size:10;DEFAULT NULL" json:"endTime"`
	Describe  string    `gorm:"size:255;DEFAULT NULL" json:"describe"`
	Priority  int       `gorm:"size:1;DEFAULT NULL;not null" json:"priority"`
	State     int       `gorm:"size:1;DEFAULT NULL;not null" json:"state"`
	UseridRef int       `gorm:"size:11;not null" json:"useridRef"`
}

func userRegister(user User)(string)  {
	MysqlDB, err := gorm.Open("mysql", "root:Wyx19980626@(127.0.0.1:3306)/interreaction_software_design?charset=utf8&parseTime=True&loc=Local")
    if err != nil {
		fmt.Println("failed to connect database:", err)
		return "Connet to DB failed."
	}else{
	    MysqlDB.SingularTable(true)
		if MysqlDB.Where("username = ?", user.Username).Find(&User{}).RecordNotFound(){
			MysqlDB.Create(&user)
			return "User created." 
		}else{
			return "User already registerd."
	    }
    }
}

func userLogin(user User)(string)  {
	MysqlDB, err := gorm.Open("mysql", "root:Wyx19980626@(127.0.0.1:3306)/interreaction_software_design?charset=utf8&parseTime=True&loc=Local")
    if err != nil {
		fmt.Println("failed to connect database:", err)
		return "Failed to connect database."
	}else{
		MysqlDB.SingularTable(true)
		if MysqlDB.Where("username = ? AND password = ?", user.Username,user.Password).Find(&User{}).RecordNotFound(){
			return "Username or password uncorrect."
		}else{
			return "Login sucess."
	    }
    }
}