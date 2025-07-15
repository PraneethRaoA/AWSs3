package com.aws.s3.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aws.s3.service.awsS3service;

@RestController
public class awsS3Controller {
	
	@Autowired
	private awsS3service awss3service;
	
	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
		awss3service.uploadFile(file);
		return ResponseEntity.ok("File Uploaded Successfully");
	}
	
	@GetMapping("/download/{filename}")
	public ResponseEntity<byte[]> download(@PathVariable String filename){
		byte[] data = awss3service.downloadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachement" )
				.body(data);
	}
}
