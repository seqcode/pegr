<h3>Samples</h3>
<p>The number of Samples: ${sampleDTOs?.size()}</p>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#sample">Description</a></li>
    <li><a data-toggle="tab" href="#epitope">Epitope Tags &amp; FastQC</a></li>
    <li><a data-toggle="tab" href="#sample-notes">Notes</a></li>
</ul>

<div class="tab-content">
    <div id="sample" class="tab-pane fade in active">
        <g:render template="/report/sampleTable" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="epitope" class="tab-pane fade">
        <g:render template="/report/epitopeTable" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="sample-notes" class="tab-pane fade">
        <g:render template="/report/sampleNotesTable" model="['sampleList':sampleDTOs]" />
    </div>
</div>