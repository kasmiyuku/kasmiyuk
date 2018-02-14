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
<style type="text/css">
	.fileDrop{
		width:80%;
		height:120px;
		border:1px dotted gray;
		background-color:lightslategray;
		margin:auto;
	}
	.fileDrop1{
		width:80%;
		height:100px;
		border:1px dotted gray;
		background-color:lightslategray;
		margin:auto;
	}
	
	.fileDrop2{
		width:80%;
		height:100px;
		border:1px dotted gray;
		background-color:lightslategray;
		margin:auto;
	}
	

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
					
						<input type="hidden" name='ttr_no' value="${theaterVO.ttr_no }"/> 
						
						<input type="hidden" name='page' value="${cri.page }" />
						<input type="hidden" name='perPageNum' value="${cri.perPageNum }" />
						<input type="hidden" name='ttr_date'  value=" <fmt:formatDate pattern="yyyy-MM-dd" value="${theaterVO.ttr_date}" />">
					<div class="box-body">
						
						<div class="form-group">
							<label for="exampleInputEmail1">작성자</label> <input type="text" 
							name='com_id' class="" value="${theaterVO.com_id}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">전시 종류</label> <input type="text" 
							name='ttr_cat' class="" value="${theaterVO.ttr_cat}" readonly="readonly">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">제목</label> <input type="text" 
							name='ttr_title' class="" value="${theaterVO.ttr_title}" >	
						</div>
						
						<div class="form-group">
							<label for="exampleInputEmail1">행사시작일</label> <input type="date" 
							name='ttr_sdate' class="" value="<fmt:formatDate pattern="yyyy-MM-dd"
											value="${theaterVO.ttr_sdate}" />" >	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">행사종료일</label> <input type="date" 
							name='ttr_edate' class="" value="<fmt:formatDate pattern="yyyy-MM-dd"
											value="${theaterVO.ttr_edate}" />" >	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">행사장소</label> <input type="text" 
							name='ttr_place' class="" value="${theaterVO.ttr_place}">	
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">시간정보</label> <input type="time" 
							name='ttr_time' class="" value="${theaterVO.ttr_time}" >	
						</div>
						<div class="form-group">
							<span >알림설정&nbsp;&nbsp;:&nbsp;&nbsp;</span> 
							<input type="radio" id="alarm_y" name='ttr_alert' class="" value="1" ><label for="alarm_y" >Yes</label>
							&nbsp;&nbsp;&nbsp;
							<input type="radio" id="alarm_n" name='ttr_alert' class="" value="0" checked><label for="alarm_n" >No</label>
							
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">내용</label> <textarea 
							name='ttr_content' class="" rows="3" >${theaterVO.ttr_content}</textarea>	
						</div>
					</div>
						
					</form>
					<div class="form-group">
							<label >썸네일</label>
						<div class="fileDrop"><ul class="mailbox-attachments clearfix uploadedList0"></ul></div>													
						</div>
						<div class="form-group">
							<label >좌석배치도</label>
						<div class="fileDrop1"><ul class="mailbox-attachments clearfix uploadedList1"></ul></div>													
						</div>
						<div class="form-group">
							<label >파일첨부</label>
							<div class="fileDrop2"><ul class="mailbox-attachments clearfix uploadedList2"></ul></div>	
					<div class="box-footer">
						<button type="submit" class="btn btn-warning">수정</button>
						<button type="submit" class="btn btn-primary">취소</button>
					</div>
					<div class="popup back" style="display:none;"></div>
<div id="popup_front" class='popup front' style="display:none;" >
	<img id="popup_img" />
</div>	


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
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning").on("click", function() {
			formObj.attr("action", "modifypage");
			formObj.attr("method", "post");
			formObj.submit();
		});

		$(".btn-primary").on("click", function() {
			
			self.location="theaterlistpage";
		});

	});
	var template = Handlebars.compile($('#template').html());

	$("body").on("dragenter dragover",function(event){
		event.preventDefault();
	});

	$("body").on("drop",function(event){
		event.preventDefault();
	});

	$('.fileDrop,.fileDrop1,.fileDrop2').on('drop',function(event){
		var files=event.originalEvent.dataTransfer.files;
		var file=files[0];
		
		var formData=new FormData();
		
		formData.append("file",file);
		
		var uploadZone=$(this).find("ul");
		$.ajax({
			url:'/uploadAjax',
			data:formData,
			processData:false,
			contentType:false,
			dataType:'text',
			type:'post',
			success:function(result){
				var fileInfo=getFileInfo(result);
				var html=template(fileInfo);
				uploadZone.append(html);
			}
		});
	});


	$('.uploadedList0').on('click','.delbtn',function(event){
		event.preventDefault();
		
		var that=$(this);
		that.parent('div').parent('li').remove();
	});
	$('.uploadedList1').on('click','.delbtn',function(event){
		event.preventDefault();		
		var that=$(this);
		that.parent('div').parent('li').remove();
	});
	$('.uploadedList2').on('click','.delbtn',function(event){
		event.preventDefault();
		var that=$(this);
		that.parent('div').parent('li').remove();
	});
	

	var ttr_no=${theaterVO.ttr_no};

	$.getJSON("/getAttach/"+ttr_no,function(list){
		var i=0;
		$(list).each(function(){
			$(this).each(function(){				
				var fileInfo=getFileInfo(this);
				
				var html=template(fileInfo);
				$(".uploadedList"+i).append(html);
			});
			i++;
		});
	});

/* 
	$('.uploadedList,.uploadedList1,.uploadedList2').on('click','.mailbox-attachment-info a',function(event){
			
		var fileLink=$(this).attr("href");
		
		if(checkImageType(fileLink)){
			event.preventDefault();
			
			var imgTag=$('#popup_img');
			imgTag.attr("src",fileLink);
			
			$('.popup').show("slow");
			imgTag.addClass("show");
		}
	});

	$('#popup_img').on('click',function(){
		$('.popup').hide("slow");
	}); */




	$(document).ready(function(){
			var formObj = $("form[role='form']");
			console.log(formObj);
			formObj.submit(function(event){
				event.preventDefault();
				var that=$(this);
				var str="";
				$('.uploadedList0 .delbtn').each(function(index){
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
		
		});
</script>

				</div>
			</div>
		</div>
	</section>
</body>
</html>