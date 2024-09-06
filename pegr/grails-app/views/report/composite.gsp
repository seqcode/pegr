<html>
    <head>
        <asset:javascript src="jquery.js"/>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages':['corechart']});
             google.charts.setOnLoadCallback(function(){
                $.ajax({
                      url: "/pegr/report/fetchCompositeDataAjax?url=${url}",
                      dataType: "json"
                }).done(function(jsonData){
                    // Create our data table out of JSON data loaded from server.
                    var data = new google.visualization.arrayToDataTable(jsonData);

                    var options = {
                        title: '',
                        hAxis: { title: 'Distance from MEME motif (bp)', 
                               titleTextStyle: {fontSize:25,italic:false},
                               gridlines: { color: '#DDDDDD', count:11 },
                        },
                        vAxis: { title: 'Tag frequency', 
                               titleTextStyle: {fontSize:25,italic:false},
                               gridlines: { color: '#DDDDDD', count:10 },
                                format: '#.##'
                        },
                        curveType: 'function',
                        legend: { position: 'bottom' },
                        series: {
                            0: { color: '#0000ff' },
                            1: { color: '#ff0000' },  
                            2: { color: '#00c9ff' },
                            3: { color: '#ff00ff' },
                        }
                    };
                    // Instantiate and draw our chart, passing in some options.
                    var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
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