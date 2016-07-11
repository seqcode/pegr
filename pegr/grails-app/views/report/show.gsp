<html>
<head>
    <title>My Reports</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div>
        <h2>Summary Report</h2>
        <g:if test="${project}">
            <h3>Project: ${project?.name}</h3>
            <p>Description: ${project?.description}</p>
            <p>The number of Samples: ${samples?.size()}</p>
            <div id="project-users">
                <g:render template="/project/userTable" model="[projectEditAuth:false]"/>
            </div>
        </g:if>        
        <h3>Samples</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#sample">Description</a></li>
            <li><a data-toggle="tab" href="#epitope">Epitope Tags &amp; FastQC</a></li>
        </ul>

        <div class="tab-content">
            <div id="sample" class="tab-pane fade in active">
                <g:render template="/report/sampleTable" model="['sampleList':samples, 'project':project]" />
            </div>
            <div id="epitope" class="tab-pane fade">
                <g:render template="/report/epitopeTable" model="['experiments':experiments]" />
            </div>
        </div>
        <h3>Mapping Statistics</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#alignment"> Number of Tags</a></li>
            <li><a data-toggle="tab" href="#alignment2"> Percentage of Tags</a></li>
            <li><a data-toggle="tab" href="#alignment3"> Additional Information</a></li>
        </ul>

        <div class="tab-content">
            <div id="alignment" class="tab-pane in active">
                <g:render template="/report/alignmentTable" model="['alignmentList':alignments]" />
            </div>
            <div id="alignment2" class="tab-pane fade">
                <g:render template="/report/alignmentTable2" model="['alignmentList':alignments]" />
            </div>
            <div id="alignment3" class="tab-pane fade">
                <g:render template="/report/alignmentTable3" model="['alignmentList':alignments]" />
            </div>
        </div>
        <h3>Downstream Analysis</h3>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#peak">Peak Statistics</a></li>
        </ul>
        
        <div class="tab-content">
            <div id="peak" class="tab-pane fade in active">
                <g:render template="/report/peakTable" model="['sampleList':samples, 'project':project]" />
            </div>
        </div>
        <ul>
            <li>
                <h4>MEME Motifs</h4>
            </li>
            <li>
                <h4>Tag PileUp</h4>
            </li>
        </ul>
        <div class="pagination">
            <g:paginate id="${project.id}" total="${sampleCount ?: 0}" max="50"/>
        </div>   
    </div>
    </br>          
    
    <script>
        $(function(){
            $("#nav-reports").addClass("active");
        });
    </script>
</body>
</html>
