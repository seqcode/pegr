<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'tissueInstance.label', default: 'Tissue')}" />
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
        <div>                    
            <button data-toggle="collapse" data-target="#merge"  aria-expanded="false" class="btn btn-default">Merge Tissues <span class="expand_caret caret"></span></button>
            <div id="merge" class="collapse well">
                <p>Please provide tissue names below. Multiple tissues can be provided in the "From" field and should be delimited by comma ",".</p>
                <g:form action="mergeTissues">
                <div class="form-group">
                    <label>From</label>
                    <input name="fromTissueNamesStr" class="form-control">
                </div>
                <div class="form-group">
                    <label>To</label>
                    <input name="toTissueName" class="form-control">
                </div>
                <g:submitButton name="submit" value="Merge" class="edit"></g:submitButton>
                </g:form>
            </div>
        </div>
        <div id="list-tissue" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${tissueInstanceList}" />

            <div class="pagination">
                <g:paginate total="${tissueCount ?: 0}" params="${params}"/>
            </div>
        </div>
    </body>
</html>