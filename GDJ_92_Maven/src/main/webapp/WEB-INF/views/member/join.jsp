<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
						<form method="post" enctype="multipart/form-data">
							<div class="mb-3">
								<label for="USERNAME" class="form-label"> USERNAME </label> 
								<input type="text" class="form-control"
									 id="USERNAME" name="username">
							</div>
							<div class="mb-3">
								<label for="PASSWORD" class="form-label"> PASSWORD </label> 
								<input type="password" class="form-control"
									 id="PASSWORD" name="password">
							</div>
							<div class="mb-3">
								<label for="NAME" class="form-label"> NAME </label> 
								<input type="text" class="form-control"
									 id="NAME" name="name">
							</div>
							<div class="mb-3">
								<label for=EMAIL class="form-label"> EMAIL </label> 
								<input type="text" class="form-control"
									 id="EMAIL" name="email">
							</div>
							<div class="mb-3">
								<label for="PHONE" class="form-label"> PHONE </label> 
								<input type="text" class="form-control"
									 id="PHONE" name="phone">
							</div>
							<div class="mb-3">
								<label for="BIRTH" class="form-label"> BIRTH </label> 
								<input type="date" class="form-control"
									 id="BIRTH" name="birth">
							</div>
								<div class="mb-3">
								<label for="profile" class="form-label"> profile </label> 
								<input type="file" class="form-control"
									 id="profile" name="profile">
							</div>

							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
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