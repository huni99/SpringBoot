package com.winter.app.sample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleTest {

	@Test
	void test() {
		// 1  =>  1
		// 2  =>  2
		// 3  =>  3
		
		// 4  =>  10
		// 5  =>  11
		// 6  =>  12
		// 7  =>  13
		
		// 8  =>  20
		// 9  =>  21
		
		int n = 5;
		int result = 10*(n/4)+n%4;
		
		
		
	}
	@Test
	public void test2() {
		//편의점 자동화
		int price = 32500;
		int money = 80000;
		int result = money-price;
		
		int m =result/10000;
		int c = (result%10000)/1000;
		int b = (result%1000)/100;
		
		System.out.println("만원 "+m+"장 "+"천원 "+c+"장 "+"백원 "+b+"장 ");
		
		
		
		
		
		
		
		
		
		
		
	}
}
