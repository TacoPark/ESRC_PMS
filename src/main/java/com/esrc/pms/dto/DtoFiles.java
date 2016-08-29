package com.esrc.pms.dto;
/**
 * @author Joshua
 *
 */
public class DtoFiles{
	int fId;
	int pId;
	String fileName;
	String filePath;
	public DtoFiles() {
		// TODO Auto-generated constructor stub
	}
	
	public DtoFiles(int fId,
				int pId,
				String fileName,
				String filePath) {
		// TODO Auto-generated constructor stub
		this.fId = fId;
		this.pId = pId;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
