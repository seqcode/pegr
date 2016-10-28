<html>
    <head>
        <meta name="layout" content="none"/>
        <style>
            body {
                width: 925px;
                padding-top: 0px;
                padding-left: 85px;
            }
            .barcode {
                display: block;
                float: left;
                width: 185px;
                height: 82px;
                font-size: 10;
            }
            .label {
                position: relative;
                left: -20px;
                top: -20px;
            }
        </style>        
    </head>
    <body>
        <g:each in="${barcodeList}" var="barcode">
        <div class="barcode">
            <img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:barcode, width:83, height:83, formatStr:"QR"])}'/>
            <span class="label">${barcode}</span>
        </div>
        </g:each>
    </body>
</html>