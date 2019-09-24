<html>
    <head>
        <title>PEGR - Analysis Status</title> 
	<g:set var="defaultGalaxy" value="${defaultGalaxy}" scope="request"/>
        <meta name="layout" content="analysis"/>
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
        <div id="yeast-qc-settings" class="tab-pane fade">            
            <g:link controller="report" action="decisionTree" params="[type:'YEAST_QC']" class="btn btn-primary">Decision Tree</g:link>
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
                        <th></th>
                        <th></th>
                        <g:each in="${meta.yeast}" var="field">
                            <th>${field}</th>
                        </g:each>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${qcSettings.yeast}" var="setting">
                        <tr>
                            <td><span class="glyphicon glyphicon-trash"></span></td>
                            <td><span class="glyphicon glyphicon-arrow-up"></span></td>
                            <td><span class="glyphicon glyphicon-arrow-down"></span></td>
                            <g:each in="${meta.yeast}" var="field">
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
        <div id="purge-alignments"  class="tab-pane fade">          
            <g:form controller="report" action="deletePurgedAlignments">
                <div id="purge-config" class="fields"><g:render template="purgeAlignments" model="[purgeConfig:purgeConfig]"></g:render></div>
                <g:if test="${purgeConfig?.status != 'RUN'}">
                    <input onclick="deletePurgedAlignments(this)" type="button" class="btn btn-primary" value="Submit">
                    <i class="fa fa-spinner fa-spin"></i>
                    <g:link controller="sequenceRun" action="index" class="btn btn-default">Cancel</g:link>
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
