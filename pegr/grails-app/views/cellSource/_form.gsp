<%@ page import="pegr.CellSource" %>

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

<script>
    function strainChanged(strainId) {
        <g:remoteFunction controller="cellSource" action="strainChangedAjax"
            update="growthMedia"
            params="'strainId='+strainId"/>
    }
</script>
