<section>
    <h4>Advanced Analysis</h4>
    <ul>
        <g:each in="${sampleList}" var="sample">
        <li>
            <h4>${sample.id} ${sample.naturalId}</h4>
            <g:each in="${sample.experiments}" var="experiment">
            <g:each in="${experiment.alignments}" var="alignment">
            <h5>MEME Motifs</h5>
            <table class="table table-bordered meme-table" data-meme-url="${alignment.memeFile}">
                <tbody>
                <g:if test="${alignment.motifCount}">
                <g:each in="${(0..<alignment.motifCount)}" var="n">
                    <tr>
                        <td style='min-width:6em;line-height:1em' class="meme-id"></td>
                        <g:if test="${alignment.memeSvgForward}">
                        <td class="meme-fig" style="width:350px">
                            <i class="fa fa-spinner fa-spin"></i>
                            <div class="preview_box">
                                <div class="preview_logo_box">
                                    <div class="meme-svg meme-svg-forward">
                                        <span class="meme-fig-url-forward" hidden="hidden">${alignment.memeSvgForward[n]}</span>
                                    </div>
                                    <div style="display:none" class="meme-svg meme-svg-reverse">
                                        <span class="meme-fig-url-reverse" hidden="hidden">${alignment.memeSvgReverse[n]}</span>
                                    </div>
                                </div>
                            </div>
                        </td>
                        </g:if>
                        <g:else>
                        <td style="width:350px">
                        </g:else>
                        <td class="composite" style="width:320px">
                          <g:if test="${alignment.composite[n]}">
                            <span class="composite-fig" data-composite-url="${alignment.composite[n]}"><i class="fa fa-spinner fa-spin"></i></span>
                          </g:if>
                        </td>
                    </tr>
                </g:each>
                </g:if>
                </tbody>
            </table>
            <g:each in="${0..<alignment.featureAnalysis.size()}" var="m">
              <g:if test="${alignment.featureAnalysis[m]}">
                <h5>${alignment.featureAnalysis[m].title}</h5>
                <div id="${alignment.id}-composite${m}" class="featureAnalysis">
                  <i class="fa fa-spinner fa-spin"></i>
                  <span class="composite-url" hidden="hidden">${alignment.featureAnalysis[m].tabular}</span>
                  <span class="composite-plot-title" hidden="hidden">${alignment.featureAnalysis[m].plot_title}</span>
                  <span class="composite-xlabel" hidden="hidden">${alignment.featureAnalysis[m].xlabel}</span>
                  <div class="composite-fig" style="width: 512px; height: 300px"></div>
                </div>
              </g:if>
            </g:each>
            </g:each>
            </g:each>
        </li>
        </g:each>
    </ul>
</section>