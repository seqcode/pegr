<html>
<head>
  <title>Create Protocol Instance Bag</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <div class="container-fluid">
        <h4>New Protocol Instance Bag</h4>
            <a href="#" onclick="window.open('${g.createLink(action:'help')}', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><u>Help</u></a>
        <form role="form" method="post" action="savePrtclInstBag" class="fields">
            <div>
                <label>Name</label>
                <g:textField name="name"></g:textField>
            </div>
            <div>
                <label>Start Time</label>
                <g:datePicker name="startTime" default="${new Date()}"/>
            </div>
            <div>
                <label>Protocol Group</label>
                <g:select name="protocolGroupId" optionKey="id" from="${protocolGroups}" noSelection="['null': '-- choose --']" 
                onChange="${remoteFunction(controller: 'protocolInstanceBag', action: 'showProtocolsInGroupAjax', update:'protocols', params: '\'id=\'+this.value')}"/>
            </div>
            <g:submitButton class="btn btn-primary" name="Save"/>
            <g:link action="index" class="btn btn-default">Cancel</g:link>
        </form>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div id="protocols">
        </div>
    </div>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>