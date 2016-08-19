<h3>Mapping Statistics</h3>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#alignment"> Number of Tags</a></li>
    <li><a data-toggle="tab" href="#alignment2"> Percentage of Tags</a></li>
    <li><a data-toggle="tab" href="#alignment3"> Additional Information</a></li>
</ul>

<div class="tab-content">
    <div id="alignment" class="tab-pane in active">
        <g:render template="/report/alignmentTable" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="alignment2" class="tab-pane fade">
        <g:render template="/report/alignmentTable2" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="alignment3" class="tab-pane fade">
        <g:render template="/report/alignmentTable3" model="['sampleList':sampleDTOs]" />
    </div>
</div>
<h3>Downstream Analysis</h3>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#peak">Peak Statistics</a></li>
</ul>

<div class="tab-content">
    <div id="peak" class="tab-pane fade in active">
        <g:render template="/report/peakTable" model="['sampleList':sampleDTOs]" />
    </div>
</div>
<ul>
    <li>
        <h4>MEME Motifs</h4>
        <g:each in="${sampleDTOs}" var="sample">
            <g:each in="${sample.experiments}" var="experiment">
                <g:each in="${experiment.alignments}" var="alignment">
                    <h5>Sample <u>${sample.id}</u> &nbsp; Run <u>${experiment.runId}</u> &nbsp; Genome <u>${alignment.genome}</u> &nbsp; Target <u>${sample.target}</u> 
                    </h5>
                    <table class="table table-bordered meme-table">                        
                        <thead>
                            <tr>
                                <th rowspan="2">ID</th>
                                <th colspan="4" class="text-center"><a href="${alignment.memeFig}" target="_blank">Meme <span class="glyphicon glyphicon-picture"></span></a><span class="meme-url" hidden="hidden">${alignment.memeFile}</span></th>
                                <th rowspan="2">Four-Color</th>
                                <th rowspan="2">Composite</th>
                            </tr>
                            <tr>
                                <th>Logo</th>
                                <th>E-value</th>
                                <th>Sites</th>
                                <th>Width</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${alignment.fourColor}" var="fourColor" status="n">
                                <tr>
                                    <td class="meme-id"></td>
                                    <td class="meme-fig"></td>
                                    <td class="meme-evalue"></td>
                                    <td class="meme-sites"></td>
                                    <td class="meme-width"></td>
                                    <td><a href="${fourColor}" target="_blank"><span class="glyphicon glyphicon-picture"></span></a></td>
                                    <td><g:link action="composite" params="[url: alignment.composite[n]]" target="_blank"><span class="glyphicon glyphicon-picture"></span></g:link></td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>                        
                </g:each>
            </g:each>
        </g:each>
    </li>
</ul>
<script>
    $(function() {
        $(".meme-table").each( function() {
            var memeTable = $(this);
            var memeUrl = $(this).find(".meme-url").text();
            $.ajax({ 
                url: "/pegr/report/fetchMemeDataAjax?url=" + memeUrl,
                success: 
                    function(result) {
                        memeTable.find(".meme-id").each(function(index, memeId) {
                            $(memeId).html(result[index].id);
                        });
                        memeTable.find(".meme-fig").each(function(index, memeFig) {
                            make_motif(memeFig, result[index]);
                        });
                        memeTable.find(".meme-evalue").each(function(index, memeEvalue) {
                            $(memeEvalue).html(result[index].evalue);
                        });
                        memeTable.find(".meme-sites").each(function(index, memeSites) {
                            $(memeSites).html(result[index].nsites);
                        });
                        memeTable.find(".meme-width").each(function(index, memeWidth) {
                            $(memeWidth).html(result[index].len);
                        });
                    }
            });  
            
        });        
    });    
</script>