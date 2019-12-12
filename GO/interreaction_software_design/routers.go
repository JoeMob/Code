package main

import "net/http"

type Route struct {
    Name        string
    Method      string
    Pattern     string
    HandlerFunc http.HandlerFunc
}

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