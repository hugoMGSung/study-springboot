# study-springboot
스프링부트 저장소 다시 만들기

## chap13. File Upload
파일 업로드 처리

### A. 기존 프로젝트 클론
1. 우선 chap13으로 프로젝트 생성. com.hugo83.b01 로 변경(테스트)
2. 테스트 확인

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0093.png" width="600">

### B. 업로드 설정
***교재대로 하면 안됨. 아예 2버전에 맞춰서 해야함 ***

1. application.properties 에 업로드용 추가설정
2. dto/upload/UploadFileDTO.java 작성(이 클래스 문제 있음)
3. controller/UpDownController.java 가장 간단히 작성
	```java
	@RestController
	@Log4j2
	public class UpDownController {

		@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		public String upload(UploadFileDTO uploadFileDTO) {
			log.info(uploadFileDTO);
			return null;
		}
	}
	```
4. 위의 소스 전부 변경
	```java
	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String uploadFile(@RequestPart List<MultipartFile> uploadFiles) {
		log.info(uploadFiles);
		if (uploadFiles.size() > 0) {
			for (MultipartFile file : uploadFiles) {
				log.info(file.getOriginalFilename());
			} // end of foreach
		}
		// return ResponseEntity.ok(null);
		return null;
	}
	```

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0095.png" width="600">

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0096.png" width="550">






