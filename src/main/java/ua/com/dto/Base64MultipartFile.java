package ua.com.dto;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class Base64MultipartFile implements MultipartFile{
	
	private byte []fileContent;
	private String fileName;
	
	public Base64MultipartFile(byte[] fileContent, String fileName) {
		this.fileContent = fileContent;
		this.fileName = fileName;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return fileContent;
	}

	@Override
	public String getContentType() {
		return fileName.split(".")[1];
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(fileContent);
	}

	@Override
	public String getName() {
		return fileName.split(".")[0];
	}

	@Override
	public String getOriginalFilename() {
		return fileName;
	}

	@Override
	public long getSize() {
		return fileContent.length;
	}

	@Override
	public boolean isEmpty() {
		return fileContent.length == 0;
	}

	@Override
	public void transferTo(File arg0) throws IOException, IllegalStateException {
		new FileOutputStream(arg0).write(fileContent);
		
	}

}
