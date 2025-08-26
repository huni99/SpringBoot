<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
						<h3>${param.failMessage}</h3>
						<form method="post">
							<div class="mb-3">
								<label for="username"class="form-label"> USERNAME </label> 
								<input type="text" class="form-control"
									 id="username" name="username"  value="${cookie.rememberId.value }" >
							</div>
							<div class="mb-3">
								<label for="password" class="form-label"> password </label> 
								<input type="password" class="form-control"
									 id="password" name="password">
							</div>
							<div class="mb-3 form-check">
								
								<input type="checkbox" class="form-check-input"
									 id="check" name="rememberId"  value="1">
									 <label for="check" class="form-label"> ID 기억하기 </label> 
							</div>
							<div class="mb-3 form-check">
								
								<input type="checkbox" class="form-check-input"
									 id="remember-me" name="remember-me"  value="1">
									 <label for="remember-me" class="form-label"> 자동 로그인 </label> 
							</div>

							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
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