<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/include/head_css.jsp"></c:import>
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/include/topbar.jsp"></c:import>
				<div class="container-fluid">
					<div class="container-fluid justify-content-center container-md">
						<!-- page contents 내용 -->
						<form:form modelAttribute="memberVO" method="post" enctype="multipart/form-data">
							<div class="mb-3">
								<label for="name" class="form-label"> NAME </label> 
								<form:input path="name" cssClass="form-control"/>
								<form:errors path="name"></form:errors>
							</div>
							<div class="mb-3">
								<label for=EMAIL class="form-label"> EMAIL </label> 
								<form:input path="email" cssClass="form-control"/>
								<form:errors path="email"></form:errors>
							</div>
							<div class="mb-3">
								<label for="phone" class="form-label"> PHONE </label> 
							<form:input path="phone" cssClass="form-control"/>
							</div>
							<div class="mb-3">
								<label for="BIRTH" class="form-label"> BIRTH </label> 
								<input type="date" class="form-control"
									 id="BIRTH" name="birth" value= "${memberVO.birth}">
									 <form:errors path="birth"></form:errors>
							</div>
								<div class="mb-3">
								<label for="profile" class="form-label"> profile </label> 
								<input type="file" class="form-control"
									 id="profile" name="profile">
							</div>

							<button type="submit" class="btn btn-primary">Submit</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<!-- END Content  -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
</body>
</html>