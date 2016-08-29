package com.esrc.pms.command;

import org.springframework.ui.Model;

public interface Command {
	
	public void execute(Model model);
	
}