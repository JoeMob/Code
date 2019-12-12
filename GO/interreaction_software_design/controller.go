package main

import (
    "fmt"
    "io/ioutil"
    "encoding/json"
    "net/http"
	"github.com/jinzhu/gorm"
    _ "github.com/jinzhu/gorm/dialects/mysql"
    "crypto/md5"
    "encoding/hex"
)

func connectMySQL(){ 
    MysqlDB, err := gorm.Open("mysql", "root:Wyx19980626@(127.0.0.1:3306)/interreaction_software_design?charset=utf8&parseTime=True&loc=Local")
	if err != nil {
		fmt.Println("failed to connect database:", err)
		return
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
    w.Header().Add("Access-Control-Allow-Origin", "*")
    type Profile struct {
    Name    string
    Hobbies []string
    }
    profile := Profile{"Alex", []string{"snowboarding", "programming"}}
    js, err := json.Marshal(profile)
    if err != nil {
        http.Error(w, err.Error(), http.StatusInternalServerError)
        return
    } 
    w.Header().Set("Content-Type", "application/json")
    w.Write(js)
}

func getFinishedItems(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintln(w,"fuck")
}

func getFailedItems(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintln(w,"fuck")
}

func register(w http.ResponseWriter, r *http.Request) {
    w.Header().Set("Access-Control-Allow-Origin", "*")
    w.Header().Set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
    w.Header().Set("Access-Control-Allow-Credentials", "true")
    var user User
    body, _ := ioutil.ReadAll(r.Body)
    json.Unmarshal(body, &user)
    fmt.Println(user.Username)
    fmt.Println(user.Password)
    md5Ctx := md5.New()
    md5Ctx.Write([]byte(user.Password))
    user.Password = hex.EncodeToString(md5Ctx.Sum(nil))
    registerService(user,w)
}

func login(w http.ResponseWriter, r *http.Request){
    w.Header().Set("Access-Control-Allow-Origin", "*")
    w.Header().Set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
    w.Header().Set("Access-Control-Allow-Credentials", "true")
    var user User
    body, _ := ioutil.ReadAll(r.Body)
    json.Unmarshal(body, &user)
    md5Ctx := md5.New()
    md5Ctx.Write([]byte(user.Password))
    user.Password = hex.EncodeToString(md5Ctx.Sum(nil))
    loginService(user,w)
}

func userOptions(w http.ResponseWriter, r *http.Request){
    w.Header().Set("Access-Control-Allow-Origin", "*")
    w.Header().Set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
    w.Header().Set("Access-Control-Allow-Credentials", "true")
}

func main() {
    connectMySQL()
    router := NewRouter()
	http.ListenAndServe(":8081", router)
}