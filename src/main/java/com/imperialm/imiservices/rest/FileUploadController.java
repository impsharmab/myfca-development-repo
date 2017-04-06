package com.imperialm.imiservices.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class FileUploadController {
	
	    @RequestMapping(value="/imageUpload", method = RequestMethod.POST)
	    public ResponseEntity<?> UploadFile(MultipartHttpServletRequest request) throws IOException {

	        Iterator<String> itr=request.getFileNames();
	        MultipartFile file=request.getFile(itr.next());
	        String fileName=file.getOriginalFilename();
	        File dir = new File("C:\\dashboardbanners");
	        
	        if (dir.isDirectory())
	        {
	            File serverFile = new File(dir,fileName);
	            	if(!serverFile.exists()){
	            		BufferedOutputStream stream = new BufferedOutputStream(
	            				new FileOutputStream(serverFile));
	            		stream.write(file.getBytes());
	            		stream.close();
	            		return ResponseEntity.ok("Uploaded");
	            }
	        }
	        
	        return ResponseEntity.badRequest().body(HttpStatus.CONFLICT);
	    }
	    
	    
	    @GetMapping("/listFiles")
	    public ResponseEntity<?> listUploadedFiles() throws IOException {

	        File dir = new File("C:\\dashboardbanners");
	        
	        if (dir.isDirectory())
	        {
	        	List<String> files = new ArrayList<String>();
	            File[] a = dir.listFiles();
	            for(File b: a){
	            	files.add(b.getName());
	            }
	            
	            return ResponseEntity.ok(files);
	        }
	        
	        return ResponseEntity.badRequest().body(HttpStatus.CONFLICT);
	    }
	    

}