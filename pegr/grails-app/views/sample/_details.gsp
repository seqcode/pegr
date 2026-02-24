<!-- this template is called from show.gsp and shows the top panel on the sample/show page. It has four tables, including cell source, antibody, protocol and other. -->
<div class="row">
    <div id="cellSource" class="col-sm-3">
        <h4>Cell Source 
            <g:if test="${sampleEditAuth}">
                <a href="#" class="edit" data-toggle="modal" data-target="#editCellSourceModal">Edit</a>
                <div id="editCellSourceModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Please select</h4>
                            </div>
                            <div class="modal-body">
                                <ul>
                                    <g:if test="${sample.cellSource}">
                                        <li>- <g:link action="editCellSource" params="[sampleId:sample.id, cellSourceId:sample.cellSource.id]">Edit this cell source.</g:link></li>
                                        <li>- <g:link action="searchCellSource" params="[sampleId:sample.id]">Change to an existing cell source.</g:link></li>
                                        </g:if>
                                    <g:else>
                                        <li>- <g:link action="searchCellSource" params="[sampleId:sample.id]">Add an existing cell source.</g:link></li>
                                    </g:else>
                                    <li>- <g:link action="editCellSource" params="[sampleId:sample.id]">Create a new cell source.</g:link></li>
                                </ul>
                            </div>
                        </div>
                  </div>
                </div>
            </g:if>
        </h4>
        <table class="table table-bordered">
            <tbody>
            <tr><td>Strain</td><td> ${sample.cellSource?.strain?.name}</td></tr>
            <tr><td>Species</td><td> ${sample.cellSource?.strain?.species}</td></tr>
            <g:if test="${sample.cellSource?.strain?.parent}">
                <tr><td>Parent Strain</td><td> ${sample.cellSource?.strain?.parent}</td></tr>
            </g:if>
            <tr><td>Genotype</td><td> ${sample.cellSource?.strain?.genotype}</td></tr>
            <g:if test="${sample.cellSource?.strain?.geneticModification}">
                <tr><td>Genetic Modifications</td><td> ${sample.cellSource?.strain?.geneticModification}</td></tr>
            </g:if>          

            <g:if test="${sample.cellSource?.sex}">
            <tr><td>Sex</td><td>${sample.cellSource.sex}</td></tr>
            </g:if>

            <g:if test="${sample.cellSource?.age}">
            <tr><td>Age</td><td>${sample.cellSource.age}</td></tr>
            </g:if>

            <g:if test="${sample.cellSource?.tissue}">
            <tr><td>Tissue</td><td>${sample.cellSource.tissue}</td></tr>
            </g:if>

            <g:if test="${sample.cellSource?.histology}">
            <tr><td>Histology</td><td>${sample.cellSource.histology}</td></tr>
            </g:if>

            <tr><td>Provider</td><td> 
            <g:if test="${sample.cellSource?.providerUser}">
               ${sample.cellSource.providerUser}
            </g:if>
            <g:if test="${sample.cellSource?.providerLab}">
                , ${sample.cellSource.providerLab}
            </g:if>
            </td></tr>

            <g:if test="${sample.cellSource?.biologicalSourceId}">
            <tr><td>Biological Source ID</td><td> ${sample.cellSource.biologicalSourceId}</td></tr>
            </g:if>

            <g:if test="${sample?.spikeInCellSource}">
            <tr><td>Spike In Cell Source</td><td> ${sample?.spikeInCellSource?.encodeAsHTML()}</td></tr>
            </g:if>
            </tbody>
        </table>
    </div>
    <div id="antibody" class="col-sm-3">
        <h4>Antibody 
            <g:if test="${sampleEditAuth}">
                <button type="button" class="edit" data-toggle="modal" data-target="#editAntibodyModal">Edit</button>
                <div id="editAntibodyModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Please select</h4>
                            </div>
                            <div class="modal-body">
                                <ul>
                                    <li>- <g:link action="editAntibody" params="[sampleId:sample.id, antibodyId:sample.antibody.id]">Edit this antibody.</g:link></li>
                                    <li>- <g:link action="searchAntibody" params="[sampleId:sample.id]">Search existing antibody through barcode.</g:link></li>
                                </ul>
                            </div>
                        </div>
                  </div>
                </div>
            </g:if>
        </h4>
        <table class="table table-bordered">
            <tbody>
            <g:if test="${sample.antibody?.company}">
            <tr><td>Company</td><td> ${sample.antibody?.company}</td></tr>
            </g:if>

            <g:if test="${sample.antibody?.catalogNumber}">
            <tr><td>Catalog Number</td><td> ${sample.antibody.catalogNumber}</td></tr>
            </g:if>

            <g:if test="${sample.antibody?.lotNumber}">
            <tr><td>Lot Number</td><td>${sample.antibody.lotNumber}</td></tr>
            </g:if>

            <g:if test="${sample.antibody?.abHost}">
            <tr><td>Antibody Host</td><td> ${sample.antibody?.abHost}</td></tr>
            </g:if>

            <g:if test="${sample.antibody?.immunogene}">
            <tr><td>Immunogene</td><td> ${sample.antibody.immunogene}</td></tr>
            </g:if>

            <g:if test="${sample.antibody?.clonal}">
            <tr><td>Mono/Poly Clonal</td><td> ${sample.antibody.clonal}</td></tr>
            </g:if>

            <g:if test="${sample.antibody?.igType}">
            <tr><td>Ig Type</td><td> ${sample.antibody?.igType}</td></tr>
            </g:if>  
            
            <g:if test="${sample.antibody?.concentration}">
            <tr><td>Concentration (ug/ul)</td><td> ${sample.antibody?.concentration}</td></tr>
            </g:if>

            <g:if test="${sample.antibody?.externalId}">
            <tr><td>External ID</td><td> ${sample.antibody.externalId}</td></tr>
            </g:if>  

            <g:if test="${notes?.containsKey('Volume Sent (ul)')}">
            <tr><td>Volume Sent (ul)</td><td> ${notes['Volume Sent (ul)']}</td></tr>
            </g:if> 
            
            <g:if test="${notes?.containsKey('Usage Per ChIP (ug)')}">
            <tr><td>Usage Per ChIP (ug)</td><td> ${notes['Usage Per ChIP (ug)']}</td></tr>
            </g:if> 
            
            <g:if test="${notes?.containsKey('Usage Per ChIP (ul)')}">
            <tr><td>Usage Per ChIP (ul)</td><td> ${notes['Usage Per ChIP (ul)']}</td></tr>
            </g:if> 
                   
            <g:if test="${sample.antibody?.note}">
            <tr><td>Notes</td><td> ${sample.antibody?.note}</td></tr>
            </g:if>
            </tbody>
        </table>
        <h4>Target <g:if test="${sampleEditAuth}"><g:link action="editTarget" params="[sampleId: sample.id]" class="edit">Edit</g:link></g:if>
        </h4>
        <table class="table table-bordered">
            <tbody>
            <tr><td>Target</td><td> ${sample.target?.name}</td></tr>
            <g:if test="${sample.target?.targetType}">
            <tr><td>Type </td><td>${sample.target.targetType}</td></tr>
            </g:if>
            <g:if test="${sample.target?.cTermTag}">
            <tr><td>C-Tag </td><td>${sample.target.cTermTag}</td></tr>
            </g:if>
            <g:if test="${sample.target?.nTermTag}">
            <tr><td>N-Tag </td><td>${sample.target.nTermTag}</td></tr>
            </g:if>
            <g:if test="${sample.target?.note}">
            <tr><td>Target Notes </td><td>${sample.target.note}</td></tr>
            </g:if>
            </tbody></table>
    </div>

    <div id="protocol" class="col-sm-3">
        <h4>Protocol <g:if test="${sampleEditAuth}"><g:link action="editProtocol" params="[sampleId: sample.id]" class="edit">Edit</g:link></g:if></h4>   
        <table class="table table-bordered">
            <tbody>
            <tr><td>Assay</td><td> ${sample.assay}</td></tr>
            
            <tr><td>Growth Media</td><td> ${sample.growthMedia}	</td></tr>
            
            <tr><td>Treatments</td><td>
                <g:each in="${sample?.treatments}" var="c">
                    ${c}
                </g:each>	
            </td></tr>

            <g:if test="${sample?.prtclInstSummary}">
                <g:if test="${notes['Resin']}">
                <tr><td>Resin</td><td> ${notes['Resin']}</td></tr>
                </g:if>
                <g:if test="${notes['PCR Cycle']}">
                <tr><td>PCR Cycle</td><td> ${notes['PCR Cycle']}</td></tr>
                </g:if>
                <tr><td>Technician</td><td> ${sample?.prtclInstSummary?.user?.fullName}</td></tr>
                <tr><td>Date</td><td> <g:formatDate format="yyyy-MM-dd" date="${sample?.prtclInstSummary?.endTime}"/></td></tr>
            </g:if>
            </tbody>
        </table>
        
        <g:if test="${protocols && protocols.size()>0}">
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Details</button>
            <ul class="dropdown-menu" class="list-group" style="padding:0">
                <g:each in="${protocols}">
                    <li class="list-group-item">
                        <g:if test="${sampleEditAuth}">
                            <h5><g:link controller="item" action="show" id="${it.item?.id}" style="color: #3f7fc0;; padding:0;">${it.item?.type?.name}</g:link></h5>
                            <ul>
                                <g:each in="${it.protocolList}">
                                    <li><g:link controller="protocolInstanceBag" action="showInstance" id="${it.id}" style="color: #3f7fc0;">${it.protocol}</g:link>
                                    <g:link controller="protocolInstanceBag" action="renderFile" params="[protocolId: it.protocol?.id]" target="_blank" style="color: #3f7fc0;"><span class="glyphicon glyphicon-file"></span></g:link></li>
                                </g:each>
                            </ul>
                        </g:if>
                        <g:else>
                            <h5>${it.item?.type?.name}</h5>                          
                            <ul>
                                <g:each in="${it.protocolList}">
                                    <li>${it.protocol}
                                        <g:link controller="protocolInstanceBag" action="renderFile" params="[protocolId: it.protocol?.id]" target="_blank"><span class="glyphicon glyphicon-file"></span></g:link></li>
                                </g:each>
                            </ul>
                        </g:else>
                    </li>
                </g:each>
            </ul>
        </div>
        </g:if>
    </div>     

    <div id="other" class="col-sm-3">      
        <h4>Other
            <g:if test="${sampleEditAuth}">
                <g:link controller="sample" action="editOther" params="[sampleId:sample?.id]" class="edit">Edit</g:link>
            </g:if>
        </h4>
        <table class="table table-bordered">
            <tbody>
            <tr><td>Sample natural ID</td><td> ${sample.naturalId}</td></tr>
            <tr><td>Index</td><td> ${sample.sequenceIndicesString} (${sample.sequenceIndicesIdString})</td></tr>

            <tr><td>Chromatin (ug)</td><td> <g:if test="${sample?.chromosomeAmount}">${sample.chromosomeAmount}</g:if></td></tr>

            <tr><td>Avail. cell# per aliquot (M)</td><td> <g:if test="${sample?.cellNumber}">${sample.cellNumber}</g:if></td></tr>

            <tr><td>Volume per aliquot (ul)</td><td> <g:if test="${sample?.volume}">${sample.volume}</g:if></td></tr>

            <tr><td>Requested tags (M)</td><td> <g:if test="${sample?.requestedTagNumber}">${sample.requestedTagNumber}</g:if></td></tr>

            <tr><td>Requested genomes</td><td> ${sample?.requestedGenomes}</td></tr>
            
            <tr><td>Send data to</td><td> ${sample?.sendDataTo}</td></tr>
            
            <g:if test="${sample?.publicationReference}">
            <tr><td>Publication reference</td><td> ${sample?.publicationReference}</td></tr>
            </g:if>
            
            <g:if test="${sample.note}">
            <tr><td>Notes</td><td> ${sample.note}</td></tr>
            <tr><td>GEO accession</td><td> ${sample?.geoAccession}</td></tr>
            </g:if>
            </tbody>
        </table>
    </div>  
</div>