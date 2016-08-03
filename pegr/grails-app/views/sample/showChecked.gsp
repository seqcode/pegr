<html>
<head>
    <title>Samples</title> 
    <meta name="layout" content="main"/>
    <asset:javascript src="cookie.js"/>
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
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
    <div>
        <g:link action="all"><span class="glyphicon glyphicon-home"></span> Home</g:link>
    </div>
    <div id="details">
        <div class="text-center">
            <i class="fa fa-spinner fa-spin"></i>
        </div>
    </div> 

    <script>
        $(function(){
            $("#nav-metadata").addClass("active");       
            $.ajax({url: "/pegr/sample/fetchDataForCheckedSamplesAjax", success: function(result) {
                $("#details").html(result);
            }});
        });
    </script>
</body>
</html>
