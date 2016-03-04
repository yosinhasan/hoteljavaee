<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="${lang.hotel }" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<!-- start header -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<!----start-images-slider---->
	<div class="images-slider">
		<!-- start slider -->
		<div id="fwslider">
			<div class="slider_container">
				<div class="slide">
					<img src="images/slider1.jpg" alt="" />
					<div class="slide_content">
						<div class="slide_content_wrap">
							<!-- Text title -->
							<h4 class="title">
								<i class="bg"></i>${lang.hotel }
							</h4>
						</div>
					</div>
				</div>
				<!--/slide -->
				<!-- /Duplicate to create more slides -->
				<div class="slide">
					<img src="images/slider2.jpg" alt="" />
					<div class="slide_content">
						<div class="slide_content_wrap">
							<!-- Text title -->
							<h4 class="title">
								<i class="bg"></i>${lang.hotel }
							</h4>
						</div>
					</div>
				</div>
				<!-- /Duplicate to create more slides -->
				<div class="slide">
					<img src="images/slider3.jpg" alt="" />
					<div class="slide_content">
						<div class="slide_content_wrap">
							<!-- Text title -->
							<h4 class="title">
								<i class="bg"></i>${lang.hotel }
							</h4>
						</div>
					</div>
				</div>
				<!-- /Duplicate to create more slides -->
				<div class="slide">
					<img src="images/slider4.jpg" alt="" />
					<div class="slide_content">
						<div class="slide_content_wrap">
							<!-- Text title -->
							<h4 class="title">
								<i class="bg"></i>${lang.hotel }
							</h4>
						</div>
					</div>
				</div>
				<!--/slide -->
				<!--/slide -->
				<!--/slide -->
			</div>
			<div class="timers"></div>
			<div class="slidePrev">
				<span> </span>
			</div>
			<div class="slideNext">
				<span> </span>
			</div>
		</div>
		<!--/slider -->
	</div>
	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="online_reservation">
				<div class="b_room">
					<div class="booking_room">
						<h4>${lang.bookOnline }</h4>
						<p>${lang.bookTitle }</p>
					</div>
					<div class="reservation">
						<form action="controller?command=reservation" method="post">
							<input type="hidden" name="action" value="reservationTrue">
							<ul>
								<li class="span1_of_1">
									<h5>${lang.typeOfRoom }:</h5> <!----------start section_room----------->
									<div class="section_room">
										<select required="required" name="roomTypeId" id="country"
											onchange="change_country(this.value)"
											class="frm-field required">
											<option value="0" draggable="true">${lang.selectTypeRoom }</option>
											<c:forEach var="item" items="${requestScope['roomTypes'] }">
												<option value="${item.getId() }">${item.getName() }</option>
											</c:forEach>
										</select>
									</div>
								</li>
								<li class="span1_of_1 left">
									<h5>${lang.checkInDate }:</h5>
									<div class="book_date">
										<input name="checkIn" required="required" class="date"
											pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
											id="datepicker" type="text" value="${lang.ddmmyy}"
											onfocus="this.value = '';"
											onblur="if (this.value == '') {this.value = '${lang.ddmmyy}';}">

									</div>
								</li>
								<li class="span1_of_1 left">
									<h5>${lang.checkOutDate }:</h5>
									<div class="book_date">
										<input name="checkOut" required="required" class="date"
											pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))"
											id="datepicker1" type="text" value="${lang.ddmmyy}"
											onfocus="this.value = '';"
											onblur="if (this.value == '') {this.value = '${lang.ddmmyy}';}">
									</div>
								</li>
								<!--
								<li class="span1_of_2 left">
 
									<h5>${lang.adults }:</h5> 
									<div class="section_room">
										<select name="amount" class="frm-field required">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
										</select>
									</div>
									
								</li>
								-->
								<li class="span1_of_3">
									<div class="date_btn">
										<input type="submit" value="${lang.bookNow }" />
									</div>
								</li>
							</ul>
						</form>

						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<!--start grids_of_3 -->
			<div class="grids_of_3">
				<c:forEach var="item" items="${requestScope['roomTypes'] }">
					<div class="grid1_of_3">
						<div class="grid1_of_3_img">
							<a href="controller?command=details&id=${item.getId() }"> <img
								src="images/${item.getImage() }" alt="" /> <span class="next">
							</span>
							</a>
						</div>
						<h4>
							<a href="controller?command=details&id=${item.getId() }">${item.getName() }<span>${item.getPrice() }$</span></a>
						</h4>
						<p>${item.getDescription().substring(0, 200) }...</p>
						<h4>
							<a href="controller?command=details&id=${item.getId()}">${lang.readMore }</a>
						</h4>
					</div>
				</c:forEach>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<!--start footer -->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>