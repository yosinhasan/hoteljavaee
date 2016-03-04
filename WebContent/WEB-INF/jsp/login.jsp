<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="${lang.signIn }" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<!-- start header -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<div class="contact">
					<c:if test="${errorSignIn != null }">
						<div class="alert alert-danger" role="alert">${errorSignIn }</div>
					</c:if>
					<form class="form-horizontal col-sm-10" action="controller?command=login"
						method="post">
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
									required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
							<!--  
									<label> <input type="checkbox">
										${lang.rememberMe }
									</label> -->
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary btn-lg">${lang.signIn }</button>
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