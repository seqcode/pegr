<g:each in="${modules}" var="module">
    <g:render template="/report/${module}" model="['sampleList':sampleDTOs]" />
</g:each>

<script>
    $(function() {
        // plot meme
        $(".preview_btn").on("click", function(){
            $(this).addClass("active");
            var logo = $(this).parent().next();
            var composite_plus = $(this).closest("tr").find(".composite-fig-plus");
            var composite_minus = $(this).closest("tr").find(".composite-fig-minus");
            
            if ($(this).hasClass("plus")) {
                $(this).next().removeClass("active");
                logo.find(".meme-svg-forward").show();
                logo.find(".meme-svg-reverse").hide();
                composite_plus.show();
                composite_minus.hide();
            } else {
                $(this).prev().removeClass("active");
                logo.find(".meme-svg-forward").hide();
                logo.find(".meme-svg-reverse").show();
                composite_minus.show();
                composite_plus.hide();
            }            
        });
        
        // time delay
        var t = 0;
        // plot peHistogram
        google.charts.load('current', {'packages':['corechart']});
        $(".peHistogram").each(function(){
            t += 2000;
            var peTd = $(this);
            var spinner = $(this).find("i");
            var peHistogramUrl = $(this).find(".peHistogram-url").text();
            var container = $(this).find(".peHistogram-fig")[0];
            setTimeout(function(){
                google.charts.setOnLoadCallback(function(){
                    $.ajax({
                        url: "/pegr/report/fetchPeHistogramDataAjax?url=" + peHistogramUrl,
                        dataType: "json",
                        timeout: 10000 // sets timeout to 10 seconds
                    }).done(function(jsonData){
                        if (jsonData["error"]) {
                            $(peTd).empty();
                            $(peTd).html(jsonData["error"]);
                        } else {
                            // Create our data table out of JSON data loaded from server.
                            var data = new google.visualization.arrayToDataTable(jsonData);

                            // Instantiate and draw our chart, passing in some options.
                            var chart = new google.visualization.ColumnChart(container);
                            var options = {
                                title: 'Paired-End Insert Size Frequency Histogram',
                                width: 450, 
                                height: 225, 
                                legend: { position: 'none'}, 
                                vAxis: { title: 'Insert Size (bp)', gridlines: {count: 3 } },
                                hAxis: { title: 'Frequency', gridlines: {count: 3 } },};
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
        
        t=0;
        $(".meme-fig").each(function(index, memeFig) {
            t += 2000; // increase time delay by 1 sec
            $(memeFig).find("i").remove();
            
            var memeUrlForward = $(this).find(".meme-fig-url-forward").text();
            setTimeout(function(){
              $.ajax({ 
                url: "/pegr/report/fetchMemeFigAjax?url=" + memeUrlForward,
                success: 
                    function(result) {
                        $(memeFig).find(".meme-svg-forward").append(result);
                    },
                timeout: 10000 // time out after 10 seconds
              });
            }, t+300);
            
            var memeUrlReverse = $(this).find(".meme-fig-url-reverse").text();
            setTimeout(function(){
              $.ajax({ 
                url: "/pegr/report/fetchMemeFigAjax?url=" + memeUrlReverse,
                success: 
                    function(result) {
                        $(memeFig).find(".meme-svg-reverse").append(result);
                    },
                timeout: 10000 // time out after 10 seconds
              });  
            }, t+600);
        });
        
        t=0;
        $(".meme-table").each(function(){
            t += 2000;
            var memeTable = $(this);
            var memeUrl = $(this).find(".meme-url").text();
            setTimeout(function(){
              $.ajax({ 
                url: "/pegr/report/fetchMemeDataAjax?url=" + memeUrl,
                timeout: 12000, // time out after 12 seconds
                success: 
                    function(result) {
                        memeTable.find(".meme-id").each(function(index, memeId) {
                            if (index < result.length) {
                                $(memeId).html(result[index].id);
                            }
                        });
                        memeTable.find(".meme-evalue").each(function(index, memeEvalue) {
                            if (index < result.length) {
                                $(memeEvalue).html(result[index].evalue);
                            }
                        });
                        memeTable.find(".meme-sites").each(function(index, memeSites) {
                            if (index < result.length) {
                                $(memeSites).html(result[index].nsites);
                            }
                        });
                        memeTable.find(".meme-width").each(function(index, memeWidth) {
                            if (index < result.length) {
                                $(memeWidth).html(result[index].len);
                            }
                        });
                    },
              }); 
            }, t+900);
        });
        
        // plot composites
        t=0;
        $(".composite").each(function(){
            t += 2000;
            var compositeTd = $(this);
            var spinner = $(this).find("i");
            var compositeUrl = $(this).find(".composite-url").text();
            var container_plus = $(this).find(".composite-fig-plus")[0];
            var container_minus = $(this).find(".composite-fig-minus")[0];
            setTimeout(function(){
                google.charts.setOnLoadCallback(function(){
                    $.ajax({
                        url: "/pegr/report/fetchCompositeDataAjax?url=" + compositeUrl,
                        dataType: "json",
                        timeout: 10000 // sets timeout to 10 seconds
                    }).done(function(jsonData){
                        if (jsonData["error"]) {
                            $(compositeTd).empty();
                            $(compositeTd).html(jsonData["error"]);
                        } else {
                            var options = { width: 300, 
                                           height: 150, 
                                           legend: { position: 'bottom'}, 
                                           vAxis: { gridlines: {count: 3 } },
                                           hAxis: { gridlines: {count: 3 } },
                                           series: {
                                             0: { color: '#0000ff' },
                                             1: { color: '#ff0000' },  
                                             2: { color: '#00c9ff' },
                                             3: { color: '#ff00ff' },
                                           }};
                            
                            // Create our data table out of JSON data loaded from server.
                            var data_plus = new google.visualization.arrayToDataTable(jsonData["plus"]);
                            
                            // Instantiate and draw our chart, passing in some options.
                            var chart_plus = new google.visualization.LineChart(container_plus); 
                            
                            chart_plus.draw(data_plus, options);   
                            
                            var data_minus = new google.visualization.arrayToDataTable(jsonData["minus"]);
                            
                            var chart_minus = new google.visualization.LineChart(container_minus); 
                            
                            chart_minus.draw(data_minus, options);   
                            
                            $(container_minus).hide();
                            
                            $(spinner).remove();
                        }
                    });
                });
            }, t+1200);
        });   
        
        t=0;
        $(".featureAnalysis").each(function(){
            t += 2000;
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
                        dataType: "json",
                        timeout: 10000 // sets timeout to 10 seconds
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
                            chart.draw(data, options);   
                            $(spinner).remove();
                            compositeTd.addClass("tab-pane fade");
                        }
                    });
                });
            }, t+1500);
        });   
    });
</script>