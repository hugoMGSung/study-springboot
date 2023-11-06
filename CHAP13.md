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

5. 첨부파일 저장시 파일명 중복문제등 해결을 위해 UUID 사용함
6. 실제 경로에 저장되도록 변경

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0097.png" width="550">

7. build.gradle에 Thumbnail 라이브러리 추가
8. UpDownController에 썸네일 처리를 추가

	이미지가 모두 되는 것은 아님, jfif 와 같은 휘귀한 이미지파일은 썸네일 못만듬

9. dto/upload/UploadResultDTO 클래스 생성(결과 리턴용)
10. 첨부파일 업로드 및 결과 리턴 처리

	String -> List<UploadResultDTO>

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0098.png" width="600">

### C. 첨부파일 조회/삭제
1. UpDownController에 viewFileGET() 메서드 작성
2. Swagger-ui에서 미리 집어넣어둔 aaa.jpg로 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0099.png" width="600">

3. UpDownController에 removeFile() 메서드 작성
4. 파일 삭제 테스트 확인

### D. Board/BoardImage 처리
1. domain/BoardImage 클래스 생성 @ManyToOne 적용
2. domain/Board 클래스에 @OneToMany 적용
3. DB에 reply, board 테이블 삭제 후 다시 프로젝트 실행

4. repository/ReplyRepository 인터페이스 delete..() 추가
5. test/.../BoardRepositoryTests에 ReplyRepository 추가
6. repository/BoardRepository 인터페이스에 findByIdWithImages() 추가
7. test/.../BoardRepositoryTests testReadWithImages() 작성 테스트
8. N+1 문제 테스트를 위한 코드 testInsertAll() 작성 테스트
9. repository/search/BoardSearch 인터페이스에 searchWithAll() 메서드 추가
10. BoardSearchImpl에 searchWithAll() 메서드 구현
11. BoardRepositoryTests에 테스트코드 작성

12. dto/BoardListAllDTO, BoardImageDTO 클래스 작성
13. service/BoardService에 listWithAll() 추가
14. service/BoardServiceImpl에 listWithAll() 구현
15. repository/search/BoardSearch searchWithAll() 의 리턴타입 BoardListAllDTO로 변경
16. BoardSearchImpl 의 searchWithAll() 변경
17. test/.../repository/BoardRepositoryTests에 testSearchImageReplyCount() 수정 테스트
18. 검색조건 추가

19. dto/BoardDTO 파일이름 리스트 추가
20. service/BoardService 에 dtoToEntry default메서드 작성
21. service/BoardServiceImpl에 register() 수정
22. BoardServiceTests 의 testRegisterWithImages() 작성 

	테이블 맨 마지막에 user00의 데이터 추가 확인할 것

23. BoardServiceImpl에 readOne() 메서드 작성 
24. BoardServiceTests testReadAll() 작성 테스트.

25. 게시물 수정, 삭제, 목록 처리 통합
	BoardServiceImpl에 modify() 수정, remove(), list() 등
26. BoardServiceTests에 testModify(), testRemoveAll(), testListWithAll() 작성 테스트.


### E. 컨트롤러와 화면 처리
1. reosurces/templates/register.html 의 submit() 처리를 Ajax 방식으로 변경. 첩부파일 업로드 버튼 추가
2. Axios처리 위한 upload.js resource/static/js에 생성
3. register.html 에 upload.js 사용위해 스크립트 추가
4. controller/BoardController 클래스 PostMapping("/register") 메서드 수정

5. UpDownController /upload 메서드 파라미터 UploadFileDTO 로 변경
	```java
	@Operation(summary = "Upload POST")
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
		log.info(uploadFileDTO);
		// ... 	
	```

6. 파일 업로드 테스트

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0100.png" width="600">

	여러개 업로드 아직 에러. 확인해야 함

7. controller/BoardController list() 메서드 수정

	BoardListReplyCountDTO 에서 BoardListAllDTO 로 변경

8. board/list.html 수정 BoardListAllDTO 객체의 boardImages 이용 출력할 때 첨부파일 보여주도록 변경

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0101.png" width="600">

9. board/read.html 에 이미지 출력 내용 추가
10. board/modify.html 로 게시물 수정과 삭제
