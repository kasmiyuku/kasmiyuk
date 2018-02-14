package com.ticket.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.domain.Criteria;
import com.ticket.domain.Page;
import com.ticket.domain.ReplyVO;
import com.ticket.service.ReplyService;
@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO reply) {
		
		ResponseEntity<String> entity=null;
		
		try {
			replyService.addReply(reply);
			entity=new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/all/{ttr_no}",method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("ttr_no")int ttr_no){
		
		ResponseEntity<List<ReplyVO>> entity=null;
		try {
			entity=new ResponseEntity<List<ReplyVO>>(
			replyService.getReplyList(ttr_no),HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity=new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	@RequestMapping(value="/{tr_rno}",method={RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("tr_rno") int tr_rno,@RequestBody ReplyVO reply){
		ResponseEntity<String> entity=null;
		
		try {
			reply.setTr_rno(tr_rno);;
			replyService.modifyReply(reply);
			entity=new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(HttpStatus.BAD_REQUEST);		
		
		}
		
		return entity;
	}
	@RequestMapping(value="/{ttr_no}/{page}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listPage(@PathVariable("ttr_no")int ttr_no,@PathVariable("page")int page){
		
		ResponseEntity<Map<String,Object>> entity=null;
		Criteria cri=new Criteria();
		cri.setPage(page);
		cri.setPerPageNum(5);
		
		Page pages=new Page();
		pages.setCri(cri);
		
		
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			List<ReplyVO> replyList=replyService.getReplyListPage(ttr_no, cri);
			
			map.put("list", replyList);
			map.put("page", pages);
			
			entity=new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity=new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		
		
		return entity;
	}
	
	@RequestMapping(value="/{tr_rno}",method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("tr_rno")int rno){
		ResponseEntity<String> entity=null;
		
			try {
				replyService.removeReply(rno);
				entity=new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			} catch (SQLException e) {
				e.printStackTrace();
				entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		
		return entity;
	}
}
