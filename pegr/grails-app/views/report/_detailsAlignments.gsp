<h3>Mapping Statistics</h3>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#alignment1-r1"> Number of Tags (Read 1)</a></li>
    <li><a data-toggle="tab" href="#alignment2-r1"> Percentage of Tags (Read 1)</a></li>
    <li><a data-toggle="tab" href="#alignment1-r2"> Number of Tags (Read 2)</a></li>
    <li><a data-toggle="tab" href="#alignment2-r2"> Percentage of Tags (Read 2)</a></li>
</ul>
<div class="tab-content">
    <div id="alignment1-r1" class="tab-pane in active">
        <g:render template="/report/alignmentTable1_R1" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="alignment2-r1" class="tab-pane fade">
        <g:render template="/report/alignmentTable2_R1" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="alignment1-r2" class="tab-pane fade">
        <g:render template="/report/alignmentTable1_R2" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="alignment2-r2" class="tab-pane fade">
        <g:render template="/report/alignmentTable2_R2" model="['sampleList':sampleDTOs]" />
    </div>
</div>
<h3>Paired-end statistics</h3>
<g:render template="/report/alignmentTable3" model="['sampleList':sampleDTOs]" />

<h3>Downstream Analysis</h3>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#peak">Peak Statistics</a></li>
</ul>

<div class="tab-content">
    <div id="peak" class="tab-pane fade in active">
        <g:render template="/report/peakTable" model="['sampleList':sampleDTOs]" />
    </div>
</div>
<ul>
    <li>
        <h4>Quality Control Analysis</h4>
        <g:each in="${sampleDTOs}" var="sample">
          <g:each in="${sample.experiments}" var="experiment">
            <g:each in="${experiment.alignments}" var="alignment">
              <h5>Sample <u>${sample.id} ${sample.naturalId}</u> &nbsp; Run <u>${experiment.runId}</u> &nbsp; Genome <u>${alignment.genome}</u> &nbsp; Target <u>${sample.target}</u> 
              </h5>
              <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#${alignment.id}-meme-table">MEME Motifs</a></li>
                <g:each in="${0..<alignment.featureAnalysis.size()}" var="m">
                  <g:if test="${alignment.featureAnalysis[m]}">
                  <li><a data-toggle="tab" href="#${alignment.id}-composite${m}">${alignment.featureAnalysis[m].title}</a></li>
                  </g:if>
                </g:each>
              </ul>
              <div class="tab-content">
                <div id="${alignment.id}-meme-table" class="tab-pane in active"> 
                  <table class="table table-bordered meme-table">
                    <thead>
                        <tr>
                            <th rowspan="2">ID</th>
                            <th colspan="4" class="text-center"><a href="${alignment.memeFig}" target="_blank">Meme <span class="glyphicon glyphicon-picture"></span></a><span class="meme-url" hidden="hidden">${alignment.memeFile}</span></th>
                            <th rowspan="2">Four-Color</th>
                            <th rowspan="2">Composite</th>
                        </tr>
                        <tr>
                            <th>Logo</th>
                            <th>E-value</th>
                            <th>Sites</th>
                            <th>Width</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:if test="${alignment.motifCount}">
                        <g:each in="${(0..<alignment.motifCount)}" var="n">
                            <tr>
                                <td class="meme-id" style="width:20px"></td>
                                <g:if test="${alignment.memeSvgForward}">
                                <td class="meme-fig" style="width:450px">
                                    <i class="fa fa-spinner fa-spin"></i>
                                    <div class="preview_box">
                                        <div class="preview_btn_box">
                                            <div class="preview_btn plus active" tabindex="0">+</div>
                                            <div class="preview_btn minus" tabindex="0">-</div>
                                        </div>
                                        <div class="preview_logo_box">
                                            <div class="meme-svg meme-svg-forward">
                                                <span class="meme-fig-url-forward" hidden="hidden">${alignment.memeSvgForward[n]}</span>
                                            </div>
                                            <div style="display:none" class="meme-svg meme-svg-reverse">
                                                <span class="meme-fig-url-reverse" hidden="hidden">${alignment.memeSvgReverse[n]}</span>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                </g:if>
                                <g:else>
                                <td style="width:450px">
                                </g:else>
                                <td class="meme-evalue" style="width:100px"></td>
                                <td class="meme-sites" style="width:100px"></td>
                                <td class="meme-width" style="width:100px"></td>
                                <td style="width:100px"><a href="${alignment.fourColor[n]}" target="_blank"><span class="glyphicon glyphicon-picture" style="font-size: 2em"></span></a></td>
                                <td class="composite" style="width:320px">
                                  <g:if test="${alignment.composite[n]}">
                                    <g:link controller="report" action="composite" params="[url: alignment.composite[n]]" target="_blank" class="pull-right"><span class="glyphicon glyphicon-fullscreen" style="z-index: 100"></span></g:link>
                                    <i class="fa fa-spinner fa-spin"></i>
                                    <span class="composite-url" hidden="hidden">${alignment.composite[n]}</span>
                                    <div class="composite-fig"></div>
                                  </g:if>
                                </td>
                            </tr>
                        </g:each>
                        </g:if>
                    </tbody>
                  </table> 
                </div>
                <g:each in="${0..<alignment.featureAnalysis.size()}" var="m">
                  <g:if test="${alignment.featureAnalysis[m]}">
                    <div id="${alignment.id}-composite${m}" class="featureAnalysis tab-pane fade">
                      <i class="fa fa-spinner fa-spin"></i>
                      <span class="composite-url" hidden="hidden">${alignment.featureAnalysis[m].tabular}</span>
                      <span class="composite-plot-title" hidden="hidden">${alignment.featureAnalysis[m].plot_title}</span>
                      <span class="composite-xlabel" hidden="hidden">${alignment.featureAnalysis[m].xlabel}</span>
                      <div class="composite-fig" style="width: 512px; height: 300px"></div>
                    </div>
                  </g:if>
                </g:each>
              </div>
            </g:each>
          </g:each>
        </g:each>
    </li>
</ul>
<script>
    $(function() {
        // plot meme
        $(".preview_btn").on("click", function(){
            $(this).addClass("active");
            var logo = $(this).parent().next();
            if ($(this).hasClass("plus")) {
                $(this).next().removeClass("active");
                logo.find(".meme-svg-forward").show();
                logo.find(".meme-svg-reverse").hide();
            } else {
                $(this).prev().removeClass("active");
                logo.find(".meme-svg-forward").hide();
                logo.find(".meme-svg-reverse").show();
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
                            var data = new google.visualization.arrayToDataTable(jsonData);

                            // Instantiate and draw our chart, passing in some options.
                            var chart = new google.visualization.LineChart(container);
                            var options = { width: 300, 
                                           height: 150, 
                                           legend: { position: 'none'}, 
                                           vAxis: { gridlines: {count: 3 } },
                                           hAxis: { gridlines: {count: 3 } },
                                          };
                            google.visualization.events.addListener(chart, 'ready', function () {
                                container.innerHTML = '<img src="' + chart.getImageURI() + '">';
                            });                 
                            chart.draw(data, options);   
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
                            var data = new google.visualization.arrayToDataTable(jsonData);

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
                                            legend: { position: 'right' }
                                          };                            
                            chart.draw(data, options);   
                            $(spinner).remove();
                        }
                    });
                });
            }, t+1500);
        });   
    });
</script>