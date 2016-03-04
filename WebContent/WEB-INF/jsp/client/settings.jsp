<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="${lang.cabinet }" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<!-- start header -->
	<%@ include file="/WEB-INF/jspf/client/header.jspf"%>
	<!--start main -->
	<div class="main_bg">
		<div class="wrap">

			<div class="main">
				<%@ include file="/WEB-INF/jspf/client/left.jspf"%>
				<div class="_content pull-right">
					<div class="well text-uppercase">
						<strong>${lang.settings }</strong>
					</div>
					<c:if test="${settingsInfo != null }">
						<div class="requestInfo alert alert-success" role="alert">
							<a href="#" class="alert-link"> <strong>${settingsInfo }</strong>
							</a>
						</div>
					</c:if>

					<article>
						<form class="form-horizontal" action="controller?command=settings"
							method="post">
							<input type="hidden" name="action" value="saveSettings">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">${lang.name }</label>
								<div class="col-sm-10">
									<input id="disabledInput" disabled="disabled" type="text"
										value="${user.getName() }" class="form-control"
										id="inputEmail3">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">${lang.surname }</label>
								<div class="col-sm-10">
									<input id="disabledInput" disabled="disabled" type="text"
										value="${user.getSurname() }" class="form-control"
										id="inputEmail3">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">${lang.phone }</label>
								<div class="col-sm-10">
									<input type="text" value="${user.getPhone() }" name="phone"
										pattern="^\+\d{2}\(\d{3}\)\d{3}-\d{2}-\d{2}$"
										class="form-control" id="inputEmail3"
										placeholder="${lang.inputPhone }" required="required">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">${lang.email }</label>
								<div class="col-sm-10">
									<input value="${user.getEmail() }" type="email" name="email"
										class="form-control" id="inputEmail3"
										placeholder="${lang.inputEmail }" required="required">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">${lang.oldPassword }</label>
								<div class="col-sm-10">
									<input type="password" name="password" class="form-control"
										id="inputPassword3" placeholder="${lang.inputPassword }"
										pattern="^.{6,}$" required="required">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">${lang.newPassword }</label>
								<div class="col-sm-10">
									<input type="password" name="newPassword" min="6"
										class="form-control" id="inputPassword3" pattern="^.{6,}$"
										placeholder="${lang.inputPassword }" required="required">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-primary btn-lg">${lang.save }</button>
								</div>
							</div>
						</form>
					</article>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<!--start footer -->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>