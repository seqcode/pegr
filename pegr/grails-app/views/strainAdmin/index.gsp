
<%@ page import="pegr.Strain" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}" />
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
            <button data-toggle="collapse" data-target="#merge"  aria-expanded="false" class="btn btn-default">Merge Strain <span class="expand_caret caret"></span></button>
            <div id="merge" class="collapse well">
                <p>Please provide strain ID's below. Multiple strains can be provided in the "From" field and should be delimited by comma ",".</p>
                <g:form action="mergeStrains">
                <div class="form-group">
                    <label>From</label>
                    <input name="fromStrainIdsStr" class="form-control">
                </div>
                <div class="form-group">
                    <label>To</label>
                    <input name="toStrainId" class="form-control">
                </div>
                <g:submitButton name="submit" value="Merge" class="edit"></g:submitButton>
                </g:form>
            </div>
        </div>
		<div id="list-strain" class="content scaffold-list" role="main">
			<h3><g:message code="default.list.label" args="[entityName]" /></h3>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="table-responsive">
			<table class="table">
			<thead>
					<tr>
                        <g:sortableColumn property="id" title="ID" />
						<g:sortableColumn property="name" title="Name" />
                        <g:sortableColumn property="species.genusName" title="Species" />
                        <g:sortableColumn property="parent" title="Parent" />
                        <g:sortableColumn property="genotype" title="Genotype" />
                        <g:sortableColumn property="sourceLab" title="Source Lab" />
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${strainInstanceList}" status="i" var="strain">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><g:link action="show" id="${strain.id}">${fieldValue(bean: strain, field: "id")}</g:link></td>
                        <td>${fieldValue(bean: strain, field: "name")}</td>
                        <td>${fieldValue(bean: strain, field: "species")}</td>
						<td>${fieldValue(bean: strain, field: "parent")}</td>
				        <td>${fieldValue(bean: strain, field: "genotype")}</td>
						<td>${fieldValue(bean: strain, field: "sourceLab")}</td>
						<td>${strain.status}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<div class="pagination">
				<g:paginate total="${strainCount ?: 0}" params="${params}"/>
			</div>
            <span class="pagination pull-right">
                <g:link params="[max:25]">25</g:link>
                <g:link params="[max:100]">100</g:link>
                / page
            </span>
		</div>
	</body>
</html>
