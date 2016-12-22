<html>
<head>
    <title>PEGR - Experiment</title>
    <meta name="layout" content="protocolInstanceBag">
    <asset:javascript src="jquery.dataTables.min.js"/>
    <style>
    .paginate_button{box-sizing:border-box;display:inline-block;min-width:1.5em;padding:0.5em 1em;margin-left:2px;text-align:center;text-decoration:none !important;cursor:pointer;*cursor:hand;color:#333 !important;border:1px solid transparent;border-radius:2px}
    </style>
</head>
<body>
    <h4><g:link action="showBag" id="${bag.id}"><span class="glyphicon glyphicon-home"></span> ${bag.name}</g:link></h4>
    <div class="row">
        <div class="col-sm-6" id="search">
            <div>
                <input name="str" value="${str}" id="search-str">
                <button id="search-btn" class="btn btn-primary">Search</button>
            </div>
            <table class="table-bordered" id="search-table">
                <thead>
                    <tr>
                        <th></th>
                        <th>Type</th>
                        <th>Name</th>
                        <th>Barcode</th>
                        <th>Location</th>
                        <th>User</th>
                        <th>Status</th>
                    </tr>
                </thead>
            </table>
        </div>
        <div class="col-sm-6">
            <g:form action="previewItemAndBag">
            <h4>Selected traced samples <g:submitButton name="submit" value="Add selected samples" id="add-btn" class="btn btn-primary pull-right"></g:submitButton></h4>            
            <g:hiddenField name="bagId" value="${bag.id}"/>
            <table class="table-bordered" id="selected-table">
                <thead>
                    <tr>
                        <th></th>
                        <th>Type</th>
                        <th>Name</th>
                        <th>Barcode</th>
                        <th>Location</th>
                        <th>User</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
            </g:form>
        </div>
    </div>
    <script>
        $("#search-btn").on("click", function() {
            var str = $("#search-str").val();
            $.ajax({
                url: "/pegr/item/searchTracedSamplesAjax?str="+str,
                success: function(result) {
                    $("#search-table").DataTable( {
                        destroy: true,
                        searching: false,
                        data: result,
                    });
                    $("#search-table tr td:first-child").each(function(){
                        var id = $(this).text();
                        $(this).html("<input class='select-item' type='checkbox' name='items' value='"+id+"'>");
                    });
                }
            });            
        });
        
        $("#search-table").on("click", "input[type=checkbox]", function() {
            var tr = $(this).closest("tr");
            tr.clone().appendTo($("#selected-table tbody")); 
            tr.remove();
        });
        
        $("#selected-table").on("click", "input[type=checkbox]", function() {
            var tr = $(this).closest("tr");
            tr.appendTo($("#search-table tbody")); 
        });
    </script>
</body>
</html>