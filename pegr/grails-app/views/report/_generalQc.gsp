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
<div id="qc-statistics">
    <table class="table">
        <thead>
            <tr>
                <th colspan="6" class="text-center group group-analysis">Analysis</th>
                <th colspan="${runStatusMap.value.steps.size()}" class="text-center group group-pipeline">Pipeline</th>
                <th colspan="${qcSettings.general?.size() + 1}" class="text-center group group-qc">Quality Control</th>
                <th colspan="2" class="text-center group group-operation">Operation</th>
            </tr>
            <tr>
                <th class="col-sample group-analysis">Sample</th>
                <th class="col-target group-analysis">Target</th>
                <th class="col-cohort group-analysis">Cohort</th>
                <th class="col-genome group-analysis">Genome</th>
                <th class="col-history group-analysis">Galaxy History</th>
                <th class="col-date group-analysis">Date</th>
                <g:if test="${runStatusMap.value.steps}">
                    <g:each in="${runStatusMap.value.steps}" var="step">
                        <th class="step-header col-step-${step[0]}  group-pipeline"><div><span>${step[1]}</span></div></th>
                    </g:each>
                </g:if>
                <th class="text-right col-tags group-qc">Requested Tags</th>
                <g:each in="${qcSettings.general}" var="setting">
                    <th class="text-right col-${setting.key} group-qc">
                        ${setting.name}
                        <ul style="font-weight: normal">
                            <g:if test="${setting.min}"><li>min: <g:formatNumber number="${setting.min}" format="${setting.numFormat}" /></li></g:if>
                            <g:if test="${setting.max}"><li>max: <g:formatNumber number="${setting.max}" format="${setting.numFormat}" /></li></g:if>
                            <g:if test="${setting.reference_min}"><li>min: ${setting.reference_min}</li></g:if>
                            <g:if test="${setting.reference_max}"><li>min: ${setting.reference_max}</li></g:if>
                        </ul>                                        
                    </th>
                </g:each>
                <th class="col-prefer group-operation">Preferred</th>
                <th class="col-delete group-operation">Delete</th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${runStatusMap.value.sampleStatusList}" var="sample">
            <tr>
            <td class="col-sample group-analysis" rowspan="${Math.max(1, sample.alignmentStatusList.size())}"><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link></td>
            <td class="col-target group-analysis" rowspan="${Math.max(1, sample.alignmentStatusList.size())}">${sample.target}</td>
            <td class="col-cohort group-analysis" rowspan="${Math.max(1, sample.alignmentStatusList.size())}">${sample.cohort}</td>
            <g:each in="${sample.alignmentStatusList}" var="alignment" status="n">
                <g:if test="${n>0}"><tr></g:if>
                    <td class="col-genome group-analysis">${alignment.genome}</td>
                    <td class="col-history group-analysis"><a href="http://galaxy-cegr.psu.edu:8080/history?id=${alignment.historyId}" target="_blank">${alignment.historyId}</a></td>
                    <td class="col-date group-analysis">${alignment.date}</td>
                    <g:each in="${alignment.status}" var="status" status="j">
                        <td class="analysis-status col-step-${runStatusMap.value.steps[j][0]} group-pipeline">
                            <div class="popover-wrapper">
                            <div class="popover-toggle">
                                <g:if test="${status?.code=='OK'}">
                                    <span class="label label-success"> </span>
                                </g:if>
                                <g:elseif test="${status?.code=='NO'}">
                                    <span class="label label-default"> </span>
                                </g:elseif>
                                <g:elseif test="${status?.code=='Permission'}">
                                    <span class="label label-warning"> </span>
                                </g:elseif> 
                                <g:elseif test="${status?.code=='Zero'}">
                                    <span class="label label-info"> </span>
                                </g:elseif>
                                <g:else>
                                    <span class="label label-danger"> </span>
                                </g:else>
                            </div>

                            <div class="popover-content">
                                <h6>${runStatusMap.value.steps[j][1]}</h6>
                                <p>${status.error}</p>
                                <p>${status.message}</p>
                            </div>
                            </div>
                        </td>
                    </g:each>

                    <td class="text-right col-tags group-qc"><g:formatNumber number="${alignment.requestedTags}" format="###,###,###" /></td>
                    <g:each in="${qcSettings.general}" var="setting">
                        <td class="text-right col-${setting.key} group-qc <g:if test='${
                                   (setting.min != null && alignment[setting.key] < setting.min)
                                   || (setting.max != null && alignment[setting.key] > setting.max)
                                   || (setting.reference_min != null && alignment.hasProperty(setting.reference_min) && alignment[setting.key] < alignment[setting.reference_min] * setting.reference_min_ratio)
                                   || (setting.reference_max != null && alignment.hasProperty(setting.reference_max) && alignment[setting.key] > alignment[setting.reference_max] * setting.reference_max_ratio)
                                   }'>bg-danger</g:if>"> 
                        <g:formatNumber number="${alignment[setting.key]}" format="${setting.numFormat}" />
                    </td>
                    </g:each>
                    <td class="col-prefer group-operation">
                        <label class="switch">
                            <input class="prefer" type="checkbox" onclick="togglePreferredAlignment(${alignment.alignmentId})" <g:if test="${alignment.isPreferred}">checked</g:if>>
                            <div class="slider round"></div>
                        </label>
                    </td>
                    <td class="col-delete group-operation"><g:link controller="report" action="deleteAlignment" params="[alignmentId:alignment.alignmentId, runId:run.id]" class="confirm"><span class="glyphicon glyphicon-trash"</g:link></td>
                </tr>
            </g:each>
            <g:if test="${sample.alignmentStatusList.size()==0}">
                </tr>
            </g:if>
        </g:each>
        </tbody>
    </table>
</div>