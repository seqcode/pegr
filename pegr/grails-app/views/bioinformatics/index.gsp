<sec:ifAnyGranted roles="ROLE_ADMIN">
    <div class="row">
        <g:link controller="genomeAdmin" class="edit pull-right">Manage</g:link>
    </div>
</sec:ifAnyGranted>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <g:sortableColumn property="species" title="Species"></g:sortableColumn>
                <g:sortableColumn property="name" title="Genome Build"></g:sortableColumn>
                <th>Link</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${genomes}" var="genome">
                <tr>
                    <td><i>${genome.species}</i></td>
                    <td>${genome.name}</td>
                    <td><a href="${genome.url}">${genome.url}</a></td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>