<html>
<head>
    <title>PEGR - Analysis Status</title> 
    <meta name="layout" content="analysis"/>
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

            <div>
                <span class="label label-success"> </span> Data received; 
                <span class="label label-danger"> </span> Error message;
                <span class="label label-warning"> </span> Permission denied;
                <span class="label label-info"> </span> Empty dataset (e.g. no peaks, no peak-pairs, no motifs, etc.);
                <span class="label label-default"> </span> No data received.
                Click each block to see the step's category.
                <span class="glyphicon glyphicon-minus-sign"></span> Hide the column;
                <span id="column-toggle"> <span class="glyphicon glyphicon-plus-sign"></span> Show all columns </span>
            </div>
            <div id="qc-statistics" class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th class="col-sample">Sample</th>                                                                    
                            <th class="col-target">Target</th>
                            <th class="col-cohort">Cohort</th>
                            <th class="col-genome">Genome</th>
                            <th class="col-history">Galaxy History</th>
                            <th class="col-date">Date</th>
                            <g:if test="${it.value.steps}">
                                <g:each in="${it.value.steps}" var="step">
                                    <th class="step-header col-step-${step[0]}"><div><span>${step[1]}</span></div></th>
                                </g:each>
                            </g:if>
                            <th class="text-right col-tags">Requested Tags</th>
                            <g:each in="${qcSettings}" var="setting">
                                <th class="text-right col-${setting.key}">
                                    ${setting.name}
                                    <ul style="font-weight: normal">
                                        <g:if test="${setting.min}"><li>min: <g:formatNumber number="${setting.min}" format="${setting.numFormat}" /></li></g:if>
                                        <g:if test="${setting.max}"><li>max: <g:formatNumber number="${setting.max}" format="${setting.numFormat}" /></li></g:if>
                                        <g:if test="${setting.reference_min}"><li>min: ${setting.reference_min}</li></g:if>
                                        <g:if test="${setting.reference_max}"><li>min: ${setting.reference_max}</li></g:if>
                                    </ul>                                        
                                </th>
                            </g:each>
                            <th class="col-prefer">Preferred</th>
                            <th class="col-delete">Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${it.value.sampleStatusList}" var="sample">
                        <tr>
                        <td class="col-sample" rowspan="${Math.max(1, sample.alignmentStatusList.size())}"><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link></td>
                        <td class="col-target" rowspan="${Math.max(1, sample.alignmentStatusList.size())}">${sample.target}</td>
                        <td class="col-cohort" rowspan="${Math.max(1, sample.alignmentStatusList.size())}">${sample.cohort}</td>
                        <g:each in="${sample.alignmentStatusList}" var="alignment" status="n">
                            <g:if test="${n>0}"><tr></g:if>
                                <td class="col-genome">${alignment.genome}</td>
                                <td class="col-history"><a href="http://galaxy-cegr.psu.edu:8080/history?id=${alignment.historyId}" target="_blank">${alignment.historyId}</a></td>
                                <td class="col-date">${alignment.date}</td>
                                <g:each in="${alignment.status}" var="status" status="j">
                                    <td class="analysis-status col-step-${it.value.steps[j][0]}">
                                        <g:if test="${status?.code=='OK'}">
                                            <span data-toggle="popover" data-content="${it.value.steps[j][1]}" data-placement="top" class="label label-success"> </span>
                                        </g:if> 
                                        <g:elseif test="${status?.code=='NO'}">
                                            <span data-toggle="popover" data-content="${it.value.steps[j][1]}" data-placement="top" class="label label-default"> </span>
                                        </g:elseif>
                                        <g:elseif test="${status?.code=='Permission'}">
                                            <span data-toggle="popover" title="${it.value.steps[j][1]}" data-content="${status.message}" data-placement="top" class="label label-warning"> </span>
                                        </g:elseif> 
                                        <g:elseif test="${status?.code=='Zero'}">
                                            <span data-toggle="popover" title="${it.value.steps[j][1]}" data-content="${status.message}" data-placement="top" class="label label-info"> </span>
                                        </g:elseif>
                                        <g:else>
                                            <span data-toggle="popover" title="${it.value.steps[j][1]}" data-content="${status?.error}" data-placement="top" class="label label-danger"> </span>
                                        </g:else>
                                    </td>
                                </g:each>

                                <td class="text-right col-tags"><g:formatNumber number="${alignment.requestedTags}" format="###,###,###" /></td>
                                <g:each in="${qcSettings}" var="setting">
                                    <td class="text-right col-${setting.key} <g:if test='${
                                               (setting.min != null && alignment[setting.key] < setting.min)
                                               || (setting.max != null && alignment[setting.key] > setting.max)
                                               || (setting.reference_min != null && alignment.hasProperty(setting.reference_min) && alignment[setting.key] < alignment[setting.reference_min])
                                               || (setting.reference_max != null && alignment.hasProperty(setting.reference_max) && alignment[setting.key] > alignment[setting.reference_max])
                                               }'>bg-danger</g:if>"> 
                                    <g:formatNumber number="${alignment[setting.key]}" format="${setting.numFormat}" />
                                </td>
                                </g:each>
                                <td class="col-prefer">
                                    <label class="switch">
                                        <input class="prefer" type="checkbox" onclick="togglePreferredAlignment(${alignment.alignmentId})" <g:if test="${alignment.isPreferred}">checked</g:if>>
                                        <div class="slider round"></div>
                                    </label>
                                </td>
                                <td class="col-delete"><g:link controller="report" action="deleteAlignment" params="[alignmentId:alignment.alignmentId, runId:run.id]" class="confirm"><span class="glyphicon glyphicon-trash"</g:link></td>
                            </tr>
                        </g:each>
                        <g:if test="${sample.alignmentStatusList.size()==0}">
                            </tr>
                        </g:if>
                    </g:each>
                    </tbody>
                </table>
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
            });
            
            $(".glyphicon-minus-sign").click(function() {
                var classAttr = $(this).parent().attr("class");
                var classes = classAttr.split(' ');
                for (n in classes) {
                    var classname = classes[n];
                    if (classname.substring(0,4) == "col-") {
                        $("." + classname).hide();
                    }
                }                
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
        
        function togglePreferredAlignment(alignmentId) {
            $.ajax({ url: "/pegr/report/togglePreferredAlignment?alignmentId=" + alignmentId });            
        }
    </script>
</body>
</html>