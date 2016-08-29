<html>
<head>
    <title>Samples</title> 
    <meta name="layout" content="analysis"/>
    <asset:javascript src="meme.js"/>
    <asset:stylesheet href="meme.css"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
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
    <div id="details">
        <div class="text-center">
            <i class="fa fa-spinner fa-spin"></i>
        </div>
    </div> 

    <script>
        $(function(){
            $(".nav-datasets").addClass("active");       
            $.ajax({url: "/pegr/sample/fetchDataForCheckedSamplesAjax", success: function(result) {
                $("#details").html(result);
            }});
        });
    </script>
</body>
</html>
