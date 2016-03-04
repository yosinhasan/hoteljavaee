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
						<strong>${lang.bookingHistory }</strong>
					</div>
					<div class="_content pull-right">
						<strong>Total amount: ${requestScope['info'].get(0) }</strong> <strong>Total
							sum: ${requestScope['info'].get(1) }$</strong>
					</div>
					<article class="thumbnails newsBlocks">
						<div class="page-header"></div>
						<div class="fresh-table full-color-blue">
							<table id="fresh-table" class="table">
								<thead>
									<th data-field="id" data-sortable="true">${lang.id }</th>
									<th data-field="user" data-sortable="true">${lang.name }</th>
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
											<td>${item.getUser().getName() }
												${item.getUser().getSurname() }</td>
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
</body>
</html>