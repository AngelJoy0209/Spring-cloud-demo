package com.zhouenzhi.restserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhouenzhi.restserver.entity.Dictionary;
import com.zhouenzhi.restserver.mapper.DictionaryMapper;

@Service
public class DictionaryService {
	
	@Autowired
	DictionaryMapper mapper;
	
	public List<Dictionary> getDictionaryList(){
		List<Dictionary> list = mapper.getDictionaryList();
		return list;
	}
}
