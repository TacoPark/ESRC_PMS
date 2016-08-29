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

import com.esrc.pms.dto.DtoFiles;
import com.esrc.pms.util.Constant;

public class DaoFile {
	DataSource dataSource;
	JdbcTemplate template = null;
	
	public DaoFile() {

		template = Constant.template;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	//pjMembers ajax로 조회
		public DtoFiles PjFIlesdto(String strID) {
			String query = "select * from pjfiles where fId = " + strID;
			return template.queryForObject(query, new BeanPropertyRowMapper<DtoFiles>(DtoFiles.class));
		}
	
	//pjFiles ajax로 조회
	public  ArrayList<DtoFiles> PjFiles(String strID) {
		String query = "select * from pjfiles where pId = " + strID ;
		return (ArrayList<DtoFiles>) template.query(query, new BeanPropertyRowMapper<DtoFiles>(DtoFiles.class));
	}
	
	//file insert DB
	public void insert_pjFile(final String pId, final String fileName , final String filePath) {
		// TODO Auto-generated method stub
		String query = "insert into pjfiles(pId , fileName , filePath)"
				+" values (?,?,?)";	
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, pId);
				ps.setString(2, fileName);
				ps.setString(3, filePath);
			}
		});
	}
	
	//파일 삭제
	public void fileDelete(final String fId) {
		String query = "DELETE FROM pjfiles WHERE fId IN (?)";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, Integer.parseInt(fId));
			}
		});
	}
}
