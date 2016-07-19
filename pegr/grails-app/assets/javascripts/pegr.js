function getScan(){
    var href=window.location.href;
    var ptr=href.lastIndexOf("#");
    if(ptr>0){
        href=href.substr(0,ptr);
    }
    window.location.href="zxing://scan/?ret="+escape(href+"#{CODE}");
}

function getHash(){
    if(!changingHash){
        changingHash=true;
        var hash=window.location.hash.substr(1);
        $('#barcode').val(unescape(hash));  
        changingHash=false;
    }else{
        //Do something with barcode here
    }
}

function refreshHash() {
    $("#barcode").val("");
    location.hash = "";
}

function closeModal() {
    $(".modal").modal('hide');
}

// jquery validation
jQuery.validator.addMethod("textrule", function(value, element) {
    return this.optional(element) || /^[a-zA-Z0-9-]+$/.test(value);
}, "Only a-z, A-Z, 0-9 and '-' are allowed!");

jQuery.validator.addClassRules({
    textcontrol: {
        textrule: true
    },
    isnumber: {
        number: true
    }
});