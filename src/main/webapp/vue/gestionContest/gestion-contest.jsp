<div class="container">
	<h1>
		Gestion du tournoi <span class="text-danger">"<c:out
				value="${nom_jeu}" />"
		</span>
	</h1>
	<div class="row">
		<div class="col-6">
			<h2 class="mb-3">Liste des joueurs</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Email</th>
						<th scope="col">Pseudo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ players }" var="player" varStatus="status">
						<tr>
							<td><c:out value="${player.email }" /></td>
							<td><c:out value="${player.nickname}" /></td>
							<td><a
								href="delete-user-contest?id_user=<c:out value="${player.id}"/>&id_contest=<c:out value="${id_contest}"/>"><i
									class="fa-solid fa-user-slash"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${nbre_joueur < min_joueur}">
				<div class="alert alert-danger">
					Il manque
					<c:out value="${ min_joueur - nbre_joueur}" />
					pour commencer le tournoi.
				</div>
			</c:if>
			<c:if test="${nbre_joueur > max_joueur}">
				<div class="alert alert-danger">
					Il y a
					<c:out value="${ nbre_joueur - max_joueur}" />
					joueur(s) en trop pour commencer le tournoi.
				</div>
			</c:if>
		</div>
		<div class="col-6">
			<h1 class="my-2">Ajoutez un joueur:</h1>
			<form method="post">
				<div class="mb-3">
					<input type="hidden" name="action" value="add_player"> <input
						type="hidden" name="contest_id" value=${id_contest }> <label
						for="game-select">Choisir le joueur à ajouter:</label> <select
						name="player_id" class="form-control" id="game-select">
						<c:forEach items="${ all_players }" var="player"
							varStatus="status">
							<option value="<c:out value="${player.id }"/>"><c:out
									value="${player.email }" /></option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-primary m-2">Ajouter
					le joueur</button>

			</form>
			<c:if test="${error != null}">
				<div class="alert alert-danger mt-2">
					<c:out value="${error }" default="" />
				</div>
			</c:if>
			<h1 class="my-2">Sélectionner le vainqueur:</h1>
			<form method="post">
				<div class="mb-3">
					<input type="hidden" name="action" value="winner"> <input
						type="hidden" name="contest_id" value=${id_contest }> <label
						for="game-select">Choisir le joueur à ajouter:</label> <select
						name="player_id" class="form-control" id="game-select">
						<c:forEach items="${ all_players }" var="player"
							varStatus="status">
							<option value="<c:out value="${player.id }"/>"><c:out
									value="${player.email }" /></option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-primary m-2">Valider</button>

			</form>
			<c:if test="${error != null}">
				<div class="alert alert-danger mt-2">
					<c:out value="${error }" default="" />
				</div>
			</c:if>
		</div>
	</div>
</div>