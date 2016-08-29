package com.esrc.pms.command;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoPj;
import com.esrc.pms.dto.DtoPj;


public class New_pjCommand implements Command {
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String statement = request.getParameter("statement");
		String pjTitle_K = request.getParameter("pjTitle_K");
		String chief = request.getParameter("chief");
		String registrant = request.getParameter("registrant");
		String date_start = request.getParameter("date_start");
		String date_end = request.getParameter("date_end");
		
		String term = date_start + " ~ " + date_end;
		
		DaoPj dao = new DaoPj();
		dao.write(statement, pjTitle_K, chief, registrant,date_start, date_end,term );
	}
}
