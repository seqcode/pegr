function getScan(){
    var href=window.location.href;
    var ptr=href.lastIndexOf("#");
    if(ptr>0){
        href=href.substr(0,ptr);
    }
    window.location.href="zxing://scan/?ret="+escape(href+"#{CODE}");
}

function getHash(){
	document.getElementById('barcode').value="1234";
    if(!changingHash){
        changingHash=true;
        var hash=window.location.hash.substr(1);
        document.getElementById('barcode').value=unescape(hash);
        window.location.hash="";   
        changingHash=false;
    }else{
        //Do something with barcode here
    }
}
