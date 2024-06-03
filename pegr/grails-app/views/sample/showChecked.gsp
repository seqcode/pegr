<html>
<head>
    <title>Samples</title> 
    <meta name="layout" content="main"/>
    <asset:javascript src="meme.js"/>
    <asset:stylesheet href="meme.css"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <style>
        .fa {
            font-size:48px;
            color:#337ab7;
        }
        h5 {
            padding-top: 10px;
        }
    </style>
</head>
<body>
    <g:link action="all"><span class="glyphicon glyphicon-home"></span> Sample List</g:link> 
    <div class="pull-right">
        <g:link action="exportSamplesMetadata" class="btn btn-primary"> Export Sample Metadata</g:link> 
        <g:link action="printChecked" class="btn btn-primary"> Generate Printable Report</g:link> 
    </div>
    <div id="details">
        <div class="text-center">
            <i class="fa fa-spinner fa-spin"></i>
        </div>
    </div> 

    <script>
        $(function(){
            $("#nav-samples").addClass("active");       
            $.ajax({url: "/pegr/sample/fetchDataForCheckedSamplesAjax", success: function(result) {
                $("#details").html(result);
            }});
        });
    </script>
</body>
</html>
