<%@ page import="pegr.CellSource" %>
<div class="row">
	<div class="col-md-6 fields">
		<div class=" ${hasErrors(bean: cellSourceInstance, field: 'strain', 'error')} required">
			<label for="strain">Strain * </label>
			<g:select id="strain" name="strain.id" from="${pegr.Strain.list()}" optionKey="id" required="" value="${cellSourceInstance?.strain?.id}" class="many-to-one"/>
		</div>

		<div class=" ${hasErrors(bean: cellSourceInstance, field: 'sex', 'error')} ">
			<label for="sex">Sex </label>
			<g:select id="sex" name="sex.id" from="${pegr.Sex.list()}" optionKey="id" value="${cellSourceInstance?.sex?.id}" class="many-to-one" noSelection="['null': '']"/>
		</div>
		
		<div class=" ${hasErrors(bean: cellSourceInstance, field: 'cellSourceTreatments', 'error')} ">
			<label for="cellSourceTreatments">Treatments</label>
			<g:select name="cellSourceTreatments" from="${pegr.CellSourceTreatment.list()}" multiple="multiple" optionKey="id" size="5" value="${cellSourceInstance?.cellSourceTreatments*.id}" class="many-to-many"/>
		</div>
		
	</div>
	<div class="col-md-6 fields">
		<div class=" ${hasErrors(bean: cellSourceInstance, field: 'providerUser', 'error')} ">
			<label for="providerUser">Provider User </label>
			<g:select id="providerUser" name="providerUser.id" from="${pegr.User.list()}" optionKey="id" value="${cellSourceInstance?.providerUser?.id}" class="many-to-one" noSelection="['null': '']"/>
		
		</div>
		
		<div class=" ${hasErrors(bean: cellSourceInstance, field: 'providerLab', 'error')} ">
			<label for="providerLab">Provider Lab</label>
			<g:select id="providerLab" name="providerLab.id" from="${pegr.Lab.list()}" optionKey="id" value="${cellSourceInstance?.providerLab?.id}" class="many-to-one" noSelection="['null': '']"/>
		</div>
		
		<div class=" ${hasErrors(bean: cellSourceInstance, field: 'biologicalSourceId', 'error')} ">
			<label for="biologicalSourceId">Biological Source ID</label>
			<g:textField name="biologicalSourceId" maxlength="50" value="${cellSourceInstance?.biologicalSourceId}"/>
		</div>
		
        <g:render template="/item/form" bean="${itemInstance}" />
        
	</div>
</div>
