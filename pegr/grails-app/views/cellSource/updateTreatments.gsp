<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <div class="container-fluid">
        <h4>Cell Source Treatments</h4>
        <g:form action="updateTreatments" class="fields" role="form" method="post" useToken="true">
            <g:hiddenField name="cellSourceId" value="${cellSourceId}"></g:hiddenField>
            <g:hiddenField name="itemId" value="${itemId}"></g:hiddenField>
            <div class="row">
                <div class="col-sm-4">
                   <g:select name="from[]" id="search" class="form-control" multiple="multiple" from="${pegr.CellSourceTreatment.list()}" optionKey="id"></g:select>
                </div>
                <div class="col-sm-1">
                    <button type="button" id="search_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
                    <button type="button" id="search_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
                </div>
                <div class="col-sm-4">
                    <select name="treatments" id="search_to" class="form-control" size="5" multiple="multiple">
                        <g:each in="${treatments}">
                        <option value="${it.id}" >${it}</option>
                        </g:each>
                    </select>
                </div>
            </div>

            <g:submitButton class="btn btn-primary" name="save" value="Save"/>
            <g:link class="btn btn-default" controller="item" action="show" id="${itemId}">Cancel</g:link>

        </g:form>
    </div>
    
    <script>
    jQuery(document).ready(function($) {
        $('#search').multipleselect({
            search: {
                left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
            }, unique: "true",
        });    
    });
    </script>
</body>
</html>