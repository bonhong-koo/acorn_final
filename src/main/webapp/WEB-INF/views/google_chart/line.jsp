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
         <h3>구글-라인차트</h3>
      </div>
      <!--//제목 -------------------------------------------------->
      
      <!-- chart -->
      <div id="chart_div" style="width: 900px; height: 500px"></div>
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
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['연도', '매출', '비용'],
          ['2022',  1000,      400],
          ['2023',  1170,      460],
          ['2024',  660,       1120],
          ['2025',  1030,      540]
        ]);

        var options = {
          title: '비용대비 매출 차트',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

        chart.draw(data, options);
      }
   
   
   </script>  
</body>
</html>