<html>
<head>
  <title>PEGR - Work Records</title> 
</head>
<body>
    <h4>Work Records <g:link action='create' class="edit">New</g:link>    <a href="#" onclick="window.open('/pegr/help#bag', 'Help: Sample Submission', 'width=600,height=400' )" class="pull-right"><small><u>Help</u></small></a></h4>
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
                            <td>
                                <g:each in="${it.projects}" var="project" status="n">
                                    <g:if test="${n>0}">, 
                                    </g:if>
                                    <g:link controller="project" action="show" id="${project.id}">${project.name}</g:link>                            
                                </g:each>
                            </td>
                            <td>${it.status}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
        <div class="col-sm-2 well">
            <h4>Filter</h4>
            <g:link>All</g:link>
            <h5>Status</h5>
            <ul>
                <li><g:link params="[status:'INACTIVE']">INACTIVE</g:link></li>
                <li><g:link params="[status:'PROCESSING']">PROCESSING</g:link></li>
                <li><g:link params="[status:'COMPLETED']">COMPLETED</g:link></li>
            </ul>
            <h5>Project</h5>
            <g:select name="project" optionKey="id" from="${pegr.Project.list()}"></g:select>
        </div>
    </div>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" total="${bags.totalCount ?: 0}" />
    </div>
    <script>
        $("select").select2({width: '100%'});
    </script>
</body>
</html>