<html>
<head>
    <title>My Reports</title> 
    <meta name="layout" content="analysis"/>
    <asset:javascript src="meme.js"/>
    <asset:stylesheet href="meme.css"/>
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
    </style>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div>
        <h2>Summary Report ${report.name} 
            <small>
                <span id="report-status-show" class="label label-default">${report.status}</span>
                <span id="report-status-select" style="display:none">
                    <g:select name="reportStatus" from="${pegr.ReportStatus}" value="${report.status}"></g:select>
                    <button id="report-status-save" class="btn btn-primary">Save</button>
                    <button id="report-status-cancel" class="btn btn-default">Cancel</button>
                </span>
            </small>
        </h2>
        <i>Edit on ${report.date}</i>
        <p>${report.note}</p>
        <g:if test="${project}">
            <h3>Project: <g:link controller="project" action="show" id="${project.id}">${project?.name}</g:link></h3>
            <p>Description: ${project?.description}</p>
            <div id="project-users">
                <g:render template="/project/userTable" model="[projectEditAuth:false]"/>
            </div>
        </g:if>
    </div> 
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
    <div id="details">
        <div class="text-center">
            <i class="fa fa-spinner fa-spin"></i>
        </div>
    </div>
    <script>
        $(function(){
            $(".nav-reports").addClass("active");
            $.ajax({url: "/pegr/report/fetchDataForReportAjax/${report.id}", success: function(result) {
                $("#details").html(result)
            }});
        });
        
        $("#report-status-show").click(function(){
            $("#report-status-show").hide();
            $("#report-status-select").show();
        });
        
        $("#report-status-save").click(function(){
            var status = $("#report-status-select option:selected").text();
            $.ajax({ url: "/pegr/report/updateReportStatusAjax?reportId=${report.id}&status=" + status,
                success: function(result) {
                    $("#report-status-show").text(result);
                    $("#report-status-select").val(result);
                    $("#report-status-show").show();
                    $("#report-status-select").hide();
                }                
            });
        });
        
        $("#report-status-cancel").click(function(){
            $("#report-status-show").show();
            var status =  $("#report-status-show").text();
            $("#report-status-select").val(status);
            $("#report-status-select").hide();
        });
    </script>
</body>
</html>
