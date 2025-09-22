<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       
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

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <!-- google char -->
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
</head>
<body>
   <!--container-->
   <div class="container">
      <!--제목-->
      <div>  
         <h3>연도별 등급별 인원수</h3>
      </div>
      <!--//제목 -------------------------------------------------->
      
      <!-- chart -->
      <div class="card shadow">
        <div class="card-header bg-primary text-white">
          <h5 class="mb-0">연도별 등급별 인원수</h5>
        </div>
        <div class="card-body">
          <div id="chart_div" style="width: 100%; "></div>
        </div>
      </div>

   </div>   
   <!--//container end ------------------------------------------------------->
   <!--bootstrap js-->
   <script src="/ehr/resources/assets/js/bootstrap.min.js"></script>
   <script>
    //chart: corechart package Load
    google.charts.load('current',{'packages':['corechart']});
    
    
   //callback 함수
   google.charts.setOnLoadCallback(drawChart);
   
   //차트 생성
   function drawChart(){
	   console.log('drawChart^^');
	   
	   
	   fetch('/ehr/user/yearGradeRation.do?year=2025',{
		    method: 'GET',
		    headers: {'Content-Type': 'application/x-www-form-urlencoded' }
		})
		.then(response=>{ 
		   if(!response.ok){
		      throw new Error('Not ok');
		   }
		   console.log("success:"+response)
		   //서버 응답 본문을 JSON 형태로 파싱하여 변환
		   return response.json();
		})
		.then(data =>{
		   console.log("data :"+data );//서버에서 받은 JSON데이터 처리
           //차트 데이터 생성
           let dataTable = new google.visualization.DataTable();
           dataTable.addColumn('string','회원등급');
           dataTable.addColumn('number','인원수');
           
           dataTable.addRows(data);
           
           let options = { 
        		           'legend':'left',
        		           'width':800,
        		           'height':600,
        		           'colors':['#cba02a','#e1c370','#ddbb5f'],
        		           'is3D':true};
           
           
           let chart = new google.visualization.PieChart(document.querySelector("#chart_div"));
           
           chart.draw(dataTable,options);
           
		})//네트워크 문제, 서버오류, JSON 파싱 오류 등 요청 중 발생한 모든 에러 처
		.catch(error => console.error('Error:',error));

	   
   }
   
   
   </script>  
</body>
</html>