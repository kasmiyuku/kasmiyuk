<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
    
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
					<div class="box-header">
						<h3 class="box-title">연극</h3>
					</div>
					
					<form role="form" method="post">
						<input type="hidden" name='tno' value="${TheaterVO.ttr_no }" />
					</form>
					
					<div class="box-body">
						
						<div class="form-group">
							<label for="exampleInputEmail1">작성자</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.com_id}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">전시 종류</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.ttr_cat}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">제목</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.title}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">등록일</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.ttr_date}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">행사시작일</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.ttr_sdate}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">행사종료일</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.ttr_edate}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">행사장소</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.ttr_place}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">시간정보</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.ttr_time}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">알림</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.ttr_alert}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">내용</label> <input type="text" 
							name='title' class="form-control" value="${TheaterVO.ttr_content}" readonly="readonly">	
						</div>
					</div>
					<div class="box-footer">
						<button type="submit" class="btn btn-warning">수정</button>
						<button type="submit" class="btn btn-danger">삭제</button>
						<button type="submit" class="btn btn-primary">목록</button>
					</div>
<script>
				
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$(".btn-warning").on("click", function(){		
		formObj.attr("action", "modifypageForm");		
		formObj.attr("method", "get");		
		formObj.submit();
	});
	
	$(".btn-danger").on("click", function(){
		formObj.attr("action", "removepage");
		formObj.submit();
	});
	
	$(".btn-primary").on("click", function(){
		formObj.attr("action", "theaterlistpage");
		formObj.attr("method", "get");
		formObj.submit();
	});
	
});


</script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>