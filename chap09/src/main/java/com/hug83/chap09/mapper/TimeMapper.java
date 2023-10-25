package com.hug83.chap09.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("select now()")
	String getTime();
}