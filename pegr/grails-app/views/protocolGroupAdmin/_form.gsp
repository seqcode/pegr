<%@ page import="pegr.ProtocolGroup" %>

<div class="${hasErrors(bean: protocolGroupInstance, field: 'name', 'error')} required">
	<label for="name">Name	<span class="required-indicator">*</span></label>
	<g:textField name="name" maxlength="30" required="" value="${protocolGroupInstance?.name}"/>
</div>

<h4>Protocols</h4>
<div class="row">
    <div class="col-sm-5">
	   <g:select name="from[]" id="search" class="form-control" multiple="multiple" from="${pegr.Protocol.list()}" optionKey="id" ></g:select>
	</div>
    <div class="col-sm-2">
        <button type="button" id="search_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
		<button type="button" id="search_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
        <button type="button" id="up" class="btn btn-block"><i class="glyphicon glyphicon-arrow-up"></i></button>
		<button type="button" id="down" class="btn btn-block"><i class="glyphicon glyphicon-arrow-down"></i></button>
    </div>
    <div class="col-sm-5">
        <select name="protocols" id="search_to" class="form-control" size="8" multiple="multiple">
            <g:each in="${protocolGroupInstance.protocols}">
            <option value="${it.id}" >${it}</option>
            </g:each>
        </select>
    </div>
</div>

<script type="text/javascript">
jQuery(document).ready(function($) {
	$('#search').multipleselect({
		search: {
			left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
		},        
		sort: false
	});    
});
    
$("#up").click(function(){
	$("select").moveSelectedUp();
});
    
$("#down").click(function(){
	$("select").moveSelectedDown();
});
</script>
