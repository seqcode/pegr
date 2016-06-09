<html>
    <head>
        <title>PEGR Help - Sample Submission</title>
        <asset:stylesheet href="application.css"/>
    </head>
    <body>
        <h3>Sample Submission</h3>
        <ol>
            <li>
                <h4>Assay</h4>
                <p>Each assay has a pre-defined template. All samples in the sample submission form have the same assay. Samples using different assays should be submitted in separate forms.</p>
            </li>
            <li>
                <h4>Sections</h4>
                <p>The sample submission form is separated into three sections: "Sample", "Antibody" and "Target". The header buttons will help you to navigate to the corresponding sections.</p>
            </li>
            <li>
                <h4>Select and Type</h4>
                <p>Click on the fields with placeholder "Select...", a drop-down menu will show up. You may select a choice directly, or type in some characters to narrow down the selections. Fields with placeholder "Select or type..." will allow you to type in new words in addition to the provided selections. Fields without placeholder accept free text. </p>
            </li>
            <li>
                <h4>Multiple selections</h4>
                <p>Some fields, such as "Treatments" and "Reference Genomes", accept multiple selections. Once you enter the first selection, that selection will be wrapped up and prefixed with a remove sign. You may continue to choose additional selections.</p>
            </li> 
            <li>
                <h4>Cascade select</h4>
                <p>When you open up a sample submission form, some fields are disabled (grey) temporarilly. These fields often depend on previous fields. For example, the selection of "Genus" will narrow down the range of "Species". When "Genus" is selected, the field "Species" will be active and a list of species in that Genus will be provided. Likewise, "Species" narrows down "Parent Strain", and then "Strain". The dependencies are list below.</p>
                <p>Sample</p>
                <ul>
                    <li>Genus -> Species -> Parent Strain -> Strain -> Genotype -> mutation </li>
                    <li>Species -> Growth Media</li>
                    <li>Species -> Reference Genomes</li>
                </ul>
                <p>Antibody</p>
                <ul>
                    <li>Catalog -> Host, Immunogene, Clonal, Ig Type and Concentration. </li>
                    <li>Catalog -> Target Type, Target, C-Term and N-Term</li>
                </ul>
            </li>            
            <li>
                <h4>Keyboard navigation</h4>
                <p>When drop-down menu is closed</p>
                <ul>
                    <li><button>Tab</button>: navigate to the next field.</li>
                    <li><button>Shift</button> + <button>Tab</button>: navigate to the previous field.</li>
                    <li><button>&darr;</button>: open drop-down menu.</li>
                </ul>
                <p>When drop-down menu is open</p>
                <ul>
                    <li><button>&uarr;</button>, <button>&darr;</button>: navigate the list of choices.</li>
                    <li><button>Enter</button>: select.</li>
                    <li><button>Esc</button>: close the drop-down menu.</li>
                </ul>
            </li>            
            <li>
                <h4>Add Row</h4>
                <p>Click the <button>Add Row</button> button, a clone (including all the data and selections) of the last row will be appended to the bottom of the form. Then you may make changes to the new row.</p>
            </li>            
            <li>
                <h4>Remove</h4>
                <p>A row may be removed by clicking the <span class="glyphicon glyphicon-trash"></span> symbol at the beginning of that row.</p>
            </li>            
            <li>
                <h4>Validations</h4>
                <p>Validations of all the fields will occur when the form is submitted (some simple input fields will be validated whenever the fields are changed). Error messages will appear in red around the corresponding fields.</p>
                <ul>
                    <li>Required Fields: Genus, Species, Parent Strain, Strain, Growth Media, Genomes, Catalog, Target.</li>
                    <li>Fields where only alphanumeric characers and hyphen (e.g. 0-9, a-z, A-Z and -) are accepted: Strain, Mutation, Growth Media, Treatments, Catalog, Target.</li>
                    <li>Fields that accept numbers: Chrom.(ug), Cell#(M), Volume(ul), Requested Tags(M), Conc.(ug/ul), Volume Sent(ul), Usage Per ChIP(ug), Usage Per ChIP(ul)</li>
                </ul>
            </li>    
            <li id="sequence-index">
                <h4>Index</h4>
                <p>Index can be input by using either sequence (e.g. "ATCG") or its ID (e.g. "iA") assigned by PEGR (see the following table). Note that your input should conform to the radio button choice. Both single-index and duo-index are accepted, and one or more indices can be attached to a single sample. For duo-index samples, the two indices on the same molecule should be deliminated by hyphen "-", and indices on different modlecules should be deliminated by comma ",". For example, if you choose to input sequence, a valid input will be like "TCGCCTTA-CTCTCTAT,CTAGTACG-TATCCTCT"; while if you choose to input ID, the input should be like "i01-iA,i02-iB".</p>
                <div class="row">
                    <g:each in="${allIndices}" var="indices">
                        <div class="col-xs-6">
                            <table class="table-bordered">
                                <caption>Version ${indices.key}</caption>
                                <thead>
                                    <th>ID</th>
                                    <th>Sequence</th>
                                </thead>
                                <tbody>                        
                                    <g:each in="${indices.value}">
                                        <tr>
                                            <td>${it.indexId}</td>
                                            <td>${it.sequence}</td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </g:each>
                </div>
            </li>
        </ol>
    </body>
</html>