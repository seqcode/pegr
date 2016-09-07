<html>
    <head>
        <title>PEGR</title>
        <meta name="layout" content="none"/>
        <asset:stylesheet href="application.css"/>
    </head>
    <body class="container">
        <h3>${instance.protocol}</h3>
        <p>${instance.startTime} ${instance.user}</p>
        <g:each in="${parents}" var="parent" status="n">
            <div class="row">
                <g:render template="/item/barcodeWithInfo" model="[item: parent]"></g:render>
                <g:render template="/item/barcodeWithInfo" model="[item: children[n]]"></g:render>
            </div>            
        </g:each>
    </body>
</html>