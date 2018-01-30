package com.ticket.domain;

import java.util.Arrays;
import java.util.Date;

public class TheaterVO {
	private int ttr_no;
	private String com_id;
	private String ttr_cat;
	private String ttr_title;
	private Date ttr_date;
	private Date ttr_sdate;
	private Date ttr_edate;
	private String ttr_place;
	private String ttr_time;
	private String ttr_alert;
	private String ttr_content;
	
	private String ttr_ss;
	
	private String[] files;
	
	public TheaterVO(){}

	public TheaterVO(int ttr_no, String com_id, String ttr_cat,
			String ttr_title, Date ttr_date, Date ttr_sdate, Date ttr_edate,
			String ttr_place, String ttr_time, String ttr_alert,
			String ttr_content, String ttr_ss, String[] files) {
		super();
		this.ttr_no = ttr_no;
		this.com_id = com_id;
		this.ttr_cat = ttr_cat;
		this.ttr_title = ttr_title;
		this.ttr_date = ttr_date;
		this.ttr_sdate = ttr_sdate;
		this.ttr_edate = ttr_edate;
		this.ttr_place = ttr_place;
		this.ttr_time = ttr_time;
		this.ttr_alert = ttr_alert;
		this.ttr_content = ttr_content;
		this.ttr_ss = ttr_ss;
		this.files = files;
	}

	public int getTtr_no() {
		return ttr_no;
	}

	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getTtr_cat() {
		return ttr_cat;
	}

	public void setTtr_cat(String ttr_cat) {
		this.ttr_cat = ttr_cat;
	}

	public String getTtr_title() {
		return ttr_title;
	}

	public void setTtr_title(String ttr_title) {
		this.ttr_title = ttr_title;
	}

	public Date getTtr_date() {
		return ttr_date;
	}

	public void setTtr_date(Date ttr_date) {
		this.ttr_date = ttr_date;
	}

	public Date getTtr_sdate() {
		return ttr_sdate;
	}

	public void setTtr_sdate(Date ttr_sdate) {
		this.ttr_sdate = ttr_sdate;
	}

	public Date getTtr_edate() {
		return ttr_edate;
	}

	public void setTtr_edate(Date ttr_edate) {
		this.ttr_edate = ttr_edate;
	}

	public String getTtr_place() {
		return ttr_place;
	}

	public void setTtr_place(String ttr_place) {
		this.ttr_place = ttr_place;
	}

	public String getTtr_time() {
		return ttr_time;
	}

	public void setTtr_time(String ttr_time) {
		this.ttr_time = ttr_time;
	}

	public String getTtr_alert() {
		return ttr_alert;
	}

	public void setTtr_alert(String ttr_alert) {
		this.ttr_alert = ttr_alert;
	}

	public String getTtr_content() {
		return ttr_content;
	}

	public void setTtr_content(String ttr_content) {
		this.ttr_content = ttr_content;
	}

	public String getTtr_ss() {
		return ttr_ss;
	}

	public void setTtr_ss(String ttr_ss) {
		this.ttr_ss = ttr_ss;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	
	@Override
	public String toString() {
		return "TheaterVO [ttr_no=" + ttr_no + ", com_id=" + com_id
				+ ", ttr_cat=" + ttr_cat + ", ttr_title=" + ttr_title
				+ ", ttr_date=" + ttr_date + ", ttr_sdate=" + ttr_sdate
				+ ", ttr_edate=" + ttr_edate + ", ttr_place=" + ttr_place
				+ ", ttr_time=" + ttr_time + ", ttr_alert=" + ttr_alert
				+ ", ttr_content=" + ttr_content + ", ttr_ss=" + ttr_ss
				+ ", files=" + Arrays.toString(files) + "]";
	}



	
	
}
