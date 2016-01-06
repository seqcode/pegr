
<li class="list-group-item">
     <h4>
        <g:link action="show" id="${userProject.project.id}">Project: ${userProject.project.name}</g:link> 
        <small><span class="label label-default">${userProject.projectRole}</span></small> 
    </h4>
    <p>Created: ${userProject.project.dateCreated}; Updated: ${userProject.project.lastUpdated}. </p>
</li>