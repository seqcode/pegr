<html>
    <head>
        <meta name="layout" content="none"/>
        <style>
            body {
                width: ${bodyWidth}mm;
                padding-top: ${marginTop}mm;
                padding-left: ${marginLeft}mm;
                margin-top: 0px;
                margin-left: 0px;
            }
            
            .barcode {
                display: block;
                float: left;
                width: ${blockWidth}mm;
                height: ${blockHeight}mm;
                font-size: 10;
            }
            
            .barcode span {
                display: inline-block;
            }
            
            .barcode span p {
                margin: 0;
            }
            
            @media print {  
              @page {
                size: ${pageWidth}mm ${pageHeight}mm;
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
                <span>
                    <img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:barcode, width:60, height:60, formatStr:"QR"])}'  width="${barcodeSize}mm" height="${barcodeSize}mm"/>
                </span>   
                <g:if test="${printText}">
                <span>
                    <p><g:formatDate format="yyMMdd" date="${date}"/></p>
                    <p>${barcode}</p>
                    <g:if test="${nameList}">
                        <p>${nameList[n]}</p>
                    </g:if>
                </span>
                </g:if>
            </g:if>
        </div>
        </g:each>
    </body>
</html>