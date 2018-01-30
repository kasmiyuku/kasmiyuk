package com.ticket.domain;

public class TheaterVOSs {
	private String file_id;
	private String file_name;
	private int ttr_no;
	
	public TheaterVOSs(){}
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public int getTtr_no() {
		return ttr_no;
	}
	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}
	public TheaterVOSs(String file_id, String file_name, int ttr_no) {
		super();
		this.file_id = file_id;
		this.file_name = file_name;
		this.ttr_no = ttr_no;
	}
	@Override
	public String toString() {
		return "TheaterVOSs [file_id=" + file_id + ", file_name=" + file_name
				+ ", ttr_no=" + ttr_no + "]";
	}
	
}
