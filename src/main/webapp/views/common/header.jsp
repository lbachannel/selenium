<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div>
	<!-- ======= Header ======= -->
	<header id="header">
		<div class="container d-flex align-items-center">
			<h1 class="logo me-auto">
				<a href="index.html">AT TEAM</a>
			</h1>

			<!--List group & navbar-->
			<nav id="navbar" class="navbar order-lg-0">
				<ul>
					<c:choose>
						<c:when test="${not empty sessionScope.currentUser}">
							<li><a href="index" class="nav-link scrollto active"> Home</a></li>
							<li><a href="#" name="fullname" class="nav-link scrollto"> Welcome ${sessionScope.currentUser.fullname}</a></li>
							<li><a href="user" class="nav-link scrollto"> User Management</a></li>
							<li><a href="logout" class="nav-link scrollto"> Logout</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="index" class="nav-link scrollto active"> Home</a></li>
							<li><a href="#" class="nav-link scrollto"> Welcome you</a></li>
							<li><a href="login" class="nav-link scrollto"> Login</a></li>
						</c:otherwise>
					</c:choose>
				</ul>

			</nav>
		</div>
		<!-- </header> -->
</div>