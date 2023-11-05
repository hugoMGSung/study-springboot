package com.hugo83.b01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hugo83.b01.dto.upload.UploadFileDTO;
import com.hugo83.b01.dto.upload.UploadResultDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Log4j2
public class UpDownController {

	@Value("${com.hugo83.upload.path}") // import 시에 springframework으로 시작하는 Value
	private String uploadPath;

	@Operation(summary = "Upload POST")
	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public List<UploadResultDTO> uploadFile(@RequestPart List<MultipartFile> uploadFiles) {
		log.info(uploadFiles);
		if (uploadFiles.size() > 0) {
			final List<UploadResultDTO> list = new ArrayList<>();

			uploadFiles.forEach(uploadFile -> {
				String originalName = uploadFile.getOriginalFilename();
				log.info("FileName >> " + originalName);
				String uuid = UUID.randomUUID().toString();
				Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);

				boolean image = false; // 일단 이미지가 아님
				try {
					uploadFile.transferTo(savePath); // 실제 파일저장

					// 이미지 파일이면
					if (Files.probeContentType(savePath).startsWith("image")) {
						image = true;
						File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
						Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200); // 썸네일 저장
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				list.add(UploadResultDTO.builder()
						.uuid(uuid)
						.fileName(originalName)
						.img(image).build());
			}); // end each

			return list;
		} // end if
			// return ResponseEntity.ok(null);
		return null;
	}

	@Operation(summary = "view 파일")
	@GetMapping("/view/{fileName}")
	public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName) {

		Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders();

		try {
			headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok().headers(headers).body(resource);
	}

	@Operation(summary = "remove 파일")
	@DeleteMapping("/remove/{fileName}")
	public Map<String, Boolean> removeFile(@PathVariable String fileName) {
		Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
		String resourceName = resource.getFilename();

		Map<String, Boolean> resultMap = new HashMap<>();
		boolean removed = false;

		try {
			String contentType = Files.probeContentType(resource.getFile().toPath());
			removed = resource.getFile().delete();

			// 섬네일이 존재한다면
			if (contentType.startsWith("image")) {
				File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
				thumbnailFile.delete();
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		resultMap.put("result", removed);
		return resultMap;
	}
}
