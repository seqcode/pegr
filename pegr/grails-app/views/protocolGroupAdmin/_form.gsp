<%@ page import="pegr.ProtocolGroup" %>

<h4>Protocols <a href="#" class="edit" onclick="window.open('${g.createLink(controller: 'protocol', action:'create')}', 'Create Protocol', 'width=800, height=600')">New</a> </h4>
<div class="row" id="select-protocols">
    <div class="col-sm-5">
       <g:select name="protocols" id="searchProtocol" class="form-control" multiple="multiple" from="${pegr.Protocol.list()}" optionKey="id" value="${protocolGroup?.protocols}"></g:select>
    </div>
</div>

<script type="text/javascript">
    $("#searchProtocol").select2()
</script>
