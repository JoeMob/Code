package main

import "fmt"

func sliceTest() {
	slice := []rune{'啊', 'b'}
	var sliceTest []string
	sliceTest = []string{"a", "吧"}
	fmt.Println(string(slice), cap(slice))
	fmt.Println(sliceTest, cap(sliceTest))
}
