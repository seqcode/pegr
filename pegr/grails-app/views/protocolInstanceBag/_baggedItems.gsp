<div id="items" class="panel-collapse collapse in">
    <ul class="list-group">
    <g:each in="${items}">
        <li class="list-group-item">
            <h4 class="list-group-item-heading">
                <g:link controller='item' action='show' id="${it.id}" target="_blank">${it.name}</g:link> 
                <g:if test="${!completed}">
                <g:link class="pull-right confirm" action="removeItemFromBag" params="[itemId: it.id, bagId: bag?.id]"><span class="glyphicon glyphicon-remove"></span></g:link>
                </g:if>
            </h4>
        </li>
    </g:each> 

    <g:each in="${subBags}">
        <li class="list-group-item">
            <h4 class="list-group-item-heading">
                <g:link controller="ProtocolInstanceBag" action="showBag" id="${it.id}" target="_blank"><span class="glyphicon glyphicon-folder-open"></span> ${it.name}</g:link>
                <g:if test="${!completed}">
                <g:link class="pull-right confirm" action="removeBagFromBag" params="[subBagId: it.id, bagId: bag?.id]"><span class="glyphicon glyphicon-remove"></span></g:link>
                </g:if>
            </h4>
        </li>
    </g:each> 
    </ul>
</div>
