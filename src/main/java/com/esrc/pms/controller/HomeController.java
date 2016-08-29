package com.esrc.pms.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.esrc.pms.command.Command;
import com.esrc.pms.command.CommandLogin;
import com.esrc.pms.command.Delete_FilesCommand;
import com.esrc.pms.command.Delete_MembersCommand;
import com.esrc.pms.command.Delete_pjCommand;
import com.esrc.pms.command.Insert_pjMembersCommand;
import com.esrc.pms.command.List_pjCommand;
import com.esrc.pms.command.Modify_pjContentCommand;
import com.esrc.pms.command.Modify_pjInfoCommand;
import com.esrc.pms.command.PjContentCommand;
import com.esrc.pms.command.PjInfoCommand;
import com.esrc.pms.dao.DaoFile;
import com.esrc.pms.dao.DaoLogin;
import com.esrc.pms.dao.DaoMembers;
import com.esrc.pms.dao.DaoPj;
import com.esrc.pms.dto.DtoFiles;
import com.esrc.pms.dto.DtoLogin;
import com.esrc.pms.dto.DtoMembers;
import com.esrc.pms.dto.DtoPj;
import com.esrc.pms.util.Constant;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	Command command;
	private JdbcTemplate template;
	private DaoLogin _DaoLogin;

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "login";
	}

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {

		model.addAttribute("request", request);
		command = new CommandLogin();
		command.execute(model);

		return "login";
	}

	// 로그인 ajax
	@RequestMapping(value = { "/loginAjax" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public ModelAndView loginAjax(HttpServletRequest req, HttpServletResponse res,
			@RequestParam HashMap<String, Object> params) throws Exception {
		ModelAndView mav = new ModelAndView("jsonViewer");

		try {
			String sUserID = req.getParameter("UserID");
			String sPassword = req.getParameter("Password");
			String sPID = "";

			DaoLogin dao = new DaoLogin();
			DtoLogin dto = dao.CheckLogin(sUserID, sPassword);

			if (dto != null) {
				sPID = dto.getsPID();
				req.getSession().setMaxInactiveInterval(3600);
				req.getSession().setAttribute("LID", sPID);
			}
		} catch (Exception e) {

		}

		// mav.setViewName("jsonView");
		mav.setViewName("/loginAjax");

		return mav;
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}

	// 초기화면
	@RequestMapping(value = "/pms", method = RequestMethod.GET)
	public String main(Model model) {

		command = new List_pjCommand();
		command.execute(model);

		return "pms";
	}

	// pId 다시 호출하기
	@ResponseBody
	@RequestMapping(value = "/pms_pId", method = RequestMethod.GET)
	public String return_pId(HttpServletRequest request, Model model) {
		String pId = "-1";
		try {
			pId = request.getParameter("pId");
		} catch (Exception e) {
		}
		return pId;
	}

	// 새로운 프로젝트 추가
	@ResponseBody
	@RequestMapping(value = "/new_pj", method = RequestMethod.POST)
	public String new_pj(HttpServletRequest request) {
		String result = "[";
		try {
			DaoPj dao = new DaoPj();

			String statement = request.getParameter("statement_new");
			String pjTitle_K = request.getParameter("pjTitle_K");
			String chief = request.getParameter("chief");
			String registrant = request.getParameter("registrant");
			String date_start = request.getParameter("date_start");
			String date_end = request.getParameter("date_end");

			String term = date_start + " ~ " + date_end;

			dao.write(statement, pjTitle_K, chief, registrant, date_start, date_end, term);
			ArrayList<DtoPj> dtos = dao.plist();
			for (int i = 0; i < dtos.size(); i++) {
				int pId = dtos.get(i).getpId();
				String Title = dtos.get(i).getpjTitle_K();
				String Statement = dtos.get(i).getStatement();
				String Chief = dtos.get(i).getChief();
				String Term = dtos.get(i).getTerm();

				result += " { ";
				result += " \"pId\" : \"" + pId + "\" ";
				result += ",";
				result += " \"Title\"  :  \"" + Title + "\"";
				result += ",";
				result += " \"Statement\"  :  \"" + Statement + "\"";
				result += ",";
				result += " \"Chief\"  :  \"" + Chief + "\"";
				result += ",";
				result += " \"Term\"  :  \"" + Term + " \"";
				result += "},";
			}
			result += "]";

			System.out.println("new_pj : statement : " + statement);
			// 마지막, 삭제
			result = result.replaceFirst(",]", "]");
			result = URLEncoder.encode(result, "UTF-8");
		} catch (Exception e) {
			System.out.println("controller error_new");
		}
		return result;
	}

	// 프로젝트 삭제
	@RequestMapping("/delete_pj")
	public String delete_pj(HttpServletRequest request, Model model) {
		System.out.println("delete_pj() controller");
		model.addAttribute("request", request);
		command = new Delete_pjCommand();
		command.execute(model);
		return "notice";
	}

	// 리스트 내용 호출
	@ResponseBody
	@RequestMapping(value = "/get_data", method = RequestMethod.POST)
	public String get_data(HttpServletRequest request) {
		String result = "[";
		try {
			DaoPj dao = new DaoPj();
			ArrayList<DtoPj> dtos = dao.plist();

			for (int i = 0; i < dtos.size(); i++) {
				int pId = dtos.get(i).getpId();
				String Title = dtos.get(i).getpjTitle_K();
				String Statement = dtos.get(i).getStatement();
				String Chief = dtos.get(i).getChief();
				String Term = dtos.get(i).getTerm();

				result += "{";
				result += "\"pId\":\"" + pId + "\"";
				result += ",";
				result += "\"Title\":\"" + Title + "\"";
				result += ",";
				result += "\"Statement\":\"" + Statement + "\"";
				result += ",";
				result += "\"Chief\":\"" + Chief + "\"";
				result += ",";
				result += "\"Term\":\"" + Term + " \"";
				result += "},";
			}
			result += "]";
			// 마지막 , 삭제
			result = result.replaceFirst(",]", "]");
			result = URLEncoder.encode(result, "UTF-8");
		} catch (Exception e) {
			System.out.println("controller error_new");
		}
		return result;
	}

	// 과제내용 화면 불러오기
	@RequestMapping(value = "/read_pjInfo", method = RequestMethod.POST)
	public String read_pjInfo(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new PjInfoCommand();
		command.execute(model);
		return "read_pjInfo";
	}

	// 과제정보 수정
	@RequestMapping(value = "/modify_pjInfo", method = RequestMethod.POST)
	public String modify_pjInfo(HttpServletRequest request, Model model) {
		System.out.println("modify_pjInfo controller");
		model.addAttribute("request", request);
		command = new Modify_pjInfoCommand();
		command.execute(model);

		return "read_pjInfo";
	}

	// 연구내용 화면 불러오기
	@RequestMapping(value = "/read_pjContent", method = RequestMethod.POST)
	public String read_pjContent(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new PjContentCommand();
		command.execute(model);
		return "read_pjContent";
	}

	// 연구내용 수정
	@RequestMapping(value = "/modify_pjContent", method = RequestMethod.POST)
	public String modify_pjContent(HttpServletRequest request, Model model) {

		model.addAttribute("request", request);
		command = new Modify_pjContentCommand();
		command.execute(model);

		return "modify_pjContent";
	}

	// 참여연구원 화면 불러오기
	@RequestMapping(value = "/read_pjMembers", method = RequestMethod.POST)
	public String read_pjMembers(HttpServletRequest request, Model model) {
		return "read_pjMembers";
	}
	
	// 참여연구원 리스트 호출
	@ResponseBody
	@RequestMapping(value = "/get_members", method = RequestMethod.POST)
	public String get_members(HttpServletRequest request) {
		String result = "[";
		try {
			DaoMembers dao = new DaoMembers();
			String pId = request.getParameter("pId");
			ArrayList<DtoMembers> dtos = dao.PjMembers(pId);

			for (int i = 0; i < dtos.size(); i++) {

				int mId = dtos.get(i).getmId();
				String name = dtos.get(i).getName();
				String role = dtos.get(i).getRole();
				String belong = dtos.get(i).getBelong();
				String phoneNum = dtos.get(i).getPhoneNum();
				String email = dtos.get(i).getEmail();
				String joinTerm = dtos.get(i).getJoinTerm();

				result += "{";
				result += "\"mId\":\"" + mId + "\"";
				result += ",";
				result += "\"name\":\"" + name + "\"";
				result += ",";
				result += "\"role\":\"" + role + "\"";
				result += ",";
				result += "\"belong\":\"" + belong + "\"";
				result += ",";
				result += "\"phoneNum\":\"" + phoneNum + "\"";
				result += ",";
				result += "\"email\":\"" + email + " \"";
				result += ",";
				result += "\"joinTerm\":\"" + joinTerm + " \"";
				result += "},";
			}
			result += "]";
			// 마지막 , 삭제
			result = result.replaceFirst(",]", "]");
			result = URLEncoder.encode(result, "UTF-8");
		} catch (Exception e) {
			System.out.println("controller error_new");
		}
		return result;
	}
	
	// 참여연구원 추가
	// 404error로인해 아무값이나 리턴
	@ResponseBody
	@RequestMapping(value = "/insert_members", method = RequestMethod.POST)
	public String insert_members(HttpServletRequest request, Model model) {
		
		DaoMembers dao = new DaoMembers();
		String pId = request.getParameter("pId");
		String name = request.getParameter("name_mem");
		
		ArrayList<DtoMembers> dtos = dao.PjMembers(pId);
		
		for(int i=0;i<dtos.size();i++){
			if(name.equals(dtos.get(i).getName())){
				return "error";
			}
		}
		model.addAttribute("request", request);
		command = new Insert_pjMembersCommand();
		command.execute(model);
		return "success insert pjMembers";
		
	}
	
	// 참여연구원 삭제
	@ResponseBody
	@RequestMapping(value = "/delete_pjMembers", method = RequestMethod.POST)
	public String delete_pjMembers(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new Delete_MembersCommand();
		command.execute(model);
		return "delete_pjMembers";
	}

	// 참여연구원 삭제
		@ResponseBody
		@RequestMapping(value = "/delete_pjFiles", method = RequestMethod.POST)
		public String delete_pjFiles(HttpServletRequest request, Model model) {
			model.addAttribute("request", request);
			command = new Delete_FilesCommand();
			command.execute(model);
			return "delete_pjFiles";
		}
	
	// 첨부파일 불러오기
	@RequestMapping(value = "/read_pjAttachment", method = RequestMethod.POST)
	public String read_pjAttachment(HttpServletRequest request, Model model) {
		return "read_pjAttachment";
	}
	
	// 첨부파일 리스트 호출
	@ResponseBody
	@RequestMapping(value = "/get_files", method = RequestMethod.POST)
	public String get_files(HttpServletRequest request) {
		String result = "[";
		try {
			DaoFile dao = new DaoFile();
			String pId = request.getParameter("pId");
			ArrayList<DtoFiles> dtos = dao.PjFiles(pId);
			for (int i = 0; i < dtos.size(); i++) {

				int fId = dtos.get(i).getfId();
				String fileName = dtos.get(i).getFileName();
				String path = dtos.get(i).getFilePath();
				result += "{";
				result += "\"fId\":\"" + fId + "\"";
				result += ",";
				result += "\"fileName\":\"" + fileName + "\"";
				result += ",";
				result += "\"path\":\"" + path + "\"";
				result += "},";
			}
			result += "]";
			// 마지막 , 삭제
			result = result.replaceFirst(",]", "]");
			result = URLEncoder.encode(result, "UTF-8");
		} catch (Exception e) {
			System.out.println("controller error_getfile");
		}
		return result;
	}
	
	// 파일 upload
	@RequestMapping(value = "/upload/{pId}", method = RequestMethod.POST)
	@ResponseBody
	public void upload(MultipartHttpServletRequest request, @PathVariable("pId") String pId) {
		System.out.println("upload controller");
		Map<String, MultipartFile> files = request.getFileMap();
		CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("uploadFile");
		
		// 경로
		String originalFilename = cmf.getOriginalFilename();
		System.out.println("originalFilename : after" +originalFilename);
		
		int Idx = originalFilename .lastIndexOf(".");
		String ext_originalFilename = originalFilename.substring(0, Idx );
		String ext = originalFilename.substring(Idx + 1);
		
		String changedFileName = ext_originalFilename + "_" + (new Date()).getTime();
		
		String path = "C:\\Users\\esrc_svr\\Desktop\\COC\\Files\\" + changedFileName +"."+ ext;
		//String path = "C:\\Users\\Joshua\\Desktop\\temp\\" + changedFileName +"."+ ext;
		
		DaoFile dao = new DaoFile();
		dao.insert_pjFile(pId, originalFilename, path);
		
		File file = new File(path);
		// 파일 업로드 처리 완료.
		try {
			cmf.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 파일 download
	@RequestMapping(value = "/download/{fId}", method = RequestMethod.GET)
	@ResponseBody
	public void download(@PathVariable("fId") String fId,
			HttpServletResponse response) {
		System.out.println("down controller");
		DaoFile dao = new DaoFile();
		DtoFiles pjFIlesdto = dao.PjFIlesdto(fId);

		String storedFileName = pjFIlesdto.getFilePath();
		String originalFileName = pjFIlesdto.getFileName();

		try {
			byte[] fileByte = FileUtils.readFileToByteArray(new File(storedFileName));
			response.setContentType("application/octet-stream");
			response.setContentLength(fileByte.length);
			response.setHeader("Content-Disposition",
					"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.getOutputStream().write(fileByte);

			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
