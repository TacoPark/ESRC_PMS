<%@ page import="com.esrc.pms.dao.DaoPj"%>
<%@ page import="com.esrc.pms.dto.DtoPj"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="pjInfo_page">
	<ul>
	    <li>
	        <div class="column_wrapper">
	        <div class="left_column_wrap"><div class="left_column"><strong>진행상황</strong></div></div>
	        <input style="display:none;" value="${pjInfo.statement}" id="state" name="state">
	        <div class="right_column state_group">
	            <input type="radio" name="statement" id="success_info" value="진행">
	            <label for="success_info"><span class="label label-success">진행</span></label>
	
	            <input type="radio" name="statement" id="warning_info" value="보류">
	            <label for="warning_info"><span class="label label-warning">보류</span></label>
	
	            <input type="radio" name="statement" id="important_info" value="완료">
	            <label for="important_info"><span class="label label-important">완료</span></label>
	        </div>
	        </div>
	    </li>
	    <li>
	        <div class="column_wrapper">
	            <div class="left_column_wrap"><div class="left_column"><label for="pjTitle_K_info"><strong>과제명(국문)</strong></label></div></div>
	            <div class="right_column"><input type="text" id="pjTitle_K_info" name="pjTitle_K_info" placeholder="" value="${pjInfo.pjTitle_K}"></div>
	        </div>
	    </li>
	    <li>
	        <div class="column_wrapper">
	            <div class="left_column_wrap"><div class="left_column"><label for="pjTitle_E_info"><strong>과제명(영문)</strong></label></div></div>
	            <div class="right_column"><input type="text" id="pjTitle_E_info" name="pjTitle_E_info" placeholder="Add Title in English" value="${pjInfo.pjTitle_E}"></div>
	        </div>
	    </li>
	    <li>
	        <div class="column_wrapper">
	            <div class="left_column_wrap"><div class="left_column"><label for="BTitle_info"><strong>사업명</strong></label></div></div>
	            <div class="right_column"><input type="text" id="BTitle_info" name="BTitle_info" placeholder="Add Business name" value="${pjInfo.BTitle}"></div>
	        </div>
	    </li>
	    <li>
	        <div class="column_wrapper">
	            <div class="left_column_wrap"><div class="left_column"><label for="chief_info"><strong>연구책임자</strong></label></div></div>
	            <div class="right_column"><input type="text" id="chief_info" name="chief_info" placeholder="Add Chief" value="${pjInfo.chief}"></div>
	        </div>
	    </li>
	    <li>
	        <div class="column_wrapper">
	            <div class="left_column_wrap"><div class="left_column"><label for="staff_info"><strong>실무담당자</strong></label></div></div>
	            <div class="right_column"><input type="text" id="staff_info" name="staff_info" placeholder="Add Incharge" value="${pjInfo.staff}"></div>
	        </div>
	    </li>
	    <li>
	        <div class="column_wrapper">
	            <div class="left_column_wrap"><div class="left_column"><label for="institution_info"><strong>지원기관 / 관련기관</strong></label></div></div>
	            <div class="right_column"><input type="text" id="institution_info" name="institution_info" placeholder="Add Institution" value="${pjInfo.institution}"></div>
	        </div>
	    </li>
	    <li>
	        <div class="column_wrapper">
	            <div class="left_column_wrap"><div class="left_column"><label for="summary_info"><strong>연구내용 요약</strong></label></div></div>
	            <div class="right_column"><input type="text" id="summary_info" name="summary_info" placeholder="Add Summary" value="${pjInfo.summary}"></div>
	        </div>
	    </li>
	    <li>
	        <div class="column_wrapper">
	            <div class="left_column_wrap"><div class="left_column"><label for="date_start_info"><strong>참여기간</strong></label></div></div>
	            <div class="right_column">
	                <input type="date" id="date_start_info" name="date_start_info" value="${pjInfo.day1}">부터&nbsp;&nbsp;
	                <input type="date" id="date_end_info" name="date_end_info" value="${pjInfo.day2}"> 까지
	            </div>
	        </div>
	    </li>
	    <li>
	         <div class="column_wrapper">
	            <div class="left_column_wrap" ><div class="left_column"><label for="refer"><strong>참고사항</strong></label></div></div>
	            <div class="right_column except"><input type="text" id="refer" name="refer" placeholder="Add Reference" value="${pjInfo.refer}"></div>
	             </div>
	         </li>
		<li>
			<input type="hidden" id="pIdCheck_info" name="pIdCheck_info" value="${pjInfo.pId}">
		</li>
	</ul>
</div>
		
    <div class="target"></div>
		


<script>
$(document).ready(function(){
	state_check = $("#state").val();
		if(state_check=="진행"){
			$('#success_info').attr('checked', 'checked');
		}
		else if(state_check == "보류") {
			$('#warning_info').attr('checked', 'checked');
		}
		else if(state_check == "완료") {
			$('#important_info').attr('checked', 'checked');
		}
});

fnInfo_modify = function(pId){
		$.ajax({
			async : false,
			url : "modify_pjInfo",
			type : "post",
			dataType : "html",
			data : {
				pId : $("#pIdCheck_info").val(),
				statement : $(':radio[name="statement"]:checked').val(),
				pjTitle_K : $("#pjTitle_K_info").val(),
				pjTitle_E : $("#pjTitle_E_info").val(),
				BTitle: $("#BTitle_info").val(),
				chief: $("#chief_info").val(),
				staff: $("#staff_info").val(),
				institution: $("#institution_info").val(),
				summary: $("#summary_info").val(), 
				date_start : $("#date_start_info").val(),
				date_end : $("#date_end_info").val(),
				refer : $("#refer").val()
			},
			success : function(data) {	
				fn_GetList();
			},
			error : function(data) {
				alert("error_pjinfo");
			}
		});
};
//enter 이벤트
$(document).ready(function(){
	$('.state_group').click(function(){
		fnInfo_modify($("#pIdCheck_info").val());
	});$
    $('.right_column').focus();
    $(".right_column").focusout(function(e) {       
            //DB에 저장하기
            fnInfo_modify($("#pIdCheck_info").val());
            return false       
    });
});
</script>
