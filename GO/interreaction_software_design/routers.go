package main

import "net/http"

//Route is structure for route.
type Route struct {
	Name        string
	Method      string
	Pattern     string
	HandlerFunc http.HandlerFunc
}

//Routes are routes.
type Routes []Route

var routes = Routes{
	Route{
		"getAllTasks",
		"GET",
		"/tasks",
		getAllTasks,
	},
	Route{
		"createTask",
		"POST",
		"/task",
		createTask,
	},
	Route{
		"register",
		"POST",
		"/user/register",
		register,
	},
	Route{
		"login",
		"POST",
		"/user/login",
		login,
	},
	Route{
		"deleteTask",
		"DELETE",
		"/task",
		deleteTask,
	},
	Route{
		"dealOptions",
		"OPTIONS",
		"/task",
		dealOptions,
	},
	Route{
		"updateTask",
		"PUT",
		"/task",
		updateTask,
	},
}
