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
					<div class="container text-center border border-primary w-75 p-3" style="height:600px" >
						<div class="row border-bottom border-dark fs-2"style="font-size:20px">
							<div class="col-sm-4"style="text-align: start;">product_num</div>
							<div class="col-sm-4">
								<div>${detail.productNum }</div>
							</div>
						</div>

						<div class="row border-bottom border-dark" style="font-size:20px">
							<div class="col-sm-4 "style="text-align: start;">product_name</div>
							<div class="col-sm-4">
								<div >${detail.productName }</div>
							</div>
						</div>
						<div class="row border-bottom border-dark "style="font-size:20px">
							<div class="col-sm-4"style="text-align: start;">product_name</div>
							<div class="col-sm-4">
								<div >${detail.productContents }</div>
							</div>
						</div>
						<div class="row border-bottom border-dark"style="font-size:20px" >
							<div class="col-sm-4"style="text-align: start;">product_Date</div>
							<div class="col-sm-4">
								<div>${detail.productDate}</div>
							</div>
						</div>
						<div class="row border-bottom border-dark"style="font-size:20px">
							<div class="col-sm-4"style="text-align: start;">product_rate</div>
							<div class="col-sm-4">
								<div>${detail.productRate }</div>
							</div>
						</div>
						<div class="row border-bottom border-dark"style="font-size:20px">
							<div class="col-sm-4"style="text-align: start;">kind_name</div>
							<div class="col-sm-4">
								<div>${detail.productKindVO.kindName }</div>
							</div>
						</div>
						<div class="row">
							<form id="frm">
								<input type="hidden" name="productNum" value="${detail.productNum }">
							</form>
							<button class="btn btn-primary action" data-kind="u">Update</button>						
							<button class="btn btn-danger action" data-kind="d">Delete</button>	
							<button class="btn btn-primary action" id="cartAdd" data-product-num="${detail.productNum}">장바구니</button>
							<button class="btn btn-danger action" id="Account" data-product-num="${detail.productNum}">상품 가입</button>	
													
						</div>
						<div>
					</div>
					</div>
				</div>
			</div>
			<!-- END Content  -->
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	<script type="text/javascript" src="/js/product/product_detail.js"></script>
</body>
</html>