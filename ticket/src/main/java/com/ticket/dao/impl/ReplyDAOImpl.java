package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.ReplyDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	private String namespace="TheaterMapper";
	
	
	public void setNamespace(String namespace){
		this.namespace=namespace;
	}
	
	@Override
	public List<ReplyVO> selectReplyList(int ttr_no) throws SQLException {
			List<ReplyVO> replyList=sqlSession.selectList(namespace+".selectReplyList",ttr_no);
		return replyList;
	}

	@Override
	public void insertReply(ReplyVO reply) throws SQLException {
			sqlSession.update(namespace+".insertReply",reply);
	}

	@Override
	public void updateReply(ReplyVO reply) throws SQLException {
			sqlSession.update(namespace+".updateReply",reply);	
	}

	@Override
	public void deleteReply(int ttr_no) throws SQLException {
			sqlSession.update(namespace+".deleteReply",ttr_no);
	}

	@Override
	public List<ReplyVO> selsctReplyListPage(int ttr_no, Criteria cri)
			throws SQLException {
			int offset=cri.getPageStart();
			int limit=cri.getPerPageNum();
			RowBounds rowbounds=new RowBounds(offset,limit);
			
			List<ReplyVO> replyList=sqlSession.selectList(namespace+".selectReplyListPage",ttr_no,rowbounds);
		
		return replyList;
	}

}
