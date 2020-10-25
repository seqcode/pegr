<html>
<head>
  <title>My Targets</title>
  <meta name="layout" content="admin"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div>
        <g:link action="showChecked" class="btn btn-default">Merge Checked Target (<span id="checked-count">${checkedCount}</span>)</g:link>
        <g:link action="cancelMerge" class="btn btn-default">Cancel</g:link>
    </div>
    <table class="table-bordered">
        <thead>
            <tr>
                <th></th>
                <g:sortableColumn property="name" title="Name" params="${params}"></g:sortableColumn>
                <g:sortableColumn property="cTermTag" title="C-Term Tag" params="${params}"></g:sortableColumn>
                <g:sortableColumn property="nTermTag" title="N-Term Tag" params="${params}"></g:sortableColumn>
                <g:sortableColumn property="targetType" title="Type" params="${params}"></g:sortableColumn>
            </tr>
        </thead>
        <tbody>
            <g:each in="${targets}" var="target">
            <tr>
                <td><input type="checkbox" name="checkedTarget" class="checkbox" value="${target.id}" onchange="toggleChecked(this)" ><g:if test="${target.id in session.checkedTarget}">checked</g:if></td>
                <td class="col-sm-3"><g:link controller="targetAdmin" action="show" id="${target.id}">${target.name}</g:link> </td>
                <td class="col-sm-3">${target.cTermTag}</td>
                <td class="col-sm-3">${target.nTermTag}</td>
                <td class="col-sm-3">${target.targetType}</td>
            </tr>
            </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="targetAdmin" action="mergeSearch" max="15" total="${totalCount ?: 0}" params="${params}"/>
    </div>     
    <script>
        function toggleChecked(element) {
            if (element.checked) {
                $.ajax({url:"/pegr/targetAdmin/addCheckedTargetAjax?id="+element.value, success: function(checkedCount) {
                    $("#checked-count").text(checkedCount);
                }});
            } else {
                $.ajax({url:"/pegr/targetAdmin/removeCheckedTargetAjax?id="+element.value, success: function(checkedCount) {
                    $("#checked-count").text(checkedCount);
                    $('#selectAll').prop('checked', false);
                }});
            }
        }
    </script>
</body>
</html>
