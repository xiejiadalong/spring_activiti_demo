package com.longge.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longge.spring.dao.FileMapper;
import com.longge.spring.entity.FilePathEntity;

@Service
public class FileService {

	@Autowired
	FileMapper fileDao ;
	public void insertFile( FilePathEntity paths){
		fileDao.insertFilePath(paths);	
	}
}
