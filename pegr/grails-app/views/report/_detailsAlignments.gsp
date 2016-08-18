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
                        <g:if test="${alignment.memeFig}">
                            <a href="${alignment.memeFig}" target="_blank"><span class="glyphicon glyphicon-picture"></span>Meme</a>
                        </g:if>   
                    </h5>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>MEME</th>
                                <th>Four-Color</th>
                                <th>Composite</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${}" status="n">
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                    
                    <div>
                        <iframe src="/pegr/report/meme?url=${alignment.memeFile}" width=800 height=200 scrolling=no frameBorder=0></iframe>   
                        <g:each in="${alignment.fourColor}" var="fourColor">
                            <a href="${fourColor}" target="_blank"><span class="glyphicon glyphicon-picture"></span>4Color</a>
                        </g:each>
                        <g:each in="${alignment.composite}" var="composite">
                            <g:link action="composite" params="[url: composite]" target="_blank"><span class="glyphicon glyphicon-picture"></span>Composite</g:link>
                        </g:each>
                    </div>
                </g:each>
            </g:each>
        </g:each>
    </li>
</ul>