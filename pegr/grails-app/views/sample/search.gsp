<html>
<head>
    <title>Samples</title> 
    <meta name="layout" content="main"/>
    <asset:javascript src="cookie.js"/>
</head>
<body>
    <div class="container-fluid">
        <div>
            <g:link action="all"><span class="glyphicon glyphicon-home"></span> Home</g:link>
            <g:link action="searchForm"><span class="glyphicon glyphicon-search"></span> Search</g:link>
        </div>
        <div>
            <g:link action="showChecked" class="btn btn-info">View Checked Samples (<span id="checked-count">${checkedCount}</span>)</g:link>
            <a href="#" onclick="clearCheckedSamples()" class="btn btn-info">Clear Checkbox</a>
        </div>
        <g:render template="table" model="['sampleList':sampleList, 'checkbox':true]"></g:render>
        <div class="pagination">
            <g:paginate next="Next" prev="Prev" controller="sample" action="search" max="15" total="${sampleList.totalCount ?: 0}"  params="${params}"/>
        </div>
    </div>
    <script>
        $(function(){
            $("#nav-metadata").addClass("active");
        });
        
        function toggleChecked(element) {
            if (element.checked) {             
                $.ajax({url:"/pegr/sample/addCheckedSampleAjax?id="+element.value, success: function(count) {
                    $("#checked-count").text(count);
                }});
            } else {
                $.ajax({url:"/pegr/sample/removeCheckedSampleAjax?id="+element.value, success: function(count) {
                    $("#checked-count").text(count);
                }});
            }
        }
        
        function clearCheckedSamples(){
            $.ajax({url:"/pegr/sample/clearCheckedSampleAjax", success: function() {
                $("#checked-count").text(0);
                $(".checkbox").prop('checked', false); 
            }});
        }

    </script>
</body>
</html>
