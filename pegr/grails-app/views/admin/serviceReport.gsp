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
        <input name="fromId" value="${fromId}">
        To
        <input name="toId" value="${toId}">
        <label>Source (optional)</label>
        <input name="source" value="${source}">
        <input type="submit" name="submit" value="Query" class="btn btn-primary">
    </g:form>
	<div>
        <g:if test="${samples_per_run && samples_per_run.size()}">
        <h4>Total samples: ${sample_count[0].total_sample_count}</h4>		
        <h4>Average samples per sequence run: ${avg_samples_per_run[0].average_sample_per_run}</h4>
        <h4>Samples per sequence run:</h4>
        <table class="table table-bordered">
            <thead>
                <th>Sequence Run ID</th>
                <th>Sample Count</th>
            </thead>
            <tbody>
                <g:each in="${samples_per_run}">
                <tr>
                    <td>${it.id}</td>
                    <td>${it.sample_count}</td>
                </tr>
                </g:each> 
            </tbody>
        </table>
        </g:if>
    </div>
</body>
</html>