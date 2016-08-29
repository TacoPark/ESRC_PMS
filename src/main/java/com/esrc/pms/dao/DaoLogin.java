package com.esrc.pms.dao;

import com.esrc.pms.dto.DtoLogin;
import com.esrc.pms.util.Constant;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class DaoLogin {

	JdbcTemplate template;
	
	public DaoLogin() {
		this.template = Constant.template;
	}
	
	
	public DtoLogin CheckLogin(final String sUserID, final String sPassword) {
		//System.out.println("BDtologin");
		String query = " select id as sPID "
				+ " from logininfo"
				+ " where id = '" + sUserID + "'"
				+ " and pass = '" + sPassword + "'";
		
		try {
			return template.queryForObject(query, new BeanPropertyRowMapper<DtoLogin>(DtoLogin.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

		
	}
	
}
