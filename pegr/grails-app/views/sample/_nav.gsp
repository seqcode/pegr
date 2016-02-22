<div class="navbar-header" id="sample-nav-header">
    <h3>Sample ${sampleId}
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sample-nav">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button></h3>
</div>

<div class="collapse navbar-collapse" id="sample-nav">
    <div class="panel-heading"></div>
    <ul class="nav nav-stacked">
        <li id="nav-sample-summary"><g:link controller="sample" action="show" id="$sampleId">Summary</g:link></li>
        <li id="nav-sample-cellculture"><g:link controller="cellSource" action="showCellSourceForSample" params="[sampleId:sampleId]">Cell Source</g:link></li>
        <li id="nav-sample-antibody"><g:link>Antibody &amp; Target</g:link></li>
        <li id="nav-sample-protocols"><g:link controller="protocol" action="showProtocolsForSample" params="[sampleId:sampleId]">Protocols</g:link>
        <li id="nav-sample-replicates"><g:link>Replicates</g:link>
        <li id="nav-sample-replicates"><g:link>Sequencing &amp; Alignment</g:link>
        <li id="nav-sample-projects"><g:link>Related Projects</g:link>
    </ul>
</div>


