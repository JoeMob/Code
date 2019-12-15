package main

//Token is the token of user, one user has one Token, it include a Token and a RefreshToken.
type Token struct {
	Token        string `json:"token"`
	RefreshToken string `json:"refreshToken"`
}

func registerService(user User) string {
	return userRegister(user)
}

func loginService(user User) string {
	return userLogin(user)
}

func createToken() Token {
	var token Token
	return token
}
