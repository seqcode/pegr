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
            <g:render template="/project/sampleTable" model="['sampleList':samples]" />
        </div>
    </body>
</html>