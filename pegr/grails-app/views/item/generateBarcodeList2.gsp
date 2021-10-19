<html>
    <head>
        <meta name="layout" content="none"/>
        <style>
            body {
                width: 202mm;
                padding-top: 13mm;
                padding-left: 15mm;
                margin-top: 0px;
                margin-left: 0px;
            }
            .barcode {
                display: block;
                float: left;
                width: 14.5mm;
                height: 14.5mm;
                font-size: 10;
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
                <img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:barcode, width:60, height:60, formatStr:"QR"])}' width="40" height="40"/>
            </g:if>
        </div>
        </g:each>
    </body>
</html>