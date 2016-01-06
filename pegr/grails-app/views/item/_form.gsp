		<div class=" ${hasErrors(bean: itemInstance, field: 'barcode', 'error')} ">
			<label for="barcode">Barcode</label>
			<g:textField id="barcode" name="barcode" value="${itemInstance?.barcode}"/>
			<input type=button value="Scan" onclick="getScan();">
		</div>
		
		<div class=" ${hasErrors(bean: itemInstance, field: 'location', 'error')} ">
			<label for="location">Location</label>
			<g:textField name="location" value="${itemInstance?.location}"/>
		</div>
		
		<div class=" ${hasErrors(bean: itemInstance, field: 'note', 'error')} ">
			<label for="note">Note</label>
			<g:textArea name="note" value="${itemInstance?.note}"/>
		</div>