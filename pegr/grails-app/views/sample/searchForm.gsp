<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="analysis">
    <title>Sample-Search</title>
</head>
<body>
    <div class="container-fluid">
        <h3>Search Sample</h3>
        <g:form action="search" class="fields">
            <div>
                <label>ID</label>
                <g:textField name="id" />
            </div>
            <div>
                <label>Source</label>
                <g:textField name="source" />
            </div>
            <div>
                <label>Source ID</label>
                <g:textField name="sourceId" />
            </div>
            <div>
                <label>Species</label>
                <g:textField name="species" />
            </div>
            <div>
                <label>Strain</label>
                <g:textField name="strain" />
            </div>

            <div>
                <label>Antibody</label>
                <g:textField name="antibody" />
            </div>
            <div>
                <label>Target</label>
                <g:textField name="target" />
            </div>
            <g:submitButton name="search" value="Search" class="btn btn-primary"/>
        </g:form>
    </div>
    <script>
    $(".nav-datasets").addClass("active");
    </script>
</body>