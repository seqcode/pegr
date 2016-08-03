<html>
    <head>
        <title>PEGR</title>
        <asset:stylesheet href="application.css"/>
    </head>
    <body>
        <div class="container-fluid">
            <g:if test="${flash.message}">
                <div class="error">${flash.message}</div>
            </g:if>
            <g:form action="merge" class="fields">
                <h4>Merge</h4>
                <div>
                    <label>Type</label>
                    <g:select name="table" from="${tables}" value="${table}" noSelection="['':'--Choose--']"></g:select>
                </div>
                <div>
                    <label>From ID</label>
                    <input name="toId">
                    <label>From ID</label>
                    <input name="toId">
                </div>
                <g:submitButton name="submit" value="Merge" class="btn btn-primary"></g:submitButton>
            </g:form>
        </div>
    </body>
</html>