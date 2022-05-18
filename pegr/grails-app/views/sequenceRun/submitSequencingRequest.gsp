<html>
<head>
  <meta name="layout" content="sequencing"/>
</head>
<body>
  <h3>Submit Sequencing Request</h3>
  <div class="container-fluid">
    <g:if test="${request.message}">
         <div class="message" role="status">
            ${request.message}
        </div>
    </g:if>
    <g:form action="submitSequencingRequest">
        <div>
            <label>Destination URL</label>
            <input type="url" name="url" style="width:50em">
        </div>
        <div class="form-group">
            <label>JSON payload</label>
            <textarea name="json" style="width:50em;height:10em"></textarea>
        </div>
        <div class="form-group">
            <g:submitButton name="submit" value="Submit" class="btn btn-primary"/>
            <g:link action="index" class="btn btn-default">Cancel</g:link>
        </div>
      </g:form>
  </div>
</body>
</html>
    