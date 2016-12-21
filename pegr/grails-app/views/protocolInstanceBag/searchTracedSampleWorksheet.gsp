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
            <h4>Selected traced samples <button id="add-btn" class="btn btn-primary pull-right">Add selected samples</button></h4>
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
            </table>
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
                    $("tbody tr td:first-child").each(function(){
                        var id = $(this).text();
                        $(this).html("<input type='checkbox' name='itemIds' value='"+id+"'>");
                    });
                }
            })
            
        });
        
        $("#add-btn").on("click", function() {
           
        });
    </script>
</body>
</html>