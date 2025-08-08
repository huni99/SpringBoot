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

					<div class="row col-md-8 offset-md-2">
						<h1 class="col-12">${board}</h1>
						
						<form id="searchForm">
									
						<div class="input-group mb-3">
							<input type="hidden" id="pageNum" name="pageNum">
							<select class="form-control" name="kind" id="inputGroupSelect04"
								aria-label="Example select with button addon">
								<option value="k1"${pager.kind eq 'k1'?'selected':'' }>Title</option>
								<option value="k2"${pager.kind eq 'k2'?'selected':'' }>Contents</option>
								<option value="k3"${pager.kind eq 'k3'?'selected':'' }>Writer</option>
							</select> 
							<input type="text" value="${pager.keyword}"name="keyword" class="form-control"
								placeholder="Recipient's username"
								aria-label="Recipient's username"
								aria-describedby="button-addon2">
							<button class="btn btn-outline-secondary" type="submit" 
								id="button-addon2">Button</button>
						</div>
						</form>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Num</th>
									<th>Title</th>
									<th>Writer</th>
									<th>Date</th>
									<th>Hit</th>
								</tr>

							</thead>
							<tbody>
								<c:forEach var="vo" items="${list}">
									<tr>
										<td>${vo.boardNum}</td>
										<td><c:catch>
												<c:forEach begin="1" end="${vo.boardDepth}">&nbsp;&nbsp;&nbsp;</c:forEach>
												<c:if test="${vo.boardDepth ne '0' }">↪</c:if>
											</c:catch> <a href="./detail?boardNum=${vo.boardNum}">${vo.boardTitle}</a>
										</td>
										<td>${vo.boardWriter}</td>
										<td>${vo.boardDate}</td>
										<td>${vo.boardHit}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<div>
							<nav aria-label="Page navigation example">
								<ul class="pagination">
									<li class="page-item"><a class="page-link pn"
										data-pn ="${pager.startNum-1}" href="#"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									</a></li>

									<c:forEach begin="${pager.startNum}" end="${pager.endNum}"
										var="i">
										<li class="page-item"><a class="page-link pn"
											data-pn ="${i}"href="#">${i} </a></li>
									</c:forEach>
									<li class="page-item"><a class="page-link pn"
										data-pn ="${pager.endNum+1}" href="#" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
									</a></li>
								</ul>
							</nav>

						</div>
						<div>
							<a class="btn btn-secondary" href="./add">글쓰기</a>

						</div>
					</div>
				</div>
			</div>
			<!-- END Content  -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script type="text/javascript" src="/js/board/board_list.js"></script>
</body>
</html>