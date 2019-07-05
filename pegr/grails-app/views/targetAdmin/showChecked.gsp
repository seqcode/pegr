<html>
<head>
    <title>Target</title>
    <meta name="layout" content="main"/>
    <asset:javascript src="cookie.js"/>
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="container-fluid">
        <br>
        <div>
            <h4>Merge Targets</h4>
            <g:form controller="targetAdmin" action="merge" class="fields">
                <div>
                    <label>Merge to</label>
                    <table class="table-bordered">
                    <thead>
                        <tr>
                            <th>Target</th>
                            <th>C-Term Tag</th>
                            <th>N-Term Tag</th>
                            <th>Type</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="col-sm-3"><input name="targetName"></td>
                            <td class="col-sm-3"><input name="cTermTag"></td>
                            <td class="col-sm-3"><input name="nTermTag"></td>
                            <td class="col-sm-3"><input name="targetType"></td>
                        </tr>
                    </tbody>
                </table>
                    <p><i>You may input an existing target or a new target. In the later case, a new target will be created.</i></p>
                </div>
                <g:submitButton class="btn btn-info" name="submit" value="Merge"></g:submitButton>
                <g:link action="cancelMerge" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>
        <br>
        <label>Merge from</label>
        <table class="table-bordered">
            <thead>
                <tr>
                    <th>Target</th>
                    <th>C-Term Tag</th>
                    <th>N-Term Tag</th>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${list}" var="target">
                <tr>
                    <td class="col-sm-3">${target.name}</td>
                    <td class="col-sm-3">${target.cTermTag}</td>
                    <td class="col-sm-3">${target.nTermTag}</td>
                    <td class="col-sm-3">${target.targetType}</td>
                </tr>
                </g:each>
            </tbody>
        </table>
    </div>
    <script>
        $("#nav-targets").addClass("active");
    </script>
</body>
</html>
