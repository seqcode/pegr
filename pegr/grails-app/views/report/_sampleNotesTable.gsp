<div class="table-responsive">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Notes</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td><g:link controller="sample" action="show" id="${sample.id}">${sample.id}</g:link> ${sample.naturalId}</td>    
                    <td>${sample.note}</td>
                </tr>
            </g:each>              
        </tbody>
      </table>
</div>
