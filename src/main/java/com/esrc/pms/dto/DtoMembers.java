package com.esrc.pms.dto;
import java.sql.Date;

/**
 * @author Joshua
 *
 */
public class DtoMembers{
	int mId;
	String name;
	String belong;
	String phoneNum;
	String email;
	
	String role;
	String day1;
	String day2;
	String joinTerm;

	public DtoMembers() {
		// TODO Auto-generated constructor stub
	}
	
	public DtoMembers(int mId,
				String userID,
				String name,
				String belong,
				String phoneNum,
				String email,
				
				String role,
				String day1,
				String day2,
				String joinTerm) {
		// TODO Auto-generated constructor stub
		this.mId = mId;
		this.name = name;
		this.belong = belong;
		this.phoneNum = phoneNum;
		this.email = email;
		this.role = role;
		this.day1 = day1;
		this.day2 = day2;
		this.joinTerm = joinTerm;		
	}



	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDay1() {
		return day1;
	}

	public void setDay1(String day1) {
		this.day1 = day1;
	}

	public String getDay2() {
		return day2;
	}

	public void setDay2(String day2) {
		this.day2 = day2;
	}

	public String getJoinTerm() {
		return joinTerm;
	}

	public void setJoinTerm(String joinTerm) {
		this.joinTerm = joinTerm;
	}


}
