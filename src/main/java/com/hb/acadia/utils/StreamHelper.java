package com.hb.acadia.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StreamHelper {

	public void addStreamHelperToResponse(String path, String contentType, HttpServletResponse response) {
		File file = new File(path);
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
		InputStream is = null;
		
		try {
			is = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(is, response.getOutputStream());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			IOUtils.closeQuietly(is);
		}
		
	}
	
}
