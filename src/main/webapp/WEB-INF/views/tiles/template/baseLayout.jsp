<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
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

<title><tiles:insertAttribute name="title"/></title>
<!-- <script src="./js/j06.js" defer></script> -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
<style>
.header {
    background-color: #5c007a;
    height: 150px;
}

.footer {
    background-color: #f8bdcd;
}
</style>
</head>
<body>
   <!--container-->
	<div class="container">
	 <table border="1" align="center">
	    <!-- header -->
	    <tr  class="header">
	      <td border="1" height="150" colspan="2"><tiles:insertAttribute name="header"/></td>
	    </tr>
	    <!-- menu,body -->
	    <tr>
	      <td border="1" width="25%"><tiles:insertAttribute name="menu"/></td>
	      <td border="1" width="75%"><tiles:insertAttribute name="body"/></td>
	    </tr>
	    <!-- footer -->
	    <tr  class="footer">
	      <td border="1" height="100" colspan="2"><tiles:insertAttribute name="footer"/></td>
	    </tr>
	 </table>
	</div>
   <!--//container end ------------------------------------------------------->  
   <script src="/ehr/resources/assets/js/bootstrap.min.js"></script>
</body>
</html>