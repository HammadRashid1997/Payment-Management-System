<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Create Payment</title>
    <style>
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center; /* Horizontally center */
            align-items: center; /* Vertically center */
            height: 100vh;
        }

        .container {
            width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            align-items: center; /* Center contents horizontally */
            justify-content: center;
        }
        
        h1 {
            color: #007bff;
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center; /* Center form items horizontally */
            width: 100%;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .success-message {
            margin-top: 20px;
            text-align: center;
            color: #009900;
            display: none;
        }

        .top-container {
		    position: absolute;
		    top: 0;
		    left: 0;
		    display: flex;
		    align-items: center;
		    padding: 10px;
		}
		
		.back-button {
		    display: inline-block;
		    background-color: #ccc;
		    color: #fff;
		    padding: 10px 20px;
		    border-radius: 4px;
		    text-decoration: none;
		    margin-right: 20px;
		    text-weight: bold;
		}
		
		.back-button:hover {
		    background-color: #999;
		}
		
		.input-pair {
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
            text-align: left;
            width: 100%; /* Ensure input fields expand to container width */
        }
		
    </style>
</head>
<body>
    <body>
    <div class="container">
        <h1>Create Payment</h1>
        <s:form action="createPayment" method="post">
            <div class="input-group">
                <div class="input-pair">
                    <s:label for="payment.paymentId">Payment ID:</s:label>
                    <s:textfield name="payment.paymentId" id="paymentId"/>
                </div>
                <div class="input-pair">
                    <s:label for="payment.senderAccountId">Sender Account ID:</s:label>
                    <s:textfield name="payment.senderAccountId" id="senderAccountId"/>
                </div>
                <div class="input-pair">
                    <s:label for="payment.receiverAccountId">Receiver Account ID:</s:label>
                    <s:textfield name="payment.receiverAccountId" id="receiverAccountId" />
                </div>
                <div class="input-pair">
                    <s:label for="payment.amount">Amount:</s:label>
                    <s:textfield name="payment.amount" id="paymentAmount"/>
                </div>
                <div class="input-pair">
                    <s:label for="payment.status">Status:</s:label>
                    <s:textfield name="payment.status" id="status"/>
                </div>
            </div>
            <s:submit value="Create Payment" cssClass="submit-button" onclick="return validateAndSubmit()" />
        </s:form>
    </div>
    
    <div class="top-container">
      	<a href="userLogin.action" class="back-button">Back to Welcome User</a>
  	</div>
  	
  	<script>
	  	function validateAndSubmit() {
	        // Get references to the input fields
	        var paymentId = document.getElementById("paymentId");
	        var senderAccountId = document.getElementById("senderAccountId");
	        var receiverAccountId = document.getElementById("receiverAccountId");
	        var paymentAmount = document.getElementById("paymentAmount");
	        var status = document.getElementById("status");
	
	        // Trim white spaces from input values
	        var paymentIdValue = paymentId.value.trim();
	        var senderAccountIdValue = senderAccountId.value.trim();
	        var receiverAccountIdValue = receiverAccountId.value.trim();
	        var paymentAmountValue = paymentAmount.value.trim();
	        var statusValue = status.value.trim();
	
	        var validationMessage = "";
	
	        if (paymentIdValue === "") {
	            validationMessage += "Payment ID is required.\n";
	        }
	        if (senderAccountIdValue === "") {
	            validationMessage += "Sender Account ID is required.\n";
	        }
	        if (receiverAccountIdValue === "") {
	            validationMessage += "Receiver Account ID is required.\n";
	        }
	        if (paymentAmountValue === "") {
	            validationMessage += "Amount is required.\n";
	        }
	        if (statusValue === "") {
	            validationMessage += "Status is required.\n";
	        }
	
	        if (validationMessage !== "") {
	            alert("Please check the following:\n\n" + validationMessage);
	            return false;
	        }
	        return true;
	    }
	</script>

</body>
</html>
