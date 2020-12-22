<html>
    <head>
        <meta name="layout" content="help">
    </head>
    <body>
        <div class="container-fluid"> 
            <div class="table-responsive" style="padding-top: 12px">            
                <table class="table table-striped">
                    <thead>
                        <th>Assay</th>
                        <th>Description</th>
                    </thead>
                    <tbody>                        
                        <g:each in="${assays}">
                        <tr>
                            <td class="col-sm-3">${it.name}</td>
                            <td class="col-sm-9">${it.description}</td>
                        </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            $(".help-assay").addClass("active");
         </script>
    </body>
</html>