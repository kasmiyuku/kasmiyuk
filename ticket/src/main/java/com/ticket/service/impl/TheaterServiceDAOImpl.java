package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticket.dao.TheaterDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
import com.ticket.domain.TheaterVO;
import com.ticket.service.TheaterServiceDAO;

public class TheaterServiceDAOImpl implements TheaterServiceDAO {

	private TheaterDAO theaterDAO;

	public void setTheaterDAO(TheaterDAO theaterDAO) {
		this.theaterDAO = theaterDAO;
	}

	@Override
	public void createTheater(TheaterVO theater) throws SQLException {

		theaterDAO.insertTheater(theater);

		int ttr_no = theaterDAO.selectMaxtno();

		if (theater.getThumFiles() != null) {
			for (String fullName : theater.getThumFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 1);
			}
		}
		if (theater.getSeatFiles() != null) {
			for (String fullName : theater.getSeatFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 2);
			}
		}
		if (theater.getFiles() != null) {
			for (String fullName : theater.getFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 3);
			}
		}

	}

	@Override
	public void updateTheater(TheaterVO theater) throws SQLException {
		theaterDAO.updateTheater(theater);
		int ttr_no = theater.getTtr_no();
		theaterDAO.deleteAttach(ttr_no);

		String[] files = theater.getFiles();
		if (files == null) {
			return;
		}
		if (theater.getThumFiles() != null) {
			for (String fullName : theater.getThumFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 1);
			}
		}
		if (theater.getSeatFiles() != null) {
			for (String fullName : theater.getSeatFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 2);
			}
		}
		if (theater.getFiles() != null) {
			for (String fullName : theater.getFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 3);
			}
		}
	}

	@Override
	public void deleteTheater(int ttr_no) throws SQLException {
		theaterDAO.deleteAttach(ttr_no);
		theaterDAO.deleteTheater(ttr_no);
	}

	@Override
	public List<TheaterVO> readTheaterList() throws SQLException {
		List<TheaterVO> theaterList = theaterDAO.selectTheaterList();
		return theaterList;
	}

	@Override
	public TheaterVO readTheaterBytno(int ttr_no) throws SQLException {
		TheaterVO theater = theaterDAO.selectTheaterBytno(ttr_no);
		
		return theater;
	}

	@Override
	public List<TheaterVO> readTheaterListCriteria(Criteria cri)
			throws SQLException {
		List<TheaterVO> theaterList = theaterDAO.selectTheaterListCriteria(cri);

		for (TheaterVO theater : theaterList) {
			theater.setThumFiles(theaterDAO.selectAttachThum(theater
					.getTtr_no()));
			theater.setSeatFiles(theaterDAO.selectAttachSeat(theater
					.getTtr_no()));
			theater.setFiles(theaterDAO.selectAttachFile(theater.getTtr_no()));
		}
		return theaterList;
	}

	@Override
	public List<String[]> getAttach(int ttr_no) throws SQLException {
		List<String[]> fileList=new ArrayList<String[]>();
		String[] files=theaterDAO.selectAttachFile(ttr_no);
		String[] seatFiles=theaterDAO.selectAttachSeat(ttr_no);
		String[] thumFiles=theaterDAO.selectAttachThum(ttr_no);
		
		fileList.add(thumFiles);
		fileList.add(seatFiles);
		fileList.add(files);
		
		return fileList;
	}

	@Override
	public List<TheaterVO> readSearchTheaterList(SearchCriteria cri)
			throws SQLException {
		List<TheaterVO> theaterList = theaterDAO.selectSearchList(cri);
		
		for (TheaterVO theater : theaterList) {
			theater.setThumFiles(theaterDAO.selectAttachThum(theater
					.getTtr_no()));
			theater.setSeatFiles(theaterDAO.selectAttachSeat(theater
					.getTtr_no()));
			theater.setFiles(theaterDAO.selectAttachFile(theater.getTtr_no()));
		}
		return theaterList;
	}

	@Override
	public int readSearchtheaterListCount(SearchCriteria cri)
			throws SQLException {
			int countList=theaterDAO.selectSearchListCount(cri);
		return countList;
	}

	@Override
	public Seatinfo readseatbyseat_id(String seat_id) throws SQLException {
			Seatinfo seat=theaterDAO.selectseatbyseat_id(seat_id);
		return seat;
	}

	@Override
	public List<Seatinfo> readseatbyttr_no(int ttr_no) throws SQLException {
			List<Seatinfo> seat=theaterDAO.selectseatbyttr_no(ttr_no);
		return seat;
	}

	@Override
	public List<Seatinfo> readseat() throws SQLException {
			List<Seatinfo> seat=theaterDAO.selectseat();
		return seat;
	}

}
