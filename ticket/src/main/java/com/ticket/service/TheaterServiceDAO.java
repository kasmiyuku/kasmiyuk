package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
import com.ticket.domain.TheaterVO;


public interface TheaterServiceDAO {

	void createTheater(TheaterVO theater) throws SQLException;

	void updateTheater(TheaterVO theater) throws SQLException;

	void deleteTheater(int ttr_no) throws SQLException;

	List<TheaterVO> readTheaterList() throws SQLException;

	TheaterVO readTheaterBytno(int ttr_no) throws SQLException;
	
	Seatinfo readseatbyseat_id(String seat_id) throws SQLException;
	List<Seatinfo> readseatbyttr_no(int ttr_no) throws SQLException;
	List<Seatinfo> readseat() throws SQLException;

	List<TheaterVO> readTheaterListCriteria(Criteria cri) 
			throws SQLException;
	int readSearchtheaterListCount(SearchCriteria cri)throws SQLException;	
	
	List<TheaterVO> readSearchTheaterList(SearchCriteria cri)throws SQLException;
	
	List<String[]> getAttach(int ttr_no)throws SQLException;

	
}
