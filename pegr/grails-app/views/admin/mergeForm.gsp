<html>
    <head>
        <title>PEGR</title>
        <meta name="layout" content="base">
    </head>
    <body>
        <div class="container-fluid">
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:form action="merge" class="fields">
                <h4>Merge</h4>
                <div>
                    <label>Type</label>
                    <g:select name="table" from="${tables}" value="${table}" noSelection="['':'--Choose--']"></g:select>
                </div>
                <div>
                    <label>From ID</label>
                    <input name="fromId">
                </div>
                <div>
                    <label>To ID</label>
                    <input name="toId">
                </div>
                <g:submitButton name="submit" value="Submit" class="btn btn-primary"></g:submitButton>
            </g:form>
        </div>
        <script>
            $(function(){
                $("select").select2();
            })
        </script>
    </body>
</html>