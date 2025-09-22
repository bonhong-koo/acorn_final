<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="CP" value="${pageContext.request.contextPath }" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd_HH:mm:ss" /></c:set> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="favicon.ico" type="image/x-icon"/>
<!-- bootstrap css-->
<link href="/ehr/resources/assets/css/bootstrap.min.css" rel="stylesheet" >
<!-- 아이콘 CSS 연결 -->
<link href="/ehr/resources/assets/css/bootstrap-icons.css" rel="stylesheet" >

<!-- SimpleMDE CSS-->
<link href="/ehr/resources/assets/css/simplemde.min.css" rel="stylesheet">
<title>즐거운_코딩</title>
<!-- <script src="./js/j06.js" defer></script> -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
  <script src='https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js'></script>
 <script src='https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js'></script>

<script>


 document.addEventListener('DOMContentLoaded', function(){
   console.log('DOMContentLoaded');
   

   //contorl
   const titleInput = document.querySelector("#title");
   console.log(titleInput);

   const regIdInput = document.querySelector("#regId");
   console.log(regIdInput);

   //const contentsTextarea = document.getElementById("contents");
   const contentsTextarea = document.querySelector("#contents");
   console.log(contentsTextarea);

   const divInput = document.querySelector("#div");
   console.log(divInput);

   //id 선택자
   //document.getElementById("contents")
   //class 선택자
   //document.getElementsByClassName("contents")
   //태그 선택자
   //document.getElementsByTagName("textarea")
   let stompClient = null;
   
   function connect() {
	   const socket = new SockJS('/ehr/ws');
	   stompClient = Stomp.over(socket);
	
	   stompClient.connect({}, function (frame) {
	       console.log('Connected: ' + frame);
	
	       // 구독
	       stompClient.subscribe('/topic/post', function (message) {
	           
	           console.log("받은 메시지: ", message.body);
	           const board = JSON.parse(message.body);
	           console.log('board: ' + board);
	           if(board.title){
	            showPopup(board.title, board.contents);
	           }
	
	       });
	       if(document.querySelector("#title").value){
	        stompClient.send("/ehr/post", {}, JSON.stringify({title: titleInput.value,contents:simplemde.value()}));
	       }
	   });
	} 
	
	
	function showPopup(title, content) {
	    console.log('title:'+title);
	    console.log('content:'+content);
	    
	    document.getElementById('modalPostTitle').textContent = title;
	    document.getElementById('modalPostContent').textContent = content;
	    
	    // Bootstrap 5 기준 모달 띄우기
	    const modal = new bootstrap.Modal(document.getElementById('postModal'));
	    modal.show();
	    
	}
	
	connect();     
   //등록 버튼을 자바스크립트로 가져 오기
   const doSaveButton = document.querySelector("#doSave");//id(#아이디명), class(.클래스명)
   console.log(doSaveButton);

   
   //목록으로 이동
   const moveToListButton=document.querySelector("#moveToList");
   console.log(moveToListButton);
   moveToListButton.addEventListener("click",function(event){
	   console.log('moveToListButton: click');
	   if(confirm('목록으로 이동 하시겠습니까?') === false)return;
	   window.location.href = '/ehr/board/doRetrieve.do?div='+divInput.value;
	   
   });
   
   
   //등록 버튼 이벤트 감지
   doSaveButton.addEventListener("click",function(event){
      console.log('doSaveButton: click');
      
      //제목
      console.log('titleInput.value: '+titleInput.value);

      //제목  
      if(isEmpty(titleInput.value) === true){
         alert('제목을 입력 하세요');
         titleInput.focus();
         return;
      }

      //등록자 필수 입력
      if(isEmpty(regIdInput.value) === true){
         alert('등록자를 입력 하세요');
         regIdInput.focus();
         return;
      }

      console.log("simplemde:"+simplemde.value());
      //내용 필수 입력
      if(isEmpty(simplemde.value()) === true){
         alert('내용을 입력 하세요');
         //simplemde : focuse
         simplemde.codemirror.focus();
         return;
      }    
      
      //확인(true)/취소(false)
      if(confirm('등록 하시겠습니까?') === false)return;

      //ajax 비동기 통신
      $.ajax({
         type:"POST",    //GET/POST
         url:"/ehr/board/doSave.do", //서버측 URL
         asyn:"true",    //비동기
         dataType:"html",//서버에서 받을 데이터 타입
         data:{          //파라메터
            "title": titleInput.value,
            "regId": regIdInput.value,
            "contents": simplemde.value(),
            "div": divInput.value
         },
         success:function(response){//요청 성공
             console.log("success:"+response)
             //문자열 : Javascript객체
             const message = JSON.parse(response);
             //{"messageId":1,"message":"제목99 등록 되었습니다.","no":0,"totalCnt":0,"pageSize":0,"pageNo":0}

             if( 1 === message.messageId){//등록 성공
                alert(message.message);
                connect();
                
                //목록화면으로 이동
                //window.location.href = '/ehr/board/doRetrieve.do?div='+divInput.value;
             }else{
                alert(message.message);
             }
             
             
             
         },
         error:function(response){//요청 실패
            console.log("error:"+response)
         }
         
         
      });
   });

   

   
   
   
 });
 // 페이지 로드시 자동 연결

</script>
</head>
<body>
   <!--container-->
   <div class="container">
           <div>
		    <c:choose>
		        <c:when test="${'20' == board_div }"><h2>자유 게시판 - 등록</h2></c:when>
		        <c:otherwise><h3>공지사항 - 등록</h3></c:otherwise>
		    </c:choose>               
		   </div> 
		    
	      <!--버튼 영역-->
	      <div  class="d-flex justify-content-end gap-1">
	          <!-- session존재에 따라: 등록(view/hide) -->
	         <c:if test="${not empty sessionScope.user.userId}"> 
	           <button class="btn btn-primary  btn-sm" id="doSave">등록</button>
	         </c:if>
	           <button class="btn btn-primary btn-sm"  id="moveToList" >목록</button>
	      </div>
	      <!--//버튼 영역 end-->
	       
	      <!--form-->
	      <form action="/ehr/board/doSave.do" method="post">
	         <input type="hidden" name="div" id="div" value="${board_div}">
				<div class="mb-3">
				 <label for="title" class="form-label">제목</label>
				 <input type="text" class="form-control form-control-sm" name="title" id="title" maxlength="200" required placeholder="제목" >
				</div>
				<div class="mb-3">
				   <label for="regId"  class="form-label">등록자</label>
				   <input type="text"  class="form-control form-control-sm" name="regId" id="regId" maxlength="20" required  placeholder="등록자" value="${sessionScope.user.userId}" disabled="disabled">
				</div>
				<div class="mb-3">
				   <label for="contents"   class="form-label">내용</label>
				   <textarea id="contents" class="form-control" name="contents"  placeholder="내용" class="contents"></textarea>
				</div>         
	      </form>
	      <!--//form end-->
			<!-- 모달 구조 -->
			<div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="postModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			    
			      <div class="modal-header">
			        <h5 class="modal-title" id="postModalLabel">새 게시글 알림</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
			      </div>
			      
			      <div class="modal-body">
			        <h5 id="modalPostTitle"></h5>
			        <p id="modalPostContent"></p>
			      </div>
			      
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			      </div>
			      
			    </div>
			  </div>
			</div>
   <!--bootstrap js-->
   <script src="/ehr/resources/assets/js/bootstrap.min.js"></script>
   <!-- SimpleMDE js-->
   <script src="/ehr/resources/assets/js/simplemde.min.js"></script>
   
   <script>
      var simplemde = new SimpleMDE({ element: document.getElementById("contents") });
   </script>
     
   </div>   
   <!--//container end ------------------------------------------------------->

<script>




</script> 
</body>
</html>