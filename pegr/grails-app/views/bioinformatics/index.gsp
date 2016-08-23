<sec:ifAnyGranted roles="ROLE_ADMIN">
    <div class="row">
        <g:link controller="genomeAdmin" class="edit pull-right">Manage</g:link>
    </div>
</sec:ifAnyGranted>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <g:sortableColumn property="name" title="Name"></g:sortableColumn>
                <g:sortableColumn property="species" title="Species"></g:sortableColumn>
                <th>Version</th>
                <th>Link</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${genomes}" var="genome">
                <tr>
                    <td>${genome.name}</td>
                    <td>${genome.species}</td>
                    <td>${genome.genomeVersion}</td>
                    <td><a href="${genome.url}">${genome.url}</a></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>