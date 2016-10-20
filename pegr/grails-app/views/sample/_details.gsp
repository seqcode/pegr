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
        <ul>
            <li>Strain: ${sample.cellSource?.strain?.name}</li>
            <li>Species: ${sample.cellSource?.strain?.species}</li>
            <g:if test="${sample.cellSource?.strain?.parent}">
                <li>Parent Strain: ${sample.cellSource?.strain?.parent}</li>
            </g:if>
            <li>Genotype: ${sample.cellSource?.strain?.genotype}</li>
            <g:if test="${sample.cellSource?.strain?.geneticModification}">
                <li>Genetic Modifications: ${sample.cellSource?.strain?.geneticModification}</li>
            </g:if>          

            <g:if test="${sample.cellSource?.sex}">
            <li>Sex:${sample.cellSource.sex}	</li>
            </g:if>

            <g:if test="${sample.cellSource?.age}">
            <li>Age:${sample.cellSource.age}	</li>
            </g:if>

            <g:if test="${sample.cellSource?.tissue}">
            <li>Tissue:${sample.cellSource.tissue}	</li>
            </g:if>

            <g:if test="${sample.cellSource?.histology}">
            <li>Histology:${sample.cellSource.histology}	</li>
            </g:if>

            <li>Provider: 
            <g:if test="${sample.cellSource?.providerUser}">
               ${sample.cellSource.providerUser}
            </g:if>
            <g:if test="${sample.cellSource?.providerLab}">
                , ${sample.cellSource.providerLab}
            </g:if>
            </li>

            <g:if test="${sample.cellSource?.biologicalSourceId}">
            <li>Biological Source ID: ${sample.cellSource.biologicalSourceId}</li>
            </g:if>

            <g:if test="${sample?.spikeInCellSource}">
            Spike In Cell Source: ${sample?.spikeInCellSource?.encodeAsHTML()}
                </li>
            </g:if>
        </ul>
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
                                    <g:if test="${sample.antibody}">
                                        <li>- <g:link action="editAntibody" params="[sampleId:sample.id, antibodyId:sample.antibody.id]">Edit this antibody.</g:link></li>
                                        <li>- <g:link action="previewAntibody" params="[sampleId:sample.id]">Change to an existing antibody.</g:link></li>
                                        </g:if>
                                    <g:else>
                                        <li>- <g:link action="previewAntibody" params="[sampleId:sample.id]">Add an existing antibody.</g:link></li>
                                    </g:else>
                                    <li>- <g:link action="editAntibody" params="[sampleId:sample.id]">Create a new antibody.</g:link></li>
                                </ul>
                            </div>
                        </div>
                  </div>
                </div>
            </g:if>
        </h4>
        <ul>
            <g:if test="${sample.antibody?.company}">
            <li>Company: ${sample.antibody?.company}</li>
            </g:if>

            <g:if test="${sample.antibody?.catalogNumber}">
            <li>Catalog Number: ${sample.antibody.catalogNumber}</li>
            </g:if>

            <g:if test="${sample.antibody?.lotNumber}">
            <li>Lot Number:${sample.antibody.lotNumber}</li>
            </g:if>

            <g:if test="${sample.antibody?.abHost}">
            <li>Antibody Host: ${sample.antibody?.abHost}</li>
            </g:if>

            <g:if test="${sample.antibody?.immunogene}">
            <li>Immunogene: ${sample.antibody.immunogene}</li>
            </g:if>

            <g:if test="${sample.antibody?.clonal}">
            <li>Mono/Poly Clonal: ${sample.antibody.clonal}</li>
            </g:if>

            <g:if test="${sample.antibody?.igType}">
            <li>Ig Type: ${sample.antibody?.igType}</li>
            </g:if>  

            <g:if test="${sample.antibody?.externalId}">
            <li>External ID: ${sample.antibody.externalId}</li>
            </g:if>  

            <g:if test="${notes?.containsKey('Volume Sent (ul)')}">
            <li>Volume Sent (ul): ${notes['Volume Sent (ul)']}</li>
            </g:if> 
            
            <g:if test="${notes?.containsKey('Usage Per ChIP (ug)')}">
            <li>Usage Per ChIP (ug): ${notes['Usage Per ChIP (ug)']}</li>
            </g:if> 
            
            <g:if test="${notes?.containsKey('Usage Per ChIP (ul)')}">
            <li>Usage Per ChIP (ul): ${notes['Usage Per ChIP (ul)']}</li>
            </g:if> 
                   
            <g:if test="${sample.antibody?.note}">
            <li>Notes: ${sample.antibody?.note}</li>
            </g:if>
        </ul>
        <h4>Target <g:if test="${sampleEditAuth}"><g:link action="editTarget" params="[sampleId: sample.id]" class="edit">Edit</g:link></g:if>
        </h4>
        <ul>
            <li>Target: ${sample.target?.name}</li>
            <g:if test="${sample.target?.targetType}">
            <li>Type: ${sample.target.targetType}</li>
            </g:if>
            <g:if test="${sample.target?.cTermTag}">
            <li>C-Tag: ${sample.target.cTermTag}</li>
            </g:if>
            <g:if test="${sample.target?.nTermTag}">
            <li>N-Tag: ${sample.target.nTermTag}</li>
            </g:if>
            <g:if test="${sample.target?.note}">
            <li>Target Notes: ${sample.target.note}</li>
            </g:if>
        </ul>
    </div>

    <div id="protocol" class="col-sm-3">
        <h4>Protocol <g:if test="${sampleEditAuth}"><g:link action="editProtocol" params="[sampleId: sample.id]" class="edit">Edit</g:link></g:if></h4>   
        <ul>
            <li>Assay: ${sample.assay}</li>
            
            <li>Growth Media: ${sample.growthMedia}	</li>
            
            <li>Treatments:
                <g:each in="${sample?.treatments}" var="c">
                    ${c}
                </g:each>	
            </li>

            <g:if test="${sample?.prtclInstSummary}">
                <g:if test="${notes['Resin']}">
                <li>Resin: ${notes['Resin']}</li>
                </g:if>
                <g:if test="${notes['PCR Cycle']}">
                <li>PCR Cycle: ${notes['PCR Cycle']}</li>
                </g:if>
                <li>Technician: ${sample?.prtclInstSummary?.user?.fullName}</li>
                <li>Date: <g:formatDate format="yyyy-MM-dd" date="${sample?.prtclInstSummary?.endTime}"/></li>
            </g:if>
        </ul>
        
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
        <ul>
            <li>Index: ${sample.sequenceIndicesString} (${sample.sequenceIndicesIdString})</li>

            <li>Chromosome (ug): <g:if test="${sample?.chromosomeAmount}">${sample.chromosomeAmount}</g:if></li>

            <li>Avail. Cell# per aliquot (M): <g:if test="${sample?.cellNumber}">${sample.cellNumber}</g:if></li>

            <li>Volume per aliquot (ul): <g:if test="${sample?.volume}">${sample.volume}</g:if></li>

            <li>Requested Tags (M): <g:if test="${sample?.requestedTagNumber}">${sample.requestedTagNumber}</g:if></li>

            <li>Requested genomes: ${sample?.requestedGenomes}</li>
            
            <li>Send data to: ${sample?.sendDataTo}</li>
            
            <g:if test="${sample?.publicationReference}">
            <li>Publication Reference: ${sample.publicationReference}</li>
            </g:if>
            
            <g:if test="${sample.note}">
            <li>Notes: ${sample.note}</li>
            </g:if>
        </ul>
    </div>  
</div>