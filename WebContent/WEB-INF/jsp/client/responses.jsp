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
						<strong>${lang.responses } </strong>
					</div>
					<article class="newsblocks">
						<c:forEach var="item" items="${requestScope['clientResponses'] }">
							<section class="newsblock">
								<div class="card-container manual-flip">
									<div class="card">
										<div class="front">
											<div class="cover">
												<img src="images/slider-bg.jpg" />
											</div>
											<div class="user">
												<img class="img-circle" src="images/pic1.jpg" />
											</div>
											<div class="content">
												<div class="main">
													<h3 class="name">${item.getName() }</h3>
													<h5 class="motto">${lang.totalPrice }
														${item.getCheckIn() } - ${item.getCheckOut() }:
														${item.getPrice() }$</h5>
													<h6 class="motto">
														<strong>${lang.roomNumber }: ${item.getNumber() }</strong>
													</h6>
													<div class="footer">
														<button class="btn btn-simple" rel="tooltip"
															onclick="rotateCard(this)">
															<i class="fa fa-mail-forward"></i>
														</button>
													</div>
												</div>

											</div>
										</div>
										<!-- end front panel -->
										<div class="back">
											<div class="header">
												<h5 class="motto">${lang.respondTip }</h5>
											</div>
											<div class="content">
												<div class="main">
													<p class="readMessage">${lang.readMessage }</p>
													<p class="hide">${item.getMsg() }</p>
													<form class="form form-inline"
														action="controller?command=responses" method="post">
														<input name="action" type="hidden" value="" /> <input
															name="id" type="hidden" value="${item.getId() }" /> <input
															name="reservationId" type="hidden"
															value="${item.getReservationId() }" />
														<button name="confirm" type="submit"
															class="btn btn-primary btn-sm">${lang.confirm }</button>
														<button name="decline" type="submit"
															class="btn btn-danger btn-sm">${lang.decline }</button>
													</form>
													<div class="footer">
														<button class="btn btn-simple" rel="tooltip"
															onclick="rotateCard(this)">
															<i class="fa fa-reply"></i>
														</button>
													</div>
												</div>
											</div>

										</div>
										<!-- end back panel -->
									</div>
									<!-- end card -->
								</div>
								<!-- end card-container -->

							</section>

						</c:forEach>
					</article>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<!--start footer -->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	<!-- Modal -->
	<div id="responseMessage" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">${lang.message }</h4>
				</div>
				<div class="modal-body">
					<p></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

</body>
</html>