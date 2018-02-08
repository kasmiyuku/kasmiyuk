package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticket.dao.TheaterDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.TheaterVO;
import com.ticket.service.TheaterService;

public class TheaterServiceImpl implements TheaterService {

	private TheaterDAO theaterDAO;

	public void setTheaterDAO(TheaterDAO theaterDAO) {
		this.theaterDAO = theaterDAO;
	}

	@Override
	public void createTheater(TheaterVO theater) throws SQLException {

		theaterDAO.insertTheater(theater);

		int ttr_no = theaterDAO.selectMaxtno();

		if (theater.getThumFiles() != null)
			for (String fullName : theater.getThumFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 1);
			}
		if (theater.getSeatFiles() != null)
			for (String fullName : theater.getSeatFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 2);
			}
		if (theater.getFiles() != null)
			for (String fullName : theater.getFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 3);
			}

	}

	@Override
	public void updateTheater(TheaterVO theater) throws SQLException {

		theaterDAO.updateTheater(theater);

		int ttr_no = theater.getTtr_no();

		theaterDAO.deleteAttachAll(ttr_no);
		if (theater.getThumFiles() != null)
			for (String fullName : theater.getThumFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 1);
			}
		if (theater.getSeatFiles() != null)
			for (String fullName : theater.getSeatFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 2);
			}
		if (theater.getFiles() != null)
			for (String fullName : theater.getFiles()) {
				theaterDAO.insertAttach(fullName, ttr_no, 3);
			}
	}

	@Override
	public void deleteTheater(int ttr_no) throws SQLException {
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

		String[] files = theaterDAO.selectAttachFile(ttr_no);
		String[] seatFiles = theaterDAO.selectAttachSeat(ttr_no);
		String[] thumFiles = theaterDAO.selectAttachThum(ttr_no);

		theater.setFiles(files);
		theater.setSeatFiles(seatFiles);
		theater.setThumFiles(thumFiles);

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
		String[] files = theaterDAO.selectAttachFile(ttr_no);
		String[] seatFiles = theaterDAO.selectAttachSeat(ttr_no);
		String[] thumFiles = theaterDAO.selectAttachThum(ttr_no);

		List<String[]> fileNames = new ArrayList<String[]>();
		fileNames.add(thumFiles);
		fileNames.add(seatFiles);
		fileNames.add(files);

		return fileNames;
	}

}
