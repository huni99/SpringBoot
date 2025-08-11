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
					<!-- page contents 내용 -->
					<div class="row col-md-4 offset-md-4 ">
					<h1>${board}</h1>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Num</th>
									<th>Title</th>
									<th>Writer</th>
									<th>Date</th>
									<th>Hit</th>
									<th>OriName</th>
									<th>SaveName</th>
								</tr>

							</thead>
							<tbody>
								<tr>
									<td>${detail.boardNum}</td>
									<td>${detail.boardTitle}</td>
									<td>${detail.boardWriter}</td>
									<td>${detail.boardDate}</td>
									<td>${detail.boardHit}</td>
									<c:forEach var="f" items="${ detail.boardFileVOs}">
									<td><a href="./fileDown?fileNum=${f.fileNum}">${f.oriName}</a></td>
									<td>${f.saveName}</td>
									</c:forEach>

								</tr>
							</tbody>

						</table>
						<div>
							<form id="frm">
								<input type="hidden" name="boardNum" value="${detail.boardNum }">
							</form>
							<button class="btn btn-primary action" data-kind="u">Update</button>						
							<button class="btn btn-danger action" data-kind="d">Delete</button>		
							<c:if test="${board ne 'notice'}">				
							<button class="btn btn-success action" data-kind="r">Reply</button>						
							</c:if>
						</div>
					</div>

				</div>
			</div>
			<!-- END Content  -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script type="text/javascript" src="/js/board/board_detail.js"></script>
</body>
</html>