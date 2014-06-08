package com.wh.app.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wh.app.web.model.raw.PublicAccountRaw;

public class PublicAccountRawDao {

	@Autowired JdbcTemplate jdbcTemplate;
	
	public void addRaw(PublicAccountRaw raw){
	}
}
