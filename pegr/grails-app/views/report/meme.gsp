<html>
<head>
    <title>MEME</title>
    <asset:javascript src="meme.js"/>
    <asset:stylesheet href="meme.css"/>
</head>
<body onload="page_loaded()">
    <div id="motifs" class="box">
        <p>Please wait... Loading...</p>
        <p>If the page has fully loaded and this message does not disappear then an error may have occurred.</p>
    </div>
    <script>
      // @JSON_VAR data
      var data = {
        "program": "MEME",
        "version": "4.11.0",
        "release": "Thu Nov 26 17:48:49 2015 +1000",
        "stop_reason": "",
        "alphabet": {
          "name": "DNA",
          "like": "dna",
          "ncore": 4,
          "symbols": [
            {
              "symbol": "A",
              "name": "Adenine",
              "colour": "CC0000",
              "complement": "T"
            }, {
              "symbol": "C",
              "name": "Cytosine",
              "colour": "0000CC",
              "complement": "G"
            }, {
              "symbol": "G",
              "name": "Guanine",
              "colour": "FFB300",
              "complement": "C"
            }, {
              "symbol": "T",
              "aliases": "U",
              "name": "Thymine",
              "colour": "008000",
              "complement": "A"
            }, {
              "symbol": "N",
              "aliases": "X.",
              "name": "Any base",
              "equals": "ACGT"
            }, {
              "symbol": "V",
              "name": "Not T",
              "equals": "ACG"
            }, {
              "symbol": "H",
              "name": "Not G",
              "equals": "ACT"
            }, {
              "symbol": "D",
              "name": "Not C",
              "equals": "AGT"
            }, {
              "symbol": "B",
              "name": "Not A",
              "equals": "CGT"
            }, {
              "symbol": "M",
              "name": "Amino",
              "equals": "AC"
            }, {
              "symbol": "R",
              "name": "Purine",
              "equals": "AG"
            }, {
              "symbol": "W",
              "name": "Weak",
              "equals": "AT"
            }, {
              "symbol": "S",
              "name": "Strong",
              "equals": "CG"
            }, {
              "symbol": "Y",
              "name": "Pyrimidine",
              "equals": "CT"
            }, {
              "symbol": "K",
              "name": "Keto",
              "equals": "GT"
            }
          ]
        },
        "background": {
          "freqs": [0.277, 0.223, 0.223, 0.277]
        },
        "motifs": ${motifs}
      };

var current_motif = 0;
var meme_alphabet = new Alphabet(data.alphabet, data.background.freqs);

function page_loaded() {
  post_load_setup();
}

function post_load_setup() {
  update_scroll_pad();
  if (data["motifs"].length > 0) {
    make_motifs();
  } else {
    $("motifs").innerHTML = "<p>No significant motifs found!</p>"; // clear content
  }
}
pre_load_setup();

function make_motifs() {
  "use strict";
  function pixel_value(str_in) {
    "use strict";
    var px_re, match;
    px_re = /^(\d+)px$/;
    if (match = px_re.exec(str_in)) {
      return parseInt(match[1], 10);
    }
    return 0;
  }
  var container, tbl;
  var colw, r, row, c, cell, cell_style, pad_left, pad_right;

  // make the motifs table
  container = $("motifs");
  container.innerHTML = ""; // clear content

  tbl = make_motifs_table(meme_alphabet, 1, data["motifs"], colw, data["stop_reason"]);
  container.appendChild(tbl);

  // measure table column widths
  colw = [];
  row = tbl.tBodies[0].rows[0];
  for (c = 0; c < row.cells.length; c++) {
    var padLeft, padRight;
    cell = row.cells[c];
    cell_style = window.getComputedStyle(cell, null);
    pad_left = pixel_value(cell_style.getPropertyValue("padding-left"));
    pad_right = pixel_value(cell_style.getPropertyValue("padding-right"));
    colw[c] = cell.clientWidth - pad_left - pad_right;
    if (typeof colw[c] !== "number" || colw[c] < 0) {
      colw[c] = 1;
    }
  }

  // set minimum table column widths on each row so later when we remove rows it still aligns
  for (r = 0; r < tbl.tBodies[0].rows.length; r++) {
    row = tbl.tBodies[0].rows[r];
    for (c = 0; c < row.cells.length; c++) {
      row.cells[c].style.minWidth = colw[c] + "px";
    }
  }

  // store the table column widths so we can create rows latter with the same minimums
  container.data_colw = colw;

  // calculate the x offset for the buttons
  row = tbl.tBodies[0].rows[0];
  container.data_more_x = coords(find_child(find_child(row, "motif_more"), "sym_btn"))[0];
  container.data_submit_x = coords(find_child(find_child(row, "motif_submit"), "sym_btn"))[0];

  draw_on_screen();
}

</script>
  </body>
</html>

