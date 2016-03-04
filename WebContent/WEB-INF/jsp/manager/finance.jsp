<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="${lang.cabinet }" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<!-- start header -->
	<%@ include file="/WEB-INF/jspf/manager/header.jspf"%>
	<!--start main -->
	<div class="main_bg">
		<div class="wrap">

			<div class="main">
				<%@ include file="/WEB-INF/jspf/manager/left.jspf"%>
				<div class="_content pull-right">
					<div class="well text-uppercase">
						<strong>${user.getName() } ${user.getSurname() } </strong>

					</div>
					<article>
						<div class="fresh-table full-color-blue">
							<table id="fresh-table" class="table">
								<thead>
									<tr>
										<th>${lang.name }</th>
										<th>${lang.bill }</th>
										<th>${lang.checkInDate }</th>
										<th>${lang.checkOutDate }</th>
										<th>${lang.typeOfRoom }</th>
										<th>${lang.roomNumber }</th>
										<th>${lang.status }</th>
										<th>${lang.action }</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${requestScope['finances'] }">
										<tr>
											<td>${item.getUsername() }</td>
											<td>${item.getBill() }$</td>
											<td>${item.getCheckIn() }</td>
											<td>${item.getCheckOut() }</td>
											<td>${item.getType() }</td>
											<td>${item.getNumber() }</td>
											<td>${item.getStatus() }</td>
											<td>
												<form action="controller?command=finance" method="post">
													<input type="hidden" name="userId" value="${item.getUserId() }" />
													<input type="hidden" name="id" value="${item.getId() }" />
													<input type="hidden" name="act" value="1" />
													<button type="submit" class="btn btn-primary btn-sm">${lang.confirm }</button>
												</form>
											<td>
												<form action="controller?command=finance" method="post">
													<input type="hidden" name="userId" value="${item.getUserId() }" />
													<input type="hidden" name="id" value="${item.getId() }" />
													<input type="hidden" name="act" value="0" />
													<button type="submit" class="btn btn-primary btn-sm">${lang.decline }</button>
												</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
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