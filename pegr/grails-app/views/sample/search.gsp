<html>
<head>
    <title>Samples</title>
    <g:set var="defaultGalaxy" value="${defaultGalaxy}" scope="request"/>
    <meta name="layout" content="analysis"/>
    <!-- <asset:javascript src="cookie.js"/> -->
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
            $("#nav-datasets").addClass("active");
        });

        $(document).ready(function(){
          // hedgiejo: reset all the checkboxes and checkedCount (View Checked Samples number)
          $.ajax({url:"/pegr/sample/clearCheckedSampleAjax", success: function(checkedCount) {
              $('.checkbox').prop('checked', false);
              $('#selectAll').prop('checked', false);
              $("#checked-count").text(checkedCount);
          }});

        $('#table_id').DataTable()({
          'scrollY': '50vh',
          'dom': 'Bfrtip',
          'scrollCollapse': true,
          'paging': true
        });
      });

        //BugFix || git:hedgiejo || Add checkbox feature that can select and deselect all checkboxes.
        $('#selectAll').click(function(checkedCount) {
          if (this.checked) {
		        var sampleIds = [];
            $('.checkbox').prop('checked', true);
            $('.checkbox').each(function(){
                sampleIds.push(this.value);
            })
            $.ajax({
        				url:"${createLink(controller: 'sample', action: 'addAllCheckedSampleAjax')}",
        				type:"GET",
        				data: {"sampleIdsList": JSON.stringify(sampleIds)},
        				success : function(checkedCount){
                  $("#checked-count").text(checkedCount);
        				}
			       });
          }
          else {
              $.ajax({url:"/pegr/sample/clearCheckedSampleAjax", success: function(checkedCount) {
                  $('.checkbox').prop('checked', false);
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
