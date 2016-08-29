<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ page import="java.util.ArrayList" %>

<%@ page import="com.esrc.pms.dto.DtoLogin" %>
<%@ page import="com.esrc.pms.dao.DaoLogin" %>

<%
	request.setCharacterEncoding("utf-8");

	String sUserID = request.getParameter("UserID");
	System.out.println(sUserID);
	String sPassword = request.getParameter("Password");
	String sPID = "";

	
	// 로그인 체크후 회원정보 가져오기
	DaoLogin dao = new DaoLogin();
	DtoLogin dto = dao.CheckLogin(sUserID, sPassword);
	if(dto != null){
		sPID = dto.getsPID();
	}
	//System.out.println("sPID:" + sPID);
	
	StringBuffer result = new StringBuffer();
	
	result.append("{");
	result.append("\"sPID\"").append(":\"").append(sPID).append("\"");
	result.append("}");
	
%>
[<%=result%>]

