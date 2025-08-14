package com.winter.app.account;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDAO {

	public int accountAdd(AccountVO accountVO)throws Exception;
}
