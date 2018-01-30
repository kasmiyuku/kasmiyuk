<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
    
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<!-- Main content -->
	<section class="content">
		<div class="row">
			<!-- left column -->
			<div class="col-md-12">
				<!-- general form elements -->

				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">연극</h3>
					</div>
					<div class="box-body">
					<a href="createBoard"><button class="btn btn-primary">글쓰기</button></a>
					
						<table class="table table-bordered">
							<tr>
								<th>글번호</th>
								<th>전시종류</th>
								<th>제목</th>
								<th>아이디</th>
								<th>등록일</th>
								
								
							</tr>


							<c:forEach items="${list}" var="TheaterVO">

								<tr>
									<td>${TheaterVO.ttr_no}</td>
									<td>${TheaterVO.ttr_cat}</td>
									<td><a href='theaterReadpage${page.makeQuery(page.cri.page) }&ttr_no=${TheaterVO.ttr_no}'>${TheaterVO.ttr_title}</a></td>
									<td>${TheaterVO.com_id}</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
											value="${TheaterVO.ttr_date}" /></td>
									
								</tr>

							</c:forEach>

						</table>

					</div>
					<!-- /.box-body -->
					<div class="box-footer">Footer</div>
					<!-- /.box-footer-->
				</div>
			</div>
			</div>
			<!--/.col (left) -->

		<!-- /.row -->
	</section>
	<!-- /.content -->
<!-- /.content-wrapper -->
</body>
</html>