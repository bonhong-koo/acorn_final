<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<c:set var="CP" value="${pageContext.request.contextPath }" />    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="/ehr/resources/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="/ehr/resources/assets/css/list.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/main.css">
<title>즐거운_코딩</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
</head>
<body>
   <div id="container">
      <!--header-->
      <header id="header"><h2>헤더</h2></header>
      <!--//header end------------------->

      <!--aside-->
      <aside id="aside"><h2>aside</h2></aside>
      <!--//aside end-------------------->

      <!--main-->
      <main id="main"><h2>본문</h2></main>
      <!--//main end-------------------->

      <!--footer-->
      <footer id="footer"><h2>footer</h2></footer>
      <!--//footer---------------------->      
   </div>
</body>
</html>