<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #64b5f6, #1976d2);
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        
        .container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 400px;
            margin: 50px auto;
        }
        
        h2 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }
        
        strong {
            font-weight: bold;
        }
        
        .form-container {
            text-align: center;
            padding: 20px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            background-color: #f9f9f9;
        }

        .form-input {
            padding: 8px;
            margin: 5px;
            width: 80%;
            border: 1px solid #e0e0e0;
            border-radius: 5px;
        }

        .form-button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .data-container {
            margin-top: 20px;
            padding: 15px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            background-color: #f9f9f9;
        }

        .data-item {
            margin: 10px 0;
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
		}
		
		.back-button:hover {
		    background-color: #999;
		}
        
    </style>
</head>
<body>
    <div class="container">
        <h2>Payment Details</h2>
        <div class="form-container">
            <form action="getPaymentDetails" method="post">
                <input type="text" name="payment.paymentId" class="form-input" placeholder="Enter Payment ID">
                <input type="submit" value="Get Payment Details" class="form-button">
            </form>
        </div>
        <div class="data-container">
            <div class="data-item"><strong>Payment ID:</strong> ${payment.paymentId}</div>
            <div class="data-item"><strong>Sender Account ID:</strong> ${payment.senderAccountId}</div>
            <div class="data-item"><strong>Receiver Account ID:</strong> ${payment.receiverAccountId}</div>
            <div class="data-item"><strong>Amount:</strong> ${payment.amount}</div>
            <div class="data-item"><strong>Status:</strong> ${payment.status}</div>
        </div>
    </div>
    
    <div class="top-container">
      	<a href="adminLogin.action" class="back-button">Back to Welcome Admin</a>
  	</div>
  	
  	<!-- Add this script just before the closing </body> tag -->
	<script>
	    document.addEventListener("DOMContentLoaded", function() {
	        var form = document.querySelector('form');
	        if (form) {
	            form.addEventListener("submit", function(event) {
	                var paymentIdInput = document.querySelector('input[name="payment.paymentId"]');
	                if (paymentIdInput) {
	                    paymentIdInput.value = paymentIdInput.value.trim();
	                }
	            });
	        }
	    });
	</script>
</body>
</html>
