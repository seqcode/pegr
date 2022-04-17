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
    <p><span id="checked-count">${checkedCount}</span> sample(s) selected</p>
    <div style="padding:6px">
        <input type="checkbox" id="selectAll" value="selectAll">
        <g:link action="showChecked" class="btn btn-default">View Samples Details</g:link>
        <g:link action="showFilesForCheckedSamples" class="btn btn-default">View Sample Files</g:link>
        <button id="clear-all-checked" class="btn btn-default">Clear Selection</button>
    </div>
    <div class="row">
      <div class="col-sm-10">
          <g:render template="table" model="['sampleList':sampleList, 'checkbox':true, 'searchParams':params]"></g:render>
          <div class="pagination">
                <g:paginate next="Next" prev="Prev" controller="sample" action="all" max="50" total="${sampleList.totalCount ?: 0}" params="${params}"/>
          </div>
      </div>
      <div id="filter" class="well col-sm-2">
          <h4>Filters</h4>
          <g:form action="all">
            <div class="form-group">
                <label>Sample ID</label>
                <g:textField name="id" class="form-control" value="${searchParams.id}"/>
            </div>
            <div class="form-group">
                <label>Species</label>
                <g:select id="species" name="speciesId" from="${pegr.Species.list().sort {it.genusName.toLowerCase()+it.name.toLowerCase()}}" optionKey="id" value="${searchParams.speciesId}" noSelection="['null': '']" class="select"/>
            </div>
            <div class="form-group">
                <label>Strain</label>
                <g:textField name="strain" class="form-control" value="${searchParams.strain}"/>
            </div>
            <div class="form-group">
                <label>Antibody</label>
                <g:textField name="antibody" class="form-control" value="${searchParams.antibody}"/>
            </div>               
            <div class="form-group">
                <label>Target</label>
                <g:textField name="target" class="form-control" value="${searchParams.target}"/>
            </div>
            <div class="form-group">
                <label>Assay <a href="#" onclick="window.open('/pegr/help/assayHelp', 'Help: Assay', 'width=600,height=400' )"><span class="glyphicon glyphicon-question-sign"></span></a></label>
                <g:select id="assay" name="assayId" from="${pegr.Assay.list(sort:'name')}" optionKey="id" value="${searchParams.assayId}" noSelection="['null': '']" class="select"/>
            </div>
            <div class="form-group">
                <label>Source</label>
                <g:textField name="source" class="form-control" value="${searchParams.source}"/>
            </div>
            <div class="form-group">
                <label>Source ID</label>
                <g:textField name="sourceId" class="form-control" value="${searchParams.sourceId}"/>
            </div>
            <div class="form-group">
                <label>Username (send data to)</label>
                <g:textField name="sendDataTo" class="form-control" value="${searchParams.sendDataTo}"/>
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
        $(".select").select2({
            width: '100%',
            tags: false});
      });
      
      $('#selectAll').click(function(checkedCount) {
          var sampleIds = [];
          $('.checkbox').each(function(){
              sampleIds.push(this.value);
          })

          if (this.checked) {
              $('.checkbox').prop('checked', true);
              $.ajax({
                  url:"/pegr/sample/addAllCheckedSampleAjax",
                  data: {"sampleIdsList":JSON.stringify(sampleIds)},
                  success : function(checkedCount){
                      $("#checked-count").text(checkedCount);
                  }});
          } else {
              $('.checkbox').prop('checked', false);
              $.ajax({
                  url:"/pegr/sample/removeCheckedSamplesAjax", 
                  data: {"sampleIdsList":JSON.stringify(sampleIds)},
                  success: function(checkedCount) {
                      $("#checked-count").text(checkedCount);
                  }});
          }
      });
      
      function toggleChecked(element) {
        if (element.checked) {
            $.ajax({
                url:"/pegr/sample/addCheckedSampleAjax?id="+element.value, 
                success: function(checkedCount) {
                    $("#checked-count").text(checkedCount);
                }});
        } else {
            $.ajax({
                url:"/pegr/sample/removeCheckedSampleAjax?id="+element.value, 
                success: function(checkedCount) {
                    $("#checked-count").text(checkedCount);
                }});
        }
      }
      
      $('#clear-all-checked').click(function(){
          $.ajax({
              url:"/pegr/sample/clearCheckedSampleAjax", 
              success: function(checkedCount) {
                  $("#checked-count").text(0);
                  $('.checkbox').prop('checked', false);
              }});
      });
  </script>
</body>
</html>
