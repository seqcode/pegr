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
        <h2>Summary Report ${report.name} <small><span class="label label-default">${report.status}</span></small></h2>
        <i>Edit by ${report.user} on ${report.date}</i>
        <p>${report.note}</p>
        <g:if test="${project}">
            <h3>Project: <g:link controller="project" action="show" id="${project.id}">${project?.name}</g:link></h3>
            <p>Description: ${project?.description}</p>
            <div id="project-users">
                <g:render template="/project/userTable" model="[projectEditAuth:false]"/>
            </div>
        </g:if>        
 
    </div>
    </br>          
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
    </script>
</body>
</html>
