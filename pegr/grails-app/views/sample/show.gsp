
<%@ page import="pegr.Sample" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Sample</title>
    <asset:javascript src="meme.js"/>
    <asset:stylesheet href="meme.css"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
    <div class="container-fluid">
      <div class="well">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <h3>
            Sample ${sample.id} ${sample.naturalId} <g:if test="${sample.sourceId}">(${sample.source}#${sample.sourceId})</g:if><g:if test="${editAuth && sample.item}"><g:link controller="sample" action="showItem" params="[sampleId:sample?.id]"><span class="glyphicon glyphicon-qrcode"></span></g:link></g:if>
            <small><span class="label label-default">${sample.status}</span></small>
            <g:if test="${editAuth}">
                <g:link action="edit" params="[sampleId:sample?.id]" class="edit pull-right">Edit</g:link>
            </g:if>
        </h3>

        <!-- pjchaffin | Adds Hyperlink Functionality to the Run# and also depricates the old Run Number (axa677: read the following comment)-->
		<!-- axa677: the previous script uses sample.runs.id[0] so it always takes the most recent run even when we have many runs for same sample.
			To address this problem: this show view is visited from two places: 1) when it comes from the sequenceRun page (so we're clicking on a sample that belongs to a sepecifc page). In that case, we need to make sure the run # is the right only one from the previous view. 
			2) when we are coming from the sample list page, then we need to list all runs related to that sample -->
        <g:if test="${sample?.runs.id[0]}">
            <p><g:link controller="report" action="runStatus" params="[runId: sample.runs.id]"><b>Run:${sample.runs.id[0]}</b></g:link></p>
        </g:if>
                                <g:else>
                                    <span class="code label label-danger"> </span>
                                </g:else>

        <g:if test="${sample?.date}">
            <p>Date: <g:formatDate format="yyyy-MM-dd" date="${sample.date}"/></p>
        </g:if>
        <g:render template="details" model="['sample': sample]"></g:render>
      </div>
      <div class="well">
        <h3>Replicates</h3>
        <div id="replicates">
            <g:render template="/replicate/list" model="[replicates: replicates]"></g:render>
        </div>
        <div id="project">
            <h3>Related Projects</h3>
            <ol>
            <g:each in="${sample.projects}">
                <li><g:link controller="project" action="show" id="${it.id}">${it}</g:link> <i>Created on <g:formatDate format="yyyy-MM-dd" date="${it.dateCreated}"/></i></li>
            </g:each>
            </ol>
        </div>
        <div id="details">
            <div class="text-center">
                <i class="fa fa-spinner fa-spin"></i>
            </div>
        </div>
      </div>
    </div>

    <script>
        $(function(){
            $("#nav-metadata").addClass("active");
            $.ajax({url: "/pegr/sample/fetchDataForSampleAjax/${sample.id}", success: function(result) {
                $("#details").html(result)
            }})
        });
    </script>
</body>
</html>
