<html>
<head>
  <meta name="layout" content="sequencing"/>
</head>
<body>
  <div class="container-fluid">
    <div id="message" >
        <g:if test="${request.message}">
             <div class="message" role="status">
                ${request.message}
            </div>
        </g:if>
    </div>
    <g:form action="submitSequencingRequest">
        <div class="form-group">
            <label>Command</label>
            <g:textField name="command" style="width:50em"></g:textField>
        </div>
        <div class="form-group">
            <g:submitButton name="submit" value="Submit" class="btn btn-primary"/>
            <g:link action="index" class="btn btn-default">Cancel</g:link>
        </div>
    </g:form>
  </div>
</body>