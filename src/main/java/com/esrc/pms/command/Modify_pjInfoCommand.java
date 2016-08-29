package com.esrc.pms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoPj;

public class Modify_pjInfoCommand implements Command{

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
			
		try {
			String pId = request.getParameter("pId");
			String statement = request.getParameter("statement");
			String pjTitle_K = request.getParameter("pjTitle_K");
			String pjTitle_E = request.getParameter("pjTitle_E");
			String BTitle = request.getParameter("BTitle");
			String chief = request.getParameter("chief");
			String staff = request.getParameter("staff");
			String institution = request.getParameter("institution");
			String summary = request.getParameter("summary");
			String date_start = request.getParameter("date_start");
			String date_end = request.getParameter("date_end");
			String refer = request.getParameter("refer");
			
			String term = date_start + " ~ " + date_end;
			
			
			DaoPj dao = new DaoPj();
			dao.modify_pjInfo(pId, statement, pjTitle_K, pjTitle_E, BTitle, chief, staff, institution, summary, refer, date_start, date_end, term);
			System.out.println("modi_pj : statement : " + refer);
		} catch (Exception e) {
			System.out.println("command modify_pjInfo error");
		}
	}
}
