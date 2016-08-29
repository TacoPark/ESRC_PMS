<%@ page import="com.esrc.pms.dao.DaoPj"%>
<%@ page import="com.esrc.pms.dto.DtoPj"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setContentType("text/html; charset=utf-8"); %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	if (session.getAttribute("LID") == null) {
%>
	<script>
		alert("로그인 해주세요");
		document.location.href = "./login";
	</script>
<%
	}
%>
<!DOCTYPE html>
<html>

<head>
    <title>PMS</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <!-- Bootstrap -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" />
    <link href="resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
    <style type="text/css">
    @import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
    @import url("resources/css/pms_phone.css") only screen and (max-width: 760px);
    @import url("resources/css/pms.css") only screen and (min-width: 761px);
    @import url("resources/css/pjInfo.css");
    @import url("resources/css/pjContent.css");
    @import url("resources/css/pjMembers.css");
    @import url("resources/css/pjAttachment.css");
    @import url("resources/css/LightBox.css");
    </style>
</head>

<body>
    <div id="header">
        <span><a href="./pms">Project Management System</a></span>
        <img src="resources/img/menu.png" class="header_btn" />
    </div>
    <!-- <div id="nav_phone_ver">
        <ul>
            <li id="btn_login" class="nav_btn btn_login">
                <a href="#">
                    <p><strong>LOGOUT</strong></p>
                </a>
            </li>
            <li id="btn_search" class="nav_btn btn_search">
                <a href="#">
                    <p><strong>SEARCH</strong></p>
                </a>
            </li>
            <li id="btn_new" class="nav_btn btn_new">
                <a href="#">
                    <p><strong>NEW</strong></p>
                </a>
            </li>
            <li id="pjInfo" class="session pjInfo">
                <a href="#">
                    <p><strong>과제정보</strong></p>
                </a>
            </li>
            <li id="pjContent" class="session pjContent">
                <a href="#">
                    <p><strong>연구내용</strong></p>
                </a>
            </li>
            <li id="pjMembers" class="session pjMembers">
                <a href="#">
                    <p><strong>참여연구원</strong></p>
                </a>
            </li>
            <li id="pjAttachment" class="session pjAttachment">
                <a href="#">
                    <p><strong>첨부파일</strong></p>
                </a>
            </li>
        </ul>
    </div> -->
    <div id="wrap" class="row-fluid">
        <div id="nav" class="span2">
            	
            <ul>
	            <div id="userInfo">
	                  <%=session.getAttribute("LID")%>님 환영합니다.
	            </div>
	            <li id="btn_login" class="nav_btn btn_login">
                    <a href="/logout">
                        <p><i class="icon-user"></i>LOGOUT</p>
                    </a>
                </li>
                <li id="btn_new" class="nav_btn btn_new" onclick="fn_GetList();">
                    <a href="#">
                        <p><i class="icon-pencil"></i>NEW</p>
                    </a>
                </li>
                <li id="btn_delete" class="nav_btn btn_delete">
                    <a href="#" onclick="fndelete(primary);">
                        <p><img	src="resources/img/Delete.png">DELETE</p>
                    </a>
                </li>
            </ul>
				<!-- Light Box -->
                 <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 id="myModalLabel">New Project</h4>
                    </div>
                   
                    <div class="modal-body">
                         <form action="" accept-charset="utf-8" id="new_pjinfo" name="new_pjinfo" method="get">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><strong>진행상황</strong></td>
                                        <td>
                                            <input type="radio" name="statement_new" id="success" value="진행" checked="checked">
                                            <label for="success"><span class="label label-success">진행</span></label>
                                            <input type="radio" name="statement_new" id="warning" value="보류">&nbsp;&nbsp;
                                            <label for="warning"><span class="label label-warning">보류</span></label>
                                            <input type="radio" name="statement_new" id="important" value="완료">
                                            <label for="important"><span class="label label-important"> 완료</span></label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label for="title"><strong>과제명</strong></label></td>
                                        <td><input type="text" id="pjTitle_K" name="pjTitle_K" class="required"></td>
                                    </tr>
                                     <tr>
                                        <td><label for="title"><strong>책임 연구원</strong></label></td>
                                        <td><input type="text" id="chief" name="chief" ></td>
                                    </tr>                                    
                                    <tr>
                                        <td><label for="date_start"><strong>과제기간</strong></label></td>
                                        <td>
                                            <input type="date" id="date_start" name="date_start" class="required"><strong>&nbsp;부터</strong>&nbsp; &nbsp;
                                            <input type="date" id="date_end" name="date_end" class="required"><strong>&nbsp;까지</strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><input type="hidden" type="text" id="registrant" name="registrant" value="<%=session.getAttribute("LID")%>"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                    <div class="modal-footer">                        
                        <input type="submit" class="btn" data-dismiss="modal" aria-hidden="true" value="Close">                        
                        <input type="submit" class="btn btn-primary" data-dismiss="modal" aria-hidden="true" value="OK"/>
                    </div>
                    
                </div>
            <div id="address">
            <blockquote>
			  <p> 
	           	Emotion Science Research Center<br>
	           	&amp; Creative Open Campus<br>
	          </p>
			  <small>© 2016.all rights reserved.</small>
			</blockquote>
            	
            </div>
        </div>
        <div id="article" class="span5 accordion accordion-group">
            <div class="navbar">
                <div class="navbar-inner">
                    <div id="article_header">
                        <!-- 헤더 -->
                        <div class="accordion-heading">
                            <h3>#PROJECT LIST</h3>
                            <!-- Search버튼 -->
                            <div class="input-append">
                            <input type='text' id='appendedDropdownButton' placeholder="번호 / 프로젝트명 / 연구책임자 / 연구기간" onkeyup='{filter();return false}' onkeypress='javascript:if(event.keyCode==13){ filter(); return false;}'>
                                <div class="btn-group">

                                    <button class="btn">Search</button>
                                </div>
                            </div>
                            <!-- 리스트 버튼 -->
                            <div id="btn-navbar" class="accordion-toggle">
                                <a data-toggle="collapse" data-parent="#article" href="#article_context">
                                    <i class="icon-chevron-down"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div id="article_context" class="accordion-body collapse in accordion-inner">
                    <!-- List 출력 -->
						<ul id="pjList">
							<!-- ajax로 구현 -->
						</ul>
					</div>
                </div>
            </div>
        </div>
        <div id="aside" class="span5">
            <div id="aside_header">
                <ul>
                    <li id="pjInfo" class="session pjInfo"><a href="#" onclick="pjInfo_Click(primary);">과제정보</a></li>
                    <li id="pjContent" class="session pjContent" ><a href="#" onclick="pjContent_Click(primary);">연구내용</a></li>
                    <li id="pjMembers" class="session pjMembers"><a href="#" onclick="pjMembers_Click(primary);">참여연구원</a></li>
                    <li id="pjAttachment" class="session pjAttachment"><a href="#" onclick="pjAttachment_Click(primary);">첨부파일</a></li>
                </ul>
            </div>
            <div id="aside_context">
            <!-- TEST SECTION -->
	            <div class="notice" id="notice">
					<div class="">
						<img src="resources/img/check-symbol.png" width="200px" height="200px">
					</div>
					<div>
						<h2>확인된 요청사항이 없습니다.</h2>
					</div>
						<p>원하시는 프로젝트 정보를 열람해보세요.</p>					
	            </div>
        	</div>
        </div>        
    </div>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.form.min.js"></script>
<script type="text/javascript" src="resources/js/pms.js"></script>
<script type="text/javascript" src="resources/js/validation.js"></script>
<script type="text/javascript" src="resources/js/effect.js"></script>
<script type="text/javascript" src="resources/js/pms_phone.js"></script>
</html>
