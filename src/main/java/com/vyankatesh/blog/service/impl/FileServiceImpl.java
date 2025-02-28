package com.vyankatesh.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vyankatesh.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		//Get original file name
		String name=file.getOriginalFilename();
		
		//Random file name
		String content= UUID.randomUUID().toString();
		String fileName1= content.concat(name.substring(name.lastIndexOf(".")));
		
		//Full path
		String fullpath= path + File.separator +fileName1;
		
		//Create folder if not created
		File file2=new File(path);
		
		if(!file2.exists())
		{
			file2.mkdir();
		}
		
		//File copy
		Files.copy(file.getInputStream(),Paths.get(fullpath));
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String filePath= path+File.separator+fileName;
		InputStream is=new FileInputStream(filePath);
		return is;
	}

}
