//pms 진입시 List 호출
$(document).ready(function(){
	fn_GetList();
});

//List data 호출
fn_GetList = function (){
	// 리스트 초기화
	document.getElementById('pjList').innerHTML = '';
  	$.ajax({
    	async: false,
     	url: "get_data",
     	type: "post",
     	dataType: "text",
     	data:{
     	},
     	success:function(result)
     	{	
     		var Ca = /\+/g;
     		result = decodeURIComponent( result.replace(Ca, " ") ); 
     
     		var data = eval(result);
     		 $.each(data, function(key, value){
     			fn_addList(value.pId , value.Title ,value.Statement, value.Chief, value.Term);
              });
     	},
     	error:function(result)
     	{
     		alert("error_list");
     	}
   });
}    

fn_addList = function(index,title,statement,chief,term){
	   
    var strHTML =  ""; 
     strHTML += '<li class="list_scope"><span class="pjID">[# '+index+']&nbsp; &nbsp;</span><a href="#" onclick="pjList('+index+');"><p class="article_contents">' + title + '</p>';
     strHTML += '</a><span class="article_subtext pj_leader">';
     if(statement == "진행"){
        strHTML += '<span class="label label-success">진행</span>';
     }
     else if(statement == "보류"){
        strHTML += '<span class="label label-warning">보류</span>';
     }
     else if(statement == "완료"){
        strHTML += '<span class="label label-important">완료</span>';
     }
     strHTML += '&nbsp; ' + chief + ' / <span><span class="article_subtext pj_period">';
     strHTML += term +'</li>';
     statement = "";
     
     $("#pjList").append(strHTML);
}

// new 버튼 클릭
$(document).ready(function(){
	    $(".btn_new").click(function(){
	    	$('#myModal').modal('show');	    	
    });
});

//New > OK 버튼 클릭
$(document).ready(function(){
    $(".btn-primary").click(function(){
    	
			if(dataValidation("new_pjinfo") == true){ //New Project 유효성 검사			
				
				document.getElementById('pjList').innerHTML = '';
				
		    	$.ajax({
		    		async: false,
		    		url: "./new_pj",
		    		type: "post",
		    		dataType: "text",
		    		data:{
		    			statement_new : $(':radio[name="statement_new"]:checked').val(),
		    			pjTitle_K : $("#pjTitle_K").val(),
		    			chief : $("#chief").val(),
		    			registrant : $("#registrant").val(),
		    			date_start : $("#date_start").val(),
		    			date_end : $("#date_end").val(),
		    		},
		    		success:function(result){
		    			var Ca = /\+/g;
		         		result = decodeURIComponent( result.replace(Ca, " ") ); 
		         		var idx=0;
		         		var data = eval(result);
		         		 $.each(data, function(key, value){
		         			fn_addList(value.pId , value.Title ,value.Statement, value.Chief, value.Term);
		         			if(idx==0)
		         				pjInfo_Click(value.pId);
		         			idx++;
		                  });
		         		
		    		},
		    		error:function(result){
		    			alert('error_new');
		    		}	
		    	});
				
			}
    	
    });
});



//검색창 기능
function filter(){
    if($('#appendedDropdownButton').val()==""){
        $("#article_context .list_scope").css('display','');
    }
    else{
        $("#article_context .list_scope").css('display','none');
        $("#article_context .list_scope p:contains('"+$('#appendedDropdownButton').val()+"')").parent().parent().css('display','');
        $("#article_context .list_scope span:contains('"+$('#appendedDropdownButton').val()+"')").parent().css('display','');
    }
    return false;
}

//프로젝트 제목 클릭
pjList = function(pId) {
	//프로젝트 제목 클릭시 과제정보 탭 클릭 효과
    $('.session').removeClass('visited');
    $('.pjInfo').addClass('visited');
    
    //pId 받아오기
	$.ajax({
		async : false,
		url : "./pms_pId",
		type : "get",
		dataType : "text",
		data : {
			pId : pId
		},
		success : function(pId) {
			primary = pId;
		},
		error : function(pId) {
			alert("fail");
		}
	});
	
	//aside_context에 과제정보 호출
	$.ajax({
		async : false,
		url : "./read_pjInfo",
		type : "post",
		dataType : "html",
		data : {
			pId : pId
		},
		success : function(data) {
			$("#aside_context").html(data);
		},
		error : function(data) {
			alert("error_pjinfo");
			}
		});
	};
	
//tab정보 클릭시 aside_context에 과제정보 호출
pjInfo_Click = function(pId) {
	$.ajax({
		async : false,
		url : "./read_pjInfo",
		type : "post",
		dataType : "html",
		data : {
			pId : pId
		},
		success : function(data) {
			$("#aside_context").html(data);
		},
		error : function(data) {
			alert("error_pjinfo");
		}
	});
};
	
pjContent_Click = function(pId) {
	$.ajax({
		async : false,
		url : "./read_pjContent",
		type : "post",
		dataType : "html",
		data : {
			pId : pId
		},
		success : function(data) {
			$("#aside_context").html(data);
		},
		error : function(data) {
			alert("error_pjcontent");
			}
		});
	};
	
pjMembers_Click = function(pId) {
		$.ajax({
			async : false,
			url : "./read_pjMembers",
			type : "post",
			dataType : "html",
			data : {
				pId : pId
			},
			success : function(data) {
				$("#aside_context").html(data);
				fn_GetMembers(pId);
			},
			error : function(data) {
				alert("error-pjMembers");
				}
			});
}
pjAttachment_Click = function(pId) {
		$.ajax({
			async : false,
			url : "./read_pjAttachment",
			type : "post",
			dataType : "html",
			data : {
				pId : pId
			},
			success : function(data) {
				$("#aside_context").html(data);
				fn_GetFiles(pId);
			},
			error : function(data) {
				alert("error-pjAttachment");
				}
			});
}

//프로젝트 삭제
fndelete = function(pId){
        msg = "프로젝트를 삭제하시겠습니까?";
        if (confirm(msg)!=0) {
             // Yes click
        	$.ajax({
    			async: false,
    			url: "delete_pj",
    			type: "post",
    			dataType: "html",
    			data:{
    				pId : pId,
    			},
    			success:function(data)
    			{
    				alert('삭제되었습니다');
    				fn_GetList();
    				$("#aside_context").html(data);
    			},
    			error:function(data)
    			{
    				alert("error_ delete ajax");
    			}
    			
    		});
        } else {
            // no click
}
};

	
//참여연구원 삭제
$(document).ready(function(){
    $(".delete_Members").click(function(){
        $(this).parent().parent().remove();
    });
});
	
//데이터 삽입 enter 이벤트
fnEnter = function(){
	$(document).ready(function(){
	    $('#yourInputBox').focus();
	    $("#yourInputBox ").bind("keydown", function(e) {
	        if (e.keyCode == 13) { // enter key
	            //DB에 저장하기
	            return false
	        }
	    });
	});
}

//Form 데이터 리셋
//New Project 추가 시에 OK 버튼을 누르면 form 데이터 값들이 리셋된다.
$(document).ready(function(){
	$(".btn").click(function(){
		$('#new_pjinfo input[type="text"]').val("");
		$('#new_pjinfo input[type="date"]').val("");	
	});
});

	