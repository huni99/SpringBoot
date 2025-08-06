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
				<div class="container-fluid justify-content-center container-md">
					<!-- page contents 내용 -->
					<form method="post" enctype="multipart/form-data">
						<input type="hidden" value="${detail.boardNum} }">
						<div class="mb-3">
							<label for="writer" class="form-label">
								Writer
							</label> 
								<input type="text" class="form-control" value="${boardVO.boardWriter }"
								id="writer" name="boardWriter">
						</div>
						<div class="mb-3">
							<label for="Title" class="form-label">
								Title
							</label> 
								<input type="text" class="form-control" value="${boardVO.boardTitle }"
								id="Title" name="boardTitle">
						</div>
						<div class="mb-3">
							<label for=Contents class="form-label">
								Contents
							</label> 
								<textarea  class="form-control"id="Contents" cols="5" rows="10" name="boardContents"
								>${boardVO.boardContents}</textarea>
						</div>
						<div class="mb-3">
							<label for="Title" class="form-label">
								File
							</label> 
								<input type="file" class="form-control" id="Attaches" name="attaches">
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
			<!-- END Content  -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
</body>
</html>