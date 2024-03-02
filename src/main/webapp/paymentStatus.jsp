<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Status</title>
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
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
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
        <h2>Payment Status</h2>
        <div class="form-container">
            <s:form action="getPaymentStatus" method="post">
                <s:textfield name="payment.paymentId" label="Enter Payment ID" class="form-input" placeholder="Enter Payment ID" />
                <s:submit value="Get Payment Status" class="form-button" />
            </s:form>
        </div>
        <div class="data-container">
            <s:if test="payment != null">
                <div class="data-item"><strong>Payment ID:</strong> <s:property value="payment.paymentId" /></div>
                <div class="data-item"><strong>Status:</strong> <s:property value="payment.status" /></div>
            </s:if>
        </div>
    </div>
    
    <div class="top-container">
      	<a href="userLogin.action" class="back-button">Back to Welcome User</a>
  	</div>
  	
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
