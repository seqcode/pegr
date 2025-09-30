<ul>
    <li>
        <h4>Quality Control Analysis</h4>
        <g:each in="${sampleDTOs}" var="sample">
          <g:each in="${sample.experiments}" var="experiment">
            <g:each in="${experiment.alignments}" var="alignment">
              <h5>Sample <u>${sample.id} ${sample.naturalId}</u> &nbsp; Run <u>${experiment.runId}</u> &nbsp; Genome <u>${alignment.genome}</u> &nbsp; Target <u>${sample.target}</u> 
              </h5>
              <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#${alignment.id}-meme-table">MEME Motifs</a></li>
                <g:each in="${0..<alignment.featureAnalysis.size()}" var="m">
                  <g:if test="${alignment.featureAnalysis[m]}">
                  <li><a data-toggle="tab" href="#${alignment.id}-composite${m}">${alignment.featureAnalysis[m].title}</a></li>
                  </g:if>
                </g:each>
              </ul>
              <div class="tab-content">
                <div id="${alignment.id}-meme-table" class="tab-pane in active"> 
                  <table class="table table-bordered meme-table">
                    <thead>
                        <tr>
                            <th rowspan="2">ID</th>
                            <th colspan="4" class="text-center"><a href="${alignment.memeFig}" target="_blank">Meme <span class="glyphicon glyphicon-picture"></span></a><span class="meme-url" hidden="hidden">${alignment.memeFile}</span></th>
                            <th rowspan="2">Four-Color</th>
                            <th rowspan="2">Composite</th>
                        </tr>
                        <tr>
                            <th>Logo</th>
                            <th>E-value</th>
                            <th>Sites</th>
                            <th>Width</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:if test="${alignment.motifCount}">
                        <g:each in="${(0..<alignment.motifCount)}" var="n">
                            <tr>
                                <td class="meme-id" style="width:20px"></td>
                                <g:if test="${alignment.memeSvgForward}">
                                <td class="meme-fig" style="width:450px">
                                    <i class="fa fa-spinner fa-spin"></i>
                                    <div class="preview_box">
                                        <div class="preview_btn_box">
                                            <div class="preview_btn plus active" tabindex="0">+</div>
                                            <div class="preview_btn minus" tabindex="0">-</div>
                                        </div>
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
                                <td style="width:450px">
                                </g:else>
                                <td class="meme-evalue" style="width:100px"></td>
                                <td class="meme-sites" style="width:100px"></td>
                                <td class="meme-width" style="width:100px"></td>
                                <td style="width:100px">
                                    <g:if test="${alignment.fourColor[n]}">
                                    <a href="${alignment.fourColor[n]}" target="_blank"><span class="glyphicon glyphicon-picture" style="font-size: 2em"></span></a>
                                    </g:if>
                                </td>
                                <td class="composite" style="width:320px">
                                  <g:if test="${alignment.composite[n]}">
                                    <g:link controller="report" action="composite" params="[url: alignment.composite[n]]" target="_blank" class="pull-right"><span class="glyphicon glyphicon-fullscreen" style="z-index: 100"></span></g:link>
                                    <i class="fa fa-spinner fa-spin"></i>
                                    <span class="composite-url" hidden="hidden">${alignment.composite[n]}</span>
                                    <div class="composite-fig-plus"></div>
                                    <div class="composite-fig-minus"></div>
                                  </g:if>
                                </td>
                            </tr>
                        </g:each>
                        </g:if>
                    </tbody>
                  </table> 
                </div>
                <g:each in="${0..<alignment.featureAnalysis.size()}" var="m">
                  <g:if test="${alignment.featureAnalysis[m]}">
                    <div id="${alignment.id}-composite${m}" class="featureAnalysis">
                      <i class="fa fa-spinner fa-spin"></i>
                      <span class="composite-url" hidden="hidden">${alignment.featureAnalysis[m].tabular}</span>
                      <span class="composite-plot-title" hidden="hidden">${alignment.featureAnalysis[m].plot_title}</span>
                      <span class="composite-xlabel" hidden="hidden">${alignment.featureAnalysis[m].xlabel}</span>
                      <div class="composite-fig" style="width: 512px; height: 300px"></div>
                    </div>
                  </g:if>
                </g:each>
              </div>
            </g:each>
          </g:each>
        </g:each>
    </li>
</ul>