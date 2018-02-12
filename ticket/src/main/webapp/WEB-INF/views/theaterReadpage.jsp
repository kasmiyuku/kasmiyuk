<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
    
<!DOCTYPE html>
<html>
<head>
<style>
	.popup{
		position:absolute;
		left:0px;
		top:0px;
	}
	.back{
		background-color:gray;
		opacity:0.5;
		width:100%;
		height:100%;
		overflow:hidden;
		z-index:1101;
	}
	.front{
		z-index:1110;				
		opacity:1;
		border:1px;	
			}
	.show{
		position:relative;
		width:50%;
		height:50%;
		margin:200px auto;
		overflow:hidden;
	}
</style>
	
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
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
					
					<form role="form" action="modifypageForm" method="post">
						<input type="hidden" name='ttr_no' value="${TheaterVO.ttr_no }" />
						
						<input type="hidden" name='page' value="${cri.page }" />
						<input type="hidden" name='perPageNum' value="${cri.perPageNum }" />
						
					</form>
					
					<div class="box-body">
						
						<div class="form-group">
							<label for="exampleInputEmail1">작성자</label> <input type="text" 
							name='com_id' class="form-control" value="${TheaterVO.com_id}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">전시 종류</label> <input type="text" 
							name='ttr_cat' class="form-control" value="${TheaterVO.ttr_cat}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">제목</label> <input type="text" 
							name='ttr_title' class="form-control" value="${TheaterVO.ttr_title}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1" >등록일</label> <input type="text" 
							name='ttr_date' class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd"
											value="${TheaterVO.ttr_date}" />" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">행사시작일</label> <input type="text" 
							name='ttr_sdate' class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd"
											value="${TheaterVO.ttr_sdate}" />" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">행사종료일</label> <input type="text" 
							name='ttr_edate' class="form-control" value="<fmt:formatDate pattern="yyyy-MM-dd"
											value="${TheaterVO.ttr_edate}" />" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">행사장소</label> <input type="text" 
							name='ttr_place' class="form-control" value="${TheaterVO.ttr_place}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">시간정보</label> <input type="text" 
							name='ttr_time' class="form-control" value="${TheaterVO.ttr_time}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">알림</label> <input type="text" 
							name='ttr_alert' class="form-control" value="${TheaterVO.ttr_alert}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">내용</label> <textarea 
							name='ttr_content' class="form-control" rows="3" readonly="readonly">${TheaterVO.ttr_content}</textarea>	
						</div>
					</div>
					<div >
					
					
					<ul class="mailbox-attachments clearfix uploadedList" ></ul>
					</div>
					<div class="box-footer">
					
						<button  type="submit" class="btn btn-warning">수정</button>
						<button  type="submit" class="btn btn-danger">삭제</button>
						<button  type="submit" class="btn btn-primary">목록</button>
					</div>
					<div class="popup back" style="display:none;"></div>
<div id="popup_front" class='popup front' style="display:none;" >
	<img id="popup_img" />
</div>	
					<script type="text/javascript" src="/resources/js/upload.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>


<script id="templateAttach" type="text/x-handlebars-template">
	<li data-src='{{fullName}}'>
		<span class="mailbox-attachment-icon has-img">
			<img src="{{imgsrc}}" alt="Attachment" />
		</span>
		<div class="mailbox-attachment-info">
			<a href="{{getLink}}" class="mailbox-attachment-name">
				{{fileName}}</a>
		</div>
	</li>
</script>
<script>
$(document).ready(function(){

	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$(".btn-warning").on("click", function(){		
		formObj.attr("action", "modifypageForm");		
		formObj.attr("method", "get");		
		formObj.submit();
	});
	
	$(".btn-danger").on("click", function() {
		
		var arr=[];
		$('.uploadedList li').each(function(index){
			arr.push($(this).attr('data-src'));				
		});
		
		if(arr.length>0){
			$.post('/deleteAllFiles',{files:arr},function(){});
		}
		
		
		formObj.attr("action", "removepage");
		formObj.submit();
	});

	$(".btn-primary").on("click", function() {
		formObj.attr("method", "get");
		formObj.attr("action", "theaterlistpage");
		formObj.submit();
	});
	
	var ttr_no=${TheaterVO.ttr_no};
	var template=Handlebars.compile($('#templateAttach').html());
	
	$.getJSON("/getAttach/"+ttr_no,function(list){
		$(list).each(function(){
			var fileInfo=getFileInfo(this);
			
			var html=template(fileInfo);
			$(".uploadedList").append(html);
		});
	});
	
	$('.uploadedList').on('click','.mailbox-attachment-info a',function(event){
		var fileLink=$(this).attr("href");
		if(checkImageType(fileLink)){
			event.preventDefault();
			
			var imgTag=$('#popup_img');
			imgTag.attr('src',fileLink);
			
			//console.log(imgTag.attr('src'));
			
			$('.popup').show('slow');
			imgTag.addClass('show');
		}
	});
	
	$('#popup_img').on('click',function(){
		$('.popup').hide('slow');
	});
});		

</script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>