<html>
<head>
    <title>Project</title>
    <meta name="layout" content="main"/>
    <asset:javascript src="cookie.js"/>
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <br>
        <div>
            <g:link action="showChecked" class="btn btn-default">Merge Checked Projects (<span id="checked-count">${checkedCount}</span>)</g:link>
            <g:link action="cancelMerge" class="btn btn-default">Cancel</g:link>
        </div>
        <g:render template="overview" model="['projects':projects, 'checkbox':true]"></g:render>
        <div class="pagination">
            <g:paginate next="Next" prev="Prev" controller="project" action="search" max="25" total="${totalCount ?: 0}" params="${params}" />
        </div>
    </div>
    <script>
        $("#nav-projects").addClass("active");

        function toggleChecked(element) {
            if (element.checked) {
                $.ajax({url:"/pegr/project/addCheckedProjectAjax?id="+element.value, success: function(checkedCount) {
                    $("#checked-count").text(checkedCount);
                }});
            } else {
                $.ajax({url:"/pegr/project/removeCheckedProjectAjax?id="+element.value, success: function(checkedCount) {
                    $("#checked-count").text(checkedCount);
                    $('#selectAll').prop('checked', false);
                }});
            }
        }
    </script>
</body>
</html>
