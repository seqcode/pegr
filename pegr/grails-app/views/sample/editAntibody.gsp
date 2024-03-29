<html>
<head>
    <title>PEGR - Sample</title> 
    <meta name="layout" content="main"/>
    <script type="text/javascript" >
        var changingHash=false;
    </script>
</head>
<body onhashchange="getHash()">
<div class="container-fluid">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h3>Edit Antibody for Sample ${sampleId}</h3>
    <g:form action="updateAntibody" role="form" method="post">
        <g:hiddenField name="sampleId" value="${sampleId}"></g:hiddenField>
        <g:hiddenField name="antibodyId" value="${antibodyId}"></g:hiddenField>    
        <div>
            <h4>Antibody Information</h4>
            <g:render template="/antibody/form" model="['object': antibody]"></g:render>
        </div>
        <div id="ifCascade">
            <h4>Do you want to cascade this change to all the samples linked to this antibody?</h4>
            <ul>
                <li><input type="radio" name="ifCascade" value="yes"/> <label>Yes, change all the samples linked to this antibody.</label></li>
                <li><input type="radio" name="ifCascade" value="no" checked/> <label>No, only change sample ${sampleId}.</label></li>
            </ul>
        </div>
        <div id="update-item">
            <h4>Do you want to update item?</h4>
            <input type="radio" name="ifUpdateItem" value="yes"/> <label>Yes</label>
            <input type="radio" name="ifUpdateItem" value="no" checked/> <label>No</label>
        </div>
        <div id="barcode-form" style="display:none">
            <h4>Barcode Information</h4>
            <g:render template="/item/formWithBarcode" model="[item: item, 'itemTypeOptions':itemTypeOptions]"></g:render>
        </div>
        <div class="text-center well">
            <g:submitButton class="btn btn-primary" name="save" value="Save"/>
            <g:link action="edit" class="btn btn-default" params="[sampleId: sampleId]">Cancel</g:link>
        </div>        
    </g:form>
    
    <script>
        $("#nav-metadata").addClass("active");
        
        $("#update-item input").click(function(){
            if($(this).val() == "yes"){
                $("#barcode-form").show();
            } else {
                $("#barcode-form").hide();
            }
        })
     </script>
</div>
</body>
</html>