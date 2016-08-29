<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div id="pjContent_page">
    <form action="" accept-charset="utf-8" name="new_pjContent" method="get">
        <div class="wrapper">
            <div class="content_head">
                <span>연구내용</span>
            </div>
            <div class="content">
                <textarea wrap="hard" placeholder="Add Project contents..." onkeyup="resize(this)" id="contents" name="contents" autofocus>${pjContent.contents}</textarea>
            </div>
        </div>
        <div class="wrapper">
            <div class="detailed_head">
                <span>세부연구내용</span>
            </div>
            <div class="detailed">
                <textarea wrap="hard" placeholder="Add Detailed contents..." onkeyup="resize(this)" id="detail_contents" name="detail_contents">${pjContent.detail_contents}</textarea>
            </div>            
        </div>
        <input type="hidden" id="pIdCheck_content" value="${pjContent.pId}">
		
    </form>
</div>

<script>
//enter 이벤트
$(document).ready(function(){
    $('#contents').focus();
    $("#contents").focusout( function(e) {
            //DB에 저장하기
            fnContent_modify($("#pIdCheck_content").val());
            return false
    });
});
//enter 이벤트
$(document).ready(function(){
    $("#detail_contents").focusout(function(e) {       
            //DB에 저장하기
            fnContent_modify($("#pIdCheck_content").val());
            return false       
    });
});

fnContent_modify = function(pId){
		$.ajax({
			async : false,
			url : "modify_pjContent",
			type : "post",
			dataType : "html",
			data : {
				pId : $("#pIdCheck_content").val(),
				contents : $("#contents").val(),
				detail_contents : $("#detail_contents").val()
			},
			success : function(data) {	
				fn_GetList();
			},
		});
};
</script>