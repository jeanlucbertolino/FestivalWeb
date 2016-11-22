<%-- Main admin menu --%>

<nav>

	<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}"/>

	<ul>
		<li><a href="#">Accueil</a></li>
		<li><a href="#" onclick="callBandController('Groupe')">Groupe</a></li>
		<li><a href="#">Sc&egrave;nes</a></li>
		<li><a href="#">Billeterie</a></li>
	</ul>
</nav>