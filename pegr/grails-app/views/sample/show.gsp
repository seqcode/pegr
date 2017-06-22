
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
        
        <g:if test="${sample?.date}">
            <p>Date: <g:formatDate format="yyyy-MM-dd" date="${sample.date}"/></p>
        </g:if>
        <h4><b>Click to reveal/edit the info below</b></h4>
        <g:render template="details" model="['sample': sample]"></g:render>


        <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title"><a data-toggle="collapse" href="#collapse1">YEP Site Details</a>
                </h4>
            </div>
        </div>

        <div id="collapse1" class="panel-collapse collapse">
            <table class="table table-responsive">
                <div id="center_iframe">
                    <div id="iframe_div">
                        <iframe src="https://worm.hanfucw.com/13137/" id="yep_iframe" scrolling="no" frameborder="no"></iframe>
                    </div>
                </div>
            </table>
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
