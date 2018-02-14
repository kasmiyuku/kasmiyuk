package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.ReplyDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.ReplyVO;
import com.ticket.service.ReplyService;

public class ReplyServiceImpl implements ReplyService {

		private ReplyDAO replyDAO;
		public void setReplyDAO(ReplyDAO replyDAO){
			this.replyDAO=replyDAO;
		}
	@Override
	public List<ReplyVO> getReplyList(int ttr_no) throws SQLException {
		return replyDAO.selectReplyList(ttr_no);
	}

	@Override
	public void addReply(ReplyVO reply) throws SQLException {
			replyDAO.insertReply(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
			replyDAO.updateReply(reply);
	}

	@Override
	public void removeReply(int tr_rno) throws SQLException {
			replyDAO.deleteReply(tr_rno);
	}

	@Override
	public List<ReplyVO> getReplyListPage(int ttr_no, Criteria cri)
			throws SQLException {
		return replyDAO.selsctReplyListPage(ttr_no, cri);
	}

}
