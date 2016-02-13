<html>
<head>
  <title>Samples</title> 
  <meta name="layout" content="main"/>
</head>
<body>
<div>
    
</div>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <g:sortableColumn property="id" defaultOrder="desc" title="ID"></g:sortableColumn>
                <g:sortableColumn property="cellSource" title="Strain"></g:sortableColumn>
                <g:sortableColumn property="antibody" title="Antibody"></g:sortableColumn>
                <g:sortableColumn property="target" title="Target"></g:sortableColumn>
                <g:sortableColumn property="prtclInstSummary" title="Assay"></g:sortableColumn>
                <g:sortableColumn property="date" defaultOrder="desc" title="Date"></g:sortableColumn>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td><g:link action="show" id="${sample.id}">${sample.id}</g:link></td>    
                    <td>${sample.cellSource} </td>
                    <td>${sample.antibody}</td>
                    <td>${sample.target}</td>
                    <td>${sample.prtclInstSummary}</td>
                    <td><g:formatDate format="yyyy-MM-dd" date="${sample.date}"/></td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="6"></td>
            </tr>
        </tbody>
      </table>
</div>

<div class="pagination">
    <g:paginate next="Next" prev="Prev" controller="sample" action="index" max="15" total="${sampleCount ?: 0}" />
</div>

<script>
$("#nav-metadata").addClass("active");
</script>
</body>
</html>
