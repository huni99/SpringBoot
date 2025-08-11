<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css"
	rel="stylesheet">

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
						<input type="hidden" value="${detail.boardNum}">
						<div class="mb-3">
							<label for="writer" class="form-label"> Writer </label> <input
								type="text" class="form-control" value="${boardVO.boardWriter }"
								id="writer" name="boardWriter">
						</div>
						<div class="mb-3">
							<label for="Title" class="form-label"> Title </label> <input
								type="text" class="form-control" value="${boardVO.boardTitle }"
								id="Title" name="boardTitle">
						</div>
						<div class="mb-3">
							<label for=Contents class="form-label"> Contents </label>
							<textarea class="form-control" id="Contents" cols="5" rows="10"
								name="boardContents">${boardVO.boardContents}</textarea>
						</div>
						<div>
							<button class="btn btn-primary" type="button" id="add">ADD</button>
						</div>
						<div>
							<c:forEach var="f" items="${ boardVO.boardFileVOs}">
								<button class="deleteFile" data-file-num="${f.fileNum}"
									type="button">${f.oriName}</button>
							</c:forEach>
						</div>
						<div id="result"
							data-file-count="${fn:length(boardVO.boardFileVOs)}"></div>

						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
			<!-- END Content  -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script type="text/javascript" src="/js/board/board_add.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>
	
	<script type="text/javascript">
	
	
		$("#Contents").summernote({
			callbacks:{
				onImageUpload:function (files){

					const form = new FormData();
					form.append("bf",files[0]);
					fetch("./boardFile",{
						method:"post",
						body:form
					}).then(r=>r.text())
					  .then(r=>{
						  $("#Contents").summernote('editor.insertImage',r);
					  })
					  .catch(e=> console.log(e));
					},
				onMediaDelete:function(files){
					const f = $(files[0]).attr("src");// /files/notice/****.jpg
					let params = new URLSearchParams();
					params.append("fileName",f);
					fetch("./boardFileDelete",{
						method:"post",
						body:params
					}).then(r=>r.json())
					  .then(r=>{
						  alert("삭제");
					  })
					  
					
				}
			}
		});
		
	
	</script>
</body>
</html>