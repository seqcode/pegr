<g:if test="${message}">
    <div class='errors'>${message}</div>
</g:if>
<div id="bioRep">
    <h4>Biological Replicates <a href="#" onclick="initialReplicateForm('BIOLOGICAL')" data-toggle="modal" data-target="#addReplicate" class="edit">Add</a></h4>
    <ul>
        <g:each in="${replicates.bioReps}">
            <li>
                <g:link controller="replicate" action="show" id="${it.id}">Biological Replicate Set ${it.id}</g:link>
            </li>
        </g:each>
    </ul>
</div>  
<div id="techRep">
    <h4>Technical Replicates <a href="#" onclick="initialReplicateForm('TECHNICAL')" data-toggle="modal" data-target="#addReplicate" class="edit">Add</a></h4>
    <ul>
        <g:each in="${replicates.techReps}">
            <li>
                <g:link controller="replicate" action="show" id="${it.id}">Technical Replicate Set ${it.id}</g:link>
            </li>                    
        </g:each>
    </ul>
</div> 