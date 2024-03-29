<html>
<head>
  <title>Create Protocol Instance Bag</title> 
  <meta name="layout" content="main"/>
</head>
<body>
    <div class="container-fluid">
        <h3>New Experiment Record</h3>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <a href="#" onclick="window.open('/pegr/help#bag', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><u>Help</u></a>
        <form role="form" method="post" action="savePrtclInstBag" class="fields">
            <div>
                <label>Name</label>
                <input name="bagName" value="${name}" required>
            </div>
            <div>
                <label>Start Time</label>
                <g:datePicker name="startTime" default="${new Date()}"/>
            </div>
            <div>
                <label>Projects</label>
                <g:select name="projects" id="projects" optionKey="id" from="${pegr.Project.list()}" multiple="multiple" style="min-width:20em;"></g:select>
            </div>
            <h4>Protocols</h4>
            <div class="radio">
                <label><input type="radio" name="protocolInput" value="defined" onclick="showDefinedProtocol()" checked> Select pre-defined protocol group</label>
            </div>
            <div class="radio">
                <label><input type="radio" name="protocolInput" value="custom" onclick="showNewProtocol()"> Customize your own protocols</label>
            </div>
            <div id="defined-protocol-group">
                <label>Protocol Group</label>
                <g:select id="select-protocol-group" name="protocolGroupId" optionKey="id" from="${protocolGroups}" noSelection="['null': '-- choose --']" />
                <div id="protocols"></div>
            </div>
            <div id="custom-protocols" class="fields well">
                <g:render template="/protocolGroupAdmin/form"></g:render>
            </div>
            <g:submitButton class="btn btn-primary" name="Save"/>
            <g:link action="index" class="btn btn-default">Cancel</g:link>
        </form>
        <br>
    </div>
    <script>
        $("#nav-experiments").addClass("active");
        $('#custom-protocols').hide();
        $("#projects").select2();
        
        // when a protocol group is selected, show the protocols in that protocol group
        $("#select-protocol-group").change(function() {
            $.ajax({
                url: "showProtocolsInGroupAjax/" + this.value,
            }).done(function(html){
                $("#protocols").html(html);
            });
        });
        
        function showDefinedProtocol() {
            $('#defined-protocol-group').show();
            $('#custom-protocols').hide();
        }
        
        function showNewProtocol() {
            $('#defined-protocol-group').hide();
            $('#custom-protocols').show();
        }
     </script>
</body>
</html>