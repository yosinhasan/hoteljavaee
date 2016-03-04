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
						<strong>${lang.requests }</strong>
					</div>
					<c:if test="${requestInfo != null }">
						<div class="requestInfo alert alert-success" role="alert">
							<a href="#" class="alert-link"> <strong>${requestInfo }</strong>
							</a>
						</div>
					</c:if>
					<!--	<div class="well"> ${lang.requestTip }  </div>-->
					<article>
						<div class="page-header">
							<!-- 	<button type="button" class="btn btn-primary btn-large"
								data-toggle="modal" data-target="#myModal">${lang.newRequest }</button>
						 -->
						</div>
						<div class="fresh-table">
							<table class="table">
								<thead>
									<th data-field="id" data-sortable="true">${lang.id }</th>
									<th data-field="name" data-sortable="true">${lang.name }</th>
									<th data-field="amount" data-sortable="true">${lang.adults }</th>
									<th data-field="checkin" data-sortable="true">${lang.checkInDate }</th>
									<th data-field="checkout" data-sortable="true">${lang.checkOutDate }</th>
									<th data-field="status" data-sortable="true">${lang.status }</th>
									<th data-field="date" data-sortable="true">${lang.date }</th>
									<th>${lang.action }</th>
									<th></th>

								</thead>
								<tbody>
									<c:set var="id" value="1" />
									<c:forEach var="item" items="${requests }">
										<tr>
											<td>${id }</td>
											<td>${item.getUser().getName() }
												${item.getUser().getSurname() }</td>
											<td>${item.getAmount() }</td>
											<td>${item.getCheckIn()}</td>
											<td>${item.getCheckOut()}</td>
											<td>${item.getStatus() }</td>
											<td>${item.getDate().substring(0,10)}</td>
											<td><c:if test="${item.getStatus() == 'UNPROCESSED' }">
													<input name="amount" type="hidden"
														value="${item.getRoomTypesId()}" />
													<input name="userId" type="hidden"
														value="${item.getUserId()}" />
													<input name="requestId" type="hidden"
														value="${item.getId() }" />
													<button class="myproc btn btn-primary btn-sm">${lang.process }</button>
												</c:if></td>
											<td><c:if test="${item.getStatus() == 'UNPROCESSED' }">
													<form class="requestForm" action="controller?command=requests" method="post">
														<input type="hidden" name="action" value="decline" /> <input
															name="userId" type="hidden" value="${item.getUserId()}" />
														<input name="requestId" type="hidden"
															value="${item.getId() }" />
														<button class="requestDecline btn btn-primary btn-sm">${lang.decline }</button>
													</form>
												</c:if></td>
										</tr>
										<c:set var="id" value="${id + 1 }" />
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

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title text-uppercase">${lang.newResponse }</h4>
					<div class="well text-uppercase">
						<p id="to" class="mark">
							<strong>${lang.to }:</strong> <span></span>
						</p>
						<p id="adults" class="mark">
							<strong>${lang.adults }:</strong> <span></span>
						</p>
					</div>
				</div>
				<div class="modal-body">
					<form action="controller?command=responses" method="post">
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
								<div class="sel_room">
									<h4>${lang.selectTypeRoom }</h4>
									<select name="roomTypesId"
										onchange="change_country(this.value)"
										class="frm-field required">
										<c:forEach var="item" items="${roomTypes }">
											<option value="${item.getId() }">${item.getName() }</option>
										</c:forEach>
									</select>

								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="res_btn">
							<input type="hidden" name="action" value="requestTrue" /> <input
								type="hidden" name="amount" value="" /> <input type="hidden"
								name="userId" value="" /> <input type="hidden" name="requestId"
								value="" />
							<button type="submit" class="btn btn-primary btn-large">${lang.submit }</button>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
</body>
</html>