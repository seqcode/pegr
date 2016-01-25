<div id="items" class="panel-collapse collapse in">
    <ul class="list-group">
    <g:each in="${items}">
        <li class="list-group-item">
            <h4 class="list-group-item-heading">${it.name}</h4>
        </li>
    </g:each> 
    <g:each in="${subBags}">
        <li class="list-group-item">
            <g:render template="/protocolInstanceBag/baggedItems" bean="${it}"></g:render>
        </li>
    </g:each> 
    </ul>
</div>