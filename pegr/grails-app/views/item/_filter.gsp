<div class="col-md-2 sidenav filter">
    <h4 class="text-center">Filter</h4>
    <ul>      
        <g:each in="${pegr.ItemTypeCategory}">
            <li>
                <h5>${it}</h5>
                <ul>
                    <g:each in="${pegr.ItemType.findAllByCategory(it)}">
                        <li <g:if test="${it==currentType}">class="active"</g:if>><g:link controller="item" action="list"  params="[typeId: it.id]">${it}</g:link></li>
                    </g:each>
                </ul>
            </li>            
        </g:each>
    </ul>
</div>