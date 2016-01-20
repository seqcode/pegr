<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Sample</title>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
    <div class="row">
        <div class="col-sm-2 sidenav">
             <g:render template="/sample/nav" />
        </div>
        <div class="col-sm-10 content">
            <h3>Cell Culture ${cellCultureId}</h3>
            <div id="cellculture-details">
            <div>
                <ul class="nav nav-tabs">
                  <li class="active"><a data-toggle="tab" href="#new-cellculture"><span class="glyphicon glyphicon-plus"></span> New</a></li>
                  <li><a data-toggle="tab" href="#scan-cellculture"><span class="glyphicon glyphicon-qrcode"></span> Scan</a></li>
                  <li><a data-toggle="tab" href="#search-cellculture"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                </ul>
                
                <div class="tab-content">
                  <div id="new-cellculture" class="tab-pane fade in active">
                    <h4>New Cell Culture</h4>
                    <g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
			        </g:if>
			        <g:hasErrors>
			            <div class="errors">
			                <g:renderErrors bean="${cellCultureInstance}" as="list"/>
			                <g:renderErrors bean="${ItemInstance}" as="list"/>
			            </div>
			        </g:hasErrors>
                    <g:form method="POST" action="createCellCultureForSample" role="form" useToken="true">
						<g:render template="form" model="['cellCultureInstance': cellCultureInstance, 'itemInstance': itemInstance]"/>
						<input type="hidden" name="sampleId" value="${sampleId}">
						<g:submitButton name="Save" class="btn btn-primary" />
                    </g:form>
                  </div>
                  
                  <div id="scan-cellculture" class="tab-pane fade">
                    <h4>Scan </h4>
                    <span class="glyphicon glyphicon-qrcode"></span>
                    <p>Some content in menu 2.</p>
                  </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $("#nav-sample-cellculture").addClass("active");
        $("#nav-projects").addClass("active");        
    </script>
</body>
</html>
