package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.ReplyVO;

public interface ReplyDAO {
	
	List<ReplyVO> selectReplyList(int ttr_no)throws SQLException;
	
	void insertReply(ReplyVO reply)throws SQLException;
	void updateReply(ReplyVO reply)throws SQLException;
	void deleteReply(int ttr_no)throws SQLException;
	
	List<ReplyVO> selsctReplyListPage(int ttr_no,Criteria cri)throws SQLException;
	
}
