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
		"getWorkingItems",
		"GET",
		"/workingitems",
		getWorkingItems,
	},
	Route{
		"getFinishedItems",
		"GET",
		"/finisheditems",
		getFinishedItems,
	},
	Route{
		"getFailedItems",
		"GET",
		"/finisheditems",
		getFailedItems,
	},
	Route{
		"register",
		"POST",
		"/user",
		register,
	},
	Route{
		"login",
		"GET",
		"/user",
		login,
	},
}
