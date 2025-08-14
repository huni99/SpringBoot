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
				<div class="container text-center container-fluid">
					<!-- page contents 내용 -->
					<div class="row col-md-8 offset-md-2">
						<h1>List</h1>
						<table class="table table-striped ">
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">상품명</th>
									<th scope="col">날짜</th>
									<th scope="col">이자율</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="l" items="${list}">
									<tr>
										<td>${l.productNum}</td>
										<td><a href="/products/detail?productNum=${l.productNum}">${l.productName}</a></td>
										<td>${l.productDate}</td>
										<td>${l.productRate}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<a class="btn btn-outline-warning" href="/products/add" role="button">상품등록</a>
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