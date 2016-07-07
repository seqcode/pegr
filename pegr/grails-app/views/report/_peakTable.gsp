<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Target</th>
                <th>Peak Calling (parameters)</th>
                <th>All Peaks</th>
                <th>Singletons</th>
                <th>Peak-pairs (parameters)</th>
                <th># of Peak-pairs</th>
                <th>Non-paired peaks</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td><g:link controller="sample" action="show" id="${sample.id}">${sample.id}</g:link></td>    
                    <td>${sample.target}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="9"></td>
            </tr>
        </tbody>
      </table>
</div>
