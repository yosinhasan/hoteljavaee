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
						<strong>${lang.bookingHistory }</strong>
					</div>
					<article class="thumbnails newsBlocks">
						<div class="page-header">
							<button type="button" class="btn btn-primary btn-large"
								data-toggle="modal" data-target="#myModal">${lang.bookNow }</button>
						</div>
						<div class="fresh-table full-color-blue">
							<table id="fresh-table" class="table">
								<thead>
									<th data-field="id" data-sortable="true">${lang.id }</th>
									<th data-field="checkIn" data-sortable="true">${lang.checkInDate }</th>
									<th data-field="checkout" data-sortable="true">${lang.checkOutDate }</th>
									<th data-field="status" data-sortable="true">${lang.status }</th>
									<th data-field="class" data-sortable="true">${lang.typeOfRoom }</th>
									<th data-field="number" data-sortable="true">${lang.numbers }</th>
									<th data-field="date" data-sortable="true">${lang.date }</th>
								</thead>
								<tbody>
									<c:set var="id" value="1" />
									<c:forEach var="item" items="${requestScope['history'] }">
										<tr>
											<td>${id }</td>
											<td>${item.getCheckIn() }</td>
											<td>${item.getCheckOut()}</td>
											<td>${item.getStatus()}</td>
											<td>${item.getType() }</td>
											<td>${item.getNumber() }</td>
											<td>${item.getReservationDate().substring(0,10)}</td>
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
					<h4 class="modal-title">${lang.reservation }</h4>
				</div>
				<div class="modal-body">
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
									<select name="roomTypeId" onchange="change_country(this.value)"
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
							<button type="submit" class="btn btn-primary btn-lg">${lang.bookNow }</button>
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