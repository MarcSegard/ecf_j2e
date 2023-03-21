<div class="container">
	<h1 class="my-2">Ajoutez un tournoi:</h1>
	<form method="post">
		<div class="mb-3">
			<label for="game-select">Choisir le jeu pour le tournoi:</label> 
			<select name="game_id" class="form-control" id="game-select">
				<c:forEach items="${ games }" var="game" varStatus="status">
					<option value="<c:out value="${game.id }"/>"><c:out
							value="${game.title }" /></option>
				</c:forEach>
			</select>
		</div>
		<div class="mb-3">
			<label for="date" class="form-label">Date du tournoi :</label> 
			<input type="date" class="form-control" maxlength="250" name="date" />
		</div>
		<button type="submit" class="btn btn-primary m-2">Enregistrer
			le tournoi</button>

	</form>
	<c:if test="${error != null}">
		<div class="alert alert-danger mt-2">
			<c:out value="${error }" default="" />
		</div>
	</c:if>
</div>