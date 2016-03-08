<div>
	<label for="species">Species</label>
	<g:select id="species" name="species.id" from="${pegr.Species.list()}" optionKey="id" value="${cellSource?.strain?.species?.id}" noSelection="['null': 'Other']" onchange="speciesChanged(this.value);"/>
</div>
<ul id="speciesForm">
    <li>
        <label>Genus Name</label>
        <g:textField name="genusName"></g:textField>
    </li>
    <li>
        <label>Species Name</label>
        <g:textField name="speciesName"></g:textField>
    </li>
</ul>
<div>
	<label for="strain">Strain</label>
	<g:select id="strain" name="strain.id" from="${pegr.Strain.where{species == cellSource?.strain?.species}.list()}" optionKey="id" value="${cellSource?.strain?.id}" noSelection="['null': 'Other']" onchange="strainChanged(this.value);"/>
</div>

<div id="strainDetails">
    <g:if test="${cellSource?.strain?.id}">
        <g:render template="/cellSource/strainDetails" model="['strain': cellSource?.strain]"></g:render>
    </g:if>
    <g:else>
        <g:render template="/cellSource/strainForm" model="['strain': cellSource?.strain]"></g:render>
    </g:else>
</div>

<div class=" ${hasErrors(bean: cellSource, field: 'sex', 'error')} ">
    <label for="sex">Sex </label>
    <g:select id="sex" name="sex.id" from="${pegr.Sex.list()}" optionKey="id" value="${cellSource?.sex?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: cellSource, field: 'age', 'error')} ">
    <label for="age">Age</label>
    <g:textField id="age" name="age" value="${cellSource?.age}"/>

</div>

<div class=" ${hasErrors(bean: cellSource, field: 'tissue', 'error')} ">
    <label for="tissue">Tissue</label>
    <g:select id="tissue" name="tissue.id" from="${pegr.Tissue.list()}" optionKey="id" value="${cellSource?.tissue?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: cellSource, field: 'histology', 'error')} ">
    <label for="histology">Histology</label>
    <g:select id="histology" name="histology.id" from="${pegr.Histology.list()}" optionKey="id" value="${cellSource?.histology?.id}" noSelection="['null': 'Other']"/>
</div>

<div>
    <label>Treatments</label>
    <span id="treatments">
    <g:select multiple="multiple" name="treatments" from="${pegr.CellSourceTreatment.list()}" optionKey="id" value="${cellSource?.treatments}" class="tokenize tokenize-sample"></g:select>
    </span>
    <a href="#" class="edit" data-toggle="modal" data-target="#new-treatment"><span class="glyphicon glyphicon-plus"></span>New</a>
</div>

<div class=" ${hasErrors(bean: cellSource, field: 'growthMeida', 'error')} ">
    <label for="growthMedia">Growth Media</label>
    <g:select id="growthMedia" name="growthMedia.id" from="${pegr.GrowthMedia.where{if(cellSource?.strain?.species?.id){species == cellSource.strain.species}}.list()}" optionKey="id" value="${cellSource?.growthMedia?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: cellSource, field: 'providerUser', 'error')} ">
    <label for="providerUser">Provider User </label>
    <g:select id="providerUser" name="providerUser.id" from="${pegr.User.list()}" optionKey="id" value="${cellSource?.providerUser?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: cellSource, field: 'providerLab', 'error')} ">
    <label for="providerLab">Provider Lab</label>
    <g:select id="providerLab" name="providerLab.id" from="${pegr.Lab.list()}" optionKey="id" value="${cellSource?.providerLab?.id}" noSelection="['null': '']"/>
</div>

<div class=" ${hasErrors(bean: cellSource, field: 'biologicalSourceId', 'error')} ">
    <label for="biologicalSourceId">Biological Source ID</label>
    <g:textField name="biologicalSourceId" maxlength="50" value="${cellSource?.biologicalSourceId}"/>
</div>

<script>
    $(document).ready(function(){
        $(".tokenize").tokenize({newElements: false});
        if($( "#species option:selected" ).text() != "Other") {
            $("#speciesForm").hide();
        }
    });
    
    function speciesChanged(speciesId) {
        if(speciesId == "null") {
            $("#speciesForm").show();
        } else {
            $("#speciesForm").hide();
        }    
        $.ajax({url: "/pegr/cellSource/fetchStrainsForSpeciesAjax/"+speciesId, success: function(result){
            $("#strain").html(result)
        }});
        strainChanged();
        
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
