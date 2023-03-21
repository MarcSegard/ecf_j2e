<div class="container">
	<h1 class="my-2">Ajoutez un joueur: </h1>
	<form method="post">
		<div class="mb-3">
			<label for="email" class="form-label">Email</label>
			 <input type="text" class="form-control" name="email" />
		</div>
		<div class="mb-3">
			<label for="nickname" class="form-label">Pseudo</label>
			 <input type="text" class="form-control" maxlength="250" name="nickname" />
		</div>
		<button type="submit" class="btn btn-primary m-2">Enregistrer le joueur</button>
		
	</form>
	<c:if test="${error != null}">
		<div class="alert alert-danger mt-2">
			<c:out value="${error }" default="" />
		</div>
	</c:if>
</div>