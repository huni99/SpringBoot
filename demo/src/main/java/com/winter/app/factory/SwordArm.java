package com.winter.app.factory;

import org.springframework.stereotype.Component;

//@Component
public class SwordArm implements Arm {
	@Override
	public void Attack() {
		System.out.println("Sword");
	}
}
