<div class="table-responsive">
    <table class="table">
        <thead>
            <tr>
                <th></th>
                <g:sortableColumn property="id" defaultOrder="desc" title="ID" params="${searchParams}"></g:sortableColumn>
                <g:sortableColumn property="cellSource" title="Strain" params="${searchParams}"></g:sortableColumn>
                <g:sortableColumn property="antibody" title="Antibody" params="${searchParams}"></g:sortableColumn>
                <g:sortableColumn property="target" title="Target" params="${searchParams}"></g:sortableColumn>
                <g:sortableColumn property="prtclInstSummary" title="Assay" params="${searchParams}"></g:sortableColumn>
                <g:sortableColumn property="date" defaultOrder="desc" title="Date" params="${searchParams}"></g:sortableColumn>
            </tr>
        </thead>
        <tbody>
            <g:each in="${sampleList}" var="sample">
                <tr>
                    <td><g:if test="${checkbox}"><input type="checkbox" name="checkedSample" class="checkbox" value="${sample.id}" onchange="toggleChecked(this)" <g:if test="${sample.id in session.checkedSample}">checked</g:if>></g:if></td>
                    <td><g:link controller="sample" action="show" id="${sample.id}">${sample.id}</g:link> ${sample.naturalId}</td>    
                    <td>${sample.cellSource} </td>
                    <td>${sample.antibody}</td>
                    <td>${sample.target}</td>
                    <td>${sample.prtclInstSummary}</td>
                    <td><g:formatDate format="yyyy-MM-dd" date="${sample.date}"/></td>
                </tr>
            </g:each>              
        </tbody>
      </table>
</div>
