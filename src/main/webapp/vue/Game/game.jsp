<div class="container">
	<h1 class="my-2">Ajoutez un jeu: </h1>
	<form method="post">
		<div class="mb-3">
			<label for="title" class="form-label">Titre</label>
			 <input type="text" class="form-control" name="title" />
		</div>
		<div class="mb-3">
			<label for="min_players" class="form-label">Nombre minimum de joueurs</label>
			 <input type="number" class="form-control" maxlength="250" name="min_players" />
				
		</div>
		<div class="mb-3">
			<label for="max_players" class="form-label">Nombre minimum de joueurs</label> 
			<input type="text" class="form-control" name="max_players" />
		</div>
		<button type="submit" class="btn btn-primary m-2">Enregistrer jeu</button>
		
	</form>
	<c:if test="${error != null}">
		<div class="alert alert-danger mt-2">
			<c:out value="${error }" default="" />
		</div>
	</c:if>
</div>