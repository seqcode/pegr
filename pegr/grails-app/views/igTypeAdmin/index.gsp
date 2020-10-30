<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'igTypeInstance.label', default: 'IgType')}" />
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
            <button data-toggle="collapse" data-target="#merge"  aria-expanded="false" class="btn btn-default">Merge Ig Types <span class="expand_caret caret"></span></button>
            <div id="merge" class="collapse well">
                <p>Please provide Ig type names below. Multiple Ig types can be provided in the "From" field and should be delimited by comma ",".</p>
                <g:form action="mergeIgTypes">
                <div class="form-group">
                    <label>From</label>
                    <input name="fromIgTypeNamesStr" class="form-control">
                </div>
                <div class="form-group">
                    <label>To</label>
                    <input name="toIgTypeName" class="form-control">
                </div>
                <g:submitButton name="submit" value="Merge" class="edit"></g:submitButton>
                </g:form>
            </div>
        </div>
        <div id="list-igType" class="content scaffold-list" role="main">
            <h3>Ig Type List</h3>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${igTypeInstanceList}" />

            <div class="pagination">
                <g:paginate total="${igTypeCount ?: 0}" params="${params}"/>
            </div>
        </div>
    </body>
</html>