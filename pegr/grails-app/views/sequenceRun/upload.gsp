<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="sequenceRun"/>
</head>
<body>
    <div class="message">
        Difference between sacCer and sacCer_cegr: sacCer is the UCSC version which uses Roman numerals; sacCer_cegr is the Pugh lab version which uses numbers. Pugh lab samples should normally use sacCer_cegr.
    </div>
    <div class="container-fluid">
        <h3>Upload Excel (xlsx) File</h3>
        <g:uploadForm action="convertXlsx" >
            <div class="form-group">
                <label>Sample Sheet Name</label>
                <g:textField name="sampleSheet" value="SAMPLE"></g:textField>
                <label>Sample Lines from</label>
                <g:textField name="startLine"></g:textField>
                <label>To</label>
                <g:textField name="endLine"></g:textField>
            </div>
            <div class="form-group">
                <label>Lane Sheet Name</label>
                <g:textField name="laneSheet" value="Lane"></g:textField>
                <label>Info Line</label>
                <g:textField name="laneLine" value="3"></g:textField>
            </div>

            <div class="form-group">
                <input type="file" name="file"/> (only xlsx files)
            </div>
            <div class="form-group">
                <g:submitButton name="upload" value="Upload" class="btn btn-primary"/>
                <g:link action="index" class="btn btn-default">Cancel</g:link>
            </div>
        </g:uploadForm>
    </div>
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
                <input type="file" name="file"/> (only csv files)
            </div>
            <div class="form-group">
                <g:submitButton name="upload" value="Upload" class="btn btn-primary"/>
                <g:link action="index" class="btn btn-default">Cancel</g:link>
            </div>
        </g:uploadForm>
    </div>
    <script>
        $("#nav-bench").addClass("active");
     </script>
</body>
</html>
