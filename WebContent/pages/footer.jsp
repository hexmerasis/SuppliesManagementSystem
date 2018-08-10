<footer>
	<h4  style="color: maroon;">Footer lang</h4>
</footer>
<script>
	$('logoutBtn').observe('click', function(){
		logout();
	});

	function logout(){
		new Ajax.Request(contextPath + "/login", {
			method: "GET",
			parameters: {
					/* userId: $F('userId'),
					password: $F('password') */
			},
			onComplete: function(response){
				window.location.assign(contextPath);	
			}
		});
}
</script>