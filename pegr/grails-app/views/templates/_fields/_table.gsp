<!-- to use this template, add <f:table collection="COLLECTION_NAME"/> -->
<table>
    <thead>
         <tr>
            <g:each in="${domainProperties}" var="p" status="i">
                <g:sortableColumn property="${p.property}" title="${p.label}" />
            </g:each>
        </tr>
    </thead>
    <tbody>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <g:each in="${domainProperties}" var="p" status="j">
                    <g:if test="${p.property=='name'}">
                        <td><g:link method="GET" action="show" id="${bean.id}"><f:display bean="${bean}" property="${p.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/></g:link></td>
                    </g:if>
                    <g:else>
                        <td><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
                    </g:else>
                </g:each>
            </tr>
        </g:each>
    </tbody>
</table>