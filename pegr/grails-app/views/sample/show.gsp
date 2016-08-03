
<%@ page import="pegr.Sample" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Sample</title>
</head>
<body>
    <div class="container-fluid">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div>
            <g:link action="all"><span class="glyphicon glyphicon-home"></span> Sample List</g:link>
        </div>     
        <h3>
            Sample ${sample.id} <g:if test="${sample.sourceId}">(${sample.source}#${sample.sourceId})</g:if>
            <small><span class="label label-default">${sample.status}</span></small> 
            <g:if test="${editAuth}">
                <g:link action="edit" params="[sampleId:sample?.id]" class="edit pull-right">Edit</g:link>
            </g:if>
        </h3>
        
        <g:if test="${sample?.date}">
            <p>Date: <g:formatDate format="yyyy-MM-dd" date="${sample.date}"/></p>
        </g:if>
        <g:render template="details" model="['sample': sample]"></g:render>

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
