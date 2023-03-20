<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="admin" />
        <g:set var="entityName" value="${message(code: 'targetInstance.label', default: 'Target')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <ul class="nav nav-pills">
			<li><a class="home" href="${createLink(uri: '/admin/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            <li><g:link action="exportCsv">Export CSV</g:link></li>
		</ul>
        <g:form action="mergeSearch" class="pull-right" style="padding:3px 0px">
            <input name="str">
            <g:submitButton class="edit" name="submit" value="Search"></g:submitButton>
        </g:form>
        <div id="list-target" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table class="table">
              <thead>
                <tr>
                  <g:sortableColumn property="id" title="ID" />
                  <g:sortableColumn property="name" title="Name" />
                  <g:sortableColumn property="nTermTag" title="N Term Tag" />
                  <g:sortableColumn property="cTermTag" title="C Term Tag" />
                  <th>Note</th>
                  <g:sortableColumn property="targetType" title="Target Type" />
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
              <g:each in="${targetInstanceList}" status="i" var="target">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                  <td><g:link action="show" id="${target.id}">${target.id}</g:link></td>
                  <td>${fieldValue(bean: target, field: "name")}</td>
                  <td>${fieldValue(bean: target, field: "nTermTag")}</td>
                  <td>${fieldValue(bean: target, field: "cTermTag")}</td>
                  <td>${fieldValue(bean: target, field: "note")}</td>
                  <td>${fieldValue(bean: target, field: "targetType")}</td>
                  <td>${fieldValue(bean: target, field: "status")}</td>
                </tr>
              </g:each>    
              </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${targetCount ?: 0}" params="${params}"/>
            </div>
        </div>
    </body>
</html>