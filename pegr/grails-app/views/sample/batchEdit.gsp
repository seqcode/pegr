<html>
<head>
    <title>PEGR - Experiments</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    <h4>Samples</h4>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Sample ID</th>
                <th>Strain</th>
                <th>Antibody</th>
                <th>Index</th>
                <th>Genome Build</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${samples}">
                <tr>
                    <td><g:link controller="sample" action="show" id="${sample.id}">${sample?.id}</g:link></td>
                    <td>${sample?.cellSource?.strain}</td>
                    <td>${sample?.antibody}</td>
                    <td>${sample?.sequenceIndicesString}</td>
                    <td>                            
                        <g:select multiple="multiple" name="genome" from="${pegr.Genome.list()}" optionKey="name" value="${it.genomes}" class="select2"></g:select>
                    </td>
                    <td> </td>
                </tr>
            </g:each>              
            <tr>
                <td colspan="6"></td>
            </tr>
        </tbody>
    </table>
</body>
</html>