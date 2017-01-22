<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>

<title>Application to departments</title>

<style>
body {
	width: 900px;
	margin: 0 auto;
}

h3 {
	margin: 50px 0;
}

a {
	background: greenyellow;
	border-radius: 5px;
	padding: 5px;
	margin: 0 10px;
	color: #872c1e;
}

.separate_line {
	border: 0;
	height: 1px;
	background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
	background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
	background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
	background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
	margin: 10px 0px;
}

.department_block{
	position: relative;
	display: block;
	width: 50%;
	border: 1px green solid;
	margin: 20px;
	padding: 5px;
	border-radius: 5px;
	background: aliceblue;
	box-shadow: 0 0 5px black;
}

.department_block:hover{
	background: #e0effc;
	box-shadow: 0 0 10px green;
}

.department_block span{
	font-style:italic;
}

.department_block div{
	width: 100%;
	margin: 5px;
}

.head_department{
	text-align: center;
	position: relative;
	width: 100%;
	margin: 15px 0;
	font-weight:bold;
}

.send_application input[type=submit]{
	margin: 0px auto;
	position: relative;
	display: block;
	background: rgb(143, 216, 255) none repeat scroll 0% 0%;
	padding: 5px;
	border-radius: 5px;
	box-shadow: 0px 0px 5px yellow;
}
</style>
</head>
<body>
	<h3>At this page you can add or delete your application to the department.</h3>
	
	
	
	<c:forEach items="${departments}" var="department" varStatus="loop">
		<div class="department_block">
			<div class="head_department">${department.nameDepartment}</div>
			<div class="required_subjects"><span>Required subject(s):</span>
				<c:forEach items="${department.necessaryItems}" var="subject" varStatus="status">
					${subject.name}<c:if test="${ ! status.last}" >, </c:if>
				</c:forEach>
			</div>
			<div class="max_enrollee"><span>Max amount student: </span>${department.maxAmountStudent}</div>
			<div class="send_application">
				<form action="<c:url value='./set_application_to_departments_post' />" method="post">
					<input type="hidden" name="departmentId" value="${department.id}" />
					<input type="submit" value="Send application to this department"
						style="position: relative; top: 7px; left: 20px;">
				</form>
			</div>
			
			
			<c:if test="${userSubjects.containsAll(department.necessaryItems)}">
				<div>Contain!!!</div>
			</c:if>
	
		</div>
		
	</c:forEach>
	

	<hr class="separate_line">

	<a href="<c:url value='./home' />">Go to home page</a>
	<a href="<c:url value='./logout' />">Log out</a>

</body>
</html>