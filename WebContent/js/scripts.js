$(function() {
	$('select[name="locale"]').change(function() {
		document.location = "changeLocale?locale=" + $(this).val();
	});

	$(".requestInfo").delay(2000).slideUp(1000);
	$(".reservationInfo").delay(2000).fadeOut(1000);
	$('.myproc')
			.click(
					function(event) {
						var target = $(event.target).parent().parent();
						var name = $(target).find("td:nth-child(2)").text()
								.replace(/\s{2,}/g, ' ');
						;
						var type = $(target).find('input[name="amount"]').val();
						var amount = $(target).find("td:nth-child(3)").text();
						var checkIn = $(target).find("td:nth-child(4)").text();
						var checkOut = $(target).find("td:nth-child(5)").text();
						var userId = $(target).find('input[name="userId"]')
								.val();
						var requestId = $(target).find(
								'input[name="requestId"]').val();
						var modalHeader = $('.modal-header');
						var modalBody = $('.modal-body');

						modalHeader.find('div p#to span').text(" " + name);
						modalHeader.find('div p#adults span')
								.text(" " + amount);
						modalBody.find('input[name="checkIn"]').val(checkIn);
						modalBody.find('input[name="checkOut"]').val(checkOut);
						modalBody.find('select option[value="' + type + '"]')
								.prop("selected", "selected");
						modalBody.find('input[name="amount"]').val(amount);
						modalBody.find('input[name="userId"]').val(userId);
						modalBody.find('input[name="requestId"]')
								.val(requestId);

						$('#myModal').modal({
							keyboard : false
						})
					});
	$('.readMessage').click(function(event) {
		var target = $(this).parent();
		var msg = target.find('p.hide').text();
		var modalHeader = $('#responseMessage .modal-header');
		var modalBody = $('#responseMessage .modal-body');
		modalBody.find('p').text(msg);
		$('#responseMessage').modal({
			keyboard : false
		});

	});
	$('button[name="confirm"]').click(function() {
		$(this).parent().find('input[name="action"]').val("confirm");
	});
	$('button[name="decline"]').click(function() {
		$(this).parent().find('input[name="action"]').val("decline");
	});
	$('.requestDecline').click(function(event){
		event.preventDefault();
		var cond = confirm('Are you sure?');
		if (cond) {
			$('.requestForm').submit();
		} else {
			return false;	
		}
	});
});
