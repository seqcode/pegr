<div id="items" class="panel-collapse collapse in">
    <ul class="list-group">
    <g:each in="${samples}">
        <li class="list-group-item">
            <h4 class="list-group-item-heading">
                <g:link controller='item' action='show' id="${it.item?.id}" target="_blank">${it.item?.name}</g:link> 
                <g:if test="${!completed}">
                <g:link class="pull-right confirm" action="removeSampleFromBag" params="[sampleId: it.id, bagId: bag?.id]"><span class="glyphicon glyphicon-remove"></span></g:link>
                </g:if>
            </h4>
        </li>
    </g:each> 
    </ul>
</div>
