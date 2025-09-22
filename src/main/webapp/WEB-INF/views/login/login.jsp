<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>


<c:set var="CP" value="${pageContext.request.contextPath }" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function(){
	console.log('DOMContentLoaded');
	//class
	//const loginButton = document.querySelector(".login-btn");
	
	//lang
	const langSelect = document.querySelector("#lang");
	console.log(langSelect);
	//id
	const loginButton = document.querySelector("#loginBtn");
	console.log(loginButton);
	
	const userIdInput = document.querySelector("#userId");
	console.log(userIdInput);
	
    const passwordInput = document.querySelector("#password");
    console.log(passwordInput);	
	
    //change  이벤트 핸들러 등록
    langSelect.addEventListener("change",function(event){
    	const selectLang = langSelect.value;
    	console.log('langSelect change:'+selectLang);
    	
    	window.location.href="/ehr/login/lang.do?lang="+selectLang;
    	
    });
    
    
    loginButton.addEventListener("click",function(event){
    	console.log('loginButton click');
    	
        //아이디
        console.log('userIdInput.value: '+userIdInput.value);    
        if(isEmpty(userIdInput.value) === true){
        	alert('아이디를 입력 하세요');
        	userIdInput.focus();
        	return;
        }
        
        if(isEmpty(passwordInput.value) === true){
            alert('비밀번호를 입력 하세요');
            passwordInput.focus();
            return;
        }
        
        $.ajax({
            type:"POST",    //GET/POST
            url:"/ehr/login/login.do", //서버측 URL
            asyn:"true",    //비동기
            dataType:"html",//서버에서 받을 데이터 타입
            data:{          //파라메터
                "userId": userIdInput.value,
                "password":passwordInput.value
            },
            success:function(response){//요청 성공
                console.log("success:"+response)
                const message = JSON.parse(response);
                //messageId->10 :id불일치
                //messageId->20 :비번 불일치
                //messageId->30 :id/비번 일치
                //pcwk01,4321
                if(10 === message.messageId){
                	alert(message.message);
                	userIdInput.focus();
                	return;
                }else if(20 === message.messageId){
                	alert(message.message);
                	passwordInput.focus();
                	return;
                }else if(30 === message.messageId){
                	alert(message.message);
                	
                	//main페이지로 이동
                	window.location.href='/ehr/main/menu.do';
                	return;
                }else{
                	alert(message.message);
                }
                
                
            },
            error:function(response){//요청 실패
                console.log("error:"+response)
                alert(response);
            }
            
            
        });
        
        
        
        
    });
});


</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Nanum+Pen+Script&display=swap');
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }  
body{
    font-family: "Nanum Gothic", sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    min-width: 100vh;
    background-color: #f4f4f9;
    height: 100vh;
}

.login-container {
   background-color: #ffffff;
   padding: 20px 30px;
   border-radius: 10px;
   box-shadow: 0 4px 6px rgba(0,0,0,0.1);
   min-width: 300px;

}

.login-container h2 {
   text-align: center;
   margin-bottom:  20px; 
}

.form-group {
   margin-bottom: 15px;
}

.form-group label {
    display: block;
    font-size: 14px;
    margin-bottom: 5px;
    color: #333333;
}

.form-group input {
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 10px;
    width: 100%;
    font-size: 14px;

}

.form-group select {
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 10px;
    width: 100%;
    font-size: 14px;

}

.form-actions {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 20px;
}

.form-actions button {
    padding: 10px 20px;
    font-size: 14px;
    border: none;
    border-radius: 5px;
    cursor: pointer;   
    color: #fff;
    transition: background-color 0.3s;
} 

.form-actions .login-btn {
    background-color: #007bff;
}           

.form-actions .login-btn:hover {
    background-color: #0056b3;
}       

.form-actions .signup-btn {
    background-color:#91ac25; 
}           

.form-actions .signup-btn:hover {
    background-color: #759103;   
}    
   
</style>  
</head>
<body>

   <div class="login-container">  
   <h2>로그인</h2>
   <form action="#" method="post" >
    <div class="form-group">
        <label for="lang"></label>
        <select id="lang" name="lang" >
          <option value="ko" <c:if test="${lang == 'ko' }">selected</c:if> >ko</option>
          <option value="en" <c:if test="${lang == 'en' }">selected</c:if> >en</option>
          <option value="fr" <c:if test="${lang == 'fr' }">selected</c:if> >fr</option>
        </select>
    </div>
        
    <div class="form-group">
        <label for="userId"><spring:message code="message.user.login.id" text="아이디" />:</label>
        <input type="text" name="userId" id="userId" placeholder='<spring:message code="message.user.login.id.placeholder" text="아이디를 입력 하세요." />' maxlength="30">
    </div>
    <div class="form-group">
        <label for="password"><spring:message code="message.user.login.password" text="비밀번호"/>:</label>
        <input type="password" name="password" id="password" placeholder='<spring:message code="message.user.login.password.placeholder" text="비밀번호를 입력 하세요."/>' maxlength="30">
    </div>         
    
   </form>   
    <div class="form-actions">
        <button type="button" id="loginBtn" class="login-btn"><spring:message code="message.user.login.loinBtn" text="로그인"/></button>
        <button type="button" id="memberRegBtn" class="signup-btn"><spring:message code="message.user.login.registBtn" text="회원가입"/></button>
    </div>     
   </div>
</body>
</html>