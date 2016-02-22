
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
                <div id="seq">
                    <h4>Sequence Runs</h4>
                    <g:each in="${sample.runs}"><a href="#"><g:formatDate format="yyyy-MM-dd" date="${it.date}"/></p></a></g:each>
                </div>  
                <div id="bioRep">
                    <h4>Biological Replicates</h4>
                    <g:each in="${sample.bioReps}">${it}</g:each>
                </div>  
                <div id="techRep">
                    <h4>Technical Replicates</h4>
                    <g:each in="${sample.techReps}">${it}</g:each>
                </div>  
                <div id="project">
                    <h4>Projects</h4>
                    <g:each in="${sample.projects}">${it}</g:each>
                </div>
            </div>
        </div>
    </div>
    <script>
        $("#nav-metadata").addClass("active");      
    </script>
</body>
</html>
