<html>
    <head>
        <asset:javascript src="jquery.js"/>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages':['corechart']});
             google.charts.setOnLoadCallback(function(){
                $.ajax({
                      url: "/pegr/report/fetchPeHistogramDataAjax?url=${url}",
                      dataType: "json"
                }).done(function(jsonData){
                    // Create our data table out of JSON data loaded from server.
                    var data = new google.visualization.arrayToDataTable(jsonData);

                    var options = {
                        title: 'Paired-End Insert Size Frequency Histogram',
                        titleTextStyle: {
                            fontSize:25
                        },
                        hAxis: { title: 'Insert Size (bp)', 
                               titleTextStyle: {fontSize:20,italic:false},
                               gridlines: { color: '#DDDDDD', count:11 },
                        },
                        vAxis: { title: 'Frequency', 
                               titleTextStyle: {fontSize:20,italic:false},
                               gridlines: { color: '#DDDDDD', count:10 },
                                format: '#.##'
                        },
                        curveType: 'function',
                        legend: { position: 'none' }
                    };
                    // Instantiate and draw our chart, passing in some options.
                    var chart = new google.visualization.ColumnChart(document.getElementById('curve_chart'));
                    chart.draw(data, options);    
                });                
            });          
        </script>
    </head>
    <body>
        <div id="test"></div>
        <div id="curve_chart" style="width: 1024px; height: 600px"></div>
    </body>
</html>