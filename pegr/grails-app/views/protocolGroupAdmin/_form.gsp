<%@ page import="pegr.ProtocolGroup" %>

<h4>Protocols <a href="#" class="edit" data-toggle="modal" data-target="#create-protocol">New</a> </h4>
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
