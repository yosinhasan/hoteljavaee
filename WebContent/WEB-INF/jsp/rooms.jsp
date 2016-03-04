<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="${lang.rooms }" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<!-- start header -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<div class="content">
					<div class="room">
						<h4>${lang.roomTypes }</h4>
						<p class="para"></p>
					</div>

					<c:forEach var="item" items="${requestScope['roomTypes'] }">
						<div class="grids_of_2">
							<div class="grids_of_img">
								<a href="controller?command=details&id=${item.getId() }"><img
									src="images/${item.getImage() }" alt="${item.getName() }"
									title="${item.getName() }" /></a>
							</div>
							<div class="grids_of_para">
								<p class="para">${item.getDescription() }</p>
							</div>
							<div class="clear"></div>
						</div>

					</c:forEach>

				</div>
				<!-- 
					
				<div class="sidebar">
					<div class="date_btn">
						<a href="controller?command=reservation">${lang.bookNow }</a>
					</div>
					<h4>room features</h4>
					<ul class="s_nav">
						<li><a href="#">laptop-size safe</a></li>
						<li><a href="#">Lorem ipsum dolor sit amet, consectetur
								adipiscing elit.</a></li>
						<li><a href="#">Etiam malesuada leo ornare</a></li>
						<li><a href="#">Sed eu magna sed lorem tincidunt</a></li>
						<li><a href="#">Proin id diam eleifend neque auctor
								scelerisque.</a></li>
					</ul>
					<h4>we accept</h4>
					<ul class="s_nav1">
						<li><a class="icon1" href="#"></a></li>
						<li><a class="icon2" href="#"></a></li>
						<li><a class="icon3" href="#"></a></li>
						<li><a class="icon4" href="#"></a></li>
					</ul>
									</div>
									 -->

				<div class="clear"></div>
			</div>
		</div>
	</div>
	<!--start footer -->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>