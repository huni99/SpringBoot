package com.winter.app.factory;

import org.springframework.stereotype.Component;

//@Component("ga")
public class GunArm implements Arm{
	
	@Override
	public void Attack() {
		System.out.println("Gun");
	}
	
}
