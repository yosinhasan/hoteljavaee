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
					<c:if test="${responseError != null }">
						<div class="requestInfo alert alert-danger" role="alert">
							<a href="#" class="alert-link"> <strong>${responseError }</strong>
							</a>
						</div>
					</c:if>
					<div class="text-uppercase alert alert-info">
						<strong> ${lang.availableRoomsForPeriod}: ${checkIn }-${checkOut }
						</strong>
					</div>
					<div class="text-uppercase alert alert-info">
						<strong> ${lang.adults}: ${requestScope['amount'] } </strong>
					</div>
					<article>
						<c:choose>
							<c:when test="${rbean == null || rbean.size() <= 0 }">
									${lang.noAvailable }
							</c:when>
							<c:otherwise>
								<div class="table-responsive">
									<form action="controller?command=responses" method="post">
										<input type="hidden" name="action" value="responesTrue" />
										<table class="table">
											<thead>
												<tr class="active">
													<th>${lang.typeOfRoom }</th>
													<th>${lang.pricePerDay }</th>
													<th>${lang.totalPrice }</th>
													<th>${lang.maxPerRoom }</th>
													<th>${lang.numbers }</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${rbean }">
													<tr class="active">
														<td>${item.getName() }</td>
														<td>${item.getPrice() }</td>
														<td>${item.getTotalPrice() }</td>
														<td>${item.getMax() }</td>
														<td><select name="number" class="form-control">
																<c:forEach var="item2" items="${item.getRooms() }">
																	<option value="${item2.getId() }">${item2.getNumber() }</option>
																</c:forEach>
														</select></td>
													</tr>
												</c:forEach>
												<tr>
													<td colspan="5"><textarea name="msg"
															class="form-control" rows="6" id="responseTable"></textarea></td>
												</tr>
												<tr>
													<td colspan="5">
														<button class="btn btn-primary btn-large">${lang.respond }</button>
													</td>
												</tr>
											</tbody>
										</table>
									</form>
								</div>
							</c:otherwise>
						</c:choose>

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