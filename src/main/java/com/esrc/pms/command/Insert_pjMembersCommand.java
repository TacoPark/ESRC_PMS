package com.esrc.pms.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoMembers;
import com.esrc.pms.dto.DtoMembers;

public class Insert_pjMembersCommand implements Command{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
			
		try {
			String pId = request.getParameter("pId");
			String name = request.getParameter("name_mem");
			String belong = request.getParameter("belong_mem");
			String role = request.getParameter("role_mem");
			String phoneNum = request.getParameter("phoneNum_mem");
			String email = request.getParameter("email_mem");
			String date_start = request.getParameter("date_start_mem");
			String date_end = request.getParameter("date_end_mem");
			
			String term = date_start + " ~ " + date_end;
			
			if(date_start=="" && date_end==""){
				date_start = null;
				date_end = null;
				term = "기간 없음";
			}
			
			DaoMembers dao = new DaoMembers();
			dao.insert_pjMembers(pId, name, belong, role, phoneNum, email, date_start, date_end, term);
			
		} catch (Exception e) {
			System.out.println("Command insert into pjMembers error");
		}
	}
}
