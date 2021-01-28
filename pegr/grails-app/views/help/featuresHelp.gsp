 <html>
    <head>
        <meta name="layout" content="help">
    </head>
    <body>
        <sec:ifAnyGranted roles="ROLE_ADMIN">
            <div class="row">
                <g:link controller="referenceFeatureAdmin" class="edit pull-right">Manage</g:link>
            </div>
        </sec:ifAnyGranted>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <g:sortableColumn property="filename" title="Filename"></g:sortableColumn>
                        <g:sortableColumn property="genome.name" title="Genome Build"></g:sortableColumn>
                        <th>Summary</th>
                        <th>Link</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${referenceFeatures}" var="feature">
                        <tr>
                            <td>${feature.filename}</td>
                            <td><a href="${feature.genome.url}">${feature.genome}</a></td>
                            <td>${feature.summary}</td>
                            <td><a href="${feature.url}">${feature.url}</a></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>

        <script>
            $(".help-features").addClass("active");
        </script>
    </body>
</html>