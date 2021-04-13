Active: <g:if test="${item?.active}"><span id="item-active-show" class="label label-success">ACTIVE</span></g:if>
<g:else><span id="item-active-show" class="label label-danger">INACTIVE</span></g:else>
<span id="item-active-select" style="display:none">
    <select name="active">
        <option value="true">ACTIVE</option>
        <option value="false">INACTIVE</option>
    </select>
    <button id="item-active-save" class="btn btn-primary">Save</button>
    <button id="item-active-cancel" class="btn btn-default">Cancel</button>
</span>

<script>
    $("#item-active-show").click(function(){
        $("#item-active-show").hide();
        $("#item-active-select").show();
    });

    $("#item-active-save").click(function(){
        var active = $("#item-active-select option:selected").val();

        $.ajax({ url: "/pegr/item/updateActiveAjax?itemId=${item.id}&active=" + active,
            success: function(result) {
                if (result.active) {
                    $("#item-active-show").text('ACTIVE');
                } else {
                    $("#item-active-show").text('INACTIVE');
                }
                
                $("#item-active-show").removeClass();
                $("#item-active-show").addClass(result.label);
                $("#item-active-select").val(result.active);
                $("#item-active-show").show();
                $("#item-active-select").hide();
            }                
        });
    });

    $("#item-active-cancel").click(function(){
        $("#item-active-show").show();
        var active =  $("#item-active-show").text();
        $("#item-active-select").val(active);
        $("#item-active-select").hide();
    });
</script>