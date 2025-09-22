<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 외부 스타일-->
<link rel="stylesheet" href="/ehr/resources/assets/list.css">
</head>
<body>
    <h2>회원목록</h2>
    
    <table border="1">
     <thead>
      <tr>
         <th>번호</th>
         <th>회원ID</th>
         <th>이름</th>
         <th>이메일</th>
         <th>등급</th>
         <th>로그인</th>
         <th>추천</th>
         <th>등록일</th>
      </tr>
     </thead>
     <tbody>
          <c:forEach var="vo" items="${list }">
            <tr>
              <td>${vo.getNo() }</td>
              <td>${vo.userId }</td>
              <td>${vo.name }</td>
              <td>${vo.email }</td>
              <td>${vo.grade }</td>
              <td class="text-right">${vo.login }</td>
              <td class="text-right">${vo.recommend }</td>
              <td class="text-center">${vo.regDt }</td>
            </tr>
          </c:forEach>
     </tbody>
    </table>
</body>
</html>