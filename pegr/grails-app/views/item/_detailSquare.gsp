<div class="col-md-3 item">
	<h5>${itemInstance?.type?.name}</h5>
    <g:render template="/item/details" bean="${itemInstance}" var="itemInstance" />
</div>