package com.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.app.dto.ChannelDTO;

@Mapper
public interface ChannelMapper {

	@Select("SELECT * FROM channel")
	public List<ChannelDTO> getChannel();
	
}
