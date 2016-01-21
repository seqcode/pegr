<h4>
    <g:link action="showBag" id="${it.id}">${it.protocolsDisplay} </g:link> 
    <small><span class="label label-default">${it.status}</span></small> 
</h4>
<p>Create: <g:formatDate format="yyyy-MM-dd" date="${it.dateCreated}"/>, Updated: <g:formatDate format="yyyy-MM-dd" date="${it.lastUpdated}"/></p>