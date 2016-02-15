
<%@ page import="pegr.Sample" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Sample</title>
</head>
<body>
    <div class="container-fluid">
        <div>
            <g:link action="index"><span class="glyphicon glyphicon-home"></span> Home</g:link>
        </div>
        <div class="row">
            <div class="col-md-10">        
                <h3>
                    Sample ${sample.id} 
                    <small><span class="label label-default">${sample.status}</span></small> 
                </h3>
                <g:if test="${sample?.date}">
                    <p>Date: <g:formatDate format="yyyy-MM-dd" date="${sample.date}"/></p>
                </g:if>
                <div class="row">
                    <div id="cellSource" class="col-sm-4">
                        <h4>Cell Source</h4>
                        <ul>
                            <li>Strain: ${sample.cellSource?.strain?.name}</li>
                            <li>Species: ${sample.cellSource?.strain?.species}</li>
                            <g:if test="${sample.cellSource?.strain?.parent}">
                                <li>Parent Strain: ${sample.cellSource?.strain?.parent}</li>
                            </g:if>
                             <g:if test="${sample.cellSource?.strain?.backgroundStrain}">
                                <li>Parent Strain: ${sample.cellSource?.strain?.backgroundStrain}</li>
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
                                <g:each in="${sample.cellSource.treatments}" var="c">
                                    ${c}
                                </g:each>	
                            </li>

                            <g:if test="${sample?.spikeInCellSource}">
                            Spike In Cell Source: ${sample?.spikeInCellSource?.encodeAsHTML()}
                                </li>
                            </g:if>
                        </ul>
                    </div>
                    <div id="antibody" class="col-sm-4">
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
                            <li>C-Tag: ${sample.target.note}</li>
                            </g:if>
                        </ul>
                    </div>

                    <div id="protocol" class="col-sm-4">
                        <h4>Protocol</h4>            
                        <ul>
                            <li>Assay: ${sample.prtclInstSummary.protocol}</li>

                            <g:if test="${sample?.requestedTagNumber}">
                            <li>Requested Tag Number: ${sample.requestedTagNumber}</li>
                            </g:if>

                            <g:if test="${sample?.volume}">
                            <li>Concentration: ${sample.volume}</li>
                            </g:if>

                            <g:if test="${sample?.cellNumber}">
                            <li>Cell Number: ${sample.cellNumber}</li>
                            </g:if>

                            <g:if test="${sample?.chromosomeAmount}">
                            <li>Chromosome Amount: ${sample.chromosomeAmount}</li>
                            </g:if>

                            <g:if test="${sample?.publicationReference}">
                            <li>Publication Reference: ${sample.publicationReference}</li>
                            </g:if>

                            <g:each in="${notes}"><li>${it.key}: ${it.value}</li></g:each>
                            <li>Index: <g:each in="${sample.sequenceIndices}">${it.sequence} </g:each></li>
                        </ul>
                    </div>     
                    
                </div>
                <div id="seq">
                    <h4>Sequencing Experiments</h4>
                    <ul>
                        <g:each in="${sample.sequencingExperiments}">
                            <li>
                                <g:each in="${it.alignments}">
                                    <li>Reference Genome: ${it.genome}</li>
                                </g:each>
                            </li>
                        </g:each>
                        
                    </ul>
                </div>
            </div>
            <div class="col-md-2 well">
                <h3 class="text-center">Links</h3>
                <div id="seq">
                    <h4>Sequence Runs</h4>
                    <g:each in="${sample.runs}"><a href="#"><g:formatDate format="yyyy-MM-dd" date="${it.date}"/></p></a></g:each>
                </div>  
                <div id="bioRep">
                    <h4>Biological Replicates</h4>
                    <g:each in="${sample.bioReps}">${it}</g:each>
                </div>  
                <div id="techRep">
                    <h4>Technical Replicates</h4>
                    <g:each in="${sample.techReps}">${it}</g:each>
                </div>  
                <div id="project">
                    <h4>Projects</h4>
                    <g:each in="${sample.projects}">${it}</g:each>
                </div>
            </div>
        </div>
    </div>
    <script>
        $("#nav-metadata").addClass("active");      
    </script>
</body>
</html>
