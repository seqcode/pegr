<html>
<head>
    <meta name="layout" content="sequencing"/>
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
    <h2>Sequence Run #${run.id} (Run Name: <span id="run-name-show">${run.runName}</span>
        <g:if test="${editable}"> 
            <small>
                <a id="run-name-edit-symbol" href="#" class="glyphicon glyphicon-pencil"></a>
                <span id="run-name-edit" style="display:none">
                    <input id="run-name-input" name="runName" value="${run.runName}" style="width:10em">
                    <button id="run-name-save" class="btn btn-primary">Save</button>
                    <button id="run-name-cancel" class="btn btn-default">Cancel</button>    
                </span>
            </small>
        </g:if>)
        <small>
            <span id="run-status-show" class="label label-default">${run.status}</span>
            <sec:ifAnyGranted roles="ROLE_ADMIN">
            <span id="run-status-select" style="display:none">
                <g:select name="runStatus" from="${pegr.RunStatus}" value="${run.status}" style="width:10em"></g:select>
                <button id="run-status-save" class="btn btn-primary">Save</button>
                <button id="run-status-cancel" class="btn btn-default">Cancel</button>
            </span>
            </sec:ifAnyGranted>
        </small>      
        <sec:ifAnyGranted roles="ROLE_ADMIN">
          <g:link action="delete" params="[runId:run.id]" onclick="return confirm('Do you want to delete the sequence run?')" class="btn btn-warning pull-right">Delete Sequence Run</g:link>
        </sec:ifAnyGranted>
        <g:link controller="report" action="runStatus" params="[runId: run.id]" class="btn btn-primary pull-right">Sequence Run Analysis</g:link>
    </h2>
    <g:link action="downloadQueueFile" params="[runId:run.id]" class="btn btn-primary pull-right">Download Queue File</g:link>
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
                    <li>Sequencer Repository: /path/to/sequencer/repository/</li>
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
                            <h3 class="modal-title">Add Samples to Sequence Run #${run.id}</h3>
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
        <g:form action="deleteSamples">
        <input type="hidden" name="runId" value="${run.id}">
        <thead>
            <tr>
                <g:if test="${editable}">				
				<th>
					<input type="checkbox" id="selectAll" value="selectAll">
                    <button title="Remove the sample from the sequence run." type="submit" name="actionType" value="remove" onClick="return confirmRemove()"><span class="glyphicon glyphicon-remove"></span></button>
                    <button title="Delete the sample and all the related data." type="submit" name="actionType" value="delete" onClick="return confirmDelete()"><span class="glyphicon glyphicon-trash"></span></button>
                </th>
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
                    <input class="experiment-id" value="${it.id}" type="hidden">
                    <g:if test="${editable}">
                        <td><input type="checkbox" name="sampleId" value="${it.sample.id}"></td> 
                    </g:if>
                    <td><g:link controller="sample" action="show" id="${it.sample.id}">${it.sample?.id}</g:link> ${it.sample?.naturalId}</td>
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
        </g:form>
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
        $("#nav-sequencing").addClass("active");
        
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
            // show the project edit widget when the status is not 'COMPLETED'.
            var status = $("#run-status-show").text();
            if (status != "COMPLETED") {
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
            }
        });
        
        $(".project").on("click", ".cancel", function() {
            var td = $(this).parent();
            toggleTd(td);
        });
        
        $(".project").on("click", ".save", function(){
            var td = $(this).parent();
            var tr = $(this).closest("tr");
            var experimentId = tr.find(".experiment-id").val();
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
            if(confirm("Delete the image?")) {
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
            }
        });
        
        <sec:ifAnyGranted roles="ROLE_ADMIN">
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
        </sec:ifAnyGranted>
        
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
        
        // edit sequence run name
        $("#run-name-edit-symbol").click(function(){
            $("#run-name-show").hide();
            $("#run-name-edit-symbol").hide();
            $("#run-name-edit").show();
        });

        $("#run-name-save").click(function(){
            var name = $("#run-name-input").val();
            $.ajax({ url: "/pegr/sequenceRun/updateRunNameAjax?runId=${run.id}&name=" + name,
                success: function(result) {
                    if (result.error) {
                        alert(result.error)
                    } else {
                        $("#run-name-show").text(result.data);
                        $("#run-name-input").val(result.data);
                        $("#run-name-show").show();
                        $("#run-name-edit-symbol").show();
                        $("#run-name-edit").hide();
                    }
                }                
            });
        });

        $("#run-name-cancel").click(function(){
            $("#run-name-show").show();
            $("#run-name-edit-symbol").show();
            $("#run-name-edit").hide();
        });
            
        function confirmRemove(){
            var agree=confirm("Are you sure you wish to remove the sample(s)?");
            if (agree)
             return true ;
            else
             return false ;
        }
        
        function confirmDelete(){
            var agree=confirm("Are you sure you wish to delete the sample(s)?");
            if (agree)
             return true ;
            else
             return false ;
        }
     </script>
</div>
</body>
</html>