<div class="col-md-2 sidenav filter">
    <h4 class="text-center">Filter</h4>
    <ul>      
        <g:each in="${pegr.ItemTypeCategory}" var="category">
            <li>
                <h5>${category}</h5>
                <ul>
                    <g:each var="itemType" in="${pegr.ItemType.findAllByCategory(category).sort{it.name}}" status="count">
                        <g:if test="${count == 5}">
                            <button onclick="switchItemTypeMenu('${category.name()}')" id="switch-button-${category.name()}">More</button>
                        </g:if>
                            <li <g:if test="${itemType==currentType}">class="active"</g:if><g:elseif test="${count >=5}">class="collapse-${category.name()}"</g:elseif>><g:link controller="item" action="list"  params="[typeId: itemType.id]">${itemType.name}</g:link></li>
                    </g:each>
                </ul>
            </li>            
        </g:each>
    </ul>
</div>

<script>
    <g:each in="${pegr.ItemTypeCategory}">
        $(".collapse-${it.name()}").hide();
    </g:each>
    function switchItemTypeMenu($category) {
        var $button = $("#switch-button-"+$category);
        if ($button.text() == "Less" ) {
            $(".collapse-" + $category).hide();
            $button.text("More"); 
        } else {
            $(".collapse-" + $category).show();
            $button.text("Less"); 
        }
    }
</script>