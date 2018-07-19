<html>
<head>
    <title>Samples</title>
    <g:set var="defaultGalaxy" value="${defaultGalaxy}" scope="request"/>
    <meta name="layout" content="analysis"/>
    <asset:javascript src="cookie.js"/>
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"></style>
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <br>
        <div>
            <input type="checkbox" id="selectAll" value="selectAll">
            <g:link action="showChecked" class="btn btn-default">View Checked Samples (<span id="checked-count">${checkedCount}</span>)</g:link>
            <g:link action="showFilesForCheckedSamples" class="btn btn-default">Files</g:link>
        </div>
        <g:render template="table" model="['sampleList':sampleList, 'checkbox':true]"></g:render>
    </div>
    <script>
        $(function(){
            $(".nav-datasets").addClass("active");
        });

        $(document).ready(function(){
        $('#table_id').DataTable()({
          scrollY: '50vh',
          dom: 'Bfrtip',
          scrollCollapse: true,
          paging: true
        });
      });

        //BugFix || git:hedgiejo || Add checkbox feature that can select and deselect all checkboxes.
        $('#selectAll').click(function(checkedCount) {
          if (this.checked) {
            var checkedSampleIds = [];
            $('input[name="checkedSample"]').prop('checked', true);
            $('input[name="checkedSample"]').each(function(checkedCount){
              if (this.checked){
                checkedSampleIds.push(this.value);

                $.ajax({url:"/pegr/sample/addCheckedSampleAjax?id="+this.value, success: function(checkedCount) {
                  $("#checked-count").text(checkedCount);
                }});
              }
            })
          }
          else {
              $.ajax({url:"/pegr/sample/clearCheckedSampleAjax", success: function(checkedCount) {
                  $('input[name="checkedSample"]').prop('checked', false);
                  checkedCount = 0;
                  $("#checked-count").text(checkedCount);
              }});
          }
        });

        function toggleChecked(element) {
            if (element.checked) {
                $.ajax({url:"/pegr/sample/addCheckedSampleAjax?id="+element.value, success: function(checkedCount) {
                    $("#checked-count").text(checkedCount);
                }});
            } else {
                $.ajax({url:"/pegr/sample/removeCheckedSampleAjax?id="+element.value, success: function(checkedCount) {
                    $("#checked-count").text(checkedCount);
                    $('#selectAll').prop('checked', false);
                }});
            }
        }
    </script>
</body>
</html>
