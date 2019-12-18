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

func createTaskService(user User, task Task) string {
	task.UseridRef = user.ID
	user.TaskAmount = getTaskAmount(user)
	if task.TaskID == -1 {
		return "Connect DB failed."
	}
	return createTaskDAO(user, task)
}
func createToken() Token {
	var token Token
	return token
}

func getAllTasksService(ID int) TaskAndInfo {
	return getAllTasksByUserID(ID)
}

func deleteTaskService(task Task) string {
	if task.ID != 0 {
		return deleteTaskDAO(task)
	}
	return "Task id couldn't = 0"
}

func updateTaskService(task Task) string {
	return updateTaskDAO(task)
}
