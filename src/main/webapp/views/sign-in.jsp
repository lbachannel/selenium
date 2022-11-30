<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/views/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%@ include file="/views/common/header.jsp"%>
	
	<section class="login">
            <div class="m-5 d-flex justify-content-center">


                
				<form class="form" action="login" method="POST">
					<div class="card">
						<div class="card-header">
							<b>Login</b>
						</div>
						<div class="card-body">
							<div class="form-group">
								<label for="username">Username</label>
								<input type="text" class="form-control" name="username" id="username" placeholder="Input username">
							</div>
							<div class="form-group">
								<label for="password">Password</label>
								<input type="password" class="form-control" name="password" id="password" placeholder="Input password">
							</div>
						</div>
						<div class="card-footer text-muted">
							<button class="btn" name="btnlogin">Login</button>
						</div>
					</div>
				</form>
 
            </div>
        </section>
	
	
	
	<%@ include file="/views/common/footer.jsp"%>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous" ></script>
</body>
</html>