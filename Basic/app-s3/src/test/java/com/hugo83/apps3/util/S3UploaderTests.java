package com.hugo83.apps3.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class S3UploaderTests {
	@Autowired
	private S3Uploader s3Uploader;

	@Test
	public void testUpload() {
		try {
			String filePath = "D:\\Temp\\italy_mountain.jpg";
			String uploaName = s3Uploader.upload(filePath);
			log.info(uploaName + " UPLOADED!!!!!!!");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Test
	public void testRemove() {
		try {
			String fileName = "mountain.jpg";
			s3Uploader.removeS3File(fileName);
			log.info(fileName + " Deleted!!!!!!");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
