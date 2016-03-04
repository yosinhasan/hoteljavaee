<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="${lang.activities }" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<!-- start header -->
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<!--start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<!-- start main_content -->
				<ul class="service_list">
					<c:forEach var="item" items="${requestScope['activities'] }">
						<li>
							<div class="ser_img">
								<a href="controller?command=activityDetails&id=${item.getId() }"> <img src="images/${item.getImage() }.jpg"
									alt="${item.getName() }" /> <span class="next"> </span>
								</a>
							</div> <a href="controller?command=activityDetails&id=${item.getId() }"><h3>${item.getName() }</h3></a>
							<p class="para">${item.getDescription().substring(0,60) }...</p>
							<h4>
								<a href="controller?command=activityDetails&id=${item.getId() }">${lang.readMore }</a>
							</h4>
						</li>
					</c:forEach>
				</ul>
				<div class="clear"></div>
				<!-- end main_content -->

			</div>
		</div>
	</div>
	<!--start footer -->
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>