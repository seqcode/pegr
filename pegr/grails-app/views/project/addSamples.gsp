<html>
<head>
    <title>Workbench</title> 
    <meta name="layout" content="main"/>
</head>
<body>
    
<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#cellSource">CellSource</a></li>
    <li><a data-toggle="tab" href="#antibody">Antibody</a></li>
    <li><a data-toggle="tab" href="#other">Other</a></li>
</ul>

<div class="tab-content">
    <div id="cellSource" class="tab-pane slide in active">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Row #</th>
                    <th>Genus</th>
                    <th>Species</th>
                    <th>Strain</th>
                    <th>Background</th>
                    <th>Genotype</th>
                    <th>Mutation</th>
                    <th>Treatments</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${1..3}">
                <tr>
                    
                   
                </tr>    
                </g:each>
            </tbody>
        </table>
    </div>
    <div id="antibody" class="tab-pane slide">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Row #</th>
                    <th>Company</th>
                    <th>Catalog</th>
                    <th>Lot #</th>
                    <th>Host</th>
                    <th>Immunogene</th>
                    <th>Mono/Poly</th>
                    <th>Ig Type</th>
                    <th>Concentration</th>
                    <th>External ID</th>
                    <th>Inventory ID</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${1..3}" var="counter">
                <tr>
                    <td>${counter}</td>
                    <td><g:select id="company" name="company.id" from="${pegr.Company.list()}" optionKey="id" value="${object?.company?.id}" noSelection="['': '']" class="tokenize-sample tokenize"/></td>
                    <td><g:textField name="catalogNumber" value="${object?.catalogNumber}"/></td>
                    <td><g:textField name="lotNumber" value="${object?.lotNumber}"/></td>
                    <td><g:select id="abHost" name="abHost.id" from="${pegr.AbHost.list()}" optionKey="id" required="" value="${object?.abHost?.id}" class="tokenize-sample tokenize" noSelection="['': '']"/></td>
                    <td><g:textField name="immunogene" value="${object?.immunogene}"/></td>
                    <td><g:select name="clonal" from="${pegr.MonoPolyClonal?.values()}" keys="${pegr.MonoPolyClonal.values()*.name()}" value="${object?.clonal?.name()}"  noSelection="['null': '']" /></td>
                    <td><g:select id="igType" name="igType.id" from="${pegr.IgType.list()}" optionKey="id" value="${object?.igType?.id}" noSelection="['': '']" class="tokenize-sample tokenize"/></td>
                    <td><g:field name="concentration" value="${fieldValue(bean: object, field: 'concentration')}" /></td>
                    <td><g:textField name="externalId" value="${object?.externalId}"/></td>
                    <td><g:textField name="inventoryId" value="${object?.inventoryId}"/></td>
                </tr>    
                </g:each>
            </tbody>
        </table>
    </div>
  <div id="other" class="tab-pane slide">

  </div>
</div>
    
    
    
    
    <script>
        $("#nav-projects").addClass("active");
        $(".tokenize").tokenize();
    </script>
</body>
</html>