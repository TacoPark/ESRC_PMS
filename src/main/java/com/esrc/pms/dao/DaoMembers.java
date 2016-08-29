package com.esrc.pms.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.sql.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.esrc.pms.dto.DtoMembers;
import com.esrc.pms.dto.DtoPj;
import com.esrc.pms.util.Constant;

public class DaoMembers {

	DataSource dataSource;
	JdbcTemplate template = null;
	
	public DaoMembers() {
		template = Constant.template;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//pjMembers ajax로 조회
		public DtoMembers PjMembersdto(String strID) {
			String query = "select * from pjMembers where pId = " + strID;
			return template.queryForObject(query, new BeanPropertyRowMapper<DtoMembers>(DtoMembers.class));
		}
	
	//pjMembers ajax로 조회
	public  ArrayList<DtoMembers> PjMembers(String strID) {
		String query = "select * from pjmembers where pId = " + strID +" order by role , resistTime";
		return (ArrayList<DtoMembers>) template.query(query, new BeanPropertyRowMapper<DtoMembers>(DtoMembers.class));
	}
	
	//중복검사
	public DtoMembers PjMembersdto(String strID, String name) {
		String query = "select * from pjMembers where pId = "+strID+" and name=\"" + name +"\"";
		return template.queryForObject(query, new BeanPropertyRowMapper<DtoMembers>(DtoMembers.class));
	}
	
	public void insert_pjMembers(final String pId, final String name, final String belong , final String role, final String phoneNum ,
			final String email ,final String date_start,final String date_end, final String term) {
		// TODO Auto-generated method stub
		String query = "insert into pjMembers(pId, name , belong , role , phoneNum , email, day1, day2 , joinTerm)"
				+" values (?,?,?,?,?,?,?,?,?)";	
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, pId);
				ps.setString(2, name);
				ps.setString(3, belong);
				ps.setString(4, role);
				ps.setString(5, phoneNum);
				ps.setString(6, email);
				ps.setString(7, date_start);
				ps.setString(8, date_end);
				ps.setString(9, term);
			}
		});
	}
	
	//참여연구원 삭제
	public void delete(final String mId) {
		// TODO Auto-generated method stub
		System.out.println("dao : " + mId);
		String query = "DELETE FROM pjmembers WHERE mId IN (?)";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, Integer.parseInt(mId));
			}
		});
	}
}
