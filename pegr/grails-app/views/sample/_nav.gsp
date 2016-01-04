<div class="navbar-header" id="sample-nav-header">
    <h3>Sample
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sample-nav">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button></h3>
</div>

<div class="collapse navbar-collapse" id="sample-nav">
    <div class="panel-heading"></div>
    <ul class="nav nav-stacked">
        <li id="nav-sample-overview"><g:link action="show" id="$sampleId">Overview</g:link></li>
        <li id="nav-sample-cellsource"><g:link action="showCellSource" params="[sampleId:sampleId]">Cell Source</g:link></li>
        <li id="nav-sample-antibody"><g:link>Antibody &amp; Target</g:link></li>
        <li id="nav-sample-protocols"><g:link action="protocols" id="$sampleId">Protocols</g:link>
        <li id="nav-sample-replicates"><g:link>Replicates</g:link>
        <li id="nav-sample-replicates"><g:link>Sequencing &amp; Alignment</g:link>
        <li id="nav-sample-projects"><g:link>Related Projects</g:link>
    </ul>
</div>


