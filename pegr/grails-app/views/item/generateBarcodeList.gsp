<html>
    <head>
        <meta name="layout" content="none"/>
        <style>
            body {
                width: 182.5mm;
                padding-top: 5mm;
                padding-left: 19mm;
                margin-top: 0px;
                margin-left: 0px;
            }
            .barcode {
                display: block;
                float: left;
                width: 36.5mm;
                height: 16mm;
                font-size: 10;
            }
            .label {
                position: relative;
                left: 0mm;
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
        <g:each in="${barcodeList}" var="barcode">
        <div class="barcode">
            <img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:barcode, width:60, height:60, formatStr:"QR"])}' width="40" height="40"/>
            <span class="label">${barcode}</span>
        </div>
        </g:each>
    </body>
</html>