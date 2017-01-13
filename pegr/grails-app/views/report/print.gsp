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
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
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
    </style>
</head>
<body>
    <main class="container">
    <h2>${report.name}</h2>
    <section>
        <h4>List of Samples</h4>
        <ol>
            <g:each in="${sampleDTOs}" var="sample">
                <li>${sample.natural_id}</li>
            </g:each>
        </ol>
    </section>
    <section>
        <h4>Images</h4>
        <table class="table table-bordered" id="project-table">
            <thead>
                <tr>
                    <th>Sonication Images</th>
                    <th>Gel Images</th>
                <tr>
            </thead>
            <tbody>
                <tr>
                    <td class="col-sm-6">
                        <ul>
                        <g:each in="${imageMap?.sonication}" var="filepath">
                            <li>
                                <img src='${createLink(controller: "file", action: "displayImage", params:[filepath:filepath, relative:true])}' height="200"/>
                            </li>
                        </g:each>
                        </ul>
                    </td>
                    <td class="col-sm-6">
                        <ul>
                        <g:each in="${imageMap?.gel}" var="filepath">
                            <li>
                                <img src='${createLink(controller: "file", action: "displayImage", params:[filepath:filepath,relative:true])}' height="200"/>
                            </li>
                        </g:each>
                        </ul>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>
    <section>
        <h4>Mapping Statistics</h4>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Target</th>
                        <th>Antibody</th>
                        <th>Celltype</th>
                        <th>Mutation</th>
                        <th>Assay</th>
                        <th>Genome</th>
                        <th class="text-right">Index count</th>
                        <th class="text-right">Uniquely mapped tags</th>
                        <th class="text-right">Uniquely mapped percentage</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${sampleList}" var="sample" status="n">
                        <tr>
                            <td rowspan="${Math.max(1, sample.alignmentCount)}">${n+1}</td>
                            <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.target}</td>
                            <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.antibody}</td>
                            <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.strain}</td>
                            <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.geneticModification}</td>
                            <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.assay}</td>
                            <g:each in="${sample.experiments}" var="experiment" status="nExp">
                                <g:if test="${nExp>0}"><tr></g:if>  
                                <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                                    <g:if test="${nAli>0}"><tr></g:if>
                                    <td>${alignment.genome}</td>
                                    <td class="text-right"><g:formatNumber number="${experiment.totalReads}" format="###,###,###" /></td>
                                    <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedReads}" format="###,###,###" /></td>
                                    <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedPct}" format="#0.0%" /></td>
                                    </tr>
                                </g:each>
                            </g:each>
                    </g:each>              
                </tbody>
              </table>
    </section>
    <section>
        <h4>Peak and Peak-pair Statistics</h4>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th class="text-right">Peaks</th>
                        <th class="text-right">Singletons</th>
                        <th class="text-right">Peak-pairs (noS)</th>
                        <th class="text-right">Genome coverage (Tag)</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${sampleList}" var="sample" status="n">
                        <tr>
                            <td rowspan="${Math.max(1, sample.alignmentCount)}">${n+1}</td>
                            <g:each in="${sample.experiments}" var="experiment" status="nExp">
                                <g:if test="${nExp>0}"><tr></g:if>
                                <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                                    <g:if test="${nAli>0}"><tr></g:if>
                                    <td class="text-right"><g:formatNumber number="${alignment.peaks}" format="###,###,###" /></td>
                                    <td class="text-right"><g:formatNumber number="${alignment.singletons}" format="###,###,###" /></td>
                                    <td class="text-right"><g:formatNumber number="${alignment.peakPairs}" format="###,###,###" /></td>
                                    <td class="text-right"><g:formatNumber number="${alignment.genomeCoverage}" format="#0.00%" /></td>
                                    </tr>
                                </g:each>
                            </g:each>
                    </g:each>      
                </tbody>
              </table>
    </section>
    <section>
        <h4>MEME Motifs</h4>
        <ol>
            <g:each in="${sampleList}" var="sample" status="n">
                <li>
                    <h4>${sample.naturalId}</h4>
                    <g:each in="${sample.experiments}" var="experiment">
                        <g:each in="${experiment.alignments}" var="alignment">
                            <table class="table table-bordered meme-table">
                                <span class="meme-url" hidden="hidden">${alignment.memeFile}</span>
                                <tbody>
                                </tbody>
                            </table>
                        </g:each>
                    </g:each>
                </li>
            </g:each>
        </ol>
    </section>
    <section>
        <h4>Tag PileUp</h4>
        <ol>
            <g:each in="${sampleList}" var="sample" status="n">
                <li>
                    <h4>${sample.naturalId}</h4>
                    <g:each in="${sample.experiments}" var="experiment">
                        <g:each in="${experiment.alignments}" var="alignment">
                            <i class="fa fa-spinner fa-spin"></i><span class="composite-url" hidden="hidden">${alignment.composite[n]}</span><div class="composite-fig"></div>
                        </g:each>
                    </g:each>
                </li>
            </g:each>
        </ol>
    </section>
</main>
<script>
    $(function() {
        // plot meme
        $(".meme-table").each(function(){
            var memeTable = $(this);
            var memeUrl = $(this).find(".meme-url").text();
            $.ajax({ 
                url: "/pegr/report/fetchMemeDataAjax?url=" + memeUrl,
                success: 
                    function(result) {
                        $.each(result, function(index, value){
                            make_motif(memeFig, result[index]);
                            memeTable.append("<tr></tr>");
                            
                            $(memeId).html("<p>MOTIF " + result[index].id + "</p><p>E-value: " + result[index].evalue + "</p><p>Sites: " + result[index].nsites + "</p><p>Width: " + result[index].len + "</p>");
                            }
                        });
                        memeTable.find(".meme-fig").each(function(index, memeFig) {
                            $(memeFig).find("i").remove();
                            if (index < result.length) {
                                
                            } else {
                                $(memeFig).html("No MEME data found!");
                            }                            
                        });
                    },
            });  
        });
        
        // plot composites
        google.charts.load('current', {'packages':['corechart']});
        var t = 0;
        $(".composite").each(function(){
            t += 200;
            var compositeTd = $(this);
            var spinner = $(this).find("i");
            var compositeUrl = $(this).find(".composite-url").text();
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
                            var data = new google.visualization.arrayToDataTable(jsonData);

                            // Instantiate and draw our chart, passing in some options.
                            var chart = new google.visualization.LineChart(container);
                            var options = { width: 200, 
                                           height: 100, 
                                           legend: { position: 'none'}, 
                                           vAxis: { gridlines: {count: 3 } },
                                           hAxis: { gridlines: {count: 3 } },
                                          };
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