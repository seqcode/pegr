<g:if test="${imageMap}">
<h3>Images</h3>
<table class="table table-bordered" id="project-table">
    <thead>
        <tr>
            <th>Sonication Images</th>
            <th>Gel Images</th>
        <tr>
    </thead>
    <tbody>
        <tr>
            <td class="col-sm-6">
                <ul>
                <g:each in="${imageMap?.sonication}" var="filepath">
                    <li>
                        <img src='${createLink(controller: "file", action: "displayImage", params:[filepath:filepath, relative:true])}' height="200"/>
                    </li>
                </g:each>
                </ul>
            </td>
            <td class="col-sm-6">
                <ul>
                <g:each in="${imageMap?.gel}" var="filepath">
                    <li>
                        <img src='${createLink(controller: "file", action: "displayImage", params:[filepath:filepath,relative:true])}' height="200"/>
                    </li>
                </g:each>
                </ul>
            </td>
        </tr>
    </tbody>
</table>
</g:if>