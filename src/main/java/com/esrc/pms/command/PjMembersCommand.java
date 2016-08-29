package com.esrc.pms.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoMembers;
import com.esrc.pms.dto.DtoMembers;


public class PjMembersCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String pId = request.getParameter("pId");
		
		DaoMembers dao = new DaoMembers();
		DtoMembers dto = dao.PjMembersdto(pId);
		
		model.addAttribute("pjMembers", dto);
	}

}