

<span style="float: left"><img src='${createLink(controller:"item", action:"displayBarcode", params:[barcode:item.barcode, width:75, height:75, formatStr:"QR"])}'/></span>

<ul style="float:left;font-size:10px;margin-top:12px;margin-right:20px">
    <li>${item.type}</li>
    <li>${item.name}</li>
    <li>${item.barcode}</li>
</ul>