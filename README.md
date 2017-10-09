PEGR is a web platform for sequencing metadata management.

Grails 2.5.5

Some notes on adding an new tab

	How to add a new tab
1. Copy paste the if(qcSettings.xxx) block in ReportController and replace xxx with your desired tab name (e.g. test)

2. Modify the Map QC_SETTINGS in ReportService and follow the format            test': "TEST_QC_SETTINGS"

3. In runStatus, add one of these in nav-tabs <li><a data-toggle="tab" href="#test">test</a></li> 

4. Then, add one of these in tab-content <div id="test" class="tab-pane fade"> 
<g:render template="testQc" model="[runStatusMap:it]"></g:render>  </div>

5. Make a _testQc.gsp and add it under report
