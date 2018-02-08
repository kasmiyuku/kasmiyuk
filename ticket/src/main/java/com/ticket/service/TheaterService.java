package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.TheaterVO;


public interface TheaterService {

	void createTheater(TheaterVO theater) throws SQLException;

	void updateTheater(TheaterVO theater) throws SQLException;

	void deleteTheater(int ttr_no) throws SQLException;

	List<TheaterVO> readTheaterList() throws SQLException;

	TheaterVO readTheaterBytno(int ttr_no) throws SQLException;

	List<TheaterVO> readTheaterListCriteria(Criteria cri) 
			throws SQLException;
	
	
	List<String[]> getAttach(int ttr_no)throws SQLException;

	
}
