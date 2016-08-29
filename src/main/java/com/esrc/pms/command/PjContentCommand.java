package com.esrc.pms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoPj;
import com.esrc.pms.dto.DtoPj;



public class PjContentCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String pId = request.getParameter("pId");
		
		DaoPj dao = new DaoPj();
		DtoPj dto = dao.pjContent(pId);
		
		model.addAttribute("pjContent", dto);
		
	}

}
