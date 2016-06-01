<div class="row">
    <div id="cellSource" class="col-sm-3">
        <h4>Cell Source</h4>
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

            <g:if test="${sample.cellSource?.growthMedia}">
            <li>Growth Media: ${sample.cellSource.growthMedia}	</li>
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

            <li>Treatments:
                <g:each in="${sample?.cellSource?.treatments}" var="c">
                    ${c}
                </g:each>	
            </li>

            <g:if test="${sample?.spikeInCellSource}">
            Spike In Cell Source: ${sample?.spikeInCellSource?.encodeAsHTML()}
                </li>
            </g:if>
        </ul>
    </div>
    <div id="antibody" class="col-sm-3">
        <h4>Antibody</h4>
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
            <li>Usage Per ChIP (ug): ${notes['Usage Per ChIP (ul)']}</li>
            </g:if> 
                   
            <g:if test="${sample.antibody?.note}">
            <li>Notes: ${sample.antibody?.note}</li>
            </g:if>
            
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
        <h4>Protocol</h4>   
        <g:if test="${sample?.prtclInstSummary}">
            <ul>
                <li>Assay: ${sample.prtclInstSummary.protocol}</li>

                <g:if test="${notes['Resin']}">
                <li>Resin: ${notes['Resin']}</li>
                </g:if>

                <g:if test="${notes['PCR Cycle']}">
                <li>PCR Cycle: ${notes['PCR Cycle']}</li>
                </g:if>
            </ul>
        </g:if>
        <g:else>
            <div class="subnumber">
                <ol>
                    <g:each in="${protocols}" var="bag">
                        <li>
                            <g:if test="${authorized}">
                                <g:link controller="protocolInstanceBag" action="showBag" id="${bag.bag?.id}">${bag.bag?.name}</g:link>
                                <ol>
                                    <g:each in="${bag.protocolList}">
                                        <li><g:link controller="protocolInstanceBag" action="showInstance" id="${it.id}">${it.protocol}</g:link>
                                        <g:link controller="protocolInstanceBag" action="renderFile" params="[protocolId: it.protocol?.id]" target="_blank"><span class="glyphicon glyphicon-file"></span></g:link></li>
                                    </g:each>
                                </ol>
                            </g:if>
                            <g:else>
                                ${bag.bag?.name}
                                <ol>
                                    <g:each in="${bag.protocolList}">
                                        <li>${it.protocol} 
                                            <g:link controller="protocolInstanceBag" action="renderFile" params="[protocolId: it.protocol?.id]" target="_blank"><span class="glyphicon glyphicon-file"></span></g:link></li>
                                    </g:each>
                                </ol>
                            </g:else>
                        </li>
                    </g:each>
                </ol>
            </div>
        </g:else>
    </div>     

    <div id="other" class="col-sm-3">      
        <h4>Other</h4>
        <ul>
            <li>Index: ${sample.sequenceIndicesString}</li>

            <li>Chromosome (ug): <g:if test="${sample?.chromosomeAmount}">${sample.chromosomeAmount}</g:if></li>

            <li>Avail. Cell# per aliquot (M): <g:if test="${sample?.cellNumber}">${sample.cellNumber}</g:if></li>

            <li>Volume per aliquot (ul): <g:if test="${sample?.volume}">${sample.volume}</g:if></li>

            <li>Requested Tags (M): <g:if test="${sample?.requestedTagNumber}">${sample.requestedTagNumber}</g:if></li>

            <li>Reference genomes: ${sample?.requestedGenomes}</li>
            
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