$(document).ready(function() {
	document.getElementById("valor").value = $('input:radio[name=inlineRadioOptions]:checked').val()

	$("input:radio[name=inlineRadioOptions]").click(function() {
		document.getElementById("valor").value = $('input:radio[name=inlineRadioOptions]:checked').val()
	});

});