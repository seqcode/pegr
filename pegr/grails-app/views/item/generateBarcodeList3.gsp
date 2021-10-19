<html>
    <head>
        <meta name="layout" content="none"/>
        <style>
            body {
                width: 25.4mm;
                padding-top: 2mm;
                padding-left: 6.5mm;
                margin-top: 0px;
                margin-left: 0px;
            }
            .barcode {
                display: block;
                width: 25.4mm;
                height: 12.6mm;
                font-size: 10;
                margin: 0;
                padding: 0;
            }
            
            .date {
                position: relative;
                left: 0mm;
                top: -7.5mm;
            }
            
            .label {
                position: relative;
                left: 11mm;
                top: -7.5mm;
            }
            
            .name {
                position: relative;
                left: 3mm;
                top: -5mm;
            }
            
            @media print {  
              @page {
                size: 38mm 30mm;
                /* you can also specify margins here: */
                margin: 0mm;
                margin-right: 0mm; /* for compatibility with both A4 and Letter */
              }
            }
        </style>        
    </head>
    <body>
        <g:each in="${barcodeList}" var="barcode" status="n">        
        <div class="barcode">
            <g:if test="${barcode}">
                <img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:barcode, width:60, height:60, formatStr:"QR"])}' width="40" height="40"/>
                <span class="date"><g:formatDate format="yyMMdd" date="${date}"/></span>
                <span class="label">${barcode}</span>
                <g:if test="${nameList}">
                    <span class="name">${nameList[n]}</span>
                </g:if>
            </g:if>
        </div>
        </g:each>
    </body>
</html>