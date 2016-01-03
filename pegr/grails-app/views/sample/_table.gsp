<div class="table-responsive" >          
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Cell Source</th>
                <th>Antibody</th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <g:each var="sample" in="${it}">
            <tr>
            	<td><g:link controller="sample" action="show" id="${sample.id}">${sample.id}</g:link></td>
            	<td>${sample.cellSource}</td>            
                <td>${sample.antibody}</td>
                <td><g:link><span class="glyphicon glyphicon-remove"></span></g:link></td>
            </tr>
            </g:each>
            <tr></tr>
        </tbody>
    </table>
</div>