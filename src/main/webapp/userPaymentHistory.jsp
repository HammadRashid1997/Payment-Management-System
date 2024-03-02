<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Get Payment History of Account</title>
    <style>
		body, h2, form {
		    margin: 0;
		    padding: 0;
		}

		body {
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    justify-content: center;
		    min-height: 100vh;
		    background: linear-gradient(135deg, #64b5f6, #1976d2);
		    font-family: Arial, sans-serif;
		}

		form {
		    background-color: #fff;
		    border-radius: 8px;
		    box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.1);
		    padding: 20px;
		    width: 300px;
		    margin-top: 20px;
		}
		
		h2 {
		    font-size: 24px;
		    margin-bottom: 16px;
		    text-align: center;
		}
		
		label {
		    display: block;
		    font-size: 14px;
		    margin-bottom: 6px;
		}
		
		input[type="text"] {
		    width: 100%;
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		    font-size: 14px;
		    margin-bottom: 10px;
		}
		
		input[type="submit"] {
		    background-color: #007bff;
		    color: #fff;
		    border: none;
		    border-radius: 4px;
		    padding: 10px 20px;
		    cursor: pointer;
		    font-size: 14px;
		}

		input[type="submit"]:hover {
		    background-color: #0056b3;
		}

		h3 {
		    font-size: 20px;
		    margin-top: 20px;
		    text-align: center;
		}
		
		ul {
		    list-style-type: none;
		    padding: 0;
		    margin-top: 10px;
		}
		
		li {
		    margin-bottom: 8px;
		    border-bottom: 1px solid #ccc;
		    padding-bottom: 6px;
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
		
		table {
	        width: 100%;
	        border-collapse: collapse;
	        margin-top: 20px;
	    }
	
	    th, td {
	        padding: 8px;
	        text-align: left;
	        border-bottom: 1px solid #ddd;
	    }
	
	    th {
	        background-color: #f2f2f2;
	    }
	
	    tr:hover {
	        background-color: #f5f5f5;
	    }
    </style>
</head>
<body>
    <h2>Get Payment History of Account</h2>
    <s:form action="userPaymentHistory" method="post">
        <label for="accountId">Enter Account ID:</label>
        <s:textfield name="accountId" id="accountId" />
        <s:submit value="Get Payment History" />
    </s:form>

    <s:if test="paymentHistory != null">
    <h3>Payment History for Account ID: ${accountId}</h3>
    <table>
        <tr>
            <th>Payment ID</th>
            <th>Sender ID</th>
            <th>Receiver ID</th>
            <th>Amount</th>
            <th>Status</th>
        </tr>
        <s:iterator value="paymentHistory">
            <tr>
                <td><s:property value="paymentId" /></td>
                <td><s:property value="senderAccountId" /></td>
                <td><s:property value="receiverAccountId" /></td>
                <td><s:property value="amount" /></td>
                <td><s:property value="status" /></td>
            </tr>
        </s:iterator>
    </table>
</s:if>
    
   	<div class="top-container">
      	<a href="userLogin.action" class="back-button">Back to Welcome User</a>
  	</div>

	<script>
	    document.addEventListener("DOMContentLoaded", function() {
	        var form = document.querySelector('form');
	        if (form) {
	            form.addEventListener("submit", function(event) {
	                var accountIdInput = document.querySelector('input[name="accountId"]');
			        if (accountIdInput) {
	                    accountIdInput.value = accountIdInput.value.trim();
	                }
	            });
	        }
	    });
	</script>
  	
</body>
</html>
