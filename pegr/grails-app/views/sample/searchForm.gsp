<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Sample-Search</title>
</head>
<body>
    <div class="container-fluid">
        <h3>Search Sample</h3>
        <g:form action="search" class="fields">

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
            <g:submitButton name="search" value="Search" class="btn btn-primary"/>
        </g:form>
    </div>
    <script>
    $("#nav-metadata").addClass("active");
    </script>
</body>