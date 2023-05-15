<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'speciesInstance.label', default: 'Species')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            <li><g:link action="exportCsv">Export CSV</g:link></li>
		</ul>
        <g:form class="pull-right" style="padding:3px 0px">
            <input name="str">
            <g:submitButton class="edit" name="submit" value="Search"></g:submitButton>
        </g:form>
        <div>                    
            <button data-toggle="collapse" data-target="#merge"  aria-expanded="false" class="btn btn-default">Merge Species <span class="expand_caret caret"></span></button>
            <div id="merge" class="collapse well">
                <p>Please provide species ID's below. Multiple species can be provided in the "From" field and should be delimited by comma ",".</p>
                <g:form action="mergeSpecies">
                <div class="form-group">
                    <label>From</label>
                    <input name="fromSpeciesIdsStr" class="form-control">
                </div>
                <div class="form-group">
                    <label>To</label>
                    <input name="toSpeciesId" class="form-control">
                </div>
                <g:submitButton name="submit" value="Merge" class="edit"></g:submitButton>
                </g:form>
            </div>
        </div>
        <div id="list-species" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table class="table">
                <thead>
					<tr>
                        <g:sortableColumn property="id" title="ID" />
						<g:sortableColumn property="genusName" title="Genus" />
                        <g:sortableColumn property="name" title="Species" />
                        <th>Note</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${speciesInstanceList}" status="i" var="species">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><g:link action="show" id="${species.id}">${species.id}</g:link></td>
                        <td>${species.genusName}</td>
                        <td>${species.name}</td>
						<td>${species.note}</td>
						<td>${species.status}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
            <div class="pagination">
                <g:paginate total="${speciesCount ?: 0}" params="${params}"/>
            </div>
        </div>
    </body>
</html>