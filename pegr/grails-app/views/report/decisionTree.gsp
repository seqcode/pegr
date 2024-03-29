<html>
<head>
    <meta charset="utf-8">
    <title>PEGR</title>
    <!-- load the d3.js library -->    	
    <script src="//d3js.org/d3.v4.min.js"></script>
    <asset:javascript src="jquery.js"/>
    <style> /* set the CSS */
        body {
            margin: 0;
        }
        .node text { font: 14px sans-serif; z-index: 1000}

        .node--leaf text {
            fill: white;
        }
        .link {
          fill: none;
          stroke: #ccc;
          stroke-width: 2px;
        }

        .link text { 
            stroke: none;
            font: 12px sans-serif; 
        }
        form {
            margin: 10px;
        }
        .alert {
            color: red;
            margin: 10px;
            padding: 10px;
            border: 2px solid red;
        }
    </style>
</head>
<body>
    <div id="tree"></div>
    <g:if test="${flash.message}"><div class="alert">${flash.message}</div></g:if>
    <g:form controller="report" action="saveDecisionTree">
        <input type="hidden" name="type" value="${type}">
        <textarea name="json" rows="100" cols="200"></textarea>
        <g:submitButton name="save" value="Save" ></g:submitButton>
    </g:form>
    
<script>
$.ajax({
    url:"/pegr/report/getDecisionTreeAjax?type=${type}",
    success: function(result) {
var settings = result;
$("textarea").text(JSON.stringify(settings, null, "\t"));
        
// set the dimensions and margins of the diagram
var margin = {top: 10, right: 10, bottom: 50, left: 10},
    width = 1700 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

// declares a tree layout and assigns the size
var treemap = d3.tree()
    .size([width, height]);
//    .separation(10);
    
//  assigns the data to a hierarchy using parent-child relationships
var nodes = d3.hierarchy(settings.treeData);

// maps the node data to the tree layout
nodes = treemap(nodes);

// append the svg obgect to the body of the page
// appends a 'group' element to 'svg'
// moves the 'group' element to the top left margin
var svg = d3.select("#tree").append("svg")
      .attr("width", width + margin.left + margin.right)
      .attr("height", height + margin.top + margin.bottom),
    g = svg.append("g")
      .attr("transform",
            "translate(" + margin.left + "," + margin.top + ")");
    
var link = svg.selectAll(".link")
    .data(nodes.descendants().slice(1))
    .enter()
    .append("g")
    .attr("class", "link");

link.append("path")
    .attr("fill", "none")
    .attr("d",  function(d) {
       return "M" + (d.x+margin.left) + "," + (d.y+margin.top)
         + "C" + (d.x+margin.left) + "," + ((d.y + d.parent.y) / 2+margin.top)
         + " " + (d.parent.x+margin.left) + "," +  ((d.y + d.parent.y) / 2+margin.top)
         + " " + (d.parent.x+margin.left) + "," + (d.parent.y+margin.top);
       });

link.append("text")
    .attr("font-family", "Arial, Helvetica, sans-serif")
    .style("font", "normal 12px Arial")
    .attr("fill", function(d){
        var color= d.data.flag == "yes" ? "Green" : "Red";
        return color
    })
    .attr("transform", function(d) {
        var dx = d.data.flag == "yes" ? -10 : 20;
        return "translate(" +
            ((d.x + d.parent.x)/2 + dx) + "," + 
            ((d.y + d.parent.y)/2) + ")";
    })   
    .attr("dy", ".35em")
    .attr("text-anchor", "middle")
    .text(function(d) {return d.data.flag;});

// adds each node as a group
var node = svg.selectAll(".node")
    .data(nodes.descendants())
    .enter().append("g")
    .attr("class", function(d) { 
      return "node" + 
        (d.children ? " node--internal" : " node--leaf"); })
    .attr("transform", function(d) { 
      return "translate(" + (d.x + 20) + "," + (d.y+ 20) + ")"; });

// adds the text to the node
node.append("text")
    .attr("dy", ".35em")
    .attr("y", 0)
    .style("text-anchor", "middle")
    .text(function(d) {return d.data.conditions ? getConditionStr(d.data.conditions, d.data.logic) : d.data.name;})
    .call(getBB);
    
// add the background color
node.insert("rect","text")
    .attr("width", function(d){return d.bbox.width + 10})
    .attr("height", function(d){return d.bbox.height + 10})
    .style("fill", function(d) {
        var col 
        if (d.children) {
            col = "#f2dede"
        } else {
            col = d.data.name in settings.colors ?  settings.colors[d.data.name] :  settings.colors["other"]
        }
        return col;
    })
    .attr("transform", function(d) {
        var dx = -d.bbox.width/2 - 5;
        return "translate(" + dx + ", -15)";
    });
    
}});
    
function getBB(selection) {
    selection.each(function(d){
        d.bbox = this.getBBox();
    });
}

function getConditionStr(conditions, logic) {
    var strings = [];
    for (i=0; i< conditions.length; i++){
        var c = conditions[i];
        var name = c.name? c.name : c.p;
        strings.push([name, c.op, c.v].join(" "));    
    }
    return strings.join(" " + logic + " ");
}
</script>
</body>
</html>