<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="/ehr/resources/favicon.ico" type="image/x-icon"/><!-- bootstrap css-->
<link href="/ehr/resources/assets/css/bootstrap.min.css" rel="stylesheet" >
<title>즐거운_코딩</title>
<!-- <script src="./js/j06.js" defer></script> -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
</head>
<body>
<body>

  <!-- container -->
  <div class="container mt-4">
    
    <!-- 제목 -->
    <div class="mb-4 border-bottom pb-2">
      <h3>게시-등록</h3>
    </div>

    <!-- 버튼 -->
    <div class="d-flex flex-wrap justify-content-end gap-2 mb-3">
      <input type="button" class="btn btn-primary btn-sm" value="등록">
      <input type="button" class="btn btn-secondary btn-sm" value="목록">
    </div> 

    <!-- form -->
    <form action="#">

      <div class="row mb-3">
        <label for="title" class="col-md-2 col-form-label">제목</label>
        <div class="col-md-10">
          <input type="text" class="form-control" id="title" name="title" maxlength="100">
        </div>
      </div>  

      <div class="row mb-3">
        <label for="regId" class="col-md-2 col-form-label">작성자</label>
        <div class="col-md-10">
          <input type="text" class="form-control" id="regId" name="regId" maxlength="30">
        </div>
      </div>  

      <div class="row mb-3">
        <label for="contents" class="col-md-2 col-form-label">내용</label>
        <div class="col-md-10">
          <textarea class="form-control" id="contents" name="contents" rows="5"></textarea>
        </div>
      </div>

    </form>
    <!-- //form -->

  </div>
  <!-- //container -->

   <!--bootstrap js-->
   <script src="/ehr/resources/assets/js/bootstrap.min.js"></script>
</body>
</html>