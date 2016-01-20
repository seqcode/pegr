<div class="table-responsive" >          
    <table class="table table-striped">
        <thead>
            <tr>
                <g:sortableColumn property="Id" title="ID" />
                <g:sortableColumn property="CellCulture" title="Cell Culture" />
                <g:sortableColumn property="Antibody" title="Antibody" />
                <g:sortableColumn property="ProtocolGroup" title="Protocols" />
                <td></td>
            </tr>
        </thead>
        <tbody>
            <g:each var="sample" in="${it}">
            <tr>
            	<td><g:link controller="sample" action="show" id="${sample.id}">${sample.id}</g:link></td>
            	<td>${sample.cellCulture}</td>            
                <td>${sample.antibody}</td>
                <td>${sample.protocolGroup}</td>
                <td><g:link><span class="glyphicon glyphicon-remove"></span></g:link></td>
            </tr>
            </g:each>
            <tr></tr>
        </tbody>
    </table>
</div>