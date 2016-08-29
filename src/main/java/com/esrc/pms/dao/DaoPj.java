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

import com.esrc.pms.dto.DtoPj;
import com.esrc.pms.util.Constant;

public class DaoPj {

	DataSource dataSource;
	JdbcTemplate template = null;
	
	public DaoPj() {
		// TODO Auto-generated constructor stub
		template = Constant.template;
		try {
			/*Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");*/
			Class.forName("com.mysql.jdbc.Driver"); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

	public ArrayList<DtoPj> plist() {
	
	String query = "select * from pjinfo order by pId desc";
	return (ArrayList<DtoPj>) template.query(query, new BeanPropertyRowMapper<DtoPj>(DtoPj.class));
	}

	public void write(final String statement, final String pjTitle_K,final String chief, final String registrant,final String day1, final String day2, final String term) {
		// TODO Auto-generated method stub
		String query = "insert into pjinfo(statement, pjTitle_K, chief, registrant,day1,day2, term) values (?, ?, ?, ?, ?, ? ,?)";
		template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, statement);
				ps.setString(2, pjTitle_K);
				ps.setString(3, chief);
				ps.setString(4, registrant);
				ps.setString(5, day1);
				ps.setString(6, day2);
				ps.setString(7, term);
			}
		});
	}
	
	//pjInfo ajax�� ��ȸ
	public DtoPj pjInfo(String strID) {
		String query = "select * from pjinfo where pId = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<DtoPj>(DtoPj.class));
	}
	
	//pjContent ajax�� ��ȸ
	public DtoPj pjContent(String strID) {
		String query = "select * from pjinfo where pId = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<DtoPj>(DtoPj.class));
	}
	
	
	public void modify_pjInfo(String pId, String statement, String pjTitle_K , String pjTitle_E, String BTitle ,
			String chief ,String staff ,String institution ,String summary, String refer, String date_start,String date_end, String term) {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://121.131.123.207:3306/esrc_db", "esrc_db", "esrctpsxjtjqj");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/pmsdb", "root", "shua727282");
			
			String query = "update pjinfo set statement = ?, pjTitle_K = ?, pjTitle_E = ?, BTitle = ?, chief = ?,"
					+ "staff = ?,institution = ?,summary = ?, refer = ? ,day1 = ?, day2 = ?, term = ? where pId = ?";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, statement);
			preparedStatement.setString(2, pjTitle_K);
			preparedStatement.setString(3, pjTitle_E);
			preparedStatement.setString(4, BTitle);
			preparedStatement.setString(5, chief);
			preparedStatement.setString(6, staff);
			preparedStatement.setString(7, institution);
			preparedStatement.setString(8, summary);
			preparedStatement.setString(9, refer);
			preparedStatement.setString(10, date_start);
			preparedStatement.setString(11, date_end);
			preparedStatement.setString(12, term);
			preparedStatement.setInt(13, Integer.parseInt(pId));
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	public void modify_pjContent(String pId, String contents, String detail_contents) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://121.131.123.207:3306/esrc_db", "esrc_db", "esrctpsxjtjqj");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/pmsdb", "root", "shua727282");
			
			String query = "update pjinfo set contents = ?, detail_contents = ? where pId = ?";		
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, contents);
			preparedStatement.setString(2, detail_contents);
			preparedStatement.setInt(3, Integer.parseInt(pId));
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public void delete(final String pId) {
		// TODO Auto-generated method stub
		String query = "delete from pjinfo where pId = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, Integer.parseInt(pId));
			}
		});	

	}
}