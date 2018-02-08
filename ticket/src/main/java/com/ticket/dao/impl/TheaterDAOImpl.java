package com.ticket.dao.impl;

import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.TheaterDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.TheaterVO;

public class TheaterDAOImpl implements TheaterDAO {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	private static final String NAMESPACE = "TheaterMapper";
	@Override
	public void insertTheater(TheaterVO theater) throws SQLException {
		sqlSession.update(NAMESPACE + ".insertTheater" , theater);
	}

	@Override
	public void updateTheater(TheaterVO theater) throws SQLException {
		sqlSession.update(NAMESPACE + ".updateTheater" , theater); 
	}

	@Override
	public void deleteTheater(int ttr_no) throws SQLException {
		sqlSession.update(NAMESPACE + ".deleteTheater", ttr_no);
	}

	@Override
	public List<TheaterVO> selectTheaterList() throws SQLException {
			List<TheaterVO> theaterList=sqlSession.selectList(NAMESPACE + ".selectTheaterList", null);
			
		return theaterList;
	}

	@Override
	public TheaterVO selectTheaterBytno(int ttr_no) throws SQLException {
			TheaterVO theater =(TheaterVO)sqlSession.selectOne(NAMESPACE + ".selectTheaterBytno",ttr_no);
					
		return theater;
	}

	@Override
	public List<TheaterVO> selectTheaterListCriteria(Criteria cri)
			throws SQLException {
			int offset =cri.getPageStart();
			int limit = cri.getPerPageNum();
			
			
			RowBounds rowBounds = new RowBounds(offset,limit);
			List<TheaterVO> theaterList = sqlSession.selectList(NAMESPACE 
					+ ".selectTheaterList", null,rowBounds);

		return theaterList;
	}

	@Override
	public void insertAttach(String fullName, int ttr_no, int file_kind) throws SQLException {

		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("fullName", fullName);
		paramMap.put("ttr_no", ttr_no);
		paramMap.put("file_kind", file_kind);
		
		
		sqlSession.update(NAMESPACE+".insertAttach",paramMap);
		
	}
	

	@Override
	public int selectMaxtno() throws SQLException {
		return (Integer)sqlSession.selectOne(NAMESPACE+".selectMaxtno",null);
	}


	
	@Override
	public List<String> selctAttachAll(int ttr_no) throws SQLException {
		List<String> files=sqlSession.selectList(NAMESPACE+".selectAttachAll", ttr_no);
		return files;
	}

	@Override
	public String[] selectAttachThum(int ttr_no) throws SQLException {
		List<String> files=sqlSession.selectList(NAMESPACE+".selectAttachThum", ttr_no);
		String[] thumFiles=new String[files.size()];
		files.toArray(thumFiles);
		return thumFiles;
	}

	

	@Override
	public String[] selectAttachSeat(int ttr_no) throws SQLException {
		List<String> files=sqlSession.selectList(NAMESPACE+".selectAttachSeat", ttr_no);
		String[] seatFiles=new String[files.size()];
		files.toArray(seatFiles);
		return seatFiles;
	}

	@Override
	public String[] selectAttachFile(int ttr_no) throws SQLException {
		List<String> files=sqlSession.selectList(NAMESPACE+".selectAttachFile", ttr_no);
		String[] tFiles=new String[files.size()];
		files.toArray(tFiles);
		return tFiles;
	}
	@Override
	public int deleteAttachAll(int ttr_no) throws SQLException {	
		int files = sqlSession.update(NAMESPACE+".deleteAttachAll",ttr_no);
		return files;
	}
	

	

	/*@Override
	public void replaceAttach(String fullName, int ttr_no) throws SQLException {
			Map<String,Object> paraMap=new HashMap<String,Object>();
			
			paraMap.put("fullname", fullName);
			paraMap.put("ttr_no", ttr_no);
			
			sqlSession.update(NAMESPACE+".replaceAttach",paraMap);
	}
sss*/
}
