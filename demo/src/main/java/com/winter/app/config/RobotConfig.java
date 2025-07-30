package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.winter.app.boards.BoardController;
import com.winter.app.factory.Arm;
import com.winter.app.factory.GunArm;


@Configuration
public class RobotConfig {
	
	private final BoardController boardController;
	
	RobotConfig(BoardController boardController){
		this.boardController=boardController;
	}
	
	@Bean
	Arm getGunArm() {
		return new GunArm();
	}
}
