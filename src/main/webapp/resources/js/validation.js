//폼 데이터 유효성 검사
dataValidation = function(selector) {
	
	//프로젝트 등록 유효성 검사
	if(document.getElementById("new_pjinfo") == document.getElementById(selector)) {
		//프로젝트 등록 관련 변수
		var title = $("#pjTitle_K"),
			chief = $("#chief"),
			date_start = $("#date_start"),
			date_end = $("#date_end");
		
		var reg_Date = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		
		if(title.val() == "") {	//과제명 입력했는지 검사
			alert("과제명을 입력해주세요~");
			title.focus();
			return false;
		}
		else if(chief.val() == "") {	//책임 연구원 입력했는지 검사
			alert("책임자를 입력해주세요~");
			chief.focus();
			return false;
		}
		else if(reg_Date.test(date_start.val()) != true | reg_Date.test(date_end.val()) != true) {
			alert("날짜 형식이 잘못됐습니다");
			date_start.focus();
			return false;
		}
		else if(dateCheck(date_start.val(), date_end.val()) != true) {
			alert('마지막날짜는 시작날짜 뒤로 선택하십시오.');
			date_start.focus();
			return false;
		}		
		return true;
		
	}
	
	
	//참여연구원 등록 유효성 검사
	else if(document.getElementById("new_member") == document.getElementById(selector)) {
		//참여연구원 등록 관련 변수
		var name = $("#name_mem"),
			belong = $("#belong_mem"),
			role = $("#role_mem"),
			tel = $("#phoneNum_mem"),
			email = $("#email_mem"),
			date_start = $("#date_start_mem"),
			date_end = $("#date_end_mem");
			
		var reg_Tel = /^\d{3}-\d{3,4}-\d{4}$/;
		var reg_Email = /^([\w\.-]+)@([a-z\d\.-]+)\.([a-z\.]{2,6})$/;
		var reg_Date = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
		
		if(name.val() == "") {
			alert("이름 입력해주세요");
			return false;
		}
		else if(belong.val() == "") {
			alert("소속 입력해주세요");
			belong.focus();
			return false;
		}
		else if(role.val() == "") {
			alert("역할 입력해주세요");
			role.focus();
			return false;
		}
		else if(tel.val() != "" && reg_Tel.test(tel.val()) != true) {
			alert("전화번호 형식이 잘못됐습니다.");
			tel.focus();
			return false;
		}
		else if(email.val() != "" && reg_Email.test(email.val()) != true) {
			alert("이메일 형식이 잘못됐습니다.");
			email.focus();
			return false;
		}
		else if(date_start.val() == "" && date_end.val() =="") {
			//날짜를 입력 안하는 경우, '기간없음'으로 표시하기 ---> 유섭이
		}
		else if(reg_Date.test(date_start.val()) != true || reg_Date.test(date_end.val()) != true) {
			alert("날짜 형식이 잘못됐습니다.");
			date_start.focus();
			return false;
		}
		else if(dateCheck(date_start.val(), date_end.val()) != true) {
			alert('마지막날짜는 시작날짜 뒤로 선택하십시오.');
			date_start.focus();
			return false;
		}
		return true;
		
	}
	
}
//날짜 유효성 검사(시작날짜, 끝날짜 범위 체크)
dateCheck = function(sDate, eDate) {
	var s_date  = sDate;//범위 시작날짜
	var e_date  = eDate;//범위 마지막날짜
	var start_dates = s_date.split("-");//날짜값 계산을 위해 -로 잘라서 배열로 만듦
	var end_dates = e_date.split("-");//날짜값 계산을 위해 -로 잘라서 배열로 만듦
	var date1 = new Date(start_dates[0], start_dates[1], start_dates[2]).valueOf();
	var date2 = new Date(end_dates[0], end_dates[1], end_dates[2]).valueOf();

	if ((date2 - date1) < 0) {	    
	    return false;
	}
	return true;
}