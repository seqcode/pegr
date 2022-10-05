
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
            Sample ${sample.id} ${sample.naturalId}<g:if test="${sample.sourceId}">(${sample.source}#${sample.sourceId})</g:if>
            <g:link controller="sample" action="showItem" params="[sampleId:sample?.id]"><span class="glyphicon glyphicon-qrcode"></span></g:link>
            <small><span class="label label-default">${sample.status}</span></small> 
            <g:link action="show" id="${sample.id}" class="edit pull-right">Show</g:link>
        </h3>
        <g:if test="${sample?.date}">
            <p>Date: <g:formatDate format="yyyy-MM-dd" date="${sample.date}"/></p>
        </g:if>
        <g:render template="/sample/details" model="['sample': sample, 'sampleEditAuth':true]"></g:render>
        <div id="seq">

        </div>
        <h4>Replicates</h4>
        <div id="replicates">
            <g:render template="/replicate/list" model="[replicates: replicates]"></g:render>
        </div>
        <div id="project">
            <h4>Related Projects</h4>
            <ol>
            <g:each in="${sample.projects}">
                <li><g:link controller="project" action="show" id="${it.id}">${it}</g:link> <i>Created on <g:formatDate format="yyyy-MM-dd" date="${it.dateCreated}"/></i></li>
            </g:each>
            </ol>
        </div>
    </div>
    <script>
        $("#nav-metadata").addClass("active");     
    </script>
</body>
</html>
