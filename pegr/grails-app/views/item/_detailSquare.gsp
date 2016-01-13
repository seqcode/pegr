<div class="col-md-3" style="border: 1px solid; margin: 5px;">
	<h5>${itemInstance?.type?.name}</h5>
    <g:render template="/item/details" bean="${itemInstance}" var="itemInstance" />
</div>