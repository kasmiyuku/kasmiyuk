package com.ticket.domain;

import java.util.Date;

public class ReplyVO {
	private int tr_rno;
	private String content;
	private Date tr_date;
	private Date tr_update;
	private String mem_id;
	private int ttr_no;
	
	
	public ReplyVO(){}
	public ReplyVO(int tr_rno, String content, Date tr_date,
			Date tr_update, String mem_id, int ttr_no) {
		super();
		this.tr_rno = tr_rno;
		this.content = content;
		this.tr_date = tr_date;
		this.tr_update = tr_update;
		this.mem_id = mem_id;
		this.ttr_no = ttr_no;
	}
	public int getTr_rno() {
		return tr_rno;
	}
	public void setTr_rno(int tr_rno) {
		this.tr_rno = tr_rno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTr_date() {
		return tr_date;
	}
	public void setTr_date(Date tr_date) {
		this.tr_date = tr_date;
	}
	public Date getTr_update() {
		return tr_update;
	}
	public void setTr_update(Date tr_update) {
		this.tr_update = tr_update;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getTtr_no() {
		return ttr_no;
	}
	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}
	@Override
	public String toString() {
		return "ReplyVO [tr_rno=" + tr_rno + ", content=" + content
				+ ", tr_date=" + tr_date + ", tr_update=" + tr_update
				+ ", mem_id=" + mem_id + ", ttr_no=" + ttr_no + "]";
	}
	
	
	
}
