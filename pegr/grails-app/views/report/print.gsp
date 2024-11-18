<html>
<head>
    <title>Report</title>
    <meta name="layout" content="none"/>
    <asset:javascript src="jquery.js"/>
    <asset:javascript src="meme.js"/>
    <asset:stylesheet href="meme.css"/>
    <asset:javascript src="bootstrap.js"/>
    <asset:stylesheet href="bootstrap.css"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <style>
        .fa {
            font-size:48px;
            color:#337ab7;
        }
        h5 {
            padding-top: 10px;
        }
        thead {
            display: table-row-group;
        }
        svg {
            max-height: 50px;
        }
    </style>
</head>
<body>
<main class="container">
    <g:if test="${!samplesOnly}">
    <h2>${report.name}</h2>
    </g:if>
    <g:each in="${modules}" var="module">
        <g:render template="/report/${module}" model="['sampleList':sampleList]" />
    </g:each>    
</main>
<script>
    $(function() {
        // plot meme
        $(".meme-fig").each(function(index, memeFig) {
            $(memeFig).find("i").remove();
            var memeUrlForward = $(this).find(".meme-fig-url-forward").text();
            $.ajax({ 
                url: "/pegr/report/fetchMemeFigAjax?url=" + memeUrlForward,
                success: 
                    function(result) {
                        $(memeFig).find(".meme-svg-forward").append(result);
                    }
            });  
            var memeUrlReverse = $(this).find(".meme-fig-url-reverse").text();
            $.ajax({ 
                url: "/pegr/report/fetchMemeFigAjax?url=" + memeUrlReverse,
                success: 
                    function(result) {
                        $(memeFig).find(".meme-svg-reverse").append(result);
                    }
            });  
        });
        
        $(".meme-table").each(function(){
            var memeTable = $(this);
            var memeUrl = $(this).attr("data-meme-url");
            $.ajax({ 
                url: "/pegr/report/fetchMemeDataAjax?url=" + memeUrl,
                success: 
                    function(result) {                        
                        memeTable.find(".meme-id").each(function(index, memeId) {
                            if (index < result.length) {
                                $(memeId).html("<p>MOTIF " + result[index].id + "</p><p>E-value: " + result[index].evalue + "</p><p>Sites: " + result[index].nsites + "</p><p>Width: " + result[index].len + "</p>");
                            }
                        });
                    },
            });  
        });
        
        // plot composites
        google.charts.load('current', {'packages':['corechart']});
        
        // time delayed to draw composite figs
        var t = 0;
        $(".composite").each(function(){
            t += 100;
            var container = $(this).find(".composite-fig")[0];
            var compositeUrl = $(container).attr("data-composite-url");
            setTimeout(function(){
                google.charts.setOnLoadCallback(function(){
                    $.ajax({
                        url: "/pegr/report/fetchCompositeDataAjax?url=" + compositeUrl,
                        dataType: "json"
                    }).done(function(jsonData){
                        if (jsonData["error"]) {
                            container.html(jsonData["error"]);
                        } else {
                            // Create our data table out of JSON data loaded from server.
                            var data = new google.visualization.arrayToDataTable(jsonData["plus"]);

                            // Instantiate and draw our chart, passing in some options.
                            var chart = new google.visualization.LineChart(container);
                            var options = { width: 400, 
                                           height: 200, 
                                           hAxis: { title: 'Distance from MEME motif (bp)', 
                                                   titleTextStyle: {fontSize:12,italic:false},
                                                   gridlines: { color: '#DDDDDD', count:5 },
                                            },
                                            vAxis: { title: 'Tag frequency', 
                                                   titleTextStyle: {fontSize:12,italic:false},
                                                   gridlines: { color: '#DDDDDD', count:4 },
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
                            google.visualization.events.addListener(chart, 'ready', function () {
                                container.innerHTML = '<img src="' + chart.getImageURI() + '">';
                            });
                            chart.draw(data, options);
                            $(this).find("i").remove();
                        }
                    });
                });
            }, t);
        });
        
        $(".featureAnalysis").each(function(){
            t += 200;
            var compositeTd = $(this);
            var spinner = $(this).find("i");
            var compositeUrl = $(this).find(".composite-url").text();
            var plot_title = $(this).find(".composite-plot-title").text();
            var xlabel = $(this).find(".composite-xlabel").text();
            var container = $(this).find(".composite-fig")[0];
            setTimeout(function(){
                google.charts.setOnLoadCallback(function(){
                    $.ajax({
                        url: "/pegr/report/fetchCompositeDataAjax?url=" + compositeUrl,
                        dataType: "json"
                    }).done(function(jsonData){
                        if (jsonData["error"]) {
                            $(compositeTd).empty();
                            $(compositeTd).html(jsonData["error"]);
                        } else {                   
                            // Create our data table out of JSON data loaded from server.
                            var data = new google.visualization.arrayToDataTable(jsonData["plus"]);

                            // Instantiate and draw our chart, passing in some options.
                            var chart = new google.visualization.LineChart(container);
                            var options = { width: 512, 
                                            height: 300, 
                                            title: plot_title,
                                            hAxis: { title: xlabel, 
                                                   titleTextStyle: {fontSize:14,italic:false},
                                                   gridlines: { color: '#DDDDDD', count:8 },
                                            },
                                            vAxis: { title: 'Tag frequency', 
                                                   titleTextStyle: {fontSize:14,italic:false},
                                                   gridlines: { color: '#DDDDDD', count:7 },
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
                            google.visualization.events.addListener(chart, 'ready', function () {
                                container.innerHTML = '<img src="' + chart.getImageURI() + '">';
                            });
                            chart.draw(data, options);   
                            $(spinner).remove();
                        }
                    });
                });
            }, t);
        }); 
    });
</script>
</body>
</html>