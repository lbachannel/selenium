<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRUD User managerment system</title>
</head>
<body>
	<%@ include file="/views/common/header.jsp"%>
	
	<main class="container mt-5 mb-5">
		<div class="row">
			<div class="offset-2 col-8">
				<c:url var="url" value="/selenium"/>
				<!-- Form -->
				<form action="${url}/user" method="post">
					<div class="card">
						<div class="card-header text-success text-center">
							<!-- Thong Bao -->
							${message}
						</div>
						<div class="card-body">
							<!-- Id -->
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">Id</label>
								<div class="col-sm-10">
									<input class="form-control" value="${form.id}" name="id" placeholder="Username">
								</div>
							</div>
							<!-- password -->
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">Password</label>
								<div class="col-sm-10">
									<input class="form-control" value="${form.password}" name="password" placeholder="Password">
								</div>
							</div>
							<!-- fullname -->
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">Fullname</label>
								<div class="col-sm-10">
									<input class="form-control" value="${form.fullname}" name="fullname" placeholder="Fullname">
								</div>
							</div>
							<!-- email -->
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">Email</label>
								<div class="col-sm-10">
									<input class="form-control" value="${form.email}" name="email" placeholder="Email">
								</div>
							</div>
							<!-- role -->
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">Role</label>
								<div class="col-sm-10 mt-2">
									<input ${form.admin?'checked':''} type="radio" value="true" name="admin"> Admin
									<input ${form.admin?'':'checked'} type="radio" value="false" name="admin"> User
								</div>
							</div>
							<div class="card-footer">
								<button class="btn btn-success" formaction="/selenium/create">Create</button>
								<button class="btn btn-primary" formaction="/selenium/update">Update</button>
								<button class="btn btn-danger" formaction="/selenium/delete">Delete</button>
								<a class="btn btn-light" href="/selenium/reset">Reset</a>
							</div> <!-- End card-footer -->
						</div>
						
					</div>
				</form>
				<!-- Table -->
				<table class="table" border="1" style="width:100%">
					<!-- Thead -->
					<thead class="thead-dark">
						<tr>
					      	<th scope="col">Id</th>
					      	<th scope="col">Password</th>
					      	<th scope="col">Fullname</th>
					      	<th scope="col">Email</th>
					      	<th scope="col">Admin</th>
					      	<th scope="col">Edit</th>
					    </tr>
					</thead>
					<!-- tbody -->
					<tbody>
						<c:forEach var="item" items="${items}">
							<tr>
								<td>${item.id}</td>
								<td>${item.password}</td>
								<td>${item.fullname}</td>
								<td>${item.email}</td>
								<td>${item.admin?'Admin':'User'}</td>
								<td><a href="/selenium/edit/${item.id}">Edit</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</main>
	
	<%@ include file="/views/common/footer.jsp"%>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous" ></script>
</body>
</html>