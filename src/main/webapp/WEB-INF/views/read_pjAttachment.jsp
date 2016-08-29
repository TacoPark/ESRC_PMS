<%@ page import="com.esrc.pms.dao.DaoPj"%>
<%@ page import="com.esrc.pms.dto.DtoPj"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="pjAttachment_page">
    <div class="page_head">
		<form id="uploadForm" enctype="multipart/form-data" method="POST">
			<input type="file" id="uploadFile" name="uploadFile" />
			<input type="button" value="추가" onclick="fn_upload(primary);"/>
		</form>
    </div>
    <div class="table table_Attachment">
        <div class="table_head">
            <span></span><span>파일명</span>
        </div>
        <div class="context_wrapper_files"  id="context_wrapper_files">
        <!-- ajax로 호출 -->
        </div>
    </div>
</div>

<script>
fn_GetFiles = function(pId){
	document.getElementById('context_wrapper_files').innerHTML = '';
	$.ajax({
    	async: false,
     	url: "get_files",
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
     			fn_addFiles(value.fId, value.fileName, value.path);
              });
     	},
     	error:function(result)
     	{
     		alert("error_file_ajax");
     	}
   });
}

fn_addFiles=function(fId, FileName, path){
	var filesList ='';
	filesList +='<div class="table_sub_head">\
                <span></span>\
                <span><a href="/download/'+ fId +'">'+ FileName +'</a></span>\
                <a href="javascript:void(0)" onclick="fnFiles_delete('+fId+');">x</a>\
                <input style="display:none;" type="text" id="fId" name="fId" value="'+fId+'">\
           		</div>';
     $("#context_wrapper_files").append(filesList);
}
//
fn_upload = function (pId) {    
	   var options = {
		  url: "/upload/" + pId,
		  dataType: 'text',
		  type: 'POST',
		  success: function (result) {
		    console.log('success');
		    fn_GetFiles(pId);
		  },
		  error: function (result) {
		    console.log('error');
		  }
	}
	jQuery('#uploadForm').ajaxForm(options).submit();
}

fnFiles_delete = function(fId){
	$.ajax({
		async: false,
		url: "./delete_pjFiles",
		type: "post",
		dataType: "html",
		data:{
			fId : fId
		},
		success:function(data)
		{
			fn_GetFiles(primary); 				
		},
		error:function(data)
		{
			alert("error_ deleteFiles ajax");
		}
});
}
</script>