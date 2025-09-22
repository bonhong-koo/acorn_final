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
<link rel="stylesheet" href="/ehr/resources/assets/css/list.css">
<link rel="icon" href="/ehr/resources/favicon.ico" type="image/x-icon"/>
<title>즐거운_코딩</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
</head>
<body>
<div class="main-container">
    <h2>임시: menu</h2>
    <hr class="title-underline">

    <table border="1"  id="listTable"  class="table">
      <colgroup>
        <col width="20%">
        <col width="80%">
      </colgroup>    
        <thead>
      <tr>
            <th class="table-head">번호</th>
            <th class="table-head">메뉴</th>
      </tr>
        </thead>
        <tbody> 
                <tr>
                    <td class="table-cell text-center">1</td>
                    <td class="table-cell text-left"><a href="/ehr/board/doRetrieve.do?div=20" target="_blank">자유게시판</a></td>      
                </tr>         
                <tr>
                    <td class="table-cell text-center">2</td>
                    <td class="table-cell text-left"><a href="/ehr/board/doRetrieve.do?div=10" target="_blank">공지사항</a></td>      
                </tr>
                <tr>
                    <td class="table-cell text-center">3</td>
                    <td class="table-cell text-left"><a href="/ehr/file/uploadView.do" target="_blank">파일업로드(동기)</a></td>      
                </tr>  
                <tr>
                    <td class="table-cell text-center">3</td>
                    <td class="table-cell text-left"><a href="/ehr/file/asynView.do" target="_blank">파일업로드(비동기)</a></td>      
                </tr>                  
                
                <!-- session존재에 따라: O(로그아웃)/X(로그인) -->
                <c:choose>
                    <c:when test="${empty sessionScope.user.userId}">
		                <tr>
		                    <td class="table-cell text-center">4</td>
		                    <td class="table-cell text-left"><a href="/ehr/login/loginView.do" target="_blank">로그인</a></td>      
		                </tr> 
                                    
                    </c:when>
                    <c:otherwise>
		                <tr>
		                    <td class="table-cell text-center">4</td>
		                    <td class="table-cell text-left"><a href="/ehr/login/logout.do" target="_blank">로그아웃</a></td>      
		                </tr>                       
                    </c:otherwise>
                </c:choose>
                                                                      
                <tr>
                    <td class="table-cell text-center">5</td>
                    <td class="table-cell text-left"><a href="/ehr/template/list_boot.do" target="_blank">템플릿_list</a></td>      
                </tr>
                <tr>
                    <td class="table-cell text-center">6</td>
                    <td class="table-cell text-left"><a href="/ehr/template/reg_boot.do" target="_blank">템플릿_reg</a></td>      
                </tr>
                <tr>
                    <td class="table-cell text-center">7</td>
                    <td class="table-cell text-left"><a href="/ehr/template/reg_boot_horizontal.do" target="_blank">템플릿_reg_horizontal</a></td>      
                </tr>      
                <tr>
                    <td class="table-cell text-center">8</td>
                    <td class="table-cell text-left"><a href="/ehr/google_chart/pie.do" target="_blank">google_pie_char</a></td>      
                </tr>     
                <tr> 
                    <td class="table-cell text-center">9</td>
                    <td class="table-cell text-left"><a href="/ehr/user/yearGradeRationView.do" target="_blank">연도별 등급별 인원수</a></td>      
                </tr>  
                <tr>
                    <td class="table-cell text-center">10</td>
                    <td class="table-cell text-left"><a href="/ehr/google_chart/chartAll.do" target="_blank">Bar_Line_Combo</a></td>      
                </tr>                                                            
            
        </tbody>

    </table>


</div>
</body>
</html>