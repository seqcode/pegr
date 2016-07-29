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

        <div id="details">
            <div>
                <a href="#" onclick="showChecked()" class="btn btn-info">View Checked Samples (<span id="checked-count"></span>)</a>
                <a href="#" onclick="clearCheckedSamples()" class="btn btn-info">Clear</a>
            </div>
            <g:render template="table" model="['sampleList':sampleList, 'checkbox':true]"></g:render>
            <div class="pagination">
                <g:paginate next="Next" prev="Prev" controller="sample" action="search" max="15" total="${sampleList.totalCount ?: 0}"  params="${params}"/>
            </div>
        </div>
    </div>
    <script>
        $(function(){            
            var cookieSamples = getCookie('checkedSamples');
            var count = 0;
            if (cookieSamples) {
                count = (cookieSamples.match(/,/g) || []).length;
            }
            $("#checked-count").text(count);
            $("#nav-metadata").addClass("active");
        });
        
        function toggleChecked(element) {
            var count = $("#checked-count").text();
            var cookieSamples = getCookie('checkedSamples');
            if (element.checked) {             
                count = Number(count) + 1;
                cookieSamples += element.value + ",";
            } else {
                count = Number(count) - 1;
                cookieSamples.replace(element.value+",", "");
            }
            $("#checked-count").text(count);
            setCookie('checkedSamples', cookieSamples, 1);
        }
        
        function clearCheckedSamples(){
            setCookie('checkedSamples', '', -1);
            $("#checked-count").text(0);
        }
        
        function showChecked() {
             var sampleIds = getCookie('checkedSamples');
            $("checkedSamples").text(sampleIds);
            var url = "/pegr/report/fetchDataForSamplesAjax?sampleIds=" + sampleIds;
            $.ajax({url: url, success: function(result) {
                $("#details").html(result);
            }});
        }
    </script>
</body>
</html>
