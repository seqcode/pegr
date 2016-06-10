<html>
    <head>
        <title>Replicate Set</title> 
        <meta name="layout" content="main"/>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div>
            <h3>${set.type} Replicate Set ${set.id}</h3>
            <g:if test="${authorized}">
                <div class="pull-right">            
                    <g:link action="delete" id="${set.id}" class="btn btn-primary confirm"><span class="glyphicon glyphicon-trash"></span> Delete Set</g:link>
                    <a href="#" class="btn btn-primary">+ Add Samples</a>
                </div>
            </g:if>
            <g:render template="/replicate/sampleTable" model="['sampleList':samples]" />
        </div>
        <script>
            $(".confirm").confirm();
        </script>
    </body>
</html>