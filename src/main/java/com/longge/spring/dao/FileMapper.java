package com.longge.spring.dao;

import org.apache.ibatis.annotations.Mapper;

import com.longge.spring.entity.FilePathEntity;

@Mapper
public interface FileMapper {

	public void insertFilePath(FilePathEntity paths);
}
