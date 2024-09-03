package com.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface Temp1Mapper {

	@Select("select 1 as no")
	public int test();
	
	@Select({"<script>"
			+ "SELECT * FROM edu.temp1 "
			+ "<if test='accept == 1'>WHERE accept = 1</if> "
			+ "<if test='accept == 0'>WHERE accept = 0</if> "
			+ "</script>"})
	public List<Map> findList(String accept);
	
	@Select("SELECT * FROM edu.temp1 WHERE no = #{no}")
	public Map findOne(int no);
	
	@Update("UPDATE edu.temp1 SET title = #{title}, content = #{content} WHERE no = #{no}")
	public int edit(Map map);
	
	@Update("UPDATE edu.temp1 SET accept = #{accept} WHERE no = #{no}")
	public int accept(Map map);
	
	@SelectKey(statementType = StatementType.PREPARED, statement = "select last_insert_id() as no", keyProperty = "no", before = false, resultType = int.class)
	@Insert("INSERT INTO edu.temp1 (title, content) VALUE (#{title}, #{content})")
	public int save(Map map);
	
	
	
}
