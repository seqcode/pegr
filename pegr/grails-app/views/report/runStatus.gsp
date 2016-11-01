<html>
<head>
    <title>PEGR - Analysis Status</title> 
    <meta name="layout" content="analysis"/>
    <asset:javascript src="meme.js"/>
    <asset:stylesheet href="meme.css"/>
    <style>
        .step-header {
            height: 140px;
            white-space: nowrap;
        }

        .step-header > div {
            transform: 
                /* Magic Numbers */
                translate(25px, 51px)
                rotate(315deg);
            /* 45 is really 360 - 45 */
            -webkit-transform: rotate(-45deg);
            -moz-transform: rotate(-45deg);
            -ms-transform: rotate(-45deg);
            -o-transform: rotate(-45deg);
            width: 30px;
        }
        .step-header > div > span {
            border-bottom: 1px solid #ccc;
            padding: 5px 10px;
        }
        
        /* The switch - the box around the slider */
        .switch {
          position: relative;
          display: inline-block;
          width: 40px;
          height: 22px;
        }

        /* Hide default HTML checkbox */
        .switch input {display:none;}

        /* The slider */
        .slider {
          position: absolute;
          cursor: pointer;
          top: 0;
          left: 0;
          right: 0;
          bottom: 0;
          background-color: #ccc;
          -webkit-transition: .2s;
          transition: .2s;
        }

        .slider:before {
                  position: absolute;
          content: "";
          height: 16px;
          width: 16px;
          left: 4px;
          bottom: 3px;
          background-color: white;
          -webkit-transition: .2s;
          transition: .2s;
        }

        input:checked + .slider {
          background-color: #2196F3;
        }

        input:focus + .slider {
          box-shadow: 0 0 1px #2196F3;
        }

        input:checked + .slider:before {
          -webkit-transform: translateX(16px);
          -ms-transform: translateX(16px);
          transform: translateX(16px);
        }

        /* Rounded sliders */
        .slider.round {
          border-radius: 22px;
        }

        .slider.round:before {
          border-radius: 50%;
        }
        
        th.group-operation {
            background-color: #f0f0f5;
            background-image: none;
        }
        
        th.group-pipeline {
            background-color: #ffffcc;
            background-image: none;
        }
        
        th.group-qc {
            background-color: #e6ffe6;
            background-image: none;
        }
        
        .popover-wrapper {
            position: relative; 
            overflow: visible; 
            height: 100%;
        }
        
        .popover-content {
            border-radius: 5px;
            bottom: 25px;
            box-shadow: 0 0 10px black;
            display: none;
            font-size: 10px;
            font-family: 'Helvetica',sans-serif;
            left: -63px;
            padding: 0px;
            position: absolute;
            width: 135px;
            z-index: 500;
            background-color: white;
            text-align: center;
        }
        
        .popover-content:before {
            border-top: 7px solid white;
            border-right: 7px solid transparent;
            border-left: 7px solid transparent;
            bottom: -5px;
            content: '';
            display: block;
            left: 50%;
            margin-left: -7px;
            position: absolute;
            z-index: 1000;
        }
        
        .popover-content h6 {
            margin: 2px;
        }
        
        .popover-content p {
            margin: 2px;
        }
        
    </style>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h3>
        <g:link controller="sequenceRun" action="show" id="${run.id}">Run ${run.id} <g:if test="${run.runNum}">(Old No.${run.runNum})</g:if></g:link>
        <small>
            <span id="run-status-show" class="label label-default">${run.status}</span> 
            <span id="run-status-select" style="display:none">
                <g:select name="runStatus" from="${pegr.RunStatus}" value="${run.status}"></g:select>
                <button id="run-status-save" class="btn btn-primary">Save</button>
                <button id="run-status-cancel" class="btn btn-default">Cancel</button>
            </span>
        </small> 
    </h3>
    <g:link controller="report" action="unknownIndex" params="[runId: run.id]">Unknown index</g:link>
    <g:each in="${runStatus}">
        <div>
            <h4>Pipeline: ${it.key.name}, version: ${it.key.pipelineVersion} (workflow ID: <a href="http://galaxy-cegr.psu.edu:8080/workflow/display_by_id?id=${it.key.workflowId}" target="_blank">${it.key.workflowId}</a>) <sec:ifAnyGranted roles="ROLE_ADMIN"><g:link controller="pipelineAdmin" action="show" id="${it.key.id}" class="edit">Manage</g:link></sec:ifAnyGranted></h4>
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#general">General</a></li>
                <li><a data-toggle="tab" href="#yeast">Yeast</a></li>
            </ul>
            <div class="tab-content">
                <div id="general" class="tab-pane fade in active">
                    <g:render template="generalQc" model="[runStatusMap:it]"></g:render>
                </div>
                <div id="yeast" class="tab-pane fade">
                    <g:render template="yeastEncodeQc" model="[runStatusMap:it]"></g:render>
                </div>
            </div>            
        </div>
    </g:each>
    <g:if test="${noResultSamples.size() > 0}">
        <table class="table">
            <caption><h4>No Results</h4></caption>
            <thead>
                <tr>
                    <th>Sample</th>
                    <th>Cohort</th>
                </tr>
            </thead>
            <tbody>
            <g:each in="${noResultSamples}" var="sample">
                <tr>
                    <td><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link></td>
                    <td>${sample.cohort}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:if>
    <h3>Reports</h3>
    <table class="table">
        <thead>
            <th>Sequencing Cohort</th>
            <th>Report</th>
            <th>Status</th>
            <th>Date</th>
            <th></th>
        </thead>
        <tbody>
        <g:each in="${run.cohorts}" var="cohort">
            <tr id="cohort-${cohort.id}">
                <g:render template="/report/reportRow" model="[cohort: cohort]"></g:render>
            </tr>
        </g:each>
        </tbody>
    </table>
    <br>
    <script>        
        $(function(){  
            var hash = window.location.hash;
            hash && $('ul.nav a[href="' + hash + '"]').tab('show');

            $('.nav-tabs a').click(function (e) {
                $(this).tab('show');
                var scrollmem = $('body').scrollTop() || $('html').scrollTop();
                window.location.hash = this.hash;
                $('html,body').scrollTop(scrollmem);
            });
            
            $(".confirm").confirm({text: "All data in this alignment will be deleted. Are you sure you want to delete this alignment?"});
            $(".nav-status").addClass("active");
            $('[data-toggle="popover"]').popover(); 

            $("#run-status-show").click(function(){
                $("#run-status-show").hide();
                $("#run-status-select").show();
            });

            $("#run-status-save").click(function(){
                var status = $("#run-status-select option:selected").text();
                $.ajax({ url: "/pegr/report/updateRunStatusAjax?runId=${run.id}&status=" + status,
                    success: function(result) {
                        $("#run-status-show").text(result);
                        $("#run-status-select").val(result);
                        $("#run-status-show").show();
                        $("#run-status-select").hide();
                    }                
                });
            });

            $("#run-status-cancel").click(function(){
                $("#run-status-show").show();
                $("#run-status-select").hide();
            });
            
            $("#qc-statistics th").each(function(){
                $(this).append(" <span class='glyphicon glyphicon-minus-sign small'></span>");
            });
            
            $("#column-toggle").click(function() {
                $("th").show();
                $("td").show();
                $(".group").each(function(){
                    var classAttr = $(this).attr("class");
                    var classes = classAttr.split(' ');
                    var groupClass;
                    for (n in classes) {
                        var classname = classes[n];
                        if (classname.substring(0,6) == "group-") {
                            groupClass = classname;
                            break;
                        }
                    }
                    var n = $(this).closest("table").find("tbody tr:first-child ." + groupClass).length;
                    $(this).attr("colspan", n);
                });
            });

            $(".glyphicon-minus-sign").click(function() {
                var classAttr = $(this).parent().attr("class");
                var classes = classAttr.split(' ');
                var category;
                var classToHide;
                var groupClass;
                for (n in classes) {
                    var classname = classes[n];
                    if (classname.substring(0,4) == "col-") {
                        category = "col";
                        classToHide = classname;
                    } else if (classname.substring(0,6) == "group-") {
                        groupClass = classname;
                        if (category != "col") {
                            category = "group";
                            classToHide = classname;
                        }
                    }
                }      
                $("." + classToHide).hide();
                if (category == "col") {
                    var groupHeader = $(".group + ." + groupClass);
                    var n = groupHeader.attr("colspan") - 1;
                    if (n == 0) {
                        groupHeader.hide();    
                    } else {
                        groupHeader.attr("colspan", n);
                    }
                }
            });
            $(".popover-content").hide();
            $(".popover-toggle").click(function() {
                var target = $(this).next();
                $(target).toggle();
            });
        });
        
        function createReport(cohortId) {
            $.ajax({ url: "/pegr/report/createReportForCohortAjax?cohortId=" + cohortId, success: function(result) {
                $("#cohort-"+cohortId).html(result);
            }});
            $('.confirm-remove-report').confirm({text: "Are you sure you want to delete this report?"});
        }
        
        function removeReport(cohortId) {
            $.confirm({
                text: "Are you sure you want to delete this report?",
                confirm: function() {
                    $.ajax({ url: "/pegr/report/deleteReportForCohortAjax?cohortId=" + cohortId, success: function(result) {
                        $("#cohort-"+cohortId).html(result);
                    }});  
                },
                cancel: function() {
                    // nothing to do
                }
            });
        }
        
        $(".prefer").on("click", function() {
            var $td = $(this).closest("td");
            var alignmentId = $td.find(".alignmentId").text();
            $.ajax({ url: "/pegr/report/togglePreferredAlignment?alignmentId=" + alignmentId,
                error: function(xhr, ajaxOptions, thrownError) {
                    var $checkbox = $td.find(".prefer");
                    if ($checkbox.is(":checked")) {
                        $checkbox.prop("checked", false);
                    } else {
                        $checkbox.prop("checked", true);
                    }
                    $checkbox.checked = !$checkbox.checked;
                    alert("Error");
                }
            });            
        });
    </script>
</body>
</html>