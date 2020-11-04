<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'genomeInstance.label', default: 'Genome')}" />
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
            <button data-toggle="collapse" data-target="#merge"  aria-expanded="false" class="btn btn-default">Merge Genomes <span class="expand_caret caret"></span></button>
            <div id="merge" class="collapse well">
                <p>Please provide genome names below. Multiple genomes can be provided in the "From" field and should be delimited by comma ",".</p>
                <g:form action="mergeGenomes">
                <div class="form-group">
                    <label>From</label>
                    <input name="fromGenomeNamesStr" class="form-control">
                </div>
                <div class="form-group">
                    <label>To</label>
                    <input name="toGenomeName" class="form-control">
                </div>
                <g:submitButton name="submit" value="Merge" class="edit"></g:submitButton>
                </g:form>
            </div>
        </div>
        <div id="list-genome" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${genomeInstanceList}" />

            <div class="pagination">
                <g:paginate total="${genomeCount ?: 0}" params="${params}"/>
            </div>
        </div>
    </body>
</html>