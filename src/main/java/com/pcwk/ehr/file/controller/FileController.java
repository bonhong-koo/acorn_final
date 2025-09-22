package com.pcwk.ehr.file.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.PcwkString;
import com.pcwk.ehr.file.domain.FileVO;

@Controller
@RequestMapping("file")
public class FileController {

	Logger log = LogManager.getLogger(getClass());
	
	//이미지 경로
	final String IMAGE_PATH = "/resources/upload";
	//none 이미지 경로
	final String FILE_PATH = "D:\\upload";
	
	private String yyyyMMPath = "";
	private String fileFullPath = "";
	private String imageFullPath = "";
	/**
	 * 
	 */
	public FileController() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ FileController()                                        │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		//년,월
		String yyyy = PcwkString.getCurrentDate("yyyy");
		String mm   = PcwkString.getCurrentDate("MM");
		
		log.debug("yyyy:{}",yyyy);
		log.debug("mm:{}",mm);
		
		//디렉토리 동적 생성
		yyyyMMPath = File.separator+yyyy+File.separator+mm+File.separator;// /2025/07/
		
		log.debug("yyyyMMPath:{}",yyyyMMPath);
		
		//none 이미지 파일 경로 생성
		fileFullPath = FILE_PATH + yyyyMMPath;
		log.debug("fileFullPath:{}",fileFullPath);
		
		File noneImageFile=new File(fileFullPath);
		if(noneImageFile.isDirectory() == false) {
			boolean isMakeDirs=noneImageFile.mkdirs();
			log.debug("fileFullPath:{}",fileFullPath);
		}	
		
		//이미지 파일 경로 생성: WEB에서 접근		
		imageFullPath = IMAGE_PATH + yyyyMMPath;
		log.debug("imageFullPath:{}",imageFullPath);
		File imageFile=new File(imageFullPath);
		
		if(imageFile.isDirectory() == false) {
			imageFile.mkdirs();
		}
		
	}
	
	@PostMapping(value="/asynFileUpload.do",produces ="text/plain;charset=UTF-8" )
	@ResponseBody
	public String asynFileUpload(@RequestParam("pFile") MultipartFile  file,HttpServletRequest request) {
		FileVO outVO=new FileVO();
		
		String orgFileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		String saveFileName = PcwkString.getUUID()+"_"+orgFileName;
		String ext = PcwkString.getExt(orgFileName);
		String uploadType = file.getContentType().startsWith("image") ? "image" : "file";
		
		
		outVO.setOrgFileName(orgFileName);
		outVO.setSaveFileName(saveFileName);
		outVO.setExt(ext);
		outVO.setFileSize(fileSize);
		outVO.setFileType(uploadType); 
		//이미지, 파일 구분
		//none 이미지

		
		//final String IMAGE_PATH = "D:\\JAP_20250317\\03_SPRING\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\sw14\\resources\\upload";
        String uploadDir = "";
        
        //image
		if(uploadType.equals("image")) {
			StringBuilder sb=new StringBuilder(100);
			sb.append(IMAGE_PATH);
			sb.append("/");
			sb.append(PcwkString.getCurrentDate("yyyy"));
			sb.append("/");
			sb.append(PcwkString.getCurrentDate("MM"));
			sb.append("/");
					
			uploadDir = request.getServletContext().getRealPath(sb.toString());
			log.debug("image uploadDir:"+sb.toString());
			log.debug("image uploadDir getRealPath:"+uploadDir);
			
			outVO.setFileUrl(request.getContextPath()+sb.toString()+saveFileName);
		//none	
		}else {
			StringBuilder sb=new StringBuilder(100);
			sb.append(FILE_PATH);
			sb.append(File.separator);
			sb.append(PcwkString.getCurrentDate("yyyy"));
			sb.append(File.separator);
			sb.append(PcwkString.getCurrentDate("MM"));
			sb.append(File.separator);
			
			uploadDir = sb.toString();
			log.debug("image uploadDir getRealPath:"+uploadDir);
			outVO.setFileUrl(sb.toString()+saveFileName);
		}

		
        File saveFile = new File(uploadDir, saveFileName);
        
        try {
			file.transferTo(saveFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        log.debug("outVO:{}",outVO);
        
		return new Gson().toJson(outVO);
	}
	
	@PostMapping("/fileUpload.do")
	public ModelAndView fileUpload(@RequestParam("pFile") MultipartFile  file,ModelAndView modelAndView ,HttpServletRequest request) {
		
		FileVO outVO=new FileVO();
		
		if(file.isEmpty() == false) {
			String orgFileName = file.getOriginalFilename();
			long fileSize = file.getSize();
			String saveFileName = PcwkString.getUUID()+"_"+orgFileName;
			String ext = PcwkString.getExt(orgFileName);
			
			outVO.setOrgFileName(orgFileName);
			outVO.setSaveFileName(saveFileName);
			outVO.setExt(ext);
			outVO.setFileSize(fileSize);
			
			//이미지, 파일 구분
			//none 이미지
			String uploadType = file.getContentType().startsWith("image") ? "image" : "file";
			
			String uploadDir = "";
	        //image
			if(uploadType.equals("image")) {
				StringBuilder sb=new StringBuilder(100);
				sb.append(IMAGE_PATH);
				sb.append("/");
				sb.append(PcwkString.getCurrentDate("yyyy"));
				sb.append("/");
				sb.append(PcwkString.getCurrentDate("MM"));
				sb.append("/");
						
				uploadDir = request.getServletContext().getRealPath(sb.toString());
				log.debug("image uploadDir:"+sb.toString());
				log.debug("image uploadDir getRealPath:"+uploadDir);
				
				outVO.setFileUrl(request.getContextPath()+sb.toString()+saveFileName);
			//none	
			}else {
				StringBuilder sb=new StringBuilder(100);
				sb.append(FILE_PATH);
				sb.append(File.separator);
				sb.append(PcwkString.getCurrentDate("yyyy"));
				sb.append(File.separator);
				sb.append(PcwkString.getCurrentDate("MM"));
				sb.append(File.separator);
				
				uploadDir = sb.toString();
				log.debug("file uploadDir getRealPath:"+uploadDir);
				outVO.setFileUrl(sb.toString()+saveFileName);
			}

	        
			outVO.setSavePath(uploadDir);
			
			try {
				
				file.transferTo(new File(fileFullPath,outVO.getSaveFileName()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
				modelAndView.addObject("message", "실패:"+e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				modelAndView.addObject("message", "실패:"+e.getMessage());
			}
			
			modelAndView.addObject("message", outVO);
		}else {
			modelAndView.addObject("message", "file이 없습니다.");
		}
        log.debug("outVO:{}",outVO);
		
		
		modelAndView.setViewName("file/fileUploadResult");
		return modelAndView;
	}
	@GetMapping("/asynView.do")
	public ModelAndView asynView(ModelAndView modelAndView) {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *asynView()*              │");
		log.debug("└───────────────────────────┘");	
		
		modelAndView.addObject("hello", "모두 건강 하세요.");
		modelAndView.setViewName("file/asynFileUpload");//화면
		return modelAndView;
	}
	
	
	@GetMapping("/uploadView.do")
	public ModelAndView uploadView(ModelAndView modelAndView) {
		log.debug("┌───────────────────────────┐");
		log.debug("│ *doSaveView()*            │");
		log.debug("└───────────────────────────┘");	
		
		modelAndView.addObject("hello", "모두 건강 하세요.");
		modelAndView.setViewName("file/fileUpload");//화면
		return modelAndView;
	}

	
	
}
