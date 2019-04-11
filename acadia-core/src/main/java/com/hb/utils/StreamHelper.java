package com.hb.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
