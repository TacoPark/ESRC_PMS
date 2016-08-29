package com.esrc.pms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoPj;

public class Delete_pjCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		System.out.println("delete command");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pId = request.getParameter("pId");
		DaoPj dao = new DaoPj();
		dao.delete(pId);
		
	}

}
