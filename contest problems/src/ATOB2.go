package main

import (
	"bufio"
	"fmt"
	"os"
)

var s1 = make([]byte, 110)
var s2 = make([]byte, 110)

var ans [][2]int

func flip(x, y int) {
	ans = append(ans, [2]int{x, y})
	for i := x; i <= y; i++ {
		if s1[i] == '0' {
			s1[i] = '1'
		} else {
			s1[i] = '0'
		}
	}
}

func solve1(n int) {
	b1 := 1

	for i := 0; i < n; i++ {
		if s1[i] != s2[i] {
			b1 = 0
		}
	}
	if b1 == 1 {
		fmt.Println(0)
		return
	}

	b1 = 1
	for i := 0; i < n; i++ {
		if s2[i] != s2[0] {
			b1 = 0
		}
	}
	if b1 == 1 {
		fmt.Println(1)
		return
	}

	b1 = 1
	for i := 0; i < n; i++ {
		if s1[i] != s1[0] {
			b1 = 0
		}
	}

	if b1 == 1 {
		fmt.Println(1)
	} else {
		fmt.Println(0)
	}
}

func solve2(n int) {
	diff := 0

	for i := 0; i < n; i++ {
		if s1[i] != s2[i] {
			diff++
		}
	}

	if diff == 0 {
		fmt.Println(0)
		return
	}

	b2 := 1
	for i := 0; i < n; i++ {
		if s2[i] != s2[0] {
			b2 = 0
		}
	}

	b1 := 1
	for i := 0; i < n; i++ {
		if s1[i] != s1[0] {
			b1 = 0
		}
	}

	if (b1 == 1 && b2 == 1) || diff == n {
		fmt.Println(1)
		fmt.Println(1, n)
		return
	}

	ans = nil

	if b1 == 1 {
		for i := 0; i < n; i++ {
			if s1[i] != s2[i] {
				flip(i, i)
				diff--
				break
			}
		}
	}

	for {
		if diff == 0 {
			break
		}
		if diff == 1 && b2 == 1 {
			break
		}

		for i := 0; i < n; i++ {
			if s1[i] != s2[i] {

				b3 := 1
				for j := 0; j < n; j++ {
					if j == i {
						continue
					}
					if s1[j] == s1[i] {
						b3 = 0
					}
				}
				if b3 == 1 {
					continue
				}

				diff--

				if i-2 >= 0 {
					j := i - 1
					k := j - 1
					for ; k >= 0; k-- {
						if s1[k] != s1[j] {
							break
						}
					}
					if k >= 0 {
						if s1[j] == s1[i] {
							flip(k, i)
							flip(k, j)
						} else {
							flip(k, j)
							flip(k, i)
						}
						break
					}
				}

				if i+2 < n {
					j := i + 1
					k := j + 1
					for ; k < n; k++ {
						if s1[k] != s1[j] {
							break
						}
					}
					if k < n {
						if s1[j] == s1[i] {
							flip(i, k)
							flip(j, k)
						} else {
							flip(j, k)
							flip(i, k)
						}
						break
					}
				}

				if s1[i-1] == s1[i] {
					flip(i, i+1)
					flip(i-1, i)
					flip(i-1, i+1)
				} else {
					flip(i-1, i)
					flip(i, i+1)
					flip(i-1, i+1)
				}
				break
			}
		}
	}

	if b2 == 1 {
		for i := 0; i < n; i++ {
			if s1[i] != s2[i] {
				flip(i, i)
				diff--
				break
			}
		}
	}

	fmt.Println(len(ans))
	for _, p := range ans {
		fmt.Println(p[0]+1, p[1]+1)
	}
}

func main() {
	in := bufio.NewReader(os.Stdin)

	var t int
	fmt.Fscan(in, &t)

	for ; t > 0; t-- {
		var n int
		var str1, str2 string

		fmt.Fscan(in, &n)
		fmt.Fscan(in, &str1)
		fmt.Fscan(in, &str2)

		for i := 0; i < n; i++ {
			s1[i] = str1[i]
			s2[i] = str2[i]
		}

		solve1(n)
		solve2(n)
	}
}
