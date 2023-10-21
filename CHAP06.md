# study-springboot
스프링부트 저장소 다시 만들기

## chap06. TodoList 만들기

### A. 컨트롤러 구현
1. java/.../todo 패키지 추가
2. TodoListController 클래스 추가
	```java
	@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
	public class TodoListController extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			System.out.println("/todo/list");

			List<TodoDTO> dtoList = TodoService.INSTANCE.getList();
        	request.setAttribute("list", dtoList);

			request.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(request, response);
		}
	}
	```

3. webapp/.../todo/list.jsp 추가
	```html
	<body>
	<h1>List Page</h1>

	${list}
	</body>
	```
4. TodoRegisterController 클래스 추가
	```java
	@WebServlet(name = "todoRegisterController", urlPatterns = "/todo/register")
	public class TodoRegisterController extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			System.out.println("입력화면 구성");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo/register.jsp");
			dispatcher.forward(request,response);
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			System.out.println("입력처리 후 목록페이지로 이동");
			response.sendRedirect("/todo/list");
		}
	}
	```
5. webapp/.../todo/register.jsp 추가
	```html
	<body>
	<form action="/todo/register" method="post">
	<button type="submit">등록 처리</button>
	</form>
	</body>
	```

### B. 모델 처리
1. java/.../todo/dto 패키지 추가
2. TodoDTO 클래스 추가
	```java
	import java.time.LocalDate;

	public class TodoDTO {
		private Long tno;
		private String title;
		private LocalDate dueDate;
		private boolean finished;

		public Long getTno() {
			return tno;
		}
		public void setTno(Long tno) {
			this.tno = tno;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public LocalDate getDueDate() {
			return dueDate;
		}
		public void setDueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
		}
		public boolean isFinished() {
			return finished;
		}
		public void setFinished(boolean finished) {
			this.finished = finished;
		}

		@Override
		public String toString() {
			return "TodoDTO{" +
					"tno=" + tno +
					", title='" + title + '\'' +
					", dueDate=" + dueDate +
					", finished=" + finished +
					'}';
		}
	}
	```
3. java/.../todo/service 패키지 추가
4. TodoService enum 추가
	```java
	public enum TodoService {
		INSTANCE;

		public void register(TodoDTO todoDTO){
			System.out.println("DEBUG............" + todoDTO);
		}

		public List<TodoDTO> getList() {
			List<TodoDTO> todoDTOS = IntStream.range(0,10).mapToObj(i -> {
				TodoDTO dto = new TodoDTO();
				dto.setTno((long)i);
				dto.setTitle("Todo.." +i);
				dto.setDueDate(LocalDate.now());

				return dto;
			}).collect(Collectors.toList());

			return todoDTOS;
		}

		public TodoDTO get(Long tno){
			TodoDTO dto = new TodoDTO();
			dto.setTno(tno);
			dto.setTitle("Sample Todo");
			dto.setDueDate(LocalDate.now());
			dto.setFinished(true);

			return dto;
		}
	}
	```
5. list.jsp에 JSTL 사용
	```html
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	...
	<ul>
		<c:forEach var="dto" items="${list}">
			<li>${dto}</li>
		</c:forEach>
	</ul>
	...
	```
6. TodoReadController 클래스 추가
	```java
	@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
	public class TodoReadController extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("/todo/read");

			// /todo/read?tno=123
			Long tno = Long.parseLong(req.getParameter("tno"));

			TodoDTO dto = TodoService.INSTANCE.get(tno);

			req.setAttribute("dto", dto);

			req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
		}
	}
	```

7. read.jsp 생성
	```html
	...
	<body>
	<div>${dto.tno}</div>
	<div>${dto.title}</div>
	<div>${dto.dueDate}</div>
	<div>${dto.finished}</div>
	</body>
	</html>
	```
8. 테스트
	1. http://localhost:8080/todo/register
	
	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0025.png" width="600">

	2. http://localhost:8080/todo/read?tno=123

	<img src="https://raw.githubusercontent.com/hugoMGSung/study-springboot/main/images/sb0026.png" width="600">

[Next](https://github.com/hugoMGSung/study-springboot/blob/main/CHAP07.md)