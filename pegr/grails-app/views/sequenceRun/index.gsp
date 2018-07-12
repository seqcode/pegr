<html>
<head>
    <title>Sequencing Records</title>
    <g:set var="defaultGalaxy" value="${defaultGalaxy}" scope="request"/>
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
        <g:form controller="sequenceRun" action="index" class="pull-right">
            <input name="str">
            <g:submitButton class="edit" name="submit" value="Search" style="margin-right: 10px"></g:submitButton>
            <br>Search run number, fcId, directory name, username, or platform
        </g:form>
    </div>
    <div class="row">
        <div class="col-sm-10">
            <g:render template="/sequenceRun/table"></g:render>
        </div>
        <div class="col-sm-2">
            <div class="well">
                <div class="dropdown">
                  <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Filter by Status
                  <span class="caret"></span></button>
                  <ul class="dropdown-menu">
                    <li><g:link >ALL</g:link></li>
                    <li title="The sample has not been submitted to bioinformatics pipeline."><g:link params="[status:'PREP']">PREP</g:link></li>
                    <li title="The sample has been submitted and is waiting in the queue to be processed by the bioinformatics pipeline."><g:link params="[status:'QUEUE']">QUEUE</g:link></li>
                    <li title="The sample is being processed by the bioinformatics pipeline."><g:link params="[status:'ANALYZING']">ANALYZING</g:link></li>
                    <li title="The Sample has been processed by the bioinformatics pipeline and review by lab bioinformatician."><g:link params="[status:'COMPLETED']">COMPLETED</g:link></li>
                    <li title="The Sample has been processed by the bioinformatics pipeline and review by lab bioinformatician and considered failed."><g:link params="[status:'FAILED']">FAILED</g:link></li>
                  </ul>
                </div>
                <h4>Sample Submission</h4>
                <ul>
                    <li><g:link action="upload">Upload</g:link></li>
                    <li><g:link action="create">Create Wizard</g:link></li>
                </ul>
                <sec:ifAnyGranted roles="ROLE_ADMIN">

                        <h4>Manage</h4>
                        <ul>
                            <li><g:link controller="report" action="manage">Sequence QC</g:link></li>
                            <li><a href="#" onclick="window.open('${g.createLink(controller:'sequenceRun',action:'editQueue')}', 'Edit Queue for Sample Submission', 'width=600,height=400' )" >Edit Queue</a></li>
                        </ul>
                    
                </sec:ifAnyGranted>
            </div>

        </div>
    </div>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" max="${params.max ?: 50}" offset="${params.offset ?: 0}" total="${runs.totalCount ?: 0}" />
    </div>
    <script>
        $(".nav-runs").addClass("active");
     </script>
</div>
</body>
</html>
