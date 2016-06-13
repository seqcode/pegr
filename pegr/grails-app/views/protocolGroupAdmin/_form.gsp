<%@ page import="pegr.ProtocolGroup" %>

<div class="${hasErrors(bean: protocolGroupInstance, field: 'name', 'error')} required">
	<label for="name">Name	<span class="required-indicator">*</span></label>
	<g:textField name="name" required="" value="${protocolGroupInstance?.name}"/>
</div>

<h4>Protocols <button class="edit" data-toggle="modal" data-target="#create-protocol">Create New Protocol</button> </h4>
<div class="row" id="select-protocols">
    <g:render template="/protocolGroupAdmin/selectProtocols"></g:render>
</div>

<script type="text/javascript">
    jQuery(document).ready(function($) {
        $('#search').multipleselect({
            search: {
                left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
            },        
        });    
    });

    $("#up").click(function(){
        $("select").moveSelectedUp();
    });

    $("#down").click(function(){
        $("select").moveSelectedDown();
    });
    
    function afterCreateProtocol() {
        $(".modal").modal('hide');
        $('#search').multipleselect({
            search: {
                left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
            },        
        });  
    }
</script>
