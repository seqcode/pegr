<html>
    <head>
        <asset:javascript src="jquery.js"/>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);
            
            function drawChart(results) {                
                var data = google.visualization.arrayToDataTable([['Position', 'Forward', 'Reverse']
                        ${compositeData}
                    ]);

                var options = {
                    title: '',
                    hAxis: { title: 'Distance from MEME motif (bp)', 
                           titleTextStyle: {fontSize:25,italic:false},
                           gridlines: { color: '#DDDDDD', count:11 }
                    },
                    vAxis: { title: 'Tag frequency', 
                           titleTextStyle: {fontSize:25,italic:false},
                           gridlines: { color: '#DDDDDD', count:10 }
                    },
                    curveType: 'function',
                    legend: { position: 'right' }
                };
                var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                chart.draw(data, options);
            }            
        </script>
    </head>
    <body>
        <div id="test"></div>
        <div id="curve_chart" style="width: 1024px; height: 600px"></div>
    </body>
</html>