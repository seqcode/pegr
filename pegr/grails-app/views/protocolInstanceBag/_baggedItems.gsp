<div id="items" class="panel-collapse collapse in">
    <ul class="list-group">
    <g:each in="${items}">
        <li class="list-group-item">
            <h4 class="list-group-item-heading">
                <a href="javascript:void(0)" onclick="javascript:asd('${g.createLink(controller: 'item', action: 'show', id: it.id)}')">${it.name}</a>
            </h4>
        </li>
    </g:each> 
    <g:each in="${subBags}">
        <li class="list-group-item">
            <h4 class="list-group-item-heading"><g:link controller="ProtocolInstanceBag" action="showBag" id="${it.id}">${it.name}</g:link></h4>
        </li>
    </g:each> 
    </ul>
</div>
