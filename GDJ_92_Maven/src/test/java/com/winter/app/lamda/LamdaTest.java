package com.winter.app.lamda;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public final class LamdaTest {
	@Test
	
	void test() {
		int n1= 10;
		int n2 = 10;
		TestFunction testFunction = (int a , int  b)->a+b;
		testFunction.f1(n1, n2);
		
		//내장
		Consumer<Integer> con = t -> {
			System.out.println(t);
			System.out.println(t);
		};
		con.accept(2);
	}
	
}
