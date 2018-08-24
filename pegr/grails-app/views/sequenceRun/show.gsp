<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
    <style>
        .btn {
            padding: 0 5px;
        }
        .project {
            width: 250px;    
        }
        input[type="file"] {
            display: inline;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <g:link action="index"><span class="glyphicon glyphicon-home"></span> Sequencing Run List</g:link>   
    <div id="message" >
        <g:if test="${flash.message}">
             <div class="message" role="status">
                ${flash.message}
            </div>
        </g:if>
    </div>
    <h2>Sequence Run #${run.runNum} <g:if test="${run.runNumAlias}">(Old No.${run.runNumAlias})</g:if> 
        <small>
            <span id="run-status-show" class="label label-default">${run.status}</span>
            <span id="run-status-select" style="display:none">
                <g:select name="runStatus" from="${pegr.RunStatus}" value="${run.status}"></g:select>
                <button id="run-status-save" class="btn btn-primary">Save</button>
                <button id="run-status-cancel" class="btn btn-default">Cancel</button>
            </span>
        </small></h2>
    <a href="#" class="btn btn-primary pull-right" data-toggle="modal" data-target="#download-run-info">Download Run Info Files</a>
    <div id="download-run-info" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
              <button type="button" class="pull-right close" data-dismiss="modal">&times;</button>
          </div>
          <div class="modal-body">
            <p>Note: please verify the directory name of this sequence run before generating the run info files.</p>  
            <g:form controller="sequenceRun" action="downloadRunInfo">
                <g:hiddenField name="runId" value="${run.id}"></g:hiddenField>
                <div class="form-group">
                    <label>Remote Root Folder</label>
                    <input name="remoteRoot" class="form-control">
                </div>
                <ul>e.g. 
                    <li>Wall-E: /home/nextseq/NSQData_PughLab/</li>
                    <li>gpfs: /gpfs/cyberstar/pughhpc/storage/illumina/illuminaNextSeq/NSQData_PughLab/</li>
                </ul>
                <g:submitButton name="submit" class="btn btn-primary" value="Download Run Info Files"></g:submitButton>
            </g:form>
          </div>
        </div>
      </div>
    </div>
    <h3>Summary <g:if test="${editable}"> <g:link action="editInfo" params="[runId:run.id]"><span class="edit">Edit</span></g:link></g:if></h3>
    <g:render template="summaryDetails"></g:render>  
    <h3>Projects 
        <g:if test="${editable}">
            <span class="edit add-project">Add</span>
            <g:form controller="sequenceRun" action="addProject" class="add-project-form">
                <input type="hidden" name="runId" value="${run.id}">
                <g:select name="projectId" from="${pegr.Project.list()}" optionKey="id" noSelection="['':'']"></g:select>
                <g:submitButton class="btn btn-primary" name="save" value="Save"></g:submitButton>
                <span class="btn btn-default cancel-project">Cancel</span>
            </g:form>
        </g:if>
    </h3>
    <table class="table table-bordered" id="project-table">
        <thead>
            <tr>
                <g:if test="${editable}"><th title="Remove the project from this sequence run, but the project remains."><span class="glyphicon glyphicon-question-sign"></span></th><th title="Delete the project."><span class="glyphicon glyphicon-question-sign"></span></th></g:if>
                <th>Project</th>
                <th>Sonication Images</th>
                <th>Gel Images</th>
            <tr>
        </thead>
        <tbody>
            <g:each in="${run.cohorts}" var="cohort">
            <tr>
                <input class="cohort-id" type="hidden" name="cohortId" value="${cohort.id}">
                <g:if test="${editable}">
                    <td title="Remove the project from this sequence run, but the project remains."><g:link controller="sequenceRun" action="removeProject" params="[cohortId:cohort.id, runId:run.id]" class="confirm-remove-project"><span class="glyphicon glyphicon-remove remove-project"></span></g:link></td>
                    <td title="Delete the project."><g:link controller="sequenceRun" action="deleteProject" params="[cohortId:cohort.id, runId:run.id]" class="confirm-delete-project"><span class="glyphicon glyphicon-trash remove-project"></span></g:link></td>
                </g:if>
                <td>${cohort.project.name}</td>
                <td>
                    <ul>
                    <g:each in="${cohort.imageMap?.sonication}" var="filepath">
                        <li>
                            <img src='${createLink(controller:"file", action: "displayImage", params:[filepath: filepath, relative: true])}' height="300"/>
                            <g:hiddenField class="filepath" name="filepath" value="${filepath}"></g:hiddenField>
                            <g:if test="${editable}"><span class="glyphicon glyphicon-remove btn remove-image"></span></g:if>
                        </li>
                    </g:each>
                    </ul>
                    <g:if test="${editable}">
                    <g:uploadForm controller="sequenceRun" action="uploadCohortImage">
                        <g:hiddenField name="type" value="sonication"></g:hiddenField>
                        <g:hiddenField name="cohortId" value="${cohort.id}"></g:hiddenField>
                        <input type="file" name="image"/>
                        <g:submitButton name="upload" value="Upload"/>
                    </g:uploadForm>
                    </g:if>
                </td>
                <td>
                    <ul>
                    <g:each in="${cohort.imageMap?.gel}" var="filepath">
                        <li>
                            <img src='${createLink(controller:"file", action: "displayImage", params:[filepath: filepath, relative: true])}' height="300"/>
                            <g:hiddenField class="filepath" name="filepath" value="${filepath}"></g:hiddenField>
                            <g:if test="${editable}"><span class="glyphicon glyphicon-remove btn remove-image"></span></g:if>
                        </li>
                    </g:each>
                    </ul>
                    <g:if test="${editable}">
                    <g:uploadForm controller="sequenceRun" action="uploadCohortImage">
                        <g:hiddenField name="type" value="gel"></g:hiddenField>
                        <g:hiddenField name="cohortId" value="${cohort.id}"></g:hiddenField>
                        <input type="file" name="image"/>
                        <g:submitButton name="upload" value="Upload"/>
                    </g:uploadForm>
                    </g:if>
                </td>
            </tr>
            </g:each>
        </tbody>
    </table>
    <p> (only png/jpg/gif files, size limit: 5MB)</p>
    <h3>Samples 
    <g:if test="${editable}"> 
        <g:link controller="sample" action="batchEdit" params="[runId: run.id]" class="edit" target="_blank">Edit</g:link>
        <button type="button" class="edit" data-toggle="modal" data-target="#add-samples-by-id">Add Sample</button>
        <g:if test="${run?.poolItem == null}">
            <g:link action="searchPool" params="['runId':run.id]" class="edit">Add Master Pool</g:link>
        </g:if>
        <g:else>
            <g:link action="removePool" params="['runId':run.id]" class="edit confirm">Remove Pool</g:link>
        </g:else>
        <div id="add-samples-by-id" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <g:form action="addSamplesById">
                        <div class="modal-header">
                            <h3 class="modal-title">Add Samples to Sequence Run #${run.runNum}</h3>
                        </div>
                        <div class="modal-body">
                            <g:hiddenField name="runId" value="${run.id}"></g:hiddenField>
                            <small class="form-group">
                                <g:render template="/sample/inputSampleIds"></g:render>
                            </small>
                        </div>
                        <div class="modal-footer">
                            <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </g:if>
    </h3>
    <table class="table table-striped">
        <thead>
            <tr>
                <g:if test="${editable}">
				
				<th title="Delete the sample and all the related data.">
					<input type="checkbox" id="selectAll" value="selectAll">
					<a id="ajaxDeleteAll">
					<span class="glyphicon glyphicon-trash"></span></a></th>
                </g:if>
                <th>Sample ID</th>
                <th>Strain</th>
                <th>Antibody</th>
                <th>Index</th>
                <th>Index ID</th>
                <th>Genome Build</th>
                <th>Project</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${run.experiments}">
                <tr>
                    <input type="hidden" name="experimentId" value="${it.id}">
                    <g:if test="${editable}">
                        <td title="Delete the sample and all the related data."><input type="checkbox" name="delete" value="${it.sample.id}" data-runId="${run.id}" data-runNum="${run.runNum}"></td> 
                    </g:if>
                    <td><g:link controller="sample" action="show" id="${it.sample.id}" params="${[runId: run.id]}">${it.sample?.id}</g:link> ${it.sample?.naturalId}</td>
                    <td>${it.sample?.cellSource?.strain}</td>
                    <td>${it.sample?.antibody}</td>
                    <td>${it.sample?.sequenceIndicesString}</td>
                    <td>${it.sample?.sequenceIndicesIdString}</td>
                    <td>${it.sample?.requestedGenomes}</td>
                    <td class="project"><span class="value">${it.cohort?.project ?: "NONE"}</span></td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="5"></td>
            </tr>
        </tbody>
    </table>
    <g:if test="${run?.experiments.size()}">
        <h3>Read and Index Positions  <g:if test="${editable}"><g:link action="editRead" params="[runId:run?.id]" class="edit">Edit</g:link></g:if></h3>
        <h5>Read Type: ${read?.readType?.name}</h5>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th></th>
                    <th>Start</th>
                    <th>End</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Read 1</td>
                    <td>${read?.rd1?.getAt(0)}</td>
                    <td>${read?.rd1?.getAt(1)}</td>
                </tr>
                <g:if test="${read?.containsKey('index')}">
                    <tr>
                        <td>Index </td>
                        <td>${read?.index?.getAt(0)}</td>
                        <td>${read?.index?.getAt(1)}</td>
                    </tr>
                </g:if>
                <g:else>
                    <tr>
                        <td>Index 1</td>
                        <td>${read?.index1?.getAt(0)}</td>
                        <td>${read?.index1?.getAt(1)}</td>
                    </tr>
                    <tr>
                        <td>Index 2</td>
                        <td>${read?.index2?.getAt(0)}</td>
                        <td>${read?.index2?.getAt(1)}</td>
                    </tr>
                </g:else>
                <g:if test="${read?.rd2}">                
                    <tr>
                        <td>Read 2</td>
                        <td>${read?.rd2?.getAt(0)}</td>
                        <td>${read?.rd2?.getAt(1)}</td>
                    </tr>
                </g:if>
            </tbody>
        </table>
    </g:if>
    <div class="row well text-center">
        <g:if test="${run.status == pegr.RunStatus.PREP}">
            <g:link action="previewRun" params="[runId: run.id]" class="btn btn-success">Submit</g:link>
        </g:if>
        <g:else>
            <span class="btn btn-default">Submitted</span>
        </g:else>
    </div>
    <script>
        $("#nav-analysis").addClass("active");
        
        $(".confirm").confirm();
        $(".confirm-remove-sample").confirm({text: "Remove the sample from this sequence run. All the analysis data will be removed, but the sample itself remains."});
        $(".confirm-delete-sample").confirm({text: "Delete the sample and all the related data."});
        $(".confirm-remove-project").confirm({text: "Remove the project from this sequence run, but the project remains."});
        $(".confirm-delete-project").confirm({text: "Delete the project."});
        
        $("select").select2();
        $(".add-project-form").hide();
        
        $(".add-project").on("click", function(){
            $(".add-project-form").show();    
        });
        
        $(".cancel-project").on("click", function(){
            $(".add-project-form").hide()
        });
        
        $(".project").on('click', ".value", function() {
            var td = $(this).parent();
            var oldValue = $(this).text();
            if (oldValue == "NONE") {
                oldValue = ""
            }
            var edit = "<span class='input'><select style='width:200px; display:none'><option selected value='" + oldValue + "'>" + oldValue + "</option></select></span>";
            appendEdit(this, edit);
            $.ajax({
                url: "/pegr/sequenceRun/fetchProjectsAjax?runId=${run.id}"
            }).done(function(result){
                td.find("select").select2({
                    data: result,
                    tags: false
                });    
            });
        });
        
        $(".project").on("click", ".cancel", function() {
            var td = $(this).parent();
            toggleTd(td);
        });
        
        $(".project").on("click", ".save", function(){
            var td = $(this).parent();
            var tr = $(this).closest("tr");
            var experimentId = tr.find("input").val();
            var projectName = td.find("select").val();
            var s = projectName;
            if (s == "") {
                s = "NONE"
            }
            $.ajax({
                type: "POST",
                url: "/pegr/sequenceRun/updateExperimentCohortAjax",
                data: {experimentId: experimentId, 
                       projectName: projectName,
                      runId: ${run?.id}},
                success: function() {
                    td.find(".value").text(s);
                    toggleTd(td);
                }
            });
        });
        
        $(".remove-image").on("click", function(){
            var parent = $(this).parent();
            var filepath = parent.find("input.filepath").val();
            var cohortId = parent.closest("tr").find(".cohort-id").val();
            $.ajax({
                url:"/pegr/sequenceRun/removeCohortImageAjax",
                type: "POST",
                data: {cohortId: cohortId, filepath: filepath},
                success: function(){
                    parent.remove();
                }});
        });
        
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
			var sampleIds = [];
			var runId = 0;
			var runNum = 0;
			$('input[name="delete"]').each(function(){
				if (this.checked) {
					sampleIds.push(this.value);
					runId = this.getAttribute("data-runId");
					runNum = this.getAttribute("data-runNum");
				}
			});
			if (confirm('All data in the selected samples(s) will be deleted. Are you sure you want to delete the following sample(s): ' + sampleIds + ' for run number: ' + runNum + '?'))
			{
				// axa677-180306: the next ajax call sends the array as a json dictionary with the run id to a controller action
		  	  	// then get the results as html
				$.ajax({
					url:"${createLink(controller: 'sequenceRun', action: 'deleteAllSampleAjax')}",
					type:"POST",
					data: {"sampleIdsList": JSON.stringify(sampleIds), "runId": runId},
					success: function(result){
						$("html").html(result);
					}
				})
			}
		});
            
     </script>
</div>
</body>
</html>