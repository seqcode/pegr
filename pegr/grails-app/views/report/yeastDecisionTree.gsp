<html>
<head>
    <meta charset="utf-8">
    <title>PEGR</title>
    <!-- load the d3.js library -->    	
    <script src="//d3js.org/d3.v4.min.js"></script>
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
        
        
    </style>
</head>
<body>
<script>
	
var p = {"dedup": 200000,
         "multiGps": 100,
         "sigPeakPair": 200,
         "enrich": 1.5,
         "polII": 0.1,
         "exprs": 0.1,
         "mapped": 0.5,
         "adapter": 0.15,
         "dup": 0.7,
         "polII2": 0.1,
         "exprs2": 0.1,
        }
    
var treeData =
  {
    "name": "Deduplicated Reads > " + p.dedup,
    "conditions": [{"label":"Dedup < ", "name": "dedup"}],
    "children": [
        { 
          "flag": "yes",
          "name": "Motif Match OR multiGPS > " + p.multiGps + " OR sig.PeakPairs > " + p.sigPeakPair + " OR Nucleosome Enrichment > " + p.enrich + " OR Enriched Segments",
          "children": [
              { "flag": "yes", "name": "Done; success"},
              { "flag": "no", "name": "Stress Gene", 
                "children": [
                    {"flag": "yes", "name": "Done; stress gene"},
                    {
                        "flag": "no",
                        "name": "polII < " + p.polII + " AND Expression < " + p.exprs,
                        "children" : [
                            {"flag": "yes", "name": "Done; low exprs"},
                            {"flag": "no", "name": "Done; failed"}
                        ]    
                    }
                ]}
          ]
      },
      { 
          "flag": "no",
          "name": "Mapped > " + p.mapped + " AND Adapter Dimer < " + p.adapter + " AND Duplication Level < " + p.dup,
          "children": [
              { "flag": "yes", "name": "Re-sequence", "color": ""},
              { "flag": "no",
                "name": "Stress Gene", 
                "children": [
                    {"flag": "yes", "name": "Done; stress gene"},
                    {
                        "flag": "no",
                        "name": "polII < " + p.polII2 + " AND Expression < " + p.exprs2,
                        "children" : [
                            {"flag": "yes", "name": "Done; low exprs"},
                            {"flag": "no", "name": "re-ChIP"}
                        ]    
                    }
                ]}
          ]
      }      
    ]
  };

var colors = {"Done; stress gene": "#f0ad4e", // yellow
              "Done; low exprs": "#f0ad4e",
              "re-ChIP": "#d9534f", //red
              "Re-sequence": "#d9534f", //red
              "Done; failed": "#d9534f",
              "Done; success": "#5cb85c", // green 
              "other": "Gray"
             }

// set the dimensions and margins of the diagram
var margin = {top: 10, right: 10, bottom: 50, left: 10},
    width = 1700 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

// declares a tree layout and assigns the size
var treemap = d3.tree()
    .size([width, height]);
//    .separation(10);
    
//  assigns the data to a hierarchy using parent-child relationships
var nodes = d3.hierarchy(treeData);

// maps the node data to the tree layout
nodes = treemap(nodes);

// append the svg obgect to the body of the page
// appends a 'group' element to 'svg'
// moves the 'group' element to the top left margin
var svg = d3.select("body").append("svg")
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
    .text(function(d) { return d.data.name; }).call(getBB);
    
// add the background color
node.insert("rect","text")
    .attr("width", function(d){return d.bbox.width + 10})
    .attr("height", function(d){return d.bbox.height + 10})
    .style("fill", function(d) {
        var col 
        if (d.children) {
            col = "#f2dede"
        } else {
            col = d.data.name in colors ?  colors[d.data.name] :  colors["other"]
        }
        return col;
    })
    .attr("transform", function(d) {
        var dx = -d.bbox.width/2 - 5;
        return "translate(" + dx + ", -15)";
    });
    
function getBB(selection) {
    selection.each(function(d){
        d.bbox = this.getBBox();
    });
}
    
</script>
</body>
</html>