<html>
<head>
  <title>Samples</title>
  <meta name="layout" content="main"/>
  <!-- <asset:javascript src="cookie.js"/> -->
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
  <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
  <div class="container-fluid">
    <h2>Samples</h2>
    <div style="padding:6px">
        <input type="checkbox" id="selectAll" value="selectAll">
        <g:link action="showChecked" class="btn btn-default">View Checked Samples (<span id="checked-count">${checkedCount}</span>)</g:link>
        <g:link action="showFilesForCheckedSamples" class="btn btn-default">Files</g:link>
    </div>
    <div class="row">
      <div class="col-sm-10">
          <g:render template="table" model="['sampleList':sampleList, 'checkbox':true]"></g:render>
          <div class="pagination">
                <g:paginate next="Next" prev="Prev" controller="sample" action="all" max="50" total="${sampleList.totalCount ?: 0}" />
          </div>
      </div>
      <div id="filter" class="well col-sm-2">
          <h4>Filters</h4>
          <g:form action="search">
            <div class="form-group">
                <label>Sample ID</label>
                <g:textField name="id" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Species</label>
                <g:textField name="species" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Strain</label>
                <g:textField name="strain" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Antibody</label>
                <g:textField name="antibody" class="form-control"/>
            </div>               
            <div class="form-group">
                <label>Target</label>
                <g:textField name="target" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Assay <a href="#" onclick="window.open('/pegr/help/assayHelp', 'Help: Assay', 'width=600,height=400' )"><span class="glyphicon glyphicon-question-sign"></span></a></label>
                <g:textField name="assay" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Source</label>
                <g:textField name="source" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Source ID</label>
                <g:textField name="sourceId" class="form-control"/>
            </div>
            <div>
                <g:submitButton name="search" value="Apply" class="btn btn-primary"/>
                <g:link action="all" class="btn btn-default">Clear</g:link>
            </div>            
          </g:form>
      </div>
    </div>
  </div>
  <script>
      $(function(){
        $("#nav-samples").addClass("active");
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
