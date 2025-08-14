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
						<form method="post">
							<div class="mb-3">
								<label for="productName" class="form-label">
									product_name </label> <input type="text" class="form-control"
									value="${productVO.productName }" id="productName"
									name="productName">
							</div>
							<div class="mb-3">
								<label for="productRate" class="form-label"> product_Rate
								</label> <input type="text" class="form-control"
									value="${productVO.productRate }" id="productRate"
									name="productRate">
							</div>
							<div class="mb-3">
								<label for="productDate" class="form-label"> product_Date
								</label> <input type="date" class="form-control"
									value="${productVO.productDate }" id="productDate"
									name="productDate">
							</div>
							<div class="mb-3">
								<select class="form-control"
									name="kindNum" >
									<option value="1" <c:if test="${productVO.kindNum eq '1'}">selected</c:if>>예금</option>
									<option value="2" <c:if test="${productVO.kindNum eq '2'}">selected</c:if>>적금</option>
									<option value="3" <c:if test="${productVO.kindNum eq '3'}">selected</c:if>>대출</option>
								</select>


							</div>
							<div class="mb-3">
								<label for=Contents class="form-label"> Contents </label>
								<textarea class="form-control" id="Contents" cols="5" rows="10"
									name="productContents">${productVO.productContents}</textarea>
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