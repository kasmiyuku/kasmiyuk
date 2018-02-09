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
	.uploadedList ul {
		overflow:hidden;
	}
	.uploadedList li{
		list-style-type:none;
		float:left;
		margin-left:10px;
		
	}
</style>
	
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-3.1.0.min.js"></script>
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
		var title=["썸네일","좌석이미지","첨부파일"];
		for(var i=0;i<list.length;i++){
			$(".uploadedList").append("<h2>"+title[i]+"</h2>");
			$(list[i]).each(function(){
				var fileInfo=getFileInfo(this);			
				var html=template(fileInfo);			
				$(".uploadedList").append("<ul>"+html+"</ul>");
			});
		}
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
		<div class="row">
			<div class="col-md-12">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">평점쓰기</h3>
					</div>
					<div class="box-body">
						<label for="newReplyWriter">아이디</label>
						<input class="form-control" type='text' placeholder="ISER ID" id="newReplyWriter"/>
						<label for="newReplyText">내용</label>
						<input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText"/>
					</div>
					<div>
						<div class="box-footer">
							<button type="button" class="btn btn-primary" id="replyAddbtn">확인</button>
						</div>
					</div>
					<ul class="timeline">
						<li class="time-label" id="repliesDiv">
							<span class="bg-green">리스트</span>
					</ul>
					<div class="text-center">
						<ul id="pagination" class="pagination pagination-sm no-marg">
						</ul>
					</div>
				</div>
			</div>
			<div id="modifyModal" class="modal modal-primary fade" role="dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							&times;
						</button>
						<h4 class="model-title"></h4>
					</div>
					<div class="modal-body" data-rno>
						<p>
							<input type="text" id="replytext" class="form-control">
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" id="replymodBtn">수정</button>
						<button type="button" class="btn btn-danger" id="replyDelBtn">삭제</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.js"></script>
	<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li class="replyLi" data-tr_rno={{tr_rno}}>
			<i class="fa fa-comments bg-blue"></i>
			<div class="timeline-item">
				<span class="time">
					<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
				</span>
				<h3 class="timeline-header"><strong>{{tr_rno}}</strong>-{{replyer}}</h3>
				<div class="timeline-body">{{replytext}}</div>
				<div class="timeline-footer">
					<a class="btn btn-primary" btn-xs" data-toggle="modal" data="#modifyModal">Modify</a>
				</div>
			</div>
		</li>
	{{/each}}
	</script>
	<script>
		Handlebars.registerHelper("prettifyDate", function(timeValue){
				var dateObj=new Date(timeValue);
				var year=dateObj.getFullYear();
				var month=dateObj.getMonth();
				var date=dateObj.getDate();
				return year+"/"+month+"/"+date;
		});
		
		var printData=function(replyArr, target, temlpateObject){
			var template=Handlebars.compile(temlpateObject.html());
			var html=template(replyArr);
			$('.replyLi').remove();
			target.after(html);
		};
		
		var ttr_no=${TheaterVO.ttr_no};
		var replyPage=1;
		
		function getPage(pageInfo){
			$.getJSON(pageInfo,function(data){
				printData(data.list,$('#repliesDiv'),$('#template'));
				printPaging(data.page,$('.pagination'));
			});
		};
		
		getPage("/replies/"+ttr_no+"/1");
		
		var printPaging=function(page,target){
			var str="";
			if(page.prev){
				str+="<li><a href='"+(page.startPage-1)+"'> << </a></li>";
			};
			for(var i=page.startPage,len=page.endPage;i<=len;i++){
				var strClass=page.cri.page==i?'class=active':'';
				str+="<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
			};
			if(page.next){
				str+="<li><a href='"+(page.endPage+1)+"'> >> </a></li>";
			};
			target.html(str);
		};
		$('.pagination').on('click','li a',function(event){
			event.preventDefault();
			replyPage=$(this).attr('href');
			getPage("/replies/"+ttr_no+"/"+replyPage)
		});
		$('#replyAddbtn').on('click',function(event){
			var replyer=$('#newReplyWriter').val();
			var replytext=$('#newReplyText').val();
			
			$.ajax({
				type:'post',
				url:'/replies/',
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"post"
				},
				dataType:'text',
				data:JSON.stringify({
					ttr_no:ttr_no,
					replyer:replytext
				}),
				success:function(data){
					if(data=='SUCCESS'){
						alert('평점을 남겨주셔서 감사합니다.');
						replyPage=1;
						getPage("/replies/"+ttr_no+"/"+replyPage);
						$('#newReplyWriter').val("");
						$('#new ReplyText').val("");
					}
				}
			});
		});
		$('.timeline').on('click','.replyLi',function(event){
			var reply=$(this);
			$('#replytext').val(reply.find('.timeline-body').text());
			$('.modal-title').html(reply.attr('data-tr_rno'));
		});
		$('#replyModBtn').on('click',function(){
			var tr_rno=$('modal-title').html();
			var replytext=$('#replytext').val();
			$.ajax({
				type:'put',
				url:"/replies/"+tr_rno,
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"PUT"
				},
				data:JSON.stringify({replytext:replytext}),
				dateType:'text',
				success:function(result){
					if(result=='SUCCESS'){
						alert("평점을 재평가 해주셔서 감사합니다.");
						$('#modifyModal').modal('hide');
						getPage("/replies/"+ttr_no+"/"+replyPage);
					}
				}
			});
		});
		
		$('#replyDelBtn').on('click',function(){
			var tr_rno=$('.modal-title').html();
			
			$.ajax({
				type:'delete',
				url:"/replies/"+tr_rno,
				headers:{
					"Content-Type":"application/json",
					"X-HTTP-Method-Override":"DELETE"
				},
				dataType:"text",
				success:function(result){
					if(result='SUCCESS'){
						alert("삭제됬습니다.");
						$('#modifyModal').modal('hide');
						getPage("/replies/"+ttr_no+"/"+replyPage);
					}
				}
			});
		});
	</script>
</body>
</html>