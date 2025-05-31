<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin"/>
    <style>
        #merge {
            margin-top: 5px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <div class="container-fluid text-right" id="merge">
        <button onclick="window.open('/pegr/admin/mergeForm', 'PEGR Admin', 'width=600,height=400')" class="edit">Merge</button>
    </div>
	<div class="panel-group">
		<div class="jumbotron">
            <div class="well">
                <div class="row" style="display: flex; align-items: top;">
                    <div style="margin:18px 10px;">
                        <svg width="32" height="32" viewBox="0 0 512 512" fill="#0077c2" xmlns="http://www.w3.org/2000/svg">
                            <path d="M310.865,256C390.083,224.448,448,126.414,448,10.667C448,4.776,443.224,0,437.333,0c-5.89,0-10.667,4.776-10.667,10.667
                            c0,129.396-76.561,234.667-170.667,234.667c-94.105,0-170.667-105.271-170.667-234.667C85.333,4.776,80.557,0,74.667,0 C68.776,0,64,4.776,64,10.667C64,126.414,121.917,224.448,201.135,256C121.917,287.552,64,385.586,64,501.333
                            C64,507.224,68.776,512,74.667,512c5.891,0,10.667-4.776,10.667-10.667c0-129.396,76.561-234.667,170.667-234.667
                            c94.106,0,170.667,105.271,170.667,234.667c0,5.891,4.776,10.667,10.667,10.667c5.891,0,10.667-4.776,10.667-10.667
                            C448,385.586,390.083,287.552,310.865,256z"/>
                          <rect x="138.987" y="63.581" width="234.667" height="21.333"/>
                          <rect x="138.987" y="447.573" width="234.667" height="21.333"/>
                          <rect x="170.987" y="127.581" width="170.667" height="21.333"/>
                          <rect x="170.987" y="383.573" width="170.667" height="21.333"/>
                        </svg> 
                    </div>
                    <div>
                        <h3>Biological Specifications</h3>
                        <p>Information about biological samples and conditions.</p>
                        <div>
                        <g:each in="${controllerGroups[pegr.AdminCategory.BIO_SPECIFICATIONS]}">
                           <g:link controller="${it.key}" class="btn btn-default" role="button">${it.value}</g:link>
                        </g:each>
                        </div>
                    </div>
                </div>
            </div>
            <div class="well">
                <div class="row" style="display: flex; align-items: top;">
                    <div style="margin:18px 10px;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="#0077c2" class="bi bi-flask" viewBox="0 0 16 16">
                            <path d="M4.5 0a.5.5 0 0 0 0 1H5v5.36L.503 13.717A1.5 1.5 0 0 0 1.783 16h12.434a1.5 1.5 0 0 0 1.28-2.282L11 6.359V1h.5a.5.5 0 0 0 0-1zM10 2H9a.5.5 0 0 0 0 1h1v1H9a.5.5 0 0 0 0 1h1v1H9a.5.5 0 0 0 0 1h1.22l.61 1H10a.5.5 0 1 0 0 1h1.442l.611 1H11a.5.5 0 1 0 0 1h1.664l.611 1H12a.5.5 0 1 0 0 1h1.886l.758 1.24a.5.5 0 0 1-.427.76H1.783a.5.5 0 0 1-.427-.76l4.57-7.48A.5.5 0 0 0 6 6.5V1h4z"/>
                        </svg> 
                    </div>
                    <div>
                        <h3>Protocol</h3>
                        <p>Details of experimental and sequencing protocols.</p>
                        <div>
                        <g:each in="${controllerGroups[pegr.AdminCategory.PROTOCOLS]}">
                           <g:link controller="${it.key}" class="btn btn-default" role="button">${it.value}</g:link>
                        </g:each>
                        </div>
                    </div>
                </div>    
            </div>
            <div class="well">
                <div class="row" style="display: flex; align-items: top;">
                    <div style="margin:18px 10px;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="#0077c2" class="bi bi-laptop" viewBox="0 0 16 16">
                          <path d="M13.5 3a.5.5 0 0 1 .5.5V11H2V3.5a.5.5 0 0 1 .5-.5zm-11-1A1.5 1.5 0 0 0 1 3.5V12h14V3.5A1.5 1.5 0 0 0 13.5 2zM0 12.5h16a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 12.5"/>
                        </svg> 
                    </div>
                    <div>
                        <h3>Alignment and Analysis</h3>
                        <p>Settings and tools used for data processing.</p>
                        <div>
                        <g:each in="${controllerGroups[pegr.AdminCategory.ALIGNMENT_ANALYSIS]}">
                           <g:link controller="${it.key}" class="btn btn-default" role="button">${it.value}</g:link>
                        </g:each>
                        </div>
                    </div>
                </div>
            </div>
            <div class="well">
                <div class="row" style="display: flex; align-items: top;">
                    <div style="margin:18px 10px;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="#0077c2" class="bi bi-collection" viewBox="0 0 16 16">
                          <path d="M2.5 3.5a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1zm2-2a.5.5 0 0 1 0-1h7a.5.5 0 0 1 0 1zM0 13a1.5 1.5 0 0 0 1.5 1.5h13A1.5 1.5 0 0 0 16 13V6a1.5 1.5 0 0 0-1.5-1.5h-13A1.5 1.5 0 0 0 0 6zm1.5.5A.5.5 0 0 1 1 13V6a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5z"/>
                        </svg> 
                    </div>
                    <div>
                        <h3>Other</h3>
                        <p>Details of experimental and sequencing protocols</p>
                        <div>
                        <g:each in="${controllerGroups[pegr.AdminCategory.OTHER]}">
                           <g:link controller="${it.key}" class="btn btn-default" role="button">${it.value}</g:link>
                        </g:each>
                        </div>
                    
                    </div>
                </div>
            </div>		   
            <div class="well">
                <div class="row" style="display: flex; align-items: top;">
                    <div style="margin:18px 10px;">
                        <g:link action="serviceReport">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="#0077c2" class="bi bi-file-earmark" viewBox="0 0 16 16">
                            <path d="M14 4.5V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h5.5zm-3 0A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5z"/>
                        </svg>
                        </g:link>
                    </div>
                    <div>
                        <h3>Service Report</h3>
                    </div>
                </div>
            </div>            
        </div>
    </div>
</body>
</html>
