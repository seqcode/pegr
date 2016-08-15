<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <g:sortableColumn property="name" title="Name"></g:sortableColumn>
                <g:sortableColumn property="species" title="Species"></g:sortableColumn>
            </tr>
        </thead>
        <tbody>
            <g:each in="${genomes}" var="genome">
                <tr>
                    <td>${genome.name}</td>
                    <td>${genome.species}</td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>
