package main

import (
    "net/http"
)

func registerService(user User,w http.ResponseWriter)  {
	userRegister(user,w)
}

func loginService(user User,w http.ResponseWriter){
	userLogin(user,w)
}