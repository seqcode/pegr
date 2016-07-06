<html>
    <head>
        <title>Replicate Set</title> 
        <meta name="layout" content="main"/>
    </head>
    <body>
        <g:if test="${set?.project}">
            <h4><g:link controller="project" action="show" id="${set?.project?.id}"><span class="glyphicon glyphicon-home"></span> Project: ${set?.project}</g:link></h4>
        </g:if>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div>
            <h3>${set.type} Replicate Set ${set.id}</h3>
            <g:if test="${authorized}">
                <div>            
                    <g:link action="delete" id="${set.id}" class="btn btn-primary confirm"><span class="glyphicon glyphicon-trash"></span> Delete Set</g:link>
                    <a href="#" data-toggle="modal" data-target="#addSamples" class="btn btn-primary">+ Add Samples</a>
                </div>
                <div id="addSamples" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4>Add Samples to ${set.type} Replicate Set ${set.id}</h4>
                            </div>
                            <div class="modal-body">
                                <g:form action="addSamples" role="form" class="fields">
                                    <g:hiddenField name="setId" value="${set.id}"></g:hiddenField>
                                    <g:render template="/sample/inputSampleIds"></g:render>
                                    <g:submitButton class="btn btn-primary" name="submit" value="Add"></g:submitButton>
                                    <a href="#" class="btn btn-default" data-dismiss="modal">Cancel</a>
                                </g:form>
                            </div>
                        </div>
                    </div>
                </div>
            </g:if>
            <g:render template="/replicate/sampleTable" model="['sampleList':samples]" />
        </div>
        <script>
            $(".confirm").confirm();
        </script>
    </body>
</html>