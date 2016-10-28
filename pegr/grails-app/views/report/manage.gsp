<html>
    <head>
        <title>PEGR - Analysis Status</title> 
        <meta name="layout" content="analysis"/>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
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
            <li><a data-toggle="tab" href="#yeast-qc-settings">Yeast QC Settings</a></li>
            <li><a data-toggle="tab" href="#purge-alignments">Delete Purged Alignments</a></li>
        </ul>
        <div class="tab-content">
        <div id="qc-settings" class="tab-pane fade in active">            
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
                        <th>key</th>
                        <th>name</th>
                        <th>format</th>
                        <th>min</th>
                        <th>max</th>
                        <th>reference min</th>
                        <th>ref. min ratio</th>
                        <th>reference max</th>
                        <th>ref. max ratio</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${qcSettings.general}">
                        <tr>
                            <td><span class="glyphicon glyphicon-trash"></span></td>
                            <td><input name="key" value="${it.key}"></input></td>
                            <td><input name="name" value="${it.name}"></input></td>
                            <td><input name="numFormat" value="${it.numFormat}" size="12"></input></td>
                            <td><input name="min" value="${it.min}" size="10"></input></td>
                            <td><input name="max" value="${it.max}" size="10"></input></td>
                            <td><input name="reference_min" value="${it.reference_min}"></input></td>
                            <td><input name="reference_min_ratio" value="${it.reference_min_ratio}" size="10"></input></td>
                            <td><input name="reference_max" value="${it.reference_max}"></input></td>
                            <td><input name="reference_max_ratio" value="${it.reference_max_ratio}" size="10"></input></td>
                        </tr>
                    </g:each>                
                </tbody>
            </table>
            <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
            <g:link controller="report" action="analysisStatus" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>
        <div id="yeast-qc-settings" class="tab-pane fade">            
            <button class="add pull-right edit">Add Row</button>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:form controller="report" action="saveQcSettings">
                <input type="hidden" name="type" value="yeast">
            <table>            
                <thead>
                    <tr>
                        <th></th>
                        <th>key</th>
                        <th>name</th>
                        <th>format</th>
                        <th>min</th>
                        <th>max</th>
                        <th>reference min</th>
                        <th>ref. min ratio</th>
                        <th>reference max</th>
                        <th>ref. max ratio</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${qcSettings.yeast}">
                        <tr>
                            <td><span class="glyphicon glyphicon-trash"></span></td>
                            <td><input name="key" value="${it.key}"></input></td>
                            <td><input name="name" value="${it.name}"></input></td>
                            <td><input name="numFormat" value="${it.numFormat}" size="12"></input></td>
                            <td><input name="min" value="${it.min}" size="10"></input></td>
                            <td><input name="max" value="${it.max}" size="10"></input></td>
                            <td><input name="reference_min" value="${it.reference_min}"></input></td>
                            <td><input name="reference_min_ratio" value="${it.reference_min_ratio}" size="10"></input></td>
                            <td><input name="reference_max" value="${it.reference_max}"></input></td>
                            <td><input name="reference_max_ratio" value="${it.reference_max_ratio}" size="10"></input></td>
                        </tr>
                    </g:each>                
                </tbody>
            </table>
            <g:submitButton name="save" value="Save" class="btn btn-primary"></g:submitButton>
            <g:link controller="report" action="analysisStatus" class="btn btn-default">Cancel</g:link>
            </g:form>
        </div>
        <div id="purge-alignments"  class="tab-pane fade">          
            <g:form controller="report" action="deletePurgedAlignments">
                <div id="purge-config" class="fields"><g:render template="purgeAlignments" model="[purgeConfig:purgeConfig]"></g:render></div>
                <g:if test="${purgeConfig?.status != 'RUN'}">
                    <input onclick="deletePurgedAlignments(this)" type="button" class="btn btn-primary" value="Submit">
                    <i class="fa fa-spinner fa-spin"></i>
                </g:if>
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
            
            function deletePurgedAlignments(elem) {
                $(elem).addClass("disabled");
                $(".fa").show();
                $(".message").remove();
                jQuery.ajax({
                    type:'POST',
                    data:jQuery(elem).parents('form:first').serialize(), 
                    url:'/pegr/report/deletePurgedAlignments',
                    success:function(data,textStatus){
                        jQuery('#purge-config').html(data);
                        $(elem).removeClass("disabled");
                        $(".fa").hide();
                    },
                });
                return false;
            }
        </script>
    </body>
</html>