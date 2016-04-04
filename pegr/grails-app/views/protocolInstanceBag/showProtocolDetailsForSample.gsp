<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main"/>
<title>Work bench</title>
<style>
   .subnumber OL { counter-reset: item }
   .subnumber LI { display: block }
   .subnumber LI:before { content: counters(item, ".") " "; counter-increment: item }
  </style>
</head>
<body>
    <h3><g:link controller="sample" action="show" id="${sample?.id}">Sample ${sample?.id}</g:link></h3>
    <h4>Protocols</h4>
    <div class="subnumber">
        <ol>
            <g:each in="${protocols}" var="bag">
                <li>
                    <g:link action="showBag" id="${bag.bag?.id}">${bag.bag?.name}</g:link>
                    <ol>
                        <g:each in="${bag.protocolList}">
                            <li><g:link action="showInstance" id="${it.id}">${it.protocol}</g:link></li>
                        </g:each>
                    </ol>
                </li>
            </g:each>
        </ol>
    </div>
</body>
</html>