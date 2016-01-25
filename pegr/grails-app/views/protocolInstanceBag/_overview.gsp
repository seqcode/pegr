<h4>
    <g:link action="showBag" id="${it.id}">${it.name} </g:link> 
    <small><span class="label label-default">${it.status}</span></small> 
</h4>
<p>Start Time: <g:formatDate format="yyyy-MM-dd" date="${it.startTime}"/>
    <g:if test="${it.endTime}">, End Time: <g:formatDate format="yyyy-MM-dd" date="${it.endTime}"/></g:if></p>