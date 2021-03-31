<html>
<head>
  <title>PEGR - Experiment Records</title>
</head>
<body>
    <h4><g:link action="list">Experiment Records</g:link> <g:link action='create' class="edit">New</g:link>    <a href="#" onclick="window.open('/pegr/help#bag', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><small><u>Help</u></small></a></h4>
    <div id="message" >
        <g:if test="${flash.message}">
             <div class="message" role="status">
                ${flash.message}
            </div>
        </g:if>
    </div>
    <div class="row">
        <div class="col-sm-10">
            <table class="table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Projects</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${bags}">
                        <tr>
                            <td><g:link action="showBag" id="${it.id}">${it.name}</g:link></td>
                            <td><g:formatDate format="yyyy-MM-dd" date="${it.startTime}"/></td>
                            <td><g:formatDate format="yyyy-MM-dd" date="${it.endTime}"/></td>
                            <td><g:render template="/project/inlineList" model="[projects: it.projects]"></g:render></td>
                            <td>${it.status}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
        <div class="col-sm-2 well">
            <g:form action="list" method="post">
                <div class="form-group">
                    <label>Name includes</label>
                    <input name="nameSub" value="${nameSub}" class="form-control">
                </div>
                <div class="form-group">
                    <label>Status</label>
                    <g:select name="status" value="${status}" from="${pegr.ProtocolStatus.values()}" keys="${pegr.ProtocolStatus.values()*.name()}" noSelection="${['null':'Select...']}" class="form-control"></g:select>
                </div>
                <div class="form-group">
                    <label>Project</label>
                    <g:select name="projectId" value="${projectId}" optionKey="id" from="${pegr.Project.list(sort: 'name')}" noSelection="${['null':'Select...']}" class="form-control"></g:select>
                </div>
                <g:submitButton name="submit" value="Search" class="btn btn-primary"></g:submitButton>
            </g:form>
        </div>
    </div>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" total="${totalCount}" params="${params}" controller="protocolInstanceBag" max="25"/>
    </div>
    <script>
        $("select").select2({width: '100%'});
    </script>
</body>
</html>
