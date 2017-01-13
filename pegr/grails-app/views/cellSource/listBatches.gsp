<html>
<head>
  <title>Item</title> 
  <meta name="layout" content="item"/>
</head>
<body>
    <g:render template="/item/searchBar"></g:render>
    <g:render template="/cellSource/head"></g:render>
    <ul class="nav nav-tabs">
        <li><g:link action="list">Cell Stock</g:link></li>
        <li class="active"><a href="#">Batches</a></li>
    </ul>
    <div class="container-fluid">
        <ul>
            <g:each in="${batches}">
                <li data-id="${it.id}">
                    <g:link action="showBatch" id="${it.id}">${it}</g:link>
                    <span class="value"><g:if test="${it.notes}">${it.notes}</g:if><g:else><span class="btn btn-default">+notes</span></g:else>
                    </span>                    
                </li>
            </g:each>
        </ul>
    </div>
    <script>
        $(".item-${categoryId}").addClass("active");
        
        var notes0 = "+notes";
        $(".value").on("click", function(){
            var value = $(this).text();
            var edit = "<input name='notes' class='input' value='" + value + "'>";
            appendEdit(this, edit);
        });
        
        $("li").on("click", ".cancel", function(){
            var parent = $(this).parent();
            toggleTd(parent);
        });
        
        $("li").on("click", ".save", function(){
            var parent = $(this).parent();
            var id = parent.attr("data-id");
            var notes = parent.find(".input").val();
            var newNotes = parent.find(".input").val();
            if (!newNotes) {
                newNotes = notes0;
            }
            $.ajax({
                method: "POST",
                url: "/pegr/cellSource/saveBatchNotesAjax",
                data: {id: id, notes: notes},
                success: function() {
                    parent.find(".value").text(newNotes);
                    toggleTd(parent);
                }
            });
        });
    </script>
</body>
</html>