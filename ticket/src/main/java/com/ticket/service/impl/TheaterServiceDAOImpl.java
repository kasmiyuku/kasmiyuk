package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;





import com.ticket.dao.TheaterDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.TheaterVO;
import com.ticket.service.TheaterServiceDAO;

public class TheaterServiceDAOImpl implements TheaterServiceDAO {

	private TheaterDAO theaterDAO;
	public void setTheaterDAO(TheaterDAO theaterDAO){
		this.theaterDAO=theaterDAO;
	}
	
	
	@Override
	public void createTheater(TheaterVO theater) throws SQLException {
		theaterDAO.insertTheater(theater);
		
		String[] files=theater.getFiles();
		
		int ttr_no=theaterDAO.selectMaxtno();
		
		if(files==null) return;
		for(String fileName:files){
			theaterDAO.insertAttach(fileName, ttr_no);
		}
		
	}

	@Override
	public void updateTheater(TheaterVO theater) throws SQLException {
		theaterDAO.updateTheater(theater);
		int ttr_no=theater.getTtr_no();
		theaterDAO.deleteAttach(ttr_no);
				
		String[] files=theater.getFiles();
		if(files==null) {return;}
		for(String fileName:files){
			theaterDAO.insertAttach(fileName, ttr_no);
		}
	}

	@Override
	public void deleteTheater(int ttr_no) throws SQLException {
		theaterDAO.deleteTheater(ttr_no);
		theaterDAO.deleteAttach(ttr_no);
	}

	@Override
	public List<TheaterVO> readTheaterList() throws SQLException {
			List<TheaterVO> theaterList=theaterDAO.selectTheaterList();
		return theaterList;
	}

	@Override
	public TheaterVO readTheaterBytno(int ttr_no) throws SQLException {
		TheaterVO theater=theaterDAO.selectTheaterBytno(ttr_no);
		return theater;
	}


	@Override
	public List<TheaterVO> readTheaterListCriteria(Criteria cri)
			throws SQLException {
			List<TheaterVO> theaterList=theaterDAO.selectTheaterListCriteria(cri);
		return theaterList;
	}


	@Override
	public List<String> getAttach(int ttr_no) throws SQLException {
		List<String> fileNames=theaterDAO.selctAttach(ttr_no);
		return fileNames;
	}

}