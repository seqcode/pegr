<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <div class="container-fluid">
        <h3>Upload CSV File</h3>
        <g:uploadForm action="convertCsv" >
            <div class="form-group">
                <label>Lines from</label>
                <g:textField name="startLine"></g:textField>
                <label>To</label>
                <g:textField name="endLine"></g:textField>
            </div>

            <div class="form-group">
                <input type="file" name="file"/>
                <g:submitButton name="upload" value="Upload"/> (only csv files)
            </div>
        </g:uploadForm>
    </div>
</body>
</html>
