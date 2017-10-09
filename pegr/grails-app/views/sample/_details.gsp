    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title"><a data-toggle="collapse" href="#collapse">Cell Source, Antibody, Target, Protocol, Other</a>
                </h4>
            </div>
        </div>
    

        <div id="collapse" class="panel-collapse collapse">
            <table class="table table-responsive">
                <thead>
                    <tr>
                        <th>Cell Source
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
                        </th>

                        <th>Antibody
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
                        </th>

                        <th>Target
                            <g:if test="${sampleEditAuth}"><g:link action="editTarget" params="[sampleId: sample.id]" class="edit">Edit</g:link></g:if>
                        </th>
                        <th>Protocol
                            <g:if test="${sampleEditAuth}"><g:link action="editProtocol" params="[sampleId: sample.id]" class="edit">Edit</g:link></g:if>
                        </th>
                        <th>Other
                            <g:if test="${sampleEditAuth}">
                                <g:link controller="sample" action="editOther" params="[sampleId:sample?.id]" class="edit">Edit</g:link>
                            </g:if>
                        </th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>
                            <ul>
                                <li><b>Strain:</b> ${sample.cellSource?.strain?.name}</li>
                                <li><b>Species:</b> ${sample.cellSource?.strain?.species}</li>
                                <g:if test="${sample.cellSource?.strain?.parent}">
                                    <li><b>Parent Strain:</b> ${sample.cellSource?.strain?.parent}</li>
                                </g:if>
                                <li><b>Genotype: </b>${sample.cellSource?.strain?.genotype}</li>
                                <g:if test="${sample.cellSource?.strain?.geneticModification}">
                                    <li><b>Genetic Modifications: </b>${sample.cellSource?.strain?.geneticModification}</li>
                                </g:if>          

                                <g:if test="${sample.cellSource?.sex}">
                                <li><b>Sex:</b>${sample.cellSource.sex}    </li>
                                 </g:if>

                                <g:if test="${sample.cellSource?.age}">
                                 <li><b>Age:</b>${sample.cellSource.age}    </li>
                                </g:if>

                                 <g:if test="${sample.cellSource?.tissue}">
                                <li><b>Tissue:</b>${sample.cellSource.tissue}  </li>
                                </g:if>

                                <g:if test="${sample.cellSource?.histology}">
                                <li><b>Histology:</b>${sample.cellSource.histology}    </li>
                                </g:if>

                                <li><b>Provider:</b> 
                                <g:if test="${sample.cellSource?.providerUser}">
                                    ${sample.cellSource.providerUser}
                                </g:if>
                                <g:if test="${sample.cellSource?.providerLab}">
                                    , ${sample.cellSource.providerLab}
                                </g:if>
                                </li>

                                <g:if test="${sample.cellSource?.biologicalSourceId}">
                                <li><b>Biological Source ID: </b>${sample.cellSource.biologicalSourceId}</li>
                                </g:if>

                                <g:if test="${sample?.spikeInCellSource}">
                                <b>Spike In Cell Source:</b> ${sample?.spikeInCellSource?.encodeAsHTML()}
                                </li>
                                </g:if>
                            </ul>
                        </td>
                        <td>
                            <ul>
                                <g:if test="${sample.antibody?.company}">
                                <li><b>Company:</b> ${sample.antibody?.company}</li>
                                </g:if>

                                <g:if test="${sample.antibody?.catalogNumber}">
                                <li><b>Catalog Number:</b> ${sample.antibody.catalogNumber}</li>
                                </g:if>

                                <g:if test="${sample.antibody?.lotNumber}">
                                <li><b>Lot Number: </b>${sample.antibody.lotNumber}</li>
                                </g:if>

                                <g:if test="${sample.antibody?.abHost}">
                                <li><b>Antibody Host:</b> ${sample.antibody?.abHost}</li>
                                </g:if>

                                <g:if test="${sample.antibody?.immunogene}">
                                <li><b>Immunogene:</b> ${sample.antibody.immunogene}</li>
                                </g:if>

                                <g:if test="${sample.antibody?.clonal}">
                                <li><b>Mono/Poly Clonal:</b> ${sample.antibody.clonal}</li>
                                </g:if>

                                <g:if test="${sample.antibody?.igType}">
                                <li><b>Ig Type: </b>${sample.antibody?.igType}</li>
                                </g:if>  
                                
                                <g:if test="${sample.antibody?.concentration}">
                                <li><b>Concentration (ug/ul): </b>${sample.antibody?.concentration}</li>
                                </g:if>

                                <g:if test="${sample.antibody?.externalId}">
                                <li><b>External ID: </b>${sample.antibody.externalId}</li>
                                </g:if>  

                                <g:if test="${notes?.containsKey('Volume Sent (ul)')}">
                                <li><b>Volume Sent (ul):</b> ${notes['Volume Sent (ul)']}</li>
                                </g:if> 
                                
                                <g:if test="${notes?.containsKey('Usage Per ChIP (ug)')}">
                                <li><b>Usage Per ChIP (ug): </b>${notes['Usage Per ChIP (ug)']}</li>
                                </g:if> 
                                
                                <g:if test="${notes?.containsKey('Usage Per ChIP (ul)')}">
                                <li><b>Usage Per ChIP (ul): </b>${notes['Usage Per ChIP (ul)']}</li>
                                </g:if> 
                                       
                                <g:if test="${sample.antibody?.note}">
                                <li><b>Notes: </b>${sample.antibody?.note}</li>
                                </g:if>
                            </ul>
                        </td>
                        <td>
                            <ul>
                                <li><b>Target:</b> ${sample.target?.name}</li>
                                <g:if test="${sample.target?.targetType}">
                                <li><b>Type:</b> ${sample.target.targetType}</li>
                                </g:if>
                                <g:if test="${sample.target?.cTermTag}">
                                <li><b>C-Tag: </b>${sample.target.cTermTag}</li>
                                </g:if>
                                <g:if test="${sample.target?.nTermTag}">
                                <li><b>N-Tag: </b>${sample.target.nTermTag}</li>
                                </g:if>
                                <g:if test="${sample.target?.note}">
                                <li><b>Target Notes: </b>${sample.target.note}</li>
                                </g:if>
                            </ul>
                        </td>
                        <td>
                            <ul>
                                <li><b>Assay: </b>${sample.assay}</li>
                                
                                <li><b>Growth Media: </b>${sample.growthMedia} </li>
                                
                                <li><b>Treatments:</b>
                                    <g:each in="${sample?.treatments}" var="c">
                                        ${c}
                                    </g:each>   
                                </li>

                                <g:if test="${sample?.prtclInstSummary}">
                                    <g:if test="${notes['Resin']}">
                                    <li><b>Resin:</b>${notes['Resin']}</li>
                                    </g:if>
                                    <g:if test="${notes['PCR Cycle']}">
                                    <li><b>PCR Cycle:</b> ${notes['PCR Cycle']}</li>
                                    </g:if>
                                    <li><b>Technician: </b>${sample?.prtclInstSummary?.user?.fullName}</li>
                                    <li><b>Date:</b> <g:formatDate format="yyyy-MM-dd" date="${sample?.prtclInstSummary?.endTime}"/></li>
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
                        </td>
                        <td>
                            <ul>
                                <li><b>Index: </b>${sample.sequenceIndicesString} (${sample.sequenceIndicesIdString})</li>

                                <li><b>Chromosome (ug): </b><g:if test="${sample?.chromosomeAmount}">${sample.chromosomeAmount}</g:if></li>

                                <li><b>Avail. Cell# per aliquot (M):</b> <g:if test="${sample?.cellNumber}">${sample.cellNumber}</g:if></li>

                                <li><b>Volume per aliquot (ul): </b><g:if test="${sample?.volume}">${sample.volume}</g:if></li>

                                <li><b>Requested Tags (M): </b><g:if test="${sample?.requestedTagNumber}">${sample.requestedTagNumber}</g:if></li>

                                <li><b>Requested genomes:</b> ${sample?.requestedGenomes}</li>
                                
                                <li><b>Send data to:</b> ${sample?.sendDataTo}</li>
                                
                                <g:if test="${sample?.publicationReference}">
                                <li><b>Publication Reference:</b> ${sample.publicationReference}</li>
                                </g:if>
                                
                                <g:if test="${sample.note}">
                                <li><b>Notes: </b>${sample.note}</li>
                                </g:if>
                            </ul>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

     