<g:each in="${projects}" var="project" status="n">
    <g:if test="${n>0}">, 
    </g:if>
    <g:link controller="project" action="show" id="${project.id}">${project.name}</g:link>                            
</g:each>