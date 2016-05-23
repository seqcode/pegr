<div class="table-responsive">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Genome</th>
                <th>Mapped Read Count</th>
                <th>Uniquely Mapped Count</th>
                <th>Deduplicated Count</th>
                <th>Duplication Level</th>
                <th>Insert Size</th>
                <th>Genome Coverage</th>   
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${alignment.genome}</td>
                <td>${alignment.mappedReads}</td>
                <td>${alignment.uniquelyMappedReads}</td>
                <td>${alignment.dedupUniquelyMappedReads}</td>
                <th>${alignment.seqDuplicationLevel}</th>
                <td>${alignment.avgInsertSize} <g:if test="${alignment.stdDevInsertSize}">&plusmn;${alignment.stdDevInsertSize}</g:if></td>
                <td>${alignment.genomeCoverage}</td>
            </tr>             
            <tr>
                <td colspan="7"></td>
            </tr>
        </tbody>
      </table>
</div>