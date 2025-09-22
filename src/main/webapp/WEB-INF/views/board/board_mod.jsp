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
 <script>
    //DOMContentLoaded: HTML문서 loading이 완료되면 발생 되는 event
    document.addEventListener('DOMContentLoaded', function(){
    	console.log('DOMContentLoaded');
    	
    	//seq
    	const seqInput = document.querySelector("#seq");
    	console.log(seqInput);
    	
    	//title
    	const titleInput = document.querySelector("#title");
    	console.log(titleInput);
    	
    	//contents
    	const contentsTextarea = document.querySelector("#contents");
    	console.log(contentsTextarea);
    	
    	//목록
    	const moveToListButton = document.querySelector("#moveToList");
    	console.log(moveToListButton);
    	
    	//수정 버튼
    	const doUpdateButton = document.querySelector("#doUpdate");
    	console.log(doUpdateButton);
    	
    	//삭제 버튼
    	const doDeleteButton = document.querySelector("#doDelete");
    	console.log(doDeleteButton);
    	
        //수정 버튼
        const doChangeUpdateButton = document.querySelector("#doChangeUpdate");
        console.log(doChangeUpdateButton);
        
        
        doChangeUpdateButton.addEventListener('click',function(event){
            
            const textarea = document.getElementById('contentsDivMDE');
            const previewDiv = document.getElementById('contentsDivHtml');
            const button = this;            
            
            if (textarea.style.display === 'none') {   
                // 편집 모드로 전환
                textarea.style.display = 'block';
                button.style.display = 'none';
                
                previewDiv.style.display = 'none';
                document.querySelector("#doUpdate").style.display = 'block';
                // SimpleMDE 에디터 리프레시
                simplemde.codemirror.refresh();
                simplemde.codemirror.focus(); // 포커스도 줄 수 있음

            } else {
                // 미리보기 모드로 전환
                textarea.style.display = 'none';
                previewDiv.style.display = 'block';
            }
            
        });
    	
    	//목록 버튼 click event감지
    	moveToListButton.addEventListener('click',function(event){
    		
    		if(confirm('목록으로 이동 하시겠습니까?') === false)return;
            //목록화면으로 이동
            window.location.href = '/ehr/board/doRetrieve.do?div='+${divValue};    		
    	});
    	
    	
    	//수정 버튼 click event감지
    	doUpdateButton.addEventListener('click',function(event){
    		console.log('doUpdateButton click');
    		
    		//제목
    		if(isEmpty(titleInput.value) === true){
    	         alert('제목을 입력 하세요');
    	         titleInput.focus();
    	         return;    			
    		}
    		
    		//내용
    		if(isEmpty(simplemde.value()) === true){
    	         alert('내용을 입력 하세요');
    	         simplemde.codemirror.focus();
    	         return;    			
    		}
    		
    		if(confirm('게시글을 수정 하시겠습니까?') === false)return;
    		
    		
            $.ajax({
                type:"POST",    //GET/POST
                url:"/ehr/board/doUpdate.do", //서버측 URL
                asyn:"true",    //비동기
                dataType:"html",//서버에서 받을 데이터 타입
                data:{          //파라메터
                    "seq": seqInput.value,
                    "title": titleInput.value,
                    "contents": simplemde.value(),
                    "div":'${divValue}',
                    "modId":'${vo.modId}'
                },
                success:function(response){//요청 성공
                    console.log("success:"+response)
                    //문자열 : Javascript객체로 변환
                    const message = JSON.parse(response);
                
                    if( 1 === message.messageId){//삭제 성공
                        alert(message.message);

                        //목록화면으로 이동
                        window.location.href = '/ehr/board/doRetrieve.do?div='+${divValue};
                    }else{
                        alert(message.message);
                    }                     
                    
                },
                error:function(response){//요청 실패
                    console.log("error:"+response)
                }
                
                
            });    		
    		
    		
    	});
    	
    	//삭제 버튼 event감지
    	doDeleteButton.addEventListener('click',function(event){
    		console.log('doDeleteButton click');
    		
    		//seq
    		if(isEmpty(seqInput.value) === true){
    			alert("SEQ를 확인 하세요.");
    			return;
    		}
    		
    		if(confirm('게시글을 삭제 하시겠습니까?') === false)return;
    		
            $.ajax({
                type:"POST",    //GET/POST
                url:"/ehr/board/doDelete.do", //서버측 URL
                asyn:"true",    //비동기
                dataType:"html",//서버에서 받을 데이터 타입
                data:{          //파라메터
                    "seq": seqInput.value
                },
                success:function(response){//요청 성공
                    console.log("success:"+response)
                    //문자열 : Javascript객체로 변환
                    const message = JSON.parse(response);
                
                    if( 1 === message.messageId){//삭제 성공
                        alert(message.message);

                        //목록화면으로 이동
                        window.location.href = '/ehr/board/doRetrieve.do?div='+${divValue};
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
   <!--container-->
   <div class="container">
        <div>
	    <c:choose>
	        <c:when test="${20 == divValue }"><h2>자유 게시판 - 관리</h2></c:when>
	        <c:otherwise><h2>공지사항 - 관리</h2></c:otherwise>
	    </c:choose>
	    </div>    	       
          <!--버튼 영역-->
        <div  class="d-flex justify-content-end gap-1 mb-1">
	        <input type="button" id="moveToList" class="btn btn-primary btn-sm"   value="목록">
	        <input type="button" id="doChangeUpdate"   class="btn btn-success btn-sm"   value="수정">
	        <input type="button" id="doUpdate"   class="btn btn-primary btn-sm" style="display: none;"  value="저장">
	        <input type="button" id="doDelete"   class="btn btn-primary btn-sm"   value="삭제">
	    </div>  
	    <!--// Button area -->
	    
	    <!-- form area -->
	    <form action="/ehr/user/doSave.do" method="post" >
	        <input type="hidden" name="seq" id="seq" value="<c:out value='${vo.seq }'/>">
	        <input type="hidden" name="div" id="div" value='<c:out value="${divValue}" />'>
	        <div class="row mb-3">
	            <label for="userId"  class="col-md-2 col-form-label">제목</label>
	            <div class="col-md-10">
	               <input type="text"  class="form-control  form-control-sm"  maxlength="50" name="title" id="title" value="${vo.title }" >
	            </div>
	        </div>
	        
	        <div class="row mb-3">
	            <label for="name"   class="col-md-2 col-form-label">조회수</label>
	            <div class="col-md-10">
	               <input type="text"   class="form-control  form-control-sm" maxlength="9" name="readCnt" id="readCnt" disabled value="${vo.readCnt }" >
	            </div>
	        </div>     
	            
	        <div class="row mb-3">
	            <label for="name"   class="col-md-2 col-form-label">등록일</label>
	            <div class="col-md-10">
	               <input type="text"   class="form-control  form-control-sm" name="regDt" id="regDt" disabled value="${vo.modDt }">
	            </div>
	        </div>   
	        
	        <div class="row mb-3">
	            <label for="name"  class="col-md-2 col-form-label">등록자</label>
	            <div class="col-md-10">
	               <input type="text"   class="form-control  form-control-sm" maxlength="30" name="regId" id="regId" disabled="disabled" value="${vo.modId }" disabled="disabled" >
	            </div>
	        </div>                  
	   
	        <!-- 단건 수정 하기 -->
	        <div class="row mb-3">
	            <label  for="contents"  class="col-md-2 col-form-label">내용</label>
	            <div class="col-md-10" id="contentsDivMDE" style="display: none;">
	               <textarea   class="form-control" id="contents" name="contents" placeholder="내용을 입력하세요">${vo.contents }</textarea>
	            </div>
                <div class="col-md-10"  id="contentsDivHtml" >
                   <div class="border border-primary" id="contentsTextAreaHtml">${contentsTextAreaHtml}</div>
                </div>	            
	        </div>

	                                        
	    </form>  
	    <!--// form area -->
   
   <!--bootstrap js-->
   <script src="/ehr/resources/assets/js/bootstrap.min.js"></script>  
   <!-- SimpleMDE js-->
   <script src="/ehr/resources/assets/js/simplemde.min.js"></script>
   
   <script>
      var simplemde = new SimpleMDE({ element: document.getElementById("contents") });


   </script>  
     
   </div>   
   <!--//container end ------------------------------------------------------->

</body>
</html>



















