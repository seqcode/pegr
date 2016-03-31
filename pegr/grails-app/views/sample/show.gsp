
<%@ page import="pegr.Sample" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Sample</title>
</head>
<body>
    <div class="container-fluid">
        <div>
            <g:link action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link>
        </div>
        <div class="row">
            <div class="col-md-10">        
                <h3>
                    Sample ${sample.id} 
                    <small><span class="label label-default">${sample.status}</span></small> 
                </h3>
                <g:if test="${sample?.date}">
                    <p>Date: <g:formatDate format="yyyy-MM-dd" date="${sample.date}"/></p>
                </g:if>
                <g:render template="details" model="['sample': sample]"></g:render>
                <div id="seq">
                    <h4>Sequencing Experiments</h4>
                    <ul>
                        <g:each in="${sample.sequencingExperiments}">
                            <li>
                                <h4><g:link controller="sequenceRun" action="show" id="${it.sequenceRun.id}">Sequence Run ${it.sequenceRun.id}(Old No.${it.sequenceRun.runNum})</g:link></h4>
                                <g:render template="sequencingTable" model="['experiment':it]"></g:render>
                                <g:each in="${it.alignments}">
                                    <li>Reference Genome: ${it.genome}</li>
                                </g:each>
                            </li>
                        </g:each>
                        
                    </ul>
                </div>
            </div>
            <div class="col-md-2 well">
                <h3 class="text-center">Links</h3>
                <div id="project">
                    <h4>Projects</h4>
                    <g:each in="${sample.projects}">
                        <g:link controller="project" action="show" id="${it.id}"><g:formatDate format="yyyy-MM-dd" date="${it.dateCreated}"/> ${it}</g:link>
                    </g:each>
                </div>
                <div id="seq">
                    <h4>Sequence Runs</h4>
                    <g:each in="${sample.runs}"><g:link controller="sequenceRun" action="show" id="${it.id}"><g:formatDate format="yyyy-MM-dd" date="${it.date}"/> Run #${it.id} (Old No.${it.runNum})</g:link></g:each>
                </div>  
                <div id="bioRep">
                    <h4>Biological Replicates</h4>
                    <g:each in="${sample.bioReps}">${it}</g:each>
                </div>  
                <div id="techRep">
                    <h4>Technical Replicates</h4>
                    <g:each in="${sample.techReps}">${it}</g:each>
                </div>  
            </div>
        </div>
    </div>
    <script>
        $("#nav-metadata").addClass("active");      
    </script>
</body>
</html>
