package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.ReplyVO;

public interface ReplyService {

		List<ReplyVO> getReplyList(int ttr_no)throws SQLException;
		
		void addReply(ReplyVO reply) throws SQLException;
		void modifyReply(ReplyVO reply)throws SQLException;
		void removeReply(int tr_rno)throws SQLException;
		
		List<ReplyVO> getReplyListPage(int ttr_no, Criteria cri)throws SQLException;
	
}
