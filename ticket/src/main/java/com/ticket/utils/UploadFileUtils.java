package com.ticket.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	public static String uploadFile(String uploadPath,String originalName,byte[] fileData)throws Exception{
		
			UUID uid = UUID.randomUUID();
			String savedName=uid.toString().replace("-", "")+"_"+originalName;
			String savedPath=calcPath(uploadPath);
			
			File target=new File(uploadPath+savedPath,savedName);
			FileCopyUtils.copy(fileData, target);
			
			String formatName=originalName.substring(originalName.lastIndexOf(".")+1);
			
			String uploadFileName=null;
			
			if(MediaUtils.getMediaType(formatName)!=null){
				//썸내일 형태로보여주는 이프문
				uploadFileName=makeThumbnail(uploadPath,savedPath,savedName);
				
			}else{
				//아이콘 형태로보여주는 이프문
				uploadFileName=makeIcon(uploadPath,savedPath,savedName);
			}
		return uploadFileName;
	}

	private static String makeIcon(String uploadPath, String path,
			String fileName)throws Exception {
		
		String iconName=uploadPath+path+File.separator+fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	private static String makeThumbnail(String uploadPath, String path,
			String fileName)throws Exception{
		
			BufferedImage sourceImg=ImageIO.read(new File(uploadPath+path,fileName));
			
			BufferedImage destImg=Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
			
			String thumbnailName=uploadPath+path+File.separator+"s_"+fileName;
			
			File newFile=new File(thumbnailName);
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			ImageIO.write(destImg, formatName.toUpperCase(), newFile);
			
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	private static String calcPath(String uploadPath)throws Exception {
		
			Calendar cal=Calendar.getInstance();
			
			String yearPath=File.separator+cal.get(Calendar.YEAR);
			String monthPath=yearPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.MONDAY)+1);
			String datePath=monthPath+File.separator+new DecimalFormat("00").format(Calendar.DATE);
			
			makeDir(uploadPath,yearPath,monthPath,datePath);
		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {
		if(new File(paths[paths.length-1]).exists()){
			return;
		}
		for(String path:paths){
			File dirPath=new File(uploadPath+path);
			if(!dirPath.exists()){
				dirPath.mkdir();
			}
		}
	}
}
