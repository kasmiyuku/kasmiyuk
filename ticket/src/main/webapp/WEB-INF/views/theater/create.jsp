<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
		.fileDrop{
			width:80%;
			height:200px;
			border:1px dotted gray;
			background-color:lightslategray;
			margin:auto;
		}	
		.fileDrop1{
			width:80%;
			height:200px;
			border:1px dotted gray;
			background-color:lightslategray;
			margin:auto;
		}
			.fileDrop2{
			width:80%;
			height:200px;
			border:1px dotted gray;
			background-color:lightslategray;
			margin:auto;
		}
	</style>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
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

					<form role="form" id="registerForm" action="create" method="post">

						<input type="hidden" name='page' value="${cri.page }" /> 
						<input type="hidden" name='perPageNum' value="${cri.perPageNum }" />


						<div class="box-body">

							<div class="form-group">
								<label for="exampleInputEmail1">작성자</label> <input type="text"
									name='com_id' class="" value="${theaterVO.com_id}">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">전시 종류</label> <input type="text"
									name='ttr_cat' class="" value="${theaterVO.ttr_cat}">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">제목</label> <input type="text"
									name='ttr_title' class="" value="${theaterVO.ttr_title}">
							</div>

							<div class="form-group">
								<label for="exampleInputEmail1">행사시작일</label> <input type="date"
									name='ttr_sdate' class=""
									value="<fmt:formatDate pattern="yyyy-MM-dd"
											value="${theaterVO.ttr_sdate}" />">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">행사종료일</label> <input type="date"
									name='ttr_edate' class=""
									value="<fmt:formatDate pattern="yyyy-MM-dd"
											value="${theaterVO.ttr_edate}" />">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">행사장소</label> <input type="text"
									name='ttr_place' class="" value="${theaterVO.ttr_place}">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">시간정보</label> <input type="time"
									name='ttr_time' class="" value="${theaterVO.ttr_time}">
							</div>
							<div class="form-group">
								<span>알림설정&nbsp;&nbsp;:&nbsp;&nbsp;</span> <input type="radio"
									id="alarm_y" name='ttr_alert' class="" value="1"><label
									for="alarm_y">Yes</label> &nbsp;&nbsp;&nbsp; <input
									type="radio" id="alarm_n" name='ttr_alert' class="" value="0"
									checked><label for="alarm_n">No</label>

							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">내용</label>
								<textarea name='ttr_content' class="" rows="3">${theaterVO.ttr_content}</textarea>
							</div>
							<div><label>좌석정보</label><button id="addseat" type="button">+</button>
								<table id="seat_table">
									<tr>
										<td>등급</td>
										<td>좌석수</td>
										<td>가격</td>
										<td>날짜</td>
										<td>시간</td>
									</tr>
								</table>
						<div class="form-group">
							<label >썸네일</label>
						<div class="fileDrop"><ul class="mailbox-attachments clearfix uploadedList"></ul></div>													
						</div>
						<div class="form-group">
							<label >좌석배치도</label>
						<div class="fileDrop1"><ul class="mailbox-attachments clearfix uploadedList1"></ul></div>													
						</div>
						<div class="form-group">
							<label >파일첨부</label>
							<div class="fileDrop2"><ul class="mailbox-attachments clearfix uploadedList2"></ul></div>	
																			
						</div>
						
						</div>
						<div class="box-footer">
						
							<button  type="submit" class="btn btn-primary">저장</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</section>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script id="template" type="text/x-handlebars-template">
<li style="width:10%;font-size:0.8em;">
	<span class="mailbox-attachment-icon has-img">
      <img src="{{imgsrc}}" alt="Attachment"></span>
    <div class="mailbox-attachment-info">
	  <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
      <a href="{{fullName}}"
         class="btn btn-default btn-xs pull-right delbtn">
			<i class="fa fa-fw fa-remove"></i></a>
    </div>
</li>
</script>
<script>
	
	var template = Handlebars.compile($('#template').html());
	$("body").on("dragenter dragover",function(event){
		event.preventDefault();
	});
	
	$("body").on("drop",function(event){
		event.preventDefault();
	});
	
	$(".fileDrop,.fileDrop1,.fileDrop2").on("drop",function(event){
		//event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		var file=files[0];
		
		//alert(file);
		
		var formData=new FormData();
		formData.append("file",file);
		
		var uploadZone=$(this).find("ul");
		
		$.ajax({
			url:"/uploadAjax",
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			dataType:'text',
			success:function(data){
				var fileInfo=getFileInfo(data);
				var html=template(fileInfo);				
				uploadZone.append(html);
				
			}		
			
		});
		
	});
	
	$('#registerForm').submit(function(event){
		event.preventDefault();
		
		var that=$(this);
		var str="";
		$('.uploadedList .delbtn').each(function(index){
			str+="<input type='hidden' name='thumFiles'"
			+"value='"+$(this).attr('href')+"'>";
		});
		
		$('.uploadedList1 .delbtn').each(function(index){
			str+="<input type='hidden' name='seatFiles'"
			+"value='"+$(this).attr('href')+"'>";
		});
		
		$('.uploadedList2 .delbtn').each(function(index){
			str+="<input type='hidden' name='files'"
			+"value='"+$(this).attr('href')+"'>";
		});
		
		that.append(str);
		that.get(0).submit();
	});
	
	$('.uploadedList,.uploadedList1,.uploadedList2').on('click','.delbtn',function(event){
		event.preventDefault();
		
		var that=$(this);
		
		$.ajax({
			url:'/deleteFile',
			type:'post',
			dataType:'text',
			data:{fileName:$(this).attr("href")},
			success:function(result){
				if(result=='deleted'){
					that.parent('div').parent('li').remove();
				}
			}
		});
	});
	('#addseat').on('click',function(event){
		if($('.seat_info').length<11){
			
		
		var str="<tr class='seat_info'><input type='hidden' name='seat_id'><td><input type='text' name='seat_grd'></td><td><input type='number' name='seat_no'></td><td><input type='number' name='seat_pri'></td><td><input type='date' name='seat_date'></td><td><input type='time' name='seat_time'></td><td><input type='button' class='delseat' value='-'></tr>";
		$('#seat_table').append(str);
		}
	})
	
	
</script>
</body>
</html>