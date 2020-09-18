<html>
<head>
    <title>Protocol Instance Bag</title> 
    <meta name="layout" content="main"/>
    <style>
        th {
            background-image: none;
            background-color: #fcf8e3;
        }   
        table {
            border-style: solid;
            border-width: 2px;
            border-color: #f8edba;
        }
        
        th a.edit {
            color: white;
        }
    </style>
</head>
<body>                
    <div class="container-fluid">
        <a href="#" onclick="window.open('/pegr/help#bag', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><u>Help</u></a>
        <ul class="nav nav-tabs">
            <li><g:link action="index"><span class="glyphicon glyphicon-home"></span> List</g:link></li>
            <g:if test="${completed}">
                <sec:ifAllGranted roles="ROLE_ADMIN"><li><g:link action="reopenBag" params="[bagId: bag?.id]" class="confirm">Reopen</g:link></li></sec:ifAllGranted>
            </g:if>
            <g:else>
                <li><g:link action="edit" params="[bagId: bag?.id]">Edit</g:link></li>
                <li><g:link action="deleteBag" params="[bagId: bag?.id]" class="confirm-deleteBag">Delete</g:link></li>                
            </g:else>
        </ul>
        <h4>
            <span id="bagName">${bag?.name}</span>
            <small>
                <span class="label label-default">${bag?.status}</span> 
            </small> 
        </h4>
        <p>Start Time: <g:formatDate format="yyyy-MM-dd" date="${bag?.startTime}"/>
        <g:if test="${bag?.endTime}">, End Time: <g:formatDate format="yyyy-MM-dd" date="${bag?.endTime}"/></g:if></p>
        <p>Project(s):<g:render template="/project/inlineList" model="[projects: bag?.projects]"></g:render></p>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title"><a data-toggle="collapse" href="#protocols">Protocols</a></h4>
                    </div>
                    <div id="protocols" class="panel-collapse collapse in">
                        <g:render template="/protocolInstanceBag/protocolInstances" model="['protocolInstances':protocolInstances, 'completedCount':count]"></g:render>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Sample <span class="badge">${tracedSamples.size()}</span> <g:link action="printTracedSamples" params="[bagId:bag.id]"><span class="glyphicon glyphicon-print"></span></g:link> <g:link controller="sample" action="batchEdit" params="[bagId:bag?.id]" class="edit" target="_blank">Edit</g:link> </th>
                            <th>Project</th>
                            <th>
                                <g:if test="${notStarted}">
                                    Add <g:link action="searchItemForBag" params="[bagId: bag?.id]"><span class="glyphicon glyphicon-qrcode"></span> </g:link> <g:link action="searchTracedSampleWorksheet" params="[bagId: bag?.id]"><span class="glyphicon glyphicon-list-alt"></span></g:link>
                                </g:if>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${tracedSamples}">
                            <tr>
                                <td class="col-xs-3"><g:link controller='item' action='show' id="${it.id}" target="_blank">${it.name}</g:link></td>
                                <td class="col-xs-6">
                                    <span class="item-project-show">${it.project?.name ?: "None"}</span>
                                    <input type="hidden" name="itemId" value="${it.id}">
                                    <g:select name="project" from="${bag?.projects}" optionKey="id" value="${it.project?.id}" class="item-project-select"></g:select>
                                    <br/>
                                    <button class="item-project-save item-project-select edit">Save</button>
                                    <button class="item-project-cancel item-project-select edit">Cancel</button>
                                </td>
                                <td class="col-xs-3">
                                    <g:if test="${notStarted}">
                                        <g:link class="pull-right confirm" action="removeSampleFromBag" params="[itemId: it.id, bagId: bag?.id]"><span class="glyphicon glyphicon-remove"></span></g:link>
                                    </g:if>
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
        </div>
        <g:if test="${toBeCompleted}">
            <div class="row well text-center">
                <button class="btn btn-success" data-toggle="modal" data-target="#confirm-complete">Complete <span class="glyphicon glyphicon-ok"></span> </button>
            </div>
            <div id="confirm-complete" class="modal fade" role="dialog">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-body">
                    <p>No changes can be made after completion!</p>
                  </div>
                  <div class="modal-footer">
                      <g:link action="completeBag" params="[bagId: bag?.id]" class="btn btn-primary">Ok</g:link>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                  </div>
                </div>
              </div>
            </div>
        </g:if>
    </div>
    <script>
        $("#nav-experiments").addClass("active");
        $(".confirm").confirm();
        $(".confirm-deleteBag").confirm({text: "All protocol instances in this bag will be deleted! Are you sure you want to delete this bag?"});
        $(".confirm-start").confirm({text: "Are you the one to perform this protocol?"});
        
        function closeModal() {
            $(".modal").modal('hide');
        }
        
        $(".confirm-start-first").confirm({text: "Have you added all samples to the bag? Are you the one to perform this protocol?"});
        
        function closeModal() {
            $(".modal").modal('hide');
        }
        
        $(".item-project-select").hide();
        
        $(".item-project-show").click(function(){
            var parent = $(this).parent();
            $(parent).find(".item-project-show").hide();
            $(parent).find(".item-project-select").show();
        });

        $(".item-project-cancel").click(function(){
            var parent = $(this).parent();
            $(parent).find(".item-project-show").show();
            $(parent).find(".item-project-select").hide();
        });
        
        $(".item-project-save").click(function(){
            var parent = $(this).parent();
            var projectId = $(parent).find("option:selected").val();
            var text = $(parent).find("option:selected").text();
            var itemId = $(parent).find("input").val();
            $.ajax({ url: "/pegr/item/saveProjectAjax?itemId=" + itemId + "&projectId=" + projectId,
                success: function() {
                    $(parent).find(".item-project-show").text(text);
                    $(parent).find(".item-project-show").show();
                    $(parent).find(".item-project-select").hide();
                }                
            });
        }); 
     </script>
</body>
</html>