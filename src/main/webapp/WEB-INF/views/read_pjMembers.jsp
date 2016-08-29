<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.esrc.pms.dao.DaoPj"%>
<%@ page import="com.esrc.pms.dto.DtoPj"%>
<%@ page import="java.util.ArrayList"%>
<div>
<div id="pjMembers_page">
<a href="javascript:void(0)"><img src="resources/img/plus.png" id="btn_new_members"></a>
	<div class="table table_Members" id="table_members">
		<div class="table_head">
			<span>이름</span><span>소속</span><span>참여구분</span>
		</div>
		<div class="context_wrapper" id="context_wrapper" name="context_wrapper">
		<!-- ajax로 호출 -->
		</div>
	</div>
</div>

<!-- Light Box -->
<div id="myModal_new_member" class="modal hide fade" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 id="myModalLabel">참여연구원 등록</h4>
	</div>
	<div class="modal-body">
		<form action="" accept-charset="utf-8" id="new_member" name="new_member">
			<table>
				<tbody>
					<tr>
						<td>성명</td>
						<td><input type="text" id="name_mem" name="name_mem"></td>
					</tr>
					<tr>
						<td>소속</td>
						<td><input type="text" id="belong_mem" name="belong_mem"></td>
					</tr>
					<tr>
						<td>참여구분</td>
						<td><input type="text" id="role_mem" name="role_mem" placeholder="ex)연구책임자"></td>
					</tr>
					<tr>
						<td>휴대전화</td>
						<td><input type="tel" id="phoneNum_mem" name="phoneNum_mem"
							placeholder="010-1234-5678" pattern="/^\d{3}-\d{3,4}-\d{4}$/;"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="email" id="email_mem" name="email_mem"
							placeholder="esrc@smu.ac.kr"
							pattern="/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i"></td>
					</tr>
					<tr>
						<td>참여기간</td>
						<td><input type="date" id="date_start_mem" name="date_start_mem" >&nbsp;부터&nbsp;&nbsp;
							<input type="date" id="date_end_mem" name="date_end_mem" >&nbsp;까지</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="modal-footer">
		<input type="submit" class="btn" data-dismiss="modal" aria-hidden="true" value="Close">
		<input type="submit" class="btn btn-primary" onclick="fnMembers_insert(primary);" data-dismiss="modal" aria-hidden="true" value="OK">
	</div>
</div>
</div>
<script>
fn_GetMembers = function (pId){
	// 리스트 초기화
	document.getElementById('context_wrapper').innerHTML = '';
  	$.ajax({
    	async: false,
     	url: "get_members",
     	type: "post",
     	dataType: "text",
     	data:{
     		pId : pId
     	},
     	success:function(result)
     	{	
     		var Ca = /\+/g;
     		result = decodeURIComponent( result.replace(Ca, " ") ); 
     		var data = eval(result);
     		 $.each(data, function(key, value){
     			fn_addMembers(value.mId, value.name , value.role ,value.belong, value.phoneNum, value.email, value.joinTerm);
              });
   		  	 $("#pjMembers_page .table_context").hide();
   		   	 $('#pjMembers_page .table_sub_head').click(function() {
   		        $(this).toggleClass("highlight");
   		        $(this).next().slideToggle('fast');
   		    });
     	},
     	error:function(result)
     	{
     		alert("error_member_ajax");
     	}
   });
}    

fn_addMembers = function(mId, name, role, belong, phoneNum, email, joinTerm){
	
		var membersList ='';
		membersList +='<div class="table_list">\
		                <div class="table_sub_head">\
		                <span>'+name +'</span><span>'+belong +'</span><span>'+role +'</span></div>\
		                <div class="table_context">\
		                    <table>\
		                       	<input type="hidden" id="mId" name="mId" value="'+mId+'">\
		                        <tr>\
		                            <td>휴대전화</td><td>'+phoneNum +'</td>\
		                        </tr>\
		                        <tr>\
		                            <td>이메일</td><td>'+email+'</td>\
		                        </tr>\
		                        <tr>\
		                            <td>참여기간</td><td>'+ joinTerm +'</td>\
		                        </tr>\
		                    </table>\
		                    <div><a href="javascript:void(0)" onclick="fnMembers_delete(primary, '+mId+');">x</a></div>\
		                </div>\
		            </div>';
	     $("#context_wrapper").append(membersList);	
}

//참여연구원 등록
fnMembers_insert = function(pId){

	if(	dataValidation("new_member") == true) { //참여연구원 등록 유효성 검사
		$.ajax({
			async : false,
			url : "./insert_members",
			type : "post",
			dataType : "html",
			data : {
				pId : pId,
				name_mem : $("#name_mem").val(),
				belong_mem : $("#belong_mem").val(),
				role_mem : $("#role_mem").val(),
				phoneNum_mem : $("#phoneNum_mem").val(),
				email_mem : $("#email_mem").val(),
				date_start_mem : $("#date_start_mem").val(),
				date_end_mem : $("#date_end_mem").val()
			},
			success : function(data) {
				if(data=="success insert pjMembers"){
					fn_GetMembers(pId);
					$('#new_member input[type="text"]').val("");
					$('#new_member input[type="date"]').val("");
					$('#new_member input[type="tel"]').val("");
					$('#new_member input[type="email"]').val("");
				}
				else if (data=="error")
					alert("이름이 중복되었습니다.");
					
					$('#new_member input[type="date"]').val("");
					$('#new_member input[type="tel"]').val("");
					$('#new_member input[type="email"]').val("");
				
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		       }
		});
	}
};

//참여연구원 추가
$(document).ready(function(){
    $("#btn_new_members").click(function(){
        $('#myModal_new_member').modal('show');
    });
});

//참여연구원 삭제
//클릭하면 mId 받아와서 json형태로 만들기 - 나중에 성언이와 협의
//x 클릭하면 해당 참여연구원 삭제
fnMembers_delete = function(pId, mId){
    		$.ajax({
    			async: false,
    			url: "./delete_pjMembers",
    			type: "post",
    			dataType: "html",
    			data:{
    				mId : mId
    			},
    			success:function(data)
    			{
    				fn_GetMembers(pId); 				
    			},
    			error:function(data)
    			{
    				alert("error_ deleteMembers ajax");
    			}
    	});
};

</script>

