package com.esrc.pms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoFile;
import com.esrc.pms.dao.DaoMembers;

public class Delete_FilesCommand implements Command {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
				Map<String, Object> map = model.asMap();
				HttpServletRequest request = (HttpServletRequest) map.get("request");
				
				String fId = request.getParameter("fId");
				DaoFile dao = new DaoFile();
				dao.fileDelete(fId);
				
				System.out.print("Command fId : " + fId );
	}
}
