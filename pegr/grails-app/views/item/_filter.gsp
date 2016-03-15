<div class="col-md-2 sidenav filter">
    <h4 class="text-center">Filter</h4>
    <ul>      
        <g:each in="${pegr.ItemTypeCategory}" var="category">
            <li>
                <h5>${category}</h5>
                <ul>
                    <g:each var="itemType" in="${pegr.ItemType.findAllByCategory(category).sort{it.name}}">
                        <li <g:if test="${itemType==currentType}">class="active"</g:if>><g:link controller="item" action="list"  params="[typeId: itemType.id]">${itemType.name}</g:link></li>
                    </g:each>
                </ul>
            </li>            
        </g:each>
    </ul>
</div>