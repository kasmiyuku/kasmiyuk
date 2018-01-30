package com.ticket.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ticket.domain.Criteria;
import com.ticket.domain.Page;
import com.ticket.domain.TheaterVO;
import com.ticket.domain.TheaterVORequest;
import com.ticket.service.TheaterServiceDAO;
import com.ticket.utils.MediaUtils;
import com.ticket.utils.UploadFileUtils;


@Controller

public class TheaterController {
	
	@Autowired
	private TheaterServiceDAO theaterServiceDAO;
	
	@RequestMapping(value="/theater", method=RequestMethod.GET)
	public String theater(Model model) throws Exception{
		String url="/theater";
		List<TheaterVO> theaterList=theaterServiceDAO.readTheaterList();
		model.addAttribute("list",theaterList);
		return url;
	}
	@RequestMapping(value="/theaterlistpage",method=RequestMethod.GET)
	public String theaterPage(@ModelAttribute("cri")Criteria cri, Model model)throws Exception{
		
			String url="/theaterlistpage";
			List<TheaterVO> theaterList = theaterServiceDAO.readTheaterListCriteria(cri);
			
			Page page=new Page();
			page.setCri(cri);
			page.setTotalCount(theaterServiceDAO.readTheaterList().size());
			
			model.addAttribute("list",theaterList);
			model.addAttribute("page",page);
			
		return url;
	}
	@RequestMapping(value="/theaterRead", method=RequestMethod.GET)
	public void theaterRead(int ttr_no,Model model)throws Exception{
		TheaterVO theater=theaterServiceDAO.readTheaterBytno(ttr_no);
		model.addAttribute(theater);
	}
	@RequestMapping(value="/theaterReadpage",method=RequestMethod.GET)
	public String theaterReadpage(@ModelAttribute("cri")Criteria cri,@RequestParam("ttr_no")int tno,Model model)throws Exception{
			String url="/theaterReadpage";
			
			TheaterVO theater=theaterServiceDAO.readTheaterBytno(tno);
			model.addAttribute("TheaterVO",theater);
		return url;
	
	}
	@RequestMapping(value="/theatercreate", method=RequestMethod.GET)
	public String thearterCreate()throws Exception{
		String url="/theatercreate";
		return url;
	}
	
	@RequestMapping(value="/theatercreate", method=RequestMethod.POST)
	public String thearterCreate(TheaterVORequest ttr)throws Exception{
		TheaterVO theater = ttr.toTheaterVO();
		theaterServiceDAO.createTheater(theater);
		return "redirect:/theaterlistpage";
	}
	
	@RequestMapping(value="/removepage", method=RequestMethod.POST)
	public String removePage(int ttr_no)throws Exception{
			String url="redirect:/theaterlistpage";
			theaterServiceDAO.deleteTheater(ttr_no);
		return url;
	}
	
	@RequestMapping(value="/modifypageForm", method=RequestMethod.GET)
	public String modifyPageForm(@RequestParam("ttr_no")int ttr_no,
			@ModelAttribute("cri")Criteria cri, Model model)throws Exception{
		String url="/theaterModifypage";
		
		model.addAttribute(theaterServiceDAO.readTheaterBytno(ttr_no));
		return url;
	}
	@RequestMapping(value="/modifypage", method=RequestMethod.POST)
	public String modifyPage(TheaterVORequest ttr,Criteria cri,RedirectAttributes rttr)throws Exception{
			TheaterVO theater = ttr.toTheaterVO();
			theaterServiceDAO.updateTheater(theater);
			
			rttr.addAttribute("page",cri.getPage());
			rttr.addAttribute("perPageNum",cri.getPerPageNum());
		
		return "redirect:/theaterlistpage";
	}
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.GET)
	public void uploadForm(){}
	@RequestMapping(value="/uploadForm",method=RequestMethod.POST)
	public String upload(MultipartFile file,Model model)throws Exception{
		String saveName=uploadFile(file.getOriginalFilename(),file.getBytes());
		
		model.addAttribute("savedName",saveName);
		
		return "uploadResult";
	}
	private String uploadFile(String originalname, byte[] fileData)throws Exception {

		UUID uid=UUID.randomUUID();
		String saveName=uid.toString().replace("-", "")+"_"+originalname;
		
		File target=new File(uploadPath,saveName);
		
		FileCopyUtils.copy(fileData,target);
		return saveName;
	}
	@RequestMapping(value="/uploadAjax",method=RequestMethod.GET)
	public void uploadAjax(){}
	
	@RequestMapping(value="/uploadAjax",method=RequestMethod.POST,produces="text/plain;charset=utf-8")
	public ResponseEntity<String>uploadAjax(MultipartFile file)throws Exception{
		
		return new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),HttpStatus.CREATED);
	}
	
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName)throws Exception{
		
		InputStream in=null;
		ResponseEntity<byte[]> entity=null;
		
		try{
		String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
		MediaType mType=MediaUtils.getMediaType(formatName);
		HttpHeaders headers=new HttpHeaders();
		
		fileName=fileName.replace('/', File.separatorChar);
		System.out.println("!!!!!!!!!!!!"+uploadPath+fileName);
		in=new FileInputStream(uploadPath+fileName);
		
		if(mType!=null){
			headers.setContentType(mType);
		}else{
			fileName=fileName.substring(fileName.indexOf("_")+1);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment;filename=\""+new String(fileName.getBytes("utf-8"),"ISO-8859-1")+"\"");
		}
		entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		}catch(IOException e){
			e.printStackTrace();
			entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		return entity;
	}
	@ResponseBody
	@RequestMapping(value="/deleteFile",method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName)throws Exception{
		String  formatName=fileName.substring(fileName.lastIndexOf(".")+1);
		
		MediaType mType=MediaUtils.getMediaType(formatName);
		
		if(mType!=null){
			String front=fileName.substring(0,12);
			String end=fileName.substring(14);
			new File(uploadPath+(front+end).replace('/', File.separatorChar)).delete();
			
		}
		new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping(value="/deleteAllFiles",method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(@RequestParam("files[]")String[] files)throws Exception{
		if(files==null||files.length==0){

			return new ResponseEntity<String>("deleted",HttpStatus.OK);
		}
		for(String fileName:files){
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType=MediaUtils.getMediaType(formatName);
			
		if(mType!=null){
			String front=fileName.substring(0,12);
			String end=fileName.substring(14);
			new File(uploadPath+(front+end).replace('/', File.separatorChar)).delete();
		}
		new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
		}
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	@RequestMapping("/getAttach/{ttr_no}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("ttr_no")int ttr_no)throws Exception{
		return theaterServiceDAO.getAttach(ttr_no);
	}
}

