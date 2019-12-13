package main

import (
    "net/http"
)

func registerService(user User)(string)  {
	return userRegister(user)
}

func loginService(user User,w http.ResponseWriter){
	userLogin(user,w)
}