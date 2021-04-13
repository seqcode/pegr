Item quality: <g:if test="${item?.status=='GOOD'}"><span id="item-status-show" class="label label-success">${item?.status}</span></g:if>
<g:elseif test="${item?.status=='BAD'}"><span id="item-status-show" class="label label-danger">${item?.status}</span></g:elseif>
<span id="item-status-select" style="display:none">
    <g:select name="status" from="${pegr.ItemStatus}" value="${item?.status}"></g:select>
    <button id="item-status-save" class="btn btn-primary">Save</button>
    <button id="item-status-cancel" class="btn btn-default">Cancel</button>
</span>

<script>
    $("#item-status-show").click(function(){
        $("#item-status-show").hide();
        $("#item-status-select").show();
    });

    $("#item-status-save").click(function(){
        var status = $("#item-status-select option:selected").text();
        $.ajax({ url: "/pegr/item/updateStatusAjax?itemId=${item.id}&status=" + status,
            success: function(result) {
                $("#item-status-show").text(result.status);
                $("#item-status-show").removeClass();
                $("#item-status-show").addClass(result.label);
                $("#item-status-select").val(result.status);
                $("#item-status-show").show();
                $("#item-status-select").hide();
            }                
        });
    });

    $("#item-status-cancel").click(function(){
        $("#item-status-show").show();
        var status =  $("#item-status-show").text();
        $("#item-status-select").val(status);
        $("#item-status-select").hide();
    });
</script>
