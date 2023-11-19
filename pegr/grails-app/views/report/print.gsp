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
    </style>
</head>
<body>
    <main class="container">
    <h2>${report.name}</h2>
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
        <h4>Samples</h4>
        <p>The number of samples: ${sampleList.size()}</p>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Sample ID</th>
                    <th>Target</th>
                    <th>Antibody</th>
                    <th>Celltype/Strain</th>
                    <th>Mutation</th>
                    <th>Growth Media</th>
                    <th>Treatments</th>
                    <th>Assay</th>
                    <th>Note</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${sampleList}" var="sample" status="n">
                <tr>
                    <td>${sample.id}</td>
                    <td>${sample.target}</td>
                    <td>${sample.antibody}</td>
                    <td>${sample.strain}</td>
                    <td>${sample.geneticModification}</td>
                    <td>${sample.growthMedia}</td>
                    <td>${sample.treatments}</td>
                    <td>${sample.assay}</td>
                    <td>${sample.note}</td>
                </tr>
                </g:each>
            </tbody>
        </table>
    </section>
    <section>
        <h4>Mapping Statistics （Read 1）</h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Sequence Run</th>
                    <th>Genome</th>
                    <th class="text-right">Read Count (R1)</th>
                    <th class="text-right">Uniquely Mapped Count (R1)</th>
                    <th class="text-right">% Uniquely Mapped Count (R1)</th>
                    <th class="text-right">Deduplicated Count (R1)</th>
                    <th class="text-right">% Deduplicated Count (R1)</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${sampleList}" var="sample" status="n">
                    <tr>
                        <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.id}</td>
                        <g:each in="${sample.experiments}" var="experiment" status="nExp">
                            <g:if test="${nExp>0}"><tr></g:if>  
                            <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                                <g:if test="${nAli>0}"><tr></g:if>
                                <td>${experiment?.runId}</td>
                                <td>${alignment.genome}</td>
                                <td class="text-right"><g:formatNumber number="${experiment.totalReads}" format="###,###,###" /></td>
                                <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedReads}" format="###,###,###" /></td>
                                <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedPct}" format="#0.0%" /></td>
                                <td class="text-right"><g:formatNumber number="${alignment.dedupUniquelyMappedReads}" format="###,###,###" /></td>
                                <td class="text-right"><g:formatNumber number="${alignment.deduplicatedPct}" format="#0.0%" /></td>
                                </tr>
                            </g:each>
                        </g:each>
                </g:each>              
            </tbody>
        </table>
    </section>
    <section>
        <h4>Mapping Statistics (Read 2)</h4>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Sequence Run</th>
                    <th>Genome</th>
                    <th class="text-right">Read Count (R2)</th>
                    <th class="text-right">Uniquely Mapped Count (R2)</th>
                    <th class="text-right">% Uniquely Mapped Count (R2)</th>
                    <th class="text-right">Deduplicated Count (R2)</th>
                    <th class="text-right">% Deduplicated Count (R2)</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${sampleList}" var="sample" status="n">
                    <tr>
                        <td rowspan="${Math.max(1, sample.alignmentCount)}">${sample.id}</td>
                        <g:each in="${sample.experiments}" var="experiment" status="nExp">
                            <g:if test="${nExp>0}"><tr></g:if>  
                            <g:each in="${experiment.alignments}" var="alignment" status="nAli">
                                <g:if test="${nAli>0}"><tr></g:if>
                                <td>${experiment?.runId}</td>
                                <td>${alignment.genome}</td>
                                <td class="text-right"><g:formatNumber number="${experiment.totalReadsR2}" format="###,###,###" /></td>
                                <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedReadsR2}" format="###,###,###" /></td>
                                <td class="text-right"><g:formatNumber number="${alignment.uniquelyMappedPct2}" format="#0.0%" /></td>
                                <td class="text-right"><g:formatNumber number="${alignment.dedupUniquelyMappedReadsR2}" format="###,###,###" /></td>
                                <td class="text-right"><g:formatNumber number="${alignment.deduplicatedPct2}" format="#0.0%" /></td>
                                </tr>
                            </g:each>
                        </g:each>
                </g:each>              
            </tbody>
        </table>
    </section>
    <section>
        <h4>MEME Motifs</h4>
        <ul>
            <g:each in="${sampleList}" var="sample">
            <li>
                <h4>${sample.id} ${sample.naturalId}</h4>
                <g:each in="${sample.experiments}" var="experiment">
                <g:each in="${experiment.alignments}" var="alignment">
                <table class="table table-bordered meme-table" data-meme-url="${alignment.memeFile}">
                    <tbody>
                    <g:if test="${alignment.motifCount}">
                    <g:each in="${(0..<alignment.motifCount)}" var="n">
                        <tr>
                            <td style='min-width:6em;line-height:1em' class="meme-id" style="width:20px"></td>
                            <td class="meme-fig" style="width:350px"><i class="fa fa-spinner fa-spin"></i></td>
                            <td class="composite" style="width:320px">
                              <g:if test="${alignment.composite[n]}">
                                <span class="composite-fig" data-composite-url="${alignment.composite[n]}"><i class="fa fa-spinner fa-spin"></i></span>
                              </g:if>
                            </td>
                        </tr>
                    </g:each>
                    </g:if>
                    </tbody>
                </table>
                </g:each>
                </g:each>
            </li>
            </g:each>
        </ul>
    </section>
</main>
<script>
    $(function() {
        // plot composites
        google.charts.load('current', {'packages':['corechart']});
        
        // time delayed to draw composite figs
        var t = 0;
        
        // plot meme
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
                        memeTable.find(".meme-fig").each(function(index, memeFig){
                            $(memeFig).find("i").remove();
                            if (index < result.length) {
                                const memeDrawer = new MemeDrawer();
                                memeDrawer.make_motif(memeFig, result[index]);
                            } else {
                                $(memeFig).html("No MEME data found!");
                            }  
                        });
                    },
            });  
        });
        
        $(".composite-fig").each(function(){
            t += 10;
            var container = $(this);
            var compositeUrl = $(this).attr("data-composite-url");
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
                            var data = new google.visualization.arrayToDataTable(jsonData);

                            // Instantiate and draw our chart, passing in some options.
                            var chart = new google.visualization.LineChart(container[0]);
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
                                            legend: { position: 'top' }
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
    });
</script>
</body>
</html>