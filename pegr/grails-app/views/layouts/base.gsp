<!DOCTYPE html>
<html lang="en">
<head>
  <title><g:layoutTitle default="PEGR"/></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">  
  <asset:javascript src="application.js"/>
  <asset:stylesheet href="application.css"/>
  <g:layoutHead/>
</head>
<body  onhashchange="${pageProperty(name:'body.onhashchange')}">
	<div class="text-center visible-xs-block, hidden-xs">
		<h1>Platform for Eukaryotic Gene Regulation</h1>
	</div>
    <g:layoutBody/>	
</body>
</html>