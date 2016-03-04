<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="${lang.details }" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<!-- start header -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">

				<div class="details">
					<h2>${items.getName() }</h2>
					<div class="slideBlock">
						<div id="slides">
							<c:forEach var="item" items="${items.getAlbum() }">
								<img src="images/${item.getImage() }.jpg"
									alt="${items.getName() }">
							</c:forEach>
							<a href="#" class="slidesjs-previous slidesjs-navigation"><i
								class="icon-chevron-left icon-large"></i></a> <a href="#"
								class="slidesjs-next slidesjs-navigation"><i
								class="icon-chevron-right icon-large"></i></a>
						</div>
					</div>
					<div class="det_text">
						<p class="para">${items.getDescription() }</p>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<!--start footer -->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	<script type="text/javascript" src="js/jquery.slides.min.js"></script>
	<script>
		$(function() {
			$('#slides').slidesjs({
				width : 600,
				height : 400,
				navigation : false
			});
		});
	</script>
</body>
</html>