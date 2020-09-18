<html>
<head>
  <title>PEGR - Experiment Records</title>
</head>
<body>
    <h4><g:link action="list">Experiment Records</g:link> <g:link action='create' class="edit">New</g:link>    <a href="#" onclick="window.open('/pegr/help#bag', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><small><u>Help</u></small></a></h4>
    <div class="row">
        <g:form controller="protocolInstanceBag" action="search" class="pull-right" style="padding-bottom:5px">
            <input name="str">
            <g:submitButton class="edit" name="submit" value="Search"></g:submitButton>
	    <br>Search experiment name
        </g:form>
    </div>
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
            <div class="dropdown">
              <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Filter by Status
              <span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><g:link >ALL</g:link></li>
                <li><g:link params="[status:'INACTIVE']">INACTIVE</g:link></li>
                <li><g:link params="[status:'PROCESSING']">PROCESSING</g:link></li>
                <li><g:link params="[status:'COMPLETED']">COMPLETED</g:link></li>
              </ul>
            </div>
            <h5>Project</h5>
            <form action="list">
                <g:select name="projectId" optionKey="id" from="${pegr.Project.list(sort: 'name')}" noSelection="${['null':'Select...']}"></g:select>
                <g:submitButton name="submit" value="Search" class="btn btn-default"></g:submitButton>
            </form>
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
