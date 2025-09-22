<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       
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
 <script src="https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
</head>
<body>
   <!--container-->
   <div class="container">
      <!--제목-->
      <div>
         <h3>Web Socket</h3>
      </div>
      <!--//제목 -------------------------------------------------->


      <!--//제목 -------------------------------------------------->
  
      <div class="row mb-3">
         <label for="nameInput" class="col-md-2 col-form-label">이름:</label>
         <div class="col-md-10">
            <input type="text" class="form-control form-control-sm" id="nameInput" name="nameInput" maxlength="100" >
            <input type="button" class="btn btn-primary btn-sm" id="sendButton" value="전송">
         </div>
      </div>     
      <div id="greetings"></div> 


   </div>   
   <!--//container end ------------------------------------------------------->
   <!--bootstrap js-->
   <script src="/ehr/resources/assets/js/bootstrap.min.js"></script>
   <script>
	   let stompClient = null;
	   
	   function connect() {
	       const socket = new SockJS('/ehr/ws');
	       stompClient = Stomp.over(socket);
	
	       stompClient.connect({}, function (frame) {
	           console.log('Connected: ' + frame);
	           // 구독
	           stompClient.subscribe('/topic/greetings', function (message) {
	        	   console.log("받은 메시지: ", message.body);
	               const greeting = JSON.parse(message.body).content;
	               console.log('greeting: ' + greeting);
	               document.getElementById('greetings').innerHTML += '<p>'+greeting+'</p>';
	           });
	       });
	   }
	   
	   
	   function sendMessage() {
	       const name = document.getElementById('nameInput').value;
	       stompClient.send("/ehr/greetings", {}, JSON.stringify({name: name}));
	   }
	   
	   const nameInput =   document.querySelector("#nameInput");
	   nameInput.addEventListener("keydown",function(event){
	       event.stopPropagation(); // 버블링 막기
	
	       if(13===event.keyCode){
	    	   sendMessage();
	       }
	   });
	   
	   
	   const sendButton = document.querySelector("#sendButton");
	   sendButton.addEventListener("click",function(event){
		   sendMessage();
	   });
	   
	   // 페이지 로드시 자동 연결
	   connect();   

   </script> 
   
      
</body>
</html>