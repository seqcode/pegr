<%@ page import="pegr.CellSource" %>

<div>
	<label for="species">Species</span>
	</label>
	<g:select id="species" name="species.id" from="${pegr.Species.list()}" optionKey="id" required="" value="${object?.strain?.species?.id}" noSelection="['null': '']" onchange="speciesChanged(this.value);"/>
</div>

<div>
	<label for="name">Strain</label>
	<g:select id="strain" name="strain.id" from="${pegr.Strain.where{if(object?.strain?.species){species == object.strain.species}}.list()}" optionKey="id" required="" value="${object?.strain?.id}" noSelection="['null': 'other']" onchange="strainChanged(this.value);"/>
</div>

<div id="strainDetails">
    <g:if test="${object?.strain}">
        <g:render template="strainDetails" model="['strain': object.strain]"></g:render>
    </g:if>
    <g:else>
        <g:render template="strainForm"></g:render>
    </g:else>
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

<div>
    <label>Treatments</label>
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

<script>
    function speciesChanged(speciesId) {
        $.ajax({url: "/pegr/cellSource/fetchStrainsForSpeciesAjax/"+speciesId, success: function(result){
            $("#strain").html(result)
        }});
        
        $.ajax({url: "/pegr/cellSource/fetchGrowthMediasForSpeciesAjax/"+speciesId, success: function(result){
            $("#growthmedia").html(result)
        }});
    }
    
    function strainChanged(strainId) {
        $.ajax({url: "/pegr/cellSource/showStrainDetailsAjax/"+strainId, success: function(result){
            $("#strainDetails").html(result)
        }});
    }
</script>
