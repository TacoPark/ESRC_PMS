package com.esrc.pms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoPj;

public class Modify_pjContentCommand implements Command{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
			
		try {
			String pId = request.getParameter("pId");
			String contents = request.getParameter("contents");
			String detail_contents = request.getParameter("detail_contents");

			System.out.println(pId + "command 넘어왓나");
			DaoPj dao = new DaoPj();
			dao.modify_pjContent(pId,contents,detail_contents);
		} catch (Exception e) {
			System.out.println("controller modify_pjInfo error");
		}
	}
}
