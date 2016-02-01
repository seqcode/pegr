<%@ page import="pegr.CellSource" %>
<%@ page import="pegr.Item" %>

<div class=" ${hasErrors(bean: item, field: 'name', 'error')} ">
    <label>Name</label>
    <g:textField name="name" value="${item?.name}"/>
</div>

<div class=" ${hasErrors(bean: item, field: 'type', 'error')} ">
    <label>Type</label>
    <select id="type" name="type.id">
        <option value="${item?.type?.id}" selected>${item?.type?.name}</option>
    </select>
</div>

<div class=" ${hasErrors(bean: object, field: 'strain', 'error')} required">
    <label for="strain">Strain * </label>
    <g:select id="strain" name="strain.id" from="${pegr.Strain.list()}" optionKey="id" required="" value="${object?.strain?.id}" onchange="strainChanged(this.value);" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: object, field: 'sex', 'error')} ">
    <label for="sex">Sex </label>
    <g:select id="sex" name="sex.id" from="${pegr.Sex.list()}" optionKey="id" value="${object?.sex?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: object, field: 'age', 'error')} ">
    <label for="age">Age</label>
    <g:textField id="age" name="age" value="${object?.age}"/>

</div>

<div class=" ${hasErrors(bean: object, field: 'tissue', 'error')} ">
    <label for="tissue">Tissue</label>
    <g:select id="tissue" name="tissue.id" from="${pegr.Tissue.list()}" optionKey="id" value="${object?.tissue?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: object, field: 'histology', 'error')} ">
    <label for="histology">Histology</label>
    <g:select id="histology" name="histology.id" from="${pegr.Histology.list()}" optionKey="id" value="${object?.histology?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: object, field: 'growthMeida', 'error')} ">
    <label for="growthMedia">Growth Media</label>
    <g:select id="growthMedia" name="growthMedia.id" from="${pegr.GrowthMedia.list()}" optionKey="id" value="${object?.growthMedia?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: object, field: 'providerUser', 'error')} ">
    <label for="providerUser">Provider User </label>
    <g:select id="providerUser" name="providerUser.id" from="${pegr.User.list()}" optionKey="id" value="${object?.providerUser?.id}" noSelection="['null': '']"/>

</div>

<div class=" ${hasErrors(bean: object, field: 'providerLab', 'error')} ">
    <label for="providerLab">Provider Lab</label>
    <g:select id="providerLab" name="providerLab.id" from="${pegr.Lab.list()}" optionKey="id" value="${object?.providerLab?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: object, field: 'biologicalSourceId', 'error')} ">
    <label for="biologicalSourceId">Biological Source ID</label>
    <g:textField name="biologicalSourceId" maxlength="50" value="${object?.biologicalSourceId}"/>
</div>

<div class=" ${hasErrors(bean: item, field: 'barcode', 'error')} ">
    <label>Barcode</label>
    <g:field name="barcode" value="${item?.barcode}" readonly="readonly" />
</div>

<div class=" ${hasErrors(bean: item, field: 'location', 'error')} ">
    <label>Location</label>
    <g:textField name="location" value="${item?.location}"/>
</div>

<div class=" ${hasErrors(bean: item, field: 'notes', 'error')} ">
    <label>Notes</label>
    <g:textArea name="notes" value="${item?.notes}"/>
</div>

<label>Cell Source Treatments</label>
<div class="row">
    <div class="col-sm-4">
	   <g:select name="from[]" id="search" class="form-control" multiple="multiple" from="${pegr.CellSourceTreatment.list()}" optionKey="id"></g:select>
	</div>
    <div class="col-sm-1">
        <button type="button" id="search_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
		<button type="button" id="search_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
    </div>
    <div class="col-sm-4">
        <select name="treatments" id="search_to" class="form-control" size="5" multiple="multiple">
            <g:each in="${object?.treatments}">
            <option value="${it.id}" >${it}</option>
            </g:each>
        </select>
    </div>
</div>
<script>
    function strainChanged(strainId) {
        <g:remoteFunction controller="item" action="strainChangedAjax"
            update="growthMedia"
            params="'strainId='+strainId"/>
    }

jQuery(document).ready(function($) {
	$('#search').multipleselect({
		search: {
			left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
		}, unique: "true",
	});    
});

</script>
