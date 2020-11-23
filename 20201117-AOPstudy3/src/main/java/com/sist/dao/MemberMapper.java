package com.sist.dao;

import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM member "
			+ "WHERE id=#{id}")
	public int idCheck(String id);
	@Select("SELECT pwd,id FROM member "
			+ "WHERE id=#{id}")
	public String memberGetPassword(String id);
}
