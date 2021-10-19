<html>
    <head>
        <meta name="layout" content="none"/>
        <style>
            body {
                width: 180mm;
                padding-top: 6mm;
                padding-left: 19.5mm;
                margin-top: 0px;
                margin-left: 0px;
            }
            .barcode {
                display: block;
                float: left;
                width: 36mm;
                height: 16mm;
                font-size: 10;
            }
            
            .date {
                position: relative;
                left: 0mm;
                top: -7.5mm;
            }
            
            .label {
                position: relative;
                left: -8mm;
                top: -5mm;
            }
            
            .name {
                position: relative;
                left: 11.5mm;
                top: -5mm;
            }
            
            @media print {  
              @page {
                size: 216mm 280mm;
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
                <img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:barcode, width:60, height:60, formatStr:"QR"])}'  width="40" height="40"/>
                <span class="date"><g:formatDate format="yyMMdd" date="${date}"/></span>
                <span class="label">${barcode}</span>
                <g:if test="${nameList}">
                    <div class="name">${nameList[n]}</div>
                </g:if>
            </g:if>
        </div>
        </g:each>
    </body>
</html>