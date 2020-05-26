<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.PaymentAPI" %>
    <%@ page import="com.Payment" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Payment</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<link rel="stylesheet" href="Views/main.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/payment.js"></script>
</head>
<body>
<div class="main">
		<div class="container">
			<div class="signup-content">
				
				<div class="signup-form">
					<form class="register-form" id="formPayment" name="formPayment" method="post" action="payment.jsp">
						<h2>Payment</h2>
						
						<div class="form-group">
							<label for="labe">Payment Type</label> <input type="text"
							class="lab" id="Pay_type" name="Pay_type" placeholder="Type">
						</div>

						<div class="form-group">
							<label for="labe">Card Number</label> <input type="text"
								class="lab" id="Pay_cno" name="Pay_cno" placeholder="Card Number">
						</div>

						<div class="form-group">
							<label for="labe">Card Expiration Date</label> <input type="text"
								class="lab" id=Pay_expdate name="Pay_expdate" placeholder="Exp Date">
						</div>

						<div class="form-group">
							<label for="labe">CVV</label> <input type="text"
								class="lab" id="Pay_code" name="Pay_code" placeholder="CVV">
						</div>
						
						<div class="form-group">
							<label for="labe">Appointment ID</label> <input type="text"
								class="lab" id="Appointment_ID" name="Appointment_ID" placeholder="Appointment Id">
						</div>
				
						<br> <input id="btnSave" name="btnSave" type="button"
						value="Save" class="btn btn-primary mb-2"> <input type="hidden"
						id="hidAppIDSave" name="hidAppIDSave" value="">
						

					</form>
				</div>
			</div>
		</div>
	</div>
	<br><br>
	<div class = "container">
	<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divItemsGrid">
					<%
					Payment payObj = new Payment();
					out.print(payObj.readPayment());
					%>
				</div>
	
	</div>
</body>
</html>