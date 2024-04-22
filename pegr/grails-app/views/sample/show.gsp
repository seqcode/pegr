
<%@ page import="pegr.Sample" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Sample</title>
    <asset:javascript src="meme.js"/>
    <asset:stylesheet href="meme.css"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
    <div class="container-fluid">
      <div class="well">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <h3>
            Sample ${sample.id} ${sample.naturalId} <g:if test="${sample.sourceId}">(${sample.source}#${sample.sourceId})</g:if><g:if test="${editAuth && sample.item}"><g:link controller="sample" action="showItem" params="[sampleId:sample?.id]"><span class="glyphicon glyphicon-qrcode"></span></g:link></g:if>
            <small>
                <span id="sample-status-show" class="label label-default">${sample.status}</span>
                <g:if test="${editAuth}">
                <span id="sample-status-select" style="display:none">
                    <g:select name="sampleStatus" from="${pegr.SampleStatus}" value="${sample.status}" style="width:10em"></g:select>
                    <button id="sample-status-save" class="btn btn-primary">Save</button>
                    <button id="sample-status-cancel" class="btn btn-default">Cancel</button>
                </span>
                </g:if>
            </small>
            <g:if test="${editAuth}">
                <g:link action="edit" params="[sampleId:sample?.id]" class="edit pull-right">Edit</g:link>
            </g:if>
        </h3>

        <!-- pjchaffin | Adds Hyperlink Functionality to the Run# and also depricates the old Run Number -->
        <g:if test="${sample?.runs.id[0]}">
            <p><g:link controller="sequenceRun" action="show" id="${sample.runs.id[0]}"><b>Sequence Run ${sample.runs.id[0]} Metadata</b></g:link></p>
            <p><g:link controller="report" action="runStatus" params="[runId: sample.runs.id]"><b>Sequence Run ${sample.runs.id[0]} Analysis</b></g:link></p>
        </g:if>


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
            
            <g:if test="${editAuth}">
            $("#sample-status-show").click(function(){
                $("#sample-status-show").hide();
                $("#sample-status-select").show();
            });

            $("#sample-status-save").click(function(){
                var status = $("#sample-status-select option:selected").text();
                $.ajax({ url: "/pegr/sample/updateSampleStatusAjax?sampleId=${sample.id}&status=" + status,
                    success: function(result) {
                        $("#sample-status-show").text(result);
                        $("#sample-status-select").val(result);
                        $("#sample-status-show").show();
                        $("#sample-status-select").hide();
                    }                
                });
            });

            $("#sample-status-cancel").click(function(){
                $("#sample-status-show").show();
                $("#sample-status-select").hide();
            });
            </g:if>
            
        });
    </script>
</body>
</html>
