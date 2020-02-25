<%@ page import="pegr.ProtocolGroup" %>

<h4>Protocols <a href="#" class="edit" onclick="window.open('${g.createLink(controller: 'protocol', action:'create')}', 'Create Protocol', 'width=800, height=600')">New</a> </h4>
<div class="row" id="select-protocols">
    <div class="col-sm-5">
       <g:select name="protocolList" id="searchProtocol" class="form-control" multiple="multiple" from="${pegr.Protocol.list()}" optionKey="id" value="${protocolGroup?.protocols}"></g:select>
    </div>
</div>

<script type="text/javascript">
    $("#searchProtocol").select2();
    $("select").on("select2:select", function (evt) {
        var element = evt.params.data.element;
        var $element = $(element);

        $element.detach();
        $(this).append($element);
        $(this).trigger("change");
    });
</script>
