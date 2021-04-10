package main

import "fmt"

func main() {
	a := []int{1, 2, 1111, 3, 4, 5, 6, 8, 111}
	fmt.Println(massage(a))
}

func massage(nums []int) int {
	var totalTime int
	totalTime = dyn(nums)
	return totalTime
}

func dyn(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	if len(nums) < 2 {
		return nums[0]
	}
	if len(nums) < 3 {
		return max(nums[0], nums[1])
	}
	return max(nums[0]+dyn(nums[2:]), nums[1]+dyn(nums[3:]))
}

func max(a int, b int) int {
	if a > b {
		return a
	}
	return b
}
