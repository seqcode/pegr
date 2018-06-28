<div>
    <span class="label label-success"> </span> Success;
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
                <th colspan="${runStatusMap.value.steps.size()-1}" class="text-center group group-pipeline">Pipeline</th>
                <th colspan="${qcSettings.general?.size()}" class="text-center group group-qc">Quality Control</th>
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
						<g:if test="${step[0]!='repeatmasker_wrapper_output_stats2'}">
	                        <th class="step-header col-step-${step[0]}  group-pipeline"><div><span>${step[1]}</span></div></th>
						</g:if>
                    </g:each>
                </g:if>
                <th class="text-right col-tags group-qc">Requested Tags</th>
                <g:each in="${qcSettings.general}" var="setting">
                  <g:if test="${setting.name != "Deduplicated"}">
                    <th class="text-right col-${setting.key} group-qc">
                        ${setting.name}
                        <ul style="font-weight: normal">
                            <g:if test="${setting.min}"><li>min: <g:formatNumber number="${setting.min}" format="${setting.numFormat}" /></li></g:if>
                            <g:if test="${setting.max}"><li>max: <g:formatNumber number="${setting.max}" format="${setting.numFormat}" /></li></g:if>
                            <g:if test="${setting.reference_min}"><li>min: ${setting.reference_min}</li></g:if>
                            <g:if test="${setting.reference_max}"><li>min: ${setting.reference_max}</li></g:if>
                        </ul>
                    </th>
                  </g:if>
                </g:each>
                <th class="col-prefer group-operation"><label class="switch"><input id="verifyAll" class="prefer2" type="checkbox"><div class="slider round"></div></label><span class="isAdmin" style="display:none">${isAdmin}</span>
					</br>Verified</th>
                <th class="col-delete group-operation"><input type="checkbox" id="selectAll" value="selectAll">
						<a id="ajaxDeleteAll" type="button"><span class="glyphicon glyphicon-trash"></a>
					</br>Delete
				</th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${runStatusMap.value.sampleStatusList}" var="sample">
            <tr>
            <td class="col-sample group-analysis" rowspan="${Math.max(1, sample.alignmentStatusList.size())}"><g:link controller="sample" action="show" id="${sample.sampleId}">${sample.sampleId}</g:link> ${sample.naturalId}</td>
            <td class="col-target group-analysis" rowspan="${Math.max(1, sample.alignmentStatusList.size())}">${sample.target}</td>
            <td class="col-cohort group-analysis" rowspan="${Math.max(1, sample.alignmentStatusList.size())}">${sample.cohort}</td>
            <g:each in="${sample.alignmentStatusList}" var="alignment" status="n">
                <g:if test="${n>0}"><tr></g:if>
                    <td class="col-genome group-analysis">${alignment.genome}</td>
                    <td class="col-history group-analysis"><a href="${alignment.galaxyBase}/history?id=${alignment.historyId}" target="_blank">${alignment.historyId}</a></td>
                    <td class="col-date group-analysis">${alignment.date}</td>
                    <g:each in="${alignment.status}" var="status" status="j">

					<g:if test="${runStatusMap.value.steps[j][0]!='repeatmasker_wrapper_output_stats2'}">
                        <td class="analysis-status col-step-${runStatusMap.value.steps[j][0]} group-pipeline">
                            <input class="analysisId" type="hidden" name="analysisId" value="${status.analysisId}">
                            <div class="popover-wrapper">
                            <div class="popover-toggle">
                                <g:if test="${status?.code=='OK'}">
                                    <span class="code label label-success"> </span>
                                </g:if>
                                <g:elseif test="${status?.code=='NO'}">
                                    <span class="code label label-default"> </span>
                                </g:elseif>
                                <g:elseif test="${status?.code=='Permission'}">
                                    <span class="code label label-warning"> </span>
                                </g:elseif>
                                <g:elseif test="${status?.code=='Zero'}">
                                    <span class="code label label-info"> </span>
                                </g:elseif>
                                <g:else>
                                    <span class="code label label-danger"> </span>
                                </g:else>
                            </div>

                            <div class="popover-content">
                                <h6>${runStatusMap.value.steps[j][1]}
                                    <g:if test="${status?.code!='NO'}"><span class="glyphicon glyphicon-pencil edit-code"></span></g:if>
                                </h6>
                                <p>${status.error}</p>
                                <p class="status-message">${status.message}</p>
                            </div>
                            </div>
                        </td>
					</g:if>
                    </g:each>

                    <td class="text-right col-tags group-qc"><g:formatNumber number="${alignment.requestedTags}" format="###,###,###" /></td>
                    <g:each in="${qcSettings.general}" var="setting">
                      <g:if test="${setting.name != "Deduplicated"}">
                        <td class="text-right col-${setting.key} group-qc <g:if test='${
                                   (setting.min != null && alignment[setting.key] < setting.min)
                                   || (setting.max != null && alignment[setting.key] > setting.max)
                                   || (setting.reference_min != null && alignment.hasProperty(setting.reference_min) && alignment[setting.key] < alignment[setting.reference_min] * setting.reference_min_ratio)
                                   || (setting.reference_max != null && alignment.hasProperty(setting.reference_max) && alignment[setting.key] > alignment[setting.reference_max] * setting.reference_max_ratio)
                                   }'>bg-danger</g:if>">
                        <g:formatNumber number="${alignment[setting.key]}" format="${setting.numFormat}" />
                        </td>
                      </g:if>
                    </g:each>
                    <td class="col-prefer group-operation">
                        <span class="alignmentId" style="display:none">${alignment.alignmentId}</span>
                        <label class="switch">
                            <input class="prefer" type="checkbox" <g:if test="${alignment.isPreferred}">checked</g:if>>
                            <div class="slider round"></div>
                        </label>
                    </td>
                    <td class="col-delete group-operation"><input type="checkbox" name="delete" value="${alignment.alignmentId}" data-runId="${run.id}"></td>
                </tr>
            </g:each>
            <g:if test="${sample.alignmentStatusList.size()==0}">
                </tr>
            </g:if>
        </g:each>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var codes = {"OK":"OK",
                 "Error":"Error",
                 "Permission":"Permission denied",
                 "Zero":"Empty datasets"};
    var codeClasses = {
        "OK":"label-success",
         "Error":"label-danger",
         "Permission":"label-warning",
         "Zero":"label-info"
    }

    $(".edit-code").on("click", function(){
        var $root = $(this).closest(".popover-content");
        var $selectCode = "<label>Code</label><select>";
        for (var code in codes) {
            $selectCode += '<option value="' + code + '">'+ codes[code] + '</option>';
        }
        $selectCode += "</select>";
        var message = $root.find(".status-message").text()
        var $inputMessage = "<input class='input-message' name='message' value='"+message+"'>";
        var $save = "<button class='btn btn-primary save'>Save</button>";
        var $cancel = "<button class='btn btn-default cancel'>Cancel</button>";
        $root.append("<div class='edit-code'>" +$selectCode + $inputMessage + $save + $cancel + "</div>");
    });

    $(".popover-wrapper").on("click", ".cancel", function(){
        $(this).closest("div.edit-code").remove();
    });

    $(".popover-wrapper").on("click", ".save", function(){
        var $td = $(this).closest("td");
        var analysisId = $td.find(".analysisId").val();
        var code = $td.find("select").val();
        var message = $td.find(".input-message").val();
        $.ajax({
            url:"/pegr/report/updateAnalysisCodeAjax",
            type:"POST",
            data:{analysisId: analysisId, code: code, message: message},
            success:function(){
                $td.find(".code").removeClass("label-default label-danger label-warning label-info").addClass(codeClasses[code]);
                $td.find(".status-message").text(message);
                $td.find("div.edit-code").remove();
            }
        })
    });
	$('#selectAll').click(function() {
		if (this.checked) {
			$('input[name="delete"]').prop('checked', true);
		}
		else {
			$('input[name="delete"]').prop('checked', false);
		}
	});

	$('input[name="delete"]').click(function(){
		if (this.checked) {
			$(this).prop('checked', true);
		}
		else {
			$('#selectAll').prop('checked', false);
		}
	});

	// axa677-180306: added the following jQuery function so when the button(link): ajaxDeletAll clicked,
	// it scans the checkboxes, retrieves their names, and stores them in an array
	$("#ajaxDeleteAll").click(function() {
		var alignmentIds = [];
		var runId = 0;
		$('input[name="delete"]').each(function(){
			if (this.checked) {
				alignmentIds.push(this.value);
				runId = this.getAttribute("data-runId");
			}
		});
		if (confirm('All data in the selected alignment(s) will be deleted. Are you sure you want to delete the following alignment(s): ' + alignmentIds + ' for run number: ' + runId + '?'))
		{ // axa677-180306: the next ajax call sends the array as a json dictionary with the run id to a controller action
		  // then get the results as html
			$.ajax({
				url:"${createLink(controller: 'report', action: 'deleteAllAlignmentAjax')}",
				type:"GET",
				data: {"alignIdsList": JSON.stringify(alignmentIds), "runId": runId},
				success : function(result){
					$("html").html(result);

				},
				error : function(e) {
					console.info("Error" + e);
				}
			});
		}
	});

  $("#verifyAll").unbind().click(function() {
                var $all = $(this);
		var $admin = $(".isAdmin").text();
                if ($admin == "false") { // save user from many alerts
                        alert("Permission denied");
                        $all.prop("checked", false);
                }
		var $res = $all.prop("checked");
        	var $rows = $("#qc-statistics").find("tr");
          var $count = 0;
        	$rows.each(function(i, row){
			var $succ = -1;
			$(row).find("td.analysis-status").each(function(j, cell){
				$succ = $(cell).find("span.label-success").length;
        if ($succ == 0) { $count = 1; }
      });

			// if all visible tools successful or unchecking all
        if ($succ + 1 > 1 || ($succ + 1 > 0 && !$res)) {
				//replicate what the on click function does
            			var $td = $(row).find("td.col-prefer");
            			var alignmentId = $td.find(".alignmentId").text();
				var $checkbox = $td.find(".prefer");
                                var $prev = $checkbox.prop("checked");
                                $checkbox.prop("checked", $res);
				//if changing
				if ($prev != $res) {
            			     $.ajax({ url: "/pegr/report/togglePreferredAlignment?alignmentId=" + alignmentId,
                			     error: function(xhr, ajaxOptions, thrownError) {
                    			         $checkbox.prop("checked", $prev);
                    			         $all.prop("checked", false);
                                   // Create cookie for the alert
                                   if( $.cookie('example') == null ) {
                                   $.cookie( 'example', '1',  { expires: 7, path: '/' } ); // Create cookie
                    			         alert("Error"); // message pops up when there's a discrepency between run result and the switch
                                   }
                			     }
            			     });
				}
			}
                });
      if ($count != 0){ alert("Not successful step found"); } // alert once for any failed sample run
	})


</script>
