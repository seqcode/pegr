<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin"/>
</head>
<body>
    <g:if test="${message}"><p class="error">${message}</p></g:if>
    <h3>Service Report on Sample Counts</h3>
    <g:form action="serviceReport">
        <label>Sequence Runs: </label>
        From
        <input name="fromId" value="${fromId}" placeholder="Starting Run ID">
        To
        <input name="toId" value="${toId}" placeholder="Ending Run ID">
        <input type="submit" name="submit" value="Query" class="btn btn-primary">
    </g:form>
	<div>
        <g:if test="${cohort_count && cohort_count.size()}">
        <h4>Total samples: <span id="total-samples">${total_sample_count[0].total_sample_count}</span></h4>	
        <table class="table table-bordered" id="assay-counts">
            <thead>
                <th>Assay</th>                
                <th>Sample count</th>
            </thead>
            <tbody>
                <g:each in="${assay_sample_count}">
                <tr>
                    <td>${it.assay}</td>
                    <td>${it.assay_sample_count}</td>
                </tr>
                </g:each> 
            </tbody>
        </table>
        <h4>Average samples per sequence run and project: <g:formatNumber number="${avg_samples_per_run[0].average_sample_per_run}" type="number" maxFractionDigits="1" /></h4>
        <h4>Samples per sequence run:</h4>
        <table class="table table-bordered">
            <thead>
                <th>Run ID</th>                
                <th>Run Name</th>
                <th>Date</th>
                <th>Samples in Run</th>
                <th>Project</th>
                <th>Samples in Run and Project</th>
            </thead>
            <tbody>
                <g:each in="${cohort_count}">
                <tr>
                    <td><g:link controller="sequenceRun" action="show" id="${it.run_id}">${it.run_id}</g:link></td>
                    <td>${it.run_name}</td>
                    <td>${it.date.toString().split(" ")[0]}</td>
                    <td>${it.samples_in_run}</td>
                    <td><g:link controller="project" action="show" id="${it.project_id}">${it.project_name}</g:link></td>
                    <td>${it.samples_in_project}</td>
                </tr>
                </g:each> 
            </tbody>
        </table>
        </g:if>
    </div>
    <script type="application/javascript">
        let sum = 0;
        $('#assay-counts tbody tr').each(function() {
          let val = parseInt($(this).find('td:eq(1)').text());
          if (!isNaN(val)) {
            sum += val;
          }
        });
        
        let totalSamples = parseInt($('#total-samples').text());
        
        if (!isNaN(totalSamples) && !isNaN(sum)) {
            let unknownSamples = totalSamples - sum;
            
            if (unknownSamples > 0) {
                // Append new row with the sum
                $('#assay-counts tbody').append(
                  '<tr><td>Unknown</td><td>' + unknownSamples + '</td></tr>'
                );
            }
        }
    </script>
</body>
</html>