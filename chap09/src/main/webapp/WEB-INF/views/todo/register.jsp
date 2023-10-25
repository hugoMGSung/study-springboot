<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8'>
	<meta http-equiv='X-UA-Compatible' content='IE=edge'>
	<title>Register</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<form action="/todo/register" method="post">
		<div>
			Title: <input type="text" name="title">
		</div>
		<div>
			DueDate: <input type="date" name="dueDate" value="2023-10-24">
		</div>
		<div>
			Writer: <input type="text" name="writer">
		</div>
		<div>
			Finished: <input type="checkbox" name="finished">
		</div>
		<div>
			<button type="submit">등록</button>
		</div>
	</form>	
</body>
</html>