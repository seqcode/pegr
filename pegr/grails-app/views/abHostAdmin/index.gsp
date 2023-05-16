<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'abHostInstance.label', default: 'AbHost')}" />
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
            <button data-toggle="collapse" data-target="#merge"  aria-expanded="false" class="btn btn-default">Merge Ab Hosts <span class="expand_caret caret"></span></button>
            <div id="merge" class="collapse well">
                <p>Please provide Ab host names below. Multiple Ab host names can be provided in the "From" field and should be delimited by comma ",".</p>
                <g:form action="mergeAbHosts">
                <div class="form-group">
                    <label>From</label>
                    <input name="fromAbHostNamesStr" class="form-control">
                </div>
                <div class="form-group">
                    <label>To</label>
                    <input name="toAbHostName" class="form-control">
                </div>
                <g:submitButton name="submit" value="Merge" class="edit"></g:submitButton>
                </g:form>
            </div>
        </div>
        <div id="list-abHost" class="content scaffold-list" role="main">
            <h3>Ab Host List</h3>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${abHostInstanceList}" />

            <div class="pagination">
                <g:paginate total="${abHostCount ?: 0}" params="${params}"/>
            </div>
        </div>
    </body>
</html>