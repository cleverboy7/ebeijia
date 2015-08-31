package com.ebeijia.util;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.ebeijia.tools.DateTime4J;
import org.springframework.web.multipart.MultipartFile;


public class UpLoad {

	public File getFile(MultipartFile imgFile,String typeName,String brandName,String ext) {  
		//String fileName = imgFile.getOriginalFilename();
		//随机数加当前日期
		String fileName =getRandom()+DateTime4J.getCurrentDateTime()+"."+ext;
		File file = this.creatFolder(typeName, brandName, fileName);
		try {
			// 保存上传的文件
			imgFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
    }  
	
	private File creatFolder(String typeName,String brandName,String fileName) {  
        File file = null;  
        typeName = typeName.replaceAll("/", "");  
        typeName = typeName.replaceAll(" ", "");   
        typeName = typeName.replaceAll(" ", "");  
        brandName = brandName.replaceAll("/", "");
        brandName = brandName.replaceAll(" ", "");  
        brandName = brandName.replaceAll(" ", "");
        String path =SystemProperties.getProperties("image.localPath"); 
        //一级文件夹  
        File firstFolder = new File(path + typeName);        
      //如果一级文件夹存在，则检测二级文件夹  
        if(firstFolder.exists()) {                             
            File secondFolder = new File(firstFolder,brandName);  
        	//如果二级文件夹也存在，则创建文件  
            if(secondFolder.exists()) {                        
                file = new File(secondFolder,fileName);  
              //如果二级文件夹不存在，则创建二级文件夹  
            }else {                                           
                secondFolder.mkdir();  
                //创建完二级文件夹后，再合建文件  
                file = new File(secondFolder,fileName);        
            }  
          //如果一级不存在，则创建一级文件夹  
        }else {                                                
            firstFolder.mkdir();  
            File secondFolder = new File(firstFolder,brandName);  
          //如果二级文件夹也存在，则创建文件  
            if(secondFolder.exists()) {                       
                file = new File(secondFolder,fileName);  
              //如果二级文件夹不存在，则创建二级文件夹  
            }else {                                           
                secondFolder.mkdir();  
                file = new File(secondFolder,fileName);  
            }  
        }  
        return file;  
   }
	
	private String getRandom(){
		int max = 99999;
		int min = 1;
		Random random = new Random();
		int s = random.nextInt(max)%(max-min+1) + min;
		return String.valueOf(s);
	}
	 
}
