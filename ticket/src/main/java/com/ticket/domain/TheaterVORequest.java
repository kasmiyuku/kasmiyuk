package com.ticket.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class TheaterVORequest {
	private int ttr_no;
	private String com_id;
	private String ttr_cat;
	private String ttr_title;
	private String ttr_date;
	private String ttr_sdate;
	private String ttr_edate;
	private String ttr_place;
	private String ttr_time;
	private String ttr_alert;
	private String ttr_content;
	
	private String[] thumFiles; // 썸네일 이미지
	private String[] seatFiles; // 좌석배치 이미지
	private String[] files; //첨부파일
	
	private String ttr_ss;

	
	
	public String[] getThumFiles() {
		return thumFiles;
	}

	public void setThumFiles(String[] thumFiles) {
		this.thumFiles = thumFiles;
	}

	public String[] getSeatFiles() {
		return seatFiles;
	}

	public void setSeatFiles(String[] seatFiles) {
		this.seatFiles = seatFiles;
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

	public String getTtr_date() {
		return ttr_date;
	}

	public void setTtr_date(String ttr_date) {
		this.ttr_date = ttr_date;
	}

	public String getTtr_sdate() {
		return ttr_sdate;
	}

	public void setTtr_sdate(String ttr_sdate) {
		this.ttr_sdate = ttr_sdate;
	}

	public String getTtr_edate() {
		return ttr_edate;
	}

	public void setTtr_edate(String ttr_edate) {
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

	
	

	@Override
	public String toString() {
		return "TheaterVORequest [ttr_no=" + ttr_no + ", com_id=" + com_id
				+ ", ttr_cat=" + ttr_cat + ", ttr_title=" + ttr_title
				+ ", ttr_date=" + ttr_date + ", ttr_sdate=" + ttr_sdate
				+ ", ttr_edate=" + ttr_edate + ", ttr_place=" + ttr_place
				+ ", ttr_time=" + ttr_time + ", ttr_alert=" + ttr_alert
				+ ", ttr_content=" + ttr_content + ", thumFiles="
				+ Arrays.toString(thumFiles) + ", seatFiles="
				+ Arrays.toString(seatFiles) + ", files="
				+ Arrays.toString(files) + ", ttr_ss=" + ttr_ss + "]";
	}

	public TheaterVO toTheaterVO() {
		TheaterVO tv = new TheaterVO();
		tv.setTtr_no(ttr_no);
		tv.setCom_id(com_id);
		tv.setTtr_cat(ttr_cat);
		tv.setTtr_title(ttr_title);
		tv.setTtr_place(ttr_place);
		tv.setTtr_time(ttr_time);
		tv.setTtr_alert(ttr_alert);
		tv.setTtr_content(ttr_content);
		tv.setFiles(files);
		tv.setTtr_ss(ttr_ss);
		tv.setSeatFiles(seatFiles);
		tv.setThumFiles(thumFiles);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
/*			tv.setTtr_date(format.parse(ttr_date));*/
			tv.setTtr_sdate(format.parse(ttr_sdate));
			tv.setTtr_edate(format.parse(ttr_edate));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		return tv;
	}
}
