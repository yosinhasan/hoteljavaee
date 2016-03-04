<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="${lang.reservation }" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<!-- start header -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<c:choose>
					<c:when test="${action == 'initial' }">
						<div class="res_online">
							<c:if test="${reservationError != null }">
								<div class="alert alert-danger" role="alert">${reservationError }</div>
							</c:if>
							<c:if test="${info != null }">
								<div class="reservationInfo alert alert-success" role="alert">${info }</div>
							</c:if>
							<h4>${lang.basicInformation }</h4>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr class="active">
											<th>${lang.typeOfRoom }</th>
											<th>${lang.pricePerDay }</th>
											<th>${lang.maxPerRoom }</th>
											<th>${lang.details }</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${roomTypes }">
											<tr class="active">
												<td>${item.getName() }</td>
												<td>${item.getPrice() }</td>
												<td>${item.getMax() }</td>
												<td><a
													href="controller?command=details&id=${item.getId() }">${lang.view }</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<form action="controller?command=reservation" method="post">

							<div class="span_of_2">
								<div class="span2_of_1">
									<h4>${lang.checkInDate }:</h4>
									<div class="book_date btm">
										<input name="checkIn" class="date" id="datepicker" type="text"
											required="required"
											pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
											value="${lang.ddmmyy}" onfocus="this.value = '';"
											onblur="if (this.value == '') {this.value = '${lang.ddmmyy}';}">
									</div>
									<div class="sel_room">
										<h4>${lang.selectTypeRoom }</h4>
										<select name="roomTypeId"
											onchange="change_country(this.value)"
											class="frm-field required">
											<option value="0">${lang.selectTypeRoom }</option>
											<c:forEach var="item" items="${roomTypes }">
												<option value="${item.getId() }">${item.getName() }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="span2_of_1">
									<h4>${lang.checkOutDate }:</h4>
									<div class="book_date btm">
										<input name="checkOut" required="required" class="date"
											id="datepicker1" type="text" value="${lang.ddmmyy}"
											required="required"
											pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
											onfocus="this.value = '';"
											onblur="if (this.value == '') {this.value = '${lang.ddmmyy}';}">
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<div class="res_btn">
								<input type="hidden" name="action" value="reservationTrue">
								<input type="submit" value="${lang.bookNow }">
							</div>
						</form>
					</c:when>
					<c:otherwise>
						<div class="res_online">
							<h2>${lang.availableRoomsForPeriod}:${checkIn }-${checkOut }</h2>
							<c:choose>
								<c:when test="${rbean == null || rbean.size() <= 0 }">
									${lang.noAvailable }
								</c:when>
								<c:otherwise>
									<div class="table-responsive">
										<table class="table table-bordered">
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
														<td>
															<form action="controller?command=process" method="post">
																<input type="hidden" name="action" value="booking" />
																<table class="table table-striped">
																	<tr class="active">
																		<td><select name="number" class="form-control">
																				<c:forEach var="item2" items="${item.getRooms() }">
																					<option value="${item2.getId() }">${item2.getNumber() }</option>
																				</c:forEach>
																		</select></td>
																		<td><button class="btn btn-primary">
																				${lang.bookNow }</button></td>
																	</tr>
																</table>
															</form>
														</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</div>
	<!--start footer -->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>