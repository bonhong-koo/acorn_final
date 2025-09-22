<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/form.css">
<title>즐거운_코딩</title>
<!--외부에 생성한 *.js-->
<!-- <script src="/ehr/resources/assets/js/common.js"></script> -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
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


   //등록 버튼을 자바스크립트로 가져 오기
   const doSaveButton = document.querySelector("#doSave");//id(#아이디명), class(.클래스명)
   console.log(doSaveButton);

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

      //내용 필수 입력
      if(isEmpty(contentsTextarea.value) === true){
         alert('내용을 입력 하세요');
         contentsTextarea.focus();
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
            "contents": contentsTextarea.value,
            "div": divInput.value
         },
         success:function(response){//요청 성공
            console.log("success:"+response)
            //문자열 : Javascript객체
            const message = JSON.parse(response);
            //{"messageId":1,"message":"제목99 등록 되었습니다.","no":0,"totalCnt":0,"pageSize":0,"pageNo":0}

            if( 1 === message.messageId){//등록 성공
               alert(message.message);

               //목록화면으로 이동
               window.location.href = '/ehr/board/doRetrieve.do?div='+divInput.value;
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

</script>
</head>
<body>
    <div class="form-container">
    <h2>게시글 등록</h2>     
    <hr class="title-underline">
    <!-- Button area -->
    <div  class="button-area">
         <input type="button" id="doSave"     value="등록">
         <input type="button" id="moveToList" value="목록">
    </div>
    <!--// Button area -->

      <!--form-->
      <form action="/ehr/board/doSave.do" method="post">
         <input type="hidden" name="div" id="div" value="10">
        <div class="form-group">
            <label for="title" >제목</label>
            <input type="text" name="title" id="title" maxlength="200" required placeholder="제목" >
         </div>
        <div class="form-group">
            <label for="regId" >등록자</label>
            <input type="text" name="regId" id="regId" maxlength="20" required  placeholder="등록자">
         </div>
        <div class="form-group">
            <label for="contents" >내용</label>
            <textarea id="contents" name="contents"  placeholder="내용" class="contents"></textarea>
         </div>         
      </form>
      <!--//form end-->

   </div>
</body>
</html>