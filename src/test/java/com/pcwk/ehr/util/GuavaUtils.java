package com.pcwk.ehr.util;

import java.io.File;

import com.google.common.base.Strings;
import com.pcwk.ehr.cmn.AESUtil;
import com.pcwk.ehr.cmn.PcwkString;

public class GuavaUtils {
	
	
	public static void encrypt() throws Exception {
		AESUtil aesUtil=new AESUtil();
		
		String encrypt=aesUtil.encrypt("이상무");
		System.out.println("***encrypt:"+encrypt);
		String decrypted= aesUtil.decrypt(encrypt);
		System.out.println("***decrypted:"+decrypted);
		
		System.out.println("***getSecretKey:"+aesUtil.getSecretKey());
	}
	  
	public static void fileUploadPath() {
		String yyyyMMPath = "";
		//년,월
		String yyyy = PcwkString.getCurrentDate("yyyy");
		String mm   = PcwkString.getCurrentDate("MM");
		
		System.out.println("yyyy:"+yyyy);
		System.out.println("mm:"+mm);

		//디렉토리 동적 생성
		yyyyMMPath = File.separator+yyyy+File.separator+mm;// /2025/07
		

		System.out.println("yyyyMMPath:"+yyyyMMPath);
		
		//none image
		String FILE_PATH = "D:\\upload";
		String fileFullPath  = FILE_PATH+yyyyMMPath;
		System.out.println("fileFullPath:"+fileFullPath);
		
		File noneImageFile=new File(fileFullPath);
		if(noneImageFile.isDirectory() == false) {
			boolean isMakeDirs=noneImageFile.mkdirs();
			System.out.println("isMakeDirs:"+isMakeDirs);
		}
		
	}
	
    
	/**
	 * null,"" 입력되면 default value로 변경
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static String nvlString(String value, String defaultValue) {
		if(Strings.isNullOrEmpty(value) == true) {
			return defaultValue;
		}
		
		return value;
	}
	
	
	public static int nvlZero(int value, int defaultValue) {
		return (value==0)?defaultValue:value;
	}
	
	public static boolean isNullOrEmpty(String str) {
		return Strings.isNullOrEmpty(str);
	}
	
	
	//null->빈문자열
	public static String nullToEmpty(String str) {
		//return  (str == null) ? "" : str;
		return Strings.nullToEmpty(str);
	}
	
	public static void uuid() {
		System.out.println(PcwkString.getUUID());
	}
	
	public static void getPK() {
		System.out.println(PcwkString.getPK("yyyyMMdd"));
	}	
	
	public static void getExt() {
		System.out.println(PcwkString.getExt("abc.a.doc"));
	}
	public static void main(String[] args) throws Exception {
		
		System.out.println("nvlString: "+nvlString(null, "10"));
		System.out.println("nvlString: "+nvlString("", "10"));
		
		//Null테스트
		System.out.println("NullOrEmpty: "+isNullOrEmpty(null));
		System.out.println("NullOrEmpty: "+isNullOrEmpty(""));
		
		System.out.println("nullToEmpty: "+nullToEmpty(null));
		
		System.out.println("nvlZero: "+nvlZero(0,10));
		
		System.out.println(PcwkString.getCurrentDate("yyyy"));
		System.out.println(PcwkString.getCurrentDate("MM"));

		//fileUploadPath();
		//uuid();
		//getPK();
		getExt();
		
		encrypt();
	}

}
