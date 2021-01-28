<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'referenceFeatureInstance.label', default: 'Reference Feature')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
        <g:form class="pull-right" style="padding:3px 0px">
            <input name="str">
            <g:submitButton class="edit" name="submit" value="Search"></g:submitButton>
        </g:form>
        <div id="list-referenceFeature" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                     <tr>
                        <g:sortableColumn property="filename" title="Filename"></g:sortableColumn>
                        <g:sortableColumn property="genome.name" title="Genome Build"></g:sortableColumn>
                        <th>Summary</th>
                        <th>Link</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${referenceFeatureInstanceList}" var="feature" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td><g:link method="GET" action="show" id="${feature.id}">${feature.filename}</g:link></td>
                            <td>${feature.genome}</td>
                            <td>${feature.summary}</td>
                            <td>${feature.url}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${referenceFeatureCount ?: 0}" params="${params}"/>
            </div>
        </div>
    </body>
</html>