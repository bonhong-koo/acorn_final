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
  
      <div class="mb-3">
         <label for="message" class="form-label">메시지</label>
         <input type="text" class="form-control form-control-sm" id="message" name="message" maxlength="200" >
      </div>     
      <div id="chatMessage"></div> 


   </div>   
   <!--//container end ------------------------------------------------------->
   <!--bootstrap js-->
   <script src="/ehr/resources/assets/js/bootstrap.min.js"></script>
   <script>
   let socket = new SockJS("/ehr/ws");
   let stompClient = Stomp.over(socket);
   stompClient.connect({}, function(frame) {
       stompClient.subscribe("/topic/notice", function(msg) {
           console.log("받은 메시지: ", msg.body);
           
           document.querySelector("#chatMessage").innerHTML +='<p>'+ msg.body+'</p>';
       });

       // 메시지 전송
       stompClient.send("/ehr/hello", {}, JSON.stringify({name: "Kim"}));
   });  
   
   const inputMessage =   document.querySelector("#message");
   inputMessage.addEventListener("keydown",function(event){
	   event.stopPropagation(); // 버블링 막기

	   if(13===event.keyCode){
		    stompClient.send("/ehr/hello", {}, JSON.stringify({name: inputMessage.value}));
	   }
   });
   
   </script> 
   
      
</body>
</html>