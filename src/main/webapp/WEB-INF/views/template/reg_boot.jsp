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
</head>
<body>
   <!--container-->
   <div class="container">
      <!--제목-->
      <div>
         <h3>게시-등록</h3>
      </div>
      <!--//제목 -------------------------------------------------->

      <!--버튼-->
      <div class="d-flex justify-content-end gap-1">
         <button class="btn btn-primary  btn-sm"><i class="bi bi-anthropic">등록</i></button>
         <button class="btn btn-primary  btn-sm"><i class="bi bi-anthropic">목록</i></button>
      </div> 
      <!--//제목 -------------------------------------------------->

      <!--form-->
      <form action="#" >
         <div class="mb-3">
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control form-control-sm" id="title" name="title" maxlength="100" >
         </div>         
         <div class="mb-3">
            <label for="regId" class="form-label">작성자</label>
            <input type="text" class="form-control form-control-sm" id="regId" name="regId"  maxlength="30">
         </div>     
         <div class="mb-3">
            <label for="contents" class="form-label">내용</label>
            <textarea class="form-control" id="contents" name="contents" rows="5"></textarea>
         </div>                 
      </form>   
      <!--//form -------------------------------------------------->

   </div>   
   <!--//container end ------------------------------------------------------->
   <!--bootstrap js-->
   <script src="/ehr/resources/assets/js/bootstrap.min.js"></script>
   <!-- SimpleMDE js-->
   <script src="/ehr/resources/assets/js/simplemde.min.js"></script>
   
	<script>
	var simplemde = new SimpleMDE({ element: document.getElementById("contents") });
	</script>   
</body>
</html>