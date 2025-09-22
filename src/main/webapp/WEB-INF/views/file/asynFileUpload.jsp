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

   const uploadButton =  document.querySelector("#doSave");
   console.log(uploadButton);
   const fileInput =  document.querySelector("#pFile");
   console.log(fileInput);
   
   
   uploadButton.addEventListener("click",function(event){
	   console.log('uploadButton click');
	   event.stopPropagation();// 이벤트 버블링 중지
	   
	    const formData = new FormData();
	    formData.append("pFile", fileInput.files[0]);
	    
        $.ajax({
            type: "POST",
            url: "/ehr/file/asynFileUpload.do",
            processData: false,
            contentType: false,
            async: true,
            dataType: "html", //문자열
            data: formData,  
            success: function(response) {
                console.log("success:"+response)
                //문자열 : Javascript객체
                const data = JSON.parse(response);
                
            	const resultDiv = document.getElementById("resultArea");
            	
            	if(data.fileType === 'image'){
            		resultDiv.innerHTML ='<img src="'+data.fileUrl+'" width=100% />';
            	}else{
            		resultDiv.innerHTML ='<p>'+data.fileUrl+'</p>';
            	}
            	       
                

            },
            error: function(response) {
                console.log("error:" + response);
            }
        });   
        
        
   });
   
   
   
   
 });

</script>
</head>
<body>
    <div class="form-container">
    <h2>파일 업로드(비동기)</h2>     
    <hr class="title-underline">
    <!-- Button area -->
    <div  class="button-area">
         <input type="button" id="doSave"     value="등록">
    </div>
    <!--// Button area -->

      <!--form-->
    <form action="#" method="post" enctype="multipart/form-data" >
        <div class="form-group">
            <label for="pFile">파일</label>
            <input type="file"   name="pFile" id="pFile" >
        </div>          
    </form>
    <!--//form end-->
    
    
    <div id="resultArea"></div>
    
    
    
   </div>
</body>
</html>