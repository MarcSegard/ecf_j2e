<div class="container">
	<div class="row mt-4">
		<div class="card col-6">
			<h2 class="mb-3 mr-2">Liste des joueurs</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">email</th>
						<th scope="col">pseudo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ joueurs }" var="joueur" varStatus="status">
						<tr>
							<td><c:out value="${ joueur.email }" /></td>
							<td><c:out value="${ joueur.nickname }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="add-player">
				<button class="btn btn-success mb-3">Ajouter un joueur</button>
			</a>
		</div>
		<div class="card col-6">
			<h2 class="mb-3">Liste des jeux disponibles</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Titre</th>
						<th scope="col">Nbre min de joueurs</th>
						<th scope="col">Nbre max de joueurs</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ games }" var="game" varStatus="status">
						<tr>
							<td><c:out value="${ game.title }" /></td>
							<td><c:out value="${ game.min_players}" /></td>
							<td><c:out value="${ game.max_players}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="add">
				<button class="btn btn-success">Ajouter un jeu</button>
			</a>
		</div>
	</div>
	<div class="row mt-3">
		<div class="card col-6">
			<h2 class="mb-3">Tournois en cours</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Game</th>
						<th scope="col">Date</th>
						<th scope="col">Ajouter un joueur</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ contests_in_progress }" var="contest"
						varStatus="status">
						<tr>
							<td><c:out value="${contest.game.title }" /></td>
							<td><c:out value="${contest.start_date}" /></td>
							<td><a
								href="gestion-contest?id=<c:out value="${contest.id}"/>"><i
									class="fa-solid fa-user-plus"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="add-contest">
				<button class="btn btn-success mb-3">Ajouter un tournoi</button>
			</a>
		</div>
	</div>
	<div class="row card  mt-3">
		<h2 class="mb-3 ml-3">Résultats des tournois</h2>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Game</th>
					<th scope="col">Date</th>
					<th scope="col">Gagnant</th>
					<th scope="col">Nbre de joueurs</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ contests }" var="contest" varStatus="status">
					<tr>
						<td><c:out value="${contest.game.title }" /></td>
						<td><c:out value="${contest.start_date}" /></td>
						<td><c:out value="${contest.winner.nickname}" /></td>
						<td><c:out value="${contest.nbre_players}" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>