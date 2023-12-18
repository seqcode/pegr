<html>
    <head>
        <title>PEGR - Analysis Status</title> 
        <meta name="layout" content="sequencing"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
        <style>
            .fa {
                font-size:24px;
            }
        </style>
    </head>
    <body>
        <br>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#qc-settings">Quality Control Settings</a></li>
        </ul>
        <div class="tab-content">
        <div id="qc-settings" class="tab-pane fade in active">     
            <p style="padding:10px 0 0 16px;">Available keys: totalReads, dedupUniquelyMappedReads, adapterDimerPct, mappedPct, uniquelyMappedPct, deduplicatedPct, duplicationLevel, totalReadsR2, dedupUniquelyMappedReadsR2, adapterDimerPctR2, MappedPctR2, uniquelyMappedPctR2, deduplicatedPctR2, duplicationLevelR2.</p>
            <button class="add pull-right edit">Add Row</button>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:form controller="report" action="saveQcSettings">
                <input type="hidden" name="type" value="general">
            <table>            
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <g:each in="${meta.general}" var="field">
                            <th>${field}</th>
                        </g:each>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${qcSettings.general}" var="setting">
                        <tr>
                            <td><span class="glyphicon glyphicon-trash"></span></td>
                            <td><span class="glyphicon glyphicon-arrow-up"></span></td>
                            <td><span class="glyphicon glyphicon-arrow-down"></span></td>
                            <g:each in="${meta.general}" var="field">
                                <td><input name="${field}" value="${setting[field]}"</td>
                            </g:each>
                        </tr>
                    </g:each>                
                </tbody>
            </table>
            <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
            <g:link controller="sequenceRun" action="index" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>
        </div>
        <script>
            $(function() {
                $(".nav-status").addClass("active");
                $(".fa").hide();
            });
            
            $(".add").click(function() {
                var parent = $(this).parent();
                var $orig = parent.find("tr").last();
                // copy the row
                parent.find('tbody').append(
                    $('<tr/>')
                        .append(
                            $orig.children().clone(true))
                );
            });
            
            $(".glyphicon-trash").on("click", function() {
                $(this).closest("tr").remove();
            });
            
            $(".glyphicon-arrow-up").on("click", function(){
                var tr = $(this).closest("tr");
                tr.insertBefore(tr.prev());
            });
            
            $(".glyphicon-arrow-down").on("click", function(){
                var tr = $(this).closest("tr");
                tr.insertAfter(tr.next());
            });
        </script>
    </body>
</html>
