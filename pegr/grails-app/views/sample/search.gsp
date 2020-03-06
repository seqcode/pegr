<html>
<head>
    <title>Samples</title>
    <meta name="layout" content="analysis"/>
    <asset:javascript src="cookie.js"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
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
        <div class="pagination">
            <g:paginate next="Next" prev="Prev" controller="sample" action="search" max="50" total="${sampleList.totalCount?: 0}" params="${params}" />
        </div>
    </div>
    <script>
        $(function(){
            $("#nav-datasets").addClass("active");
        });

        $(document).ready(function(){
          // BugFix || git:hedgiejo || Reset all the checkboxes and checkedCount when reloading
          $('#selectAll').prop('checked', false);
          $('.checkbox').prop('checked', false);
          $.ajax({url:"/pegr/sample/clearCheckedSampleAjax", success: function(checkedCount) {
            checkedCount = 0;
            $("#checked-count").text(checkedCount);
          }});
      });

        // BugFix || git:hedgiejo || Add checkbox feature that can select and deselect all checkboxes.
        $('#selectAll').click(function(checkedCount) {
          if (this.checked) {
		        var sampleIds = [];
            $('.checkbox').prop('checked', true);
            $('.checkbox').each(function(){
                sampleIds.push(this.value);
            })
            console.log(JSON.stringify(sampleIds))
            $.ajax({
        				url:"/pegr/sample/addAllCheckedSampleAjax",
        				data: {"sampleIdsList":JSON.stringify(sampleIds)},
        				success : function(checkedCount){
                  $("#checked-count").text(checkedCount);
        				}
			       });
          }
          else {
                  $('.checkbox').prop('checked', false);
              $.ajax({url:"/pegr/sample/clearCheckedSampleAjax", success: function(checkedCount) {
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
