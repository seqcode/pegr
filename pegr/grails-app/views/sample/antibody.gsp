<g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${sampleInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${sampleInstance}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
        </g:hasErrors>
        <g:form action='save' >
            <g:render template="form"/>
            <g:submitButton name="save" value="Save" />
        </g:form>
        <div class=" ${hasErrors(bean: sampleInstance, field: 'cellSource', 'error')} ">
            <label for="cellSource">Cell Source</label>
            <g:select id="cellSource" name="cellSource.id" from="${pegr.CellSource.list()}" optionKey="id" value="${sampleInstance?.cellSource?.id}" class="many-to-one" noSelection="['null': '']"/>
        </div>
        <div class=" ${hasErrors(bean: sampleInstance, field: 'spikeInCellSource', 'error')} ">
            <label for="spikeInCellSource">Spike In Cell Source</label>
            <g:select id="spikeInCellSource" name="spikeInCellSource.id" from="${pegr.CellSource.list()}" optionKey="id" value="${sampleInstance?.spikeInCellSource?.id}" class="many-to-one" noSelection="['null': '']"/>
        </div>

        <div class=" ${hasErrors(bean: sampleInstance, field: 'antibody', 'error')} ">
            <label for="antibody">Antibody </label>
            <g:select id="antibody" name="antibody.id" from="${pegr.Antibody.list()}" optionKey="id" value="${sampleInstance?.antibody?.id}" class="many-to-one" noSelection="['null': '']"/>
        </div>

        <div class=" ${hasErrors(bean: sampleInstance, field: 'target', 'error')} ">
            <label for="target">Target</label>
            <g:select id="target" name="target.id" from="${pegr.Target.list()}" optionKey="id" value="${sampleInstance?.target?.id}" class="many-to-one" noSelection="['null': '']"/>
        </div>

        <div class=" ${hasErrors(bean: sampleInstance, field: 'sequenceIndices', 'error')} ">
        <label for="sequenceIndices">Sequence Indices</label>
        <g:select name="sequenceIndices" from="${pegr.SequenceIndex.list()}"  optionKey="id"  value="${sequenceIndex?.id}"/>
        </div>
            <h3>Protocols</h3>
            <div class=" ${hasErrors(bean: sampleInstance, field: 'protocolGroup', 'error')} ">
                <label for="protocolGroup">Protocol Group</label>
                <g:select id="protocolGroup" name="protocolGroup.id" from="${pegr.ProtocolGroup.list()}" optionKey="id" value="${sampleInstance?.protocolGroup?.id}" class="many-to-one" noSelection="['null': '']"/>
            </div>

            <h3>Stats</h3>

            <div class=" ${hasErrors(bean: sampleInstance, field: 'requestedTagNumber', 'error')}">
            <label for="requestedTagNumber">Requested Tag Number</label>
            <g:field name="requestedTagNumber" type="number" value="${sampleInstance.requestedTagNumber}" />
            </div>

            <div class=" ${hasErrors(bean: sampleInstance, field: 'quantityReceived', 'error')} ">
            <label for="quantityReceived">Quantity Received</label>
            <g:field name="quantityReceived" type="number" value="${sampleInstance.quantityReceived}" />
            </div>

            <div class=" ${hasErrors(bean: sampleInstance, field: 'concentration', 'error')}">
            <label for="concentration">Concentration</label>
            <g:field name="concentration" value="${fieldValue(bean: sampleInstance, field: 'concentration')}" />
            </div>

            <div class=" ${hasErrors(bean: sampleInstance, field: 'cellNumber', 'error')}">
            <label for="cellNumber">
                <g:message code="sample.cellNumber.label" default="Cell Number" />
            </label>
            <g:field name="cellNumber" type="number" value="${sampleInstance.cellNumber}" />
            </div>

            <div class=" ${hasErrors(bean: sampleInstance, field: 'chromosomeAmount', 'error')}">
            <label for="chromosomeAmount">Chromosome Amount</label>
            <g:field name="chromosomeAmount" type="number" value="${sampleInstance.chromosomeAmount}" />
            </div>

            <h3>Replicates</h3>
            <div class=" ${hasErrors(bean: sampleInstance, field: 'biologicalReplicateSet', 'error')} ">
            <label for="biologicalReplicateSet">Biological Replicate Set</label>
            <g:select id="biologicalReplicateSet" name="biologicalReplicateSet.id" from="${pegr.BiologicalReplicateSet.list()}" optionKey="id" value="${sampleInstance?.biologicalReplicateSet?.id}" class="many-to-one" noSelection="['null': '']"/>
            </div>

            <div class=" ${hasErrors(bean: sampleInstance, field: 'technicalReplicateSet', 'error')} ">
            <label for="technicalReplicateSet">Technical Replicate Set</label>
            <g:select id="technicalReplicateSet" name="technicalReplicateSet.id" from="${pegr.TechnicalReplicateSet.list()}" optionKey="id" value="${sampleInstance?.technicalReplicateSet?.id}" class="many-to-one" noSelection="['null': '']"/>
            </div>
            <h3>Related Projects</h3>
            <div class=" ${hasErrors(bean: sampleInstance, field: 'projects', 'error')} ">
                <label for="projects">Projects</label>
            </div>

            <div class=" ${hasErrors(bean: sampleInstance, field: 'publicationReference', 'error')} ">
        <label for="publicationReference">Publication Reference</label>
        <g:textField name="publicationReference" maxlength="30" value="${sampleInstance?.publicationReference}"/>
        </div>

        <div class=" ${hasErrors(bean: sampleInstance, field: 'note', 'error')} ">
        <label for="note">Note</label>
        <g:textArea  name="note" value="${sampleInstance?.note}"/>
        </div>