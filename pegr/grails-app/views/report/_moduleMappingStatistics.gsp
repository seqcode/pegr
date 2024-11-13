<h3>Mapping Statistics</h3>
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#alignment1-r1"> Number of Tags (Read 1)</a></li>
    <li><a data-toggle="tab" href="#alignment2-r1"> Percentage of Tags (Read 1)</a></li>
    <li><a data-toggle="tab" href="#alignment1-r2"> Number of Tags (Read 2)</a></li>
    <li><a data-toggle="tab" href="#alignment2-r2"> Percentage of Tags (Read 2)</a></li>
</ul>
<div class="tab-content">
    <div id="alignment1-r1" class="tab-pane in active">
        <g:render template="/report/mappingTable1_R1" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="alignment2-r1" class="tab-pane fade">
        <g:render template="/report/mappingTable2_R1" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="alignment1-r2" class="tab-pane fade">
        <g:render template="/report/mappingTable1_R2" model="['sampleList':sampleDTOs]" />
    </div>
    <div id="alignment2-r2" class="tab-pane fade">
        <g:render template="/report/mappingTable2_R2" model="['sampleList':sampleDTOs]" />
    </div>
</div>