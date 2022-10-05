<html>
    <head>
        <title>Replicate Set</title> 
        <meta name="layout" content="main"/>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div>
            <h3>${type} Replicates of Sample ${sampleId}</h3> 
            <g:form action="save" role="form" class="fields">
                <input type="hidden" name="sampleId" value="${sampleId}">
                <input type="hidden" name="type" value="${type}">
                <div>
                    <label for="sampleIds">Sample IDs</label>
                    <g:textField id="sampleIds" name="sampleIds" value="${replicates}" size= "100" class="form-control"></g:textField>
                    <p>e.g. 1-10, 11, 15</p>
                </div>
                <g:submitButton class="btn btn-primary" name="submit" value="Save"></g:submitButton>
                <g:link controller="sample" action="edit" params="[sampleId:sampleId]" class="btn btn-default" data-dismiss="modal">Cancel</g:link>
            </g:form>
        </div>
    </body>
</html>