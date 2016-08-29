package com.esrc.pms.command;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoMembers;
import com.esrc.pms.dao.DaoPj;

public class Delete_MembersCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String mId = request.getParameter("mId");
		DaoMembers dao = new DaoMembers();
		dao.delete(mId);
		
		System.out.print("mId delete :"+mId);
	}

}
