<html>
    <head>
        <meta name="layout" content="help">
    </head>
    <body>
        <div class="container-fluid">
            <div id="sequence-index">
                <h3>Index</h3>
                <p>Index can be input by using either sequence (e.g. "ATCG") or its ID (e.g. "iA") assigned by PEGR (see the following table). Note that your input should conform to the radio button choice. Both single-index and duo-index are accepted, and one or more indices can be attached to a single sample. For duo-index samples, the two indices on the same molecule should be deliminated by hyphen "-", and indices on different modlecules should be deliminated by comma ",". For example, if you choose to input sequence, a valid input will be like "TCGCCTTA-CTCTCTAT,CTAGTACG-TATCCTCT"; while if you choose to input ID, the input should be like "i01-iA,i02-iB".</p>
                <div class="row">
                    <g:each in="${allIndices}" var="indices">
                        <div class="col-xs-6">
                            <table class="table-bordered">
                                <caption>Version: ${indices.key} Index</caption>
                                <thead>
                                    <th>ID</th>
                                    <th>Sequence</th>
                                </thead>
                                <tbody>                        
                                    <g:each in="${indices.value}">
                                        <tr>
                                            <td>${it.indexId}</td>
                                            <td>${it.sequence}</td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
        <script>
            $(".help-index").addClass("active");
         </script>
    </body>
</html>