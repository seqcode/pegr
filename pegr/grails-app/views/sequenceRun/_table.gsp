<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <g:sortableColumn property="runNum" defaultOrder="desc" title="Run #"></g:sortableColumn>
                <g:sortableColumn property="platform" title="Platform"></g:sortableColumn>
                <g:sortableColumn property="date" defaultOrder="desc" title="Date"></g:sortableColumn>
                <g:sortableColumn property="user" title="User"></g:sortableColumn>                
            </tr>
        </thead>
        <tbody>
            <g:each in="${runs}" var="run">
                <tr>
                    <td><g:link controller="sequenceRun" action="show" id="${run.id}">${run.runNum}</g:link></td>                        
                    <td>${run.platform}</td>
                    <td><g:formatDate format="yyyy-MM-dd" date="${run.date}"/></td>
                    <td>${run.user}</td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="4"></td>
            </tr>
        </tbody>
      </table>
</div>
