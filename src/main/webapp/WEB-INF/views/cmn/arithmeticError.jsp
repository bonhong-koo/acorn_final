<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/list.css">
<title>Insert title here</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
</head>
<body>
<div class="main-container">
    <h2>예외(ArithmeticException)</h2>
    <hr class="title-underline">
    <table   id="listTable"  class="table">
      <colgroup>
        <col width="20%">
        <col width="40%">
        <col width="20%">
        <col width="20%">       
      </colgroup>    
        <thead>
          <tr>
              <th class="table-head">예외</th>
              <th class="table-head">예외상세</th>
              <th class="table-head">웹 상태코드</th>
              <th class="table-head">요청URI</th>
          </tr>
        </thead>
        <tbody> 
          <tr>
              <td class="table-cell ">${requestScope['javax.servlet.error.message'] }</td>
              <td class="table-cell ">${requestScope['javax.servlet.error.exception'] }</td>
              <td class="table-cell ">${requestScope['javax.servlet.error.status_code'] }</td>
              <td class="table-cell ">${requestScope['javax.servlet.error.request_uri'] }</td>
          </tr> 
        </tbody>              
    </table>   
</div>  
</body>
</html>