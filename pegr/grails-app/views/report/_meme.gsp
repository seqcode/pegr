<g:each in="${sampleDTOs}" var="sample">
    <iframe src="meme.html" width=800 height=200 scrolling=no frameBorder=0></iframe>
    <div onload="renderMeme()">
        <div id="motifs" class="box">
          <p>Please wait... Loading...</p>
          <p>If the page has fully loaded and this message does not disappear then an error may have occurred.</p>
        </div>
    </div>
</g:each>

