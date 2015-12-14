<html>
    <head>
        <title>My Projects</title> 
        <meta name="layout" content="main"/>
    </head>
    <body>
      <form class="form-inline" role="form" id="search">
        <div class="form-group">
		  <input type="text" class="form-control" size="50"
		  ><button type="button" class="btn" data-toggle="collapse" data-target="#adv-search"><span class="caret"></span></button>
		  <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
		</div>
	  </form>
	  <form class="form collapse" role="form" id="adv-search">
	    <div class="form-group">
	     <label>Field</label>
		  <input type="text" class="form-control" size="50" >
		</div>
        <div class="form-group">
		  <button type="submit" class="btn btn-default">Advanced Search</button>
		</div>
	  </form>
		<script>
			$("#nav-metadata").addClass("active");
		</script>
    </body>
</html>