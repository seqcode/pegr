<html>
<head>
    <title>PEGR - Experiments</title> 
    <meta name="layout" content="base"/>
</head>
<body>
    <div class="container-fluid">
    <h4>Samples</h4>
    <g:form controller="sample" action="batchUpdate">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Sample ID</th>
                    <th>Barcode</th>
                    <th>Strain</th>
                    <th>Genotype</th>
                    <th>Antibody</th>
                    <th>Target Type</th>
                    <th>Target</th>
                    <th>C-Term</th>
                    <th>N-Term</th>
                    <th>Index</th>
                    <th>Reference Genome(s)</th>
                    <th>Growth Media</th>
                    <th>Treatments</th>
                    <th>Chrom.(ug)</th>
                    <th>Cell#(M)</th>
                    <th>Volume(ul)</th>
                    <th>Requested Tags(M)</th>
                    <th>Send data to</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${samples}" var="sample" status="n">
                    <tr>
                        <td><g:link controller="sample" action="show" id="${sample.id}" class="sample-id">${sample.id}</g:link></td>
                        <td>${sample.item?.barcode}</td>
                        <td>${sample.cellSource?.strain?.name}</td>
                        <td>${sample.cellSource?.strain?.genotype}</td>
                        <td>${sample.antibody}</td>
                        <td><input name="samples[${n}].targetType" value="${sample.target?.targetType}"></td>
                        <td><input name="samples[${n}].target" value="${sample.target?.name}"></td>
                        <td><input name="samples[${n}].cterm" value="${sample.target?.cTermTag}"></td>
                        <td><input name="samples[${n}].nterm" value="${sample.target?.nTermTag}"></td>
                        <td><input name="samples[${n}].index" value="${sample.sequenceIndicesString}"></td>
                        <td class="genome">
                            <g:select multiple="multiple" name="samples[${n}].genome" from="${pegr.Genome.list()}" optionKey="name" value="${sample.requestedGenomes}" class="select2"></g:select>
                        </td>
                        <td>
                            <select name="samples[${n}].growthMedia" class="growth-media tag-select2 textcontrol" style="width: 150px" required>
                                <option></option>
                            </select>
                        </td>
                        <td>
                            <select multiple="multiple" class="treatments tag-select2" name="samples[0].treatments" style="width: 300px">
                                <option></option>
                            </select>
                        </td>
                        <td><g:textField name="samples[0].chrom" class="isnumber"></g:textField></td>
                        <td><g:textField name="samples[0].cellNum" class="isnumber"></g:textField></td>
                        <td><g:textField name="samples[0].volume" class="isnumber"></g:textField></td>
                        <td><g:textField name="samples[0].requestedTags" class="isnumber"></g:textField></td>
                        <td>
                            <select class="sendTo no-tag-select2" name="samples[0].sendToId" style="width: 150px">
                                <option></option>
                            </select>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
        <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
        <span onclick="window.close();" class="btn btn-default">Cancel</span>
    </g:form>
    </div>
    <script>
        $("select").select2();
    </script>
</body>
</html>