package com.esrc.pms.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.esrc.pms.dao.DaoPj;
import com.esrc.pms.dto.DtoPj;


public class List_pjCommand implements Command {
	@Override
	public void execute(Model model) {
		System.out.println("List_command");
		DaoPj dao = new DaoPj();
		ArrayList<DtoPj> dtos =  dao.plist();
		
		model.addAttribute("list", dtos);
	}
}