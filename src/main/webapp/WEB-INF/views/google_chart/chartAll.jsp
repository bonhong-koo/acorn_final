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
  <style>
    .card { margin-bottom: 1.5rem; }
    .chart-container { height: 300px; }
  </style>
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
         <h3>구글-파이차트</h3>
      </div>
      <!--//제목 -------------------------------------------------->
      
      <!-- chart -->
	  <div class="row" id="chartCards">
	    <!-- Chart Cards will be injected here -->
          <div class="card">
            <div class="card-header fw-bold">Bar Chart</div>
            <div class="card-body">
              <div id="bar_chart_div" class="chart-container"></div>
            </div>
          </div>
          
          <div class="card">
            <div class="card-header fw-bold">Line Chart</div>
            <div class="card-body">
              <div id="line_chart_div" class="chart-container"></div>
            </div>
          </div>          
          	    
          <div class="card">
            <div class="card-header fw-bold">Combo Chart</div>
            <div class="card-body">
              <div id="combo_chart_div" class="chart-container"></div>
            </div>
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
    google.charts.setOnLoadCallback(drawAllCharts);

    function drawAllCharts() {
      drawBarChart();
      drawLineChart();
      drawComboChart();
    }
   
   //차트 생성
   function drawBarChart() {
      const data = google.visualization.arrayToDataTable([
        ['Year', 'Sales', 'Expenses'],
        ['2020', 1000, 400],
        ['2021', 1170, 460],
        ['2022', 660, 1120],
        ['2023', 1030, 540]
      ]);

      const options = {
        title: 'Bar Chart - Company Performance',
        chartArea: {width: '70%'},
        hAxis: {title: 'Total'},
        vAxis: {title: 'Year'},
      };

      //insertCard('Bar Chart', 'bar_chart_div');
      new google.visualization.BarChart(document.getElementById('bar_chart_div')).draw(data, options);
    }

    function drawLineChart() {
      const data = google.visualization.arrayToDataTable([
        ['Month', 'Visitors'],
        ['Jan',  1000],
        ['Feb',  1170],
        ['Mar',  660],
        ['Apr',  1030]
      ]);

      const options = {
        title: 'Line Chart - Monthly Visitors',
        curveType: 'function',
        legend: { position: 'bottom' }
      };

      new google.visualization.LineChart(document.getElementById('line_chart_div')).draw(data, options);
    }

    function drawComboChart() {
      const data = google.visualization.arrayToDataTable([
        ['Month', 'Sales', 'Expenses', 'Profit'],
        ['Jan', 165, 938, 522],
        ['Feb', 135, 1120, 599],
        ['Mar', 157, 1167, 587],
        ['Apr', 139, 1110, 615]
      ]);

      const options = {
        title : 'Combo Chart Example',
        vAxis: {title: 'Amount'},
        hAxis: {title: 'Month'},
        seriesType: 'bars',
        series: {2: {type: 'line'}}
      };

      //insertCard('Combo Chart', 'combo_chart_div');
      new google.visualization.ComboChart(document.getElementById('combo_chart_div')).draw(data, options);
    }

   
   </script>  
</body>
</html>