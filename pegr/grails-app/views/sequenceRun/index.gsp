<html>
<head>
    <title>Sequencing Records</title>
    <meta name="layout" content="analysis"/>
</head>
<body>
<div class="container-fluid">
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:if test="${flash.messageList}">
        <g:each in="${flash.messageList}">
            <div class="message" role="status">
                ${it}
             </div>
         </g:each>   
    </g:if> 
    <div class="row">
        <g:link action="create" class="edit pull-right">New</g:link>
        <g:link action="upload" class="edit pull-right">Upload</g:link>
        <g:form controller="sequenceRun" action="index" class="pull-right">
            <input name="str">
            <g:submitButton class="edit" name="submit" value="Search" style="margin-right: 10px"></g:submitButton>
        </g:form>
    </div>
    <div class="row">
        <div class="col-sm-10">
            <g:render template="/sequenceRun/table"></g:render>
        </div>
        <div class="col-sm-2 well">
            <g:link >All</g:link>
            <h4>Status</h4>
            <ul>
                <li><g:link params="[status:'PREP']">PREP</g:link></li>
                <li><g:link params="[status:'QUEUE']">QUEUE</g:link></li>
                <li><g:link params="[status:'ANALYZING']">ANALYZING</g:link></li>
                <li><g:link params="[status:'COMPLETED']">COMPLETED</g:link></li>                
            </ul>
        </div>
    </div>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" params="${params}" total="${runs.totalCount ?: 0}" />
    </div>
    <script>
        $(".nav-runs").addClass("active");
     </script>
</div>
</body>
</html>