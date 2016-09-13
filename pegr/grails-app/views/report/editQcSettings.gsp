<html>
    <head>
        <title>PEGR - Analysis Status</title> 
        <meta name="layout" content="analysis"/>
    </head>
    <body>
        <button id="add" class="pull-right edit">Add Row</button>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:form controller="report" action="saveQcSettings">
        <table>            
            <thead>
                <tr>
                    <th>key</th>
                    <th>name</th>
                    <th>format</th>
                    <th>min</th>
                    <th>max</th>
                    <th>reference min</th>
                    <th>reference max</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${qcSettings}">
                    <tr>
                        <td><input name="key" value="${it.key}"></input></td>
                        <td><input name="name" value="${it.name}"></input></td>
                        <td><input name="numFormat" value="${it.numFormat}"></input></td>
                        <td><input name="min" value="${it.min}"></input></td>
                        <td><input name="max" value="${it.max}"></input></td>
                        <td><input name="reference_min" value="${it.reference_min}"></input></td>
                        <td><input name="reference_max" value="${it.reference_max}"></input></td>
                    </tr>
                </g:each>                
            </tbody>
        </table>
        <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
        <g:link controller="report" action="analysisStatus" class="btn btn-default">Cancel</g:link>
        </g:form>
        
        <script>
            $(function() {
               $(".nav-status").addClass("active");
            });
            
            $("#add").click(function() {
                var $orig = $("tr").last();
                // copy the row
                $('tbody').append(
                    $('<tr/>')
                        .append(
                            $orig.children().clone(true))
                );
            });
        </script>
    </body>
</html>