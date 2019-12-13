package main

import (
)

func registerService(user User)(string)  {
	return userRegister(user)
}

func loginService(user User)(string){
	return userLogin(user)
}