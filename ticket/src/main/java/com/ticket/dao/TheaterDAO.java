package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.TheaterVO;


public interface TheaterDAO {

	void insertTheater(TheaterVO theater) throws SQLException;
	//업데이트
	void updateTheater(TheaterVO theater) throws SQLException;
	//삭제
	void deleteTheater(int ttr_no) throws SQLException;
	//리스트목록
	List<TheaterVO> selectTheaterList() throws SQLException;

	TheaterVO selectTheaterBytno(int ttr_no) throws SQLException;

	
	List<TheaterVO> selectTheaterListCriteria(Criteria cri) throws SQLException;

	void insertAttach(String fullName,int ttr_no)throws SQLException;
	int selectMaxtno()throws SQLException;
	List<String> selctAttach(int ttr_no)throws SQLException;
	void deleteAttach(int ttr_no)throws SQLException;
	void replaceAttach(String fullName,int ttr_no)throws SQLException;
}
