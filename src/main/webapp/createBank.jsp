<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Create Bank</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #007bff;
            text-align: center;
        }

        form {
            margin-top: 20px;
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

        
    </style>
</head>
<body>
    <div class="container">
        <h1>Create Bank</h1>
        <s:form action="createBank" theme="simple">
            <label for="bankId">Bank ID:</label>
            <s:textfield id="bankId" name="newBank.bankId" required="true"/>
            
            <label for="name">Bank Name:</label>
            <s:textfield id="name" name="newBank.name" required="true"/>
            
            <input type="submit" value="Create Bank" onclick="validateAndSubmit(); showSuccessMessage();" />

        </s:form>

        <!-- Display success message if any -->
        <div class="success-message" id="successMessage">
            <s:actionmessage />
        </div>

    </div>
    
    <div class="top-container">
    	<a href="adminLogin.action" class="back-button">Back to Welcome Admin</a>
	</div>

    <script>
        function showSuccessMessage() {
            document.getElementById('successMessage').style.display = 'block';
        }
        
        function validateAndSubmit() {
            // Get references to the input fields
            var bankId = document.getElementById("bankId");
            var name = document.getElementById("name");

            
            // Trim white spaces from input values
            bankId.value = bankId.value.trim();
            name.value = name.value.trim();
            
            console.log("Bank ID value: " + bankId.value); 
            console.log("Name value: " + name.value);
            
			var validationMessage = "";
            
            
            if(bankid === "") {
            	validationMessage += "Bank ID is required.\n";
            }
            if(name === "") {
            	validationMessage += "Name is required.\n";
            }
            
            if (validationMessage !== "") {
                alert("Please check the following:\n\n" + validationMessage);
                return false;
            }
        }
    </script>
</body>
</html>
