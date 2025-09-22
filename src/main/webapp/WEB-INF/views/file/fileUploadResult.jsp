<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h2>파일업로드 결과</h2>
   <p>${message }</p>
   <c:if test="${message !='file이 없습니다.' }">
      <p>${message.savePath }${message.orgFileName}</p>
      <p>${message.savePath }${message.saveFileName}</p>
   </c:if>
   
</body>
</html>