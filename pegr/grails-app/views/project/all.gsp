<html>
<head>
  <title>My Projects</title>
  <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MEMBER">
    <div>
        <g:link action='index'  class="btn btn-info">My Projects</g:link>
        <g:link action='all'  class="btn btn-info active">All Projects</g:link>
        <g:link action='create'  class="btn btn-info" ><span class="glyphicon glyphicon-plus"></span>Add Project</g:link>
    </div>
    </sec:ifAnyGranted>
    <g:form controller="project" action="search" class="pull-right">
        <input name="str">
        <g:submitButton class="edit" name="submit" value="Search"></g:submitButton>
        <g:submitButton name="merge" value="Merge" class="edit"></g:submitButton>
        <br>Search project name or description
    </g:form>
    <g:render template="overview" model="[projects:projects]" />
    <div class="pagination">
        <g:paginate next="Next" prev="Prev" controller="project" action="all" max="15" total="${totalCount ?: 0}" />
    </div>     
</body>
</html>
