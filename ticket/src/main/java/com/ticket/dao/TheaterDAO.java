package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
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
	
	List<TheaterVO> selectSearchList(SearchCriteria cri)throws SQLException;	
	
	List<TheaterVO> selectTheaterListCriteria(Criteria cri) throws SQLException;

	int selectSearchListCount(SearchCriteria cri)throws SQLException;
	
	void insertAttach(String fullName,int ttr_no,int file_kind)throws SQLException;
	
	void insertseat(Seatinfo seat) throws SQLException;
	void deleteseatbyseat_id(String seat_id) throws SQLException;
	void deleteseatbyttr_no(int ttr_no) throws SQLException;
	void updateseatbyseat_id(Seatinfo seat) throws SQLException;
	void updateseatbyttr_no(Seatinfo seat) throws SQLException;
	List<Seatinfo> selectseatbyttr_no(int ttr_no) throws SQLException;
	Seatinfo selectseatbyseat_id(String seat_id) throws SQLException;
	List<Seatinfo> selectseat() throws SQLException;
	
	int selectMaxtno()throws SQLException;
	List<String> selctAttachAll(int ttr_no)throws SQLException;
	void deleteAttach(int ttr_no)throws SQLException;
	String[] selectAttachThum(int ttr_no)throws SQLException;
	String[] selectAttachSeat(int ttr_no)throws SQLException;
	String[] selectAttachFile(int ttr_no)throws SQLException;
	/*void replaceAttach(String fullName,int ttr_no)throws SQLException;*/
}
