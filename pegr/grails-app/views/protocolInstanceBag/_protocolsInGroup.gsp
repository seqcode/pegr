<h4>Protocol group: ${it.name} </h4>
<ol class="list-group">
<g:each in="${it.protocols}" var="protocol">
    <li class="list-group-item list-group-item-info">${protocol.name}</li>
</g:each>
</ol>
