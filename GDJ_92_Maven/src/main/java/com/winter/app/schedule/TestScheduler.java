package com.winter.app.schedule;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {

//	@Scheduled(fixedRateString = "2000")
	public void m1()throws Exception {
		System.out.println(LocalDateTime.now());
		System.out.println("+++++++++++++++++++++++++++m2++++++++++++++++++++++++++");
		Thread.sleep(3000);
		System.out.println(LocalDateTime.now());
		
	}
//	@Scheduled(fixedDelay = 2000)
	public void m2() throws Exception{
		System.out.println(LocalDateTime.now());
		System.out.println("+++++++++++++++++++++++++++m2++++++++++++++++++++++++++");
		Thread.sleep(3000);
		System.out.println(LocalDateTime.now());
	}
	
	@Scheduled(cron ="3 * * * * * " )
	public void m3()throws Exception{
		System.out.println(LocalDateTime.now());

	}
}
