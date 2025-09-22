<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/form.css">
<title>게시글 관리</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
</head>
<body>     
    <div class="form-container">
    <h2>파일 업로드</h2>     
    <hr class="title-underline">
       
    <!-- Button area -->

    <!--// Button area -->
    
    <!-- form area -->
    <form action="/ehr/file/fileUpload.do" method="post" enctype="multipart/form-data" >
        <div class="form-group">
            <label for="pFile">파일</label>
            <input type="file"   name="pFile" id="pFile" >
        </div>                    
        <div class="form-group">
            <label for="uploadBtn">업로드</label>
            <input type="submit"   value="업로드" id="uploadBtn" >
        </div>          
                  
    </form>
    <!--// form area -->
  </div>  
</body>
</html>