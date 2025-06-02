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

function appendEdit(elem, edit) {
    var td = $(elem).parent();
    td.find(".value").hide();
    td.append(edit);
    var $save = "<button class='btn btn-primary save'>Save</button>";
    var $cancel = "<button class='btn btn-default cancel'>Cancel</button>";
    td.append($save);
    td.append($cancel);
}

function toggleTd(td) {
    td.find(".value").show();
    td.find(".input").remove();
    td.find(".cancel").remove();
    td.find(".save").remove();
}

function createSelect(tr, classname, data) {
    var td = tr.find(classname);
    var elem = td.find(".value");
    var oldValue = elem.text();
    var edit =  "<span class='input'><select style='width:120px; display:none'><option selected value='" + oldValue + "'>" + oldValue + "</option></select></span>";
    td.append(edit);
    elem.hide();
    td.find("select").select2({
        data: data,
        placeholder: "Select or type",
        tags: true,
        allowClear: true
    });
}
