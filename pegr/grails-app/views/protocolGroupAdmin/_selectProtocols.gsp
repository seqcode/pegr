<g:hiddenField name="protocolGroupId" value="${protocolGroup?.id}"></g:hiddenField>
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
        <g:each in="${protocolGroup?.protocols}">
        <option value="${it.id}" >${it}</option>
        </g:each>
    </select>
</div>