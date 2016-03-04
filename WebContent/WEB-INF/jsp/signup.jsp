<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="${lang.signUp }" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<!-- start header -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<div class="contact">
					<c:if test="${errorSignUp != null }">
						<c:forEach var="item" items="${errorSignUp}">
							<div class="alert alert-success" role="alert">${item }</div>
						</c:forEach>
					</c:if>
					<form class="form-horizontal col-sm-10" action="controller?command=signup"
						method="post">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">${lang.name }</label>
							<div class="col-sm-10">
								<input pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F\u0451\u0401]{3,}$" type="text"
									name="name" class="form-control" id="inputEmail3"
									placeholder="${lang.inputName }" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">${lang.surname }</label>
							<div class="col-sm-10">
								<input pattern="^[a-zA-Z\u0430-\u044F\u0410-\u042F\u0451\u0401]{3,}$" type="text"
									name="surname" class="form-control" id="inputEmail3"
									placeholder="${lang.inputSurname }" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">${lang.phone }</label>
							<div class="col-sm-10">
								<input type="text" name="phone"
									pattern="^\+\d{2}\(\d{3}\)\d{3}-\d{2}-\d{2}$"
									class="form-control" id="inputEmail3"
									placeholder="${lang.inputPhone }" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">${lang.email }</label>
							<div class="col-sm-10">
								<input type="email" name="email" class="form-control"
									id="inputEmail3" placeholder="${lang.inputEmail }"
									required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">${lang.password }</label>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control"
									id="inputPassword3" placeholder="${lang.inputPassword }"
									pattern="^.{6,}$" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">${lang.repeatPassword }</label>
							<div class="col-sm-10">
								<input type="password" name="repeatPassword" min="6"
									class="form-control" id="inputPassword3" pattern="^.{6,}$"
									placeholder="${lang.inputRepeatPassword }" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary btn-lg">${lang.signUp }</button>
							</div>
						</div>
					</form>




					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	<!--start footer -->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>