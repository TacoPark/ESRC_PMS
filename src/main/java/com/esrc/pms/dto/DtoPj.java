package com.esrc.pms.dto;
import java.sql.Date;

/**
 * @author Joshua
 *
 */
public class DtoPj {

	int pId;
	String statement;
	String pjTitle_K;
	String pjTitle_E;
	String BTitle;
	String chief;
	String staff;
	String term;
	String institution;
	String summary ;
	String refer ;
	String registrant;
	String day1;
	String day2;
	Date date;
	
	String contents;
	String detail_contents;
	

	public DtoPj() {
		// TODO Auto-generated constructor stub
	}
	
	public DtoPj(int pId,
				String statement,
				String pjTitle_K,
				String pjTitle_E,
				String BTitle,
				String chief,
				String staff,
				String term,
				String institution,
				String summary ,
				String refer ,
				String day1,
				String day2,
				Date date,
				String registrant,
				String contents,
				String detail_contents) {
		// TODO Auto-generated constructor stub
		this.pId = pId;
		this.statement = statement;
		this.pjTitle_K = pjTitle_K;
		this.pjTitle_E = pjTitle_E;
		this.BTitle = BTitle;
		this.chief = chief;
		this.staff = staff;
		this.term = term;
		this.institution = institution;
		this.summary = summary;
		this.refer = refer;
		this.day1=day1;
		this.day2=day2;
		this.date = date;
		this.registrant = registrant;
		this.contents = contents;
		this.detail_contents = detail_contents;
	}


	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getpjTitle_K() {
		return pjTitle_K;
	}

	public void setpjTitle_K(String pjTitle_K) {
		this.pjTitle_K = pjTitle_K;
	}

	public String getpjTitle_E() {
		return pjTitle_E;
	}

	public void setpjTitle_E(String pjTitle_E) {
		this.pjTitle_E = pjTitle_E;
	}

	public String getBTitle() {
		return BTitle;
	}

	public void setBTitle(String bTitle) {
		BTitle = bTitle;
	}

	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
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
	
	public Date getDate() {
		return date;
	}

	public void setdate(Date date) {
		this.date = date;
	}

	public String getRegistrant() {
		return registrant;
	}

	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getDetail_contents() {
		return detail_contents;
	}

	public void setDetail_contents(String detail_contents) {
		this.detail_contents = detail_contents;
	}
}
