<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Banks</title>
    <style>
		body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        h1 {
            text-align: center;
            color: blue;
            margin-bottom: 20px;
        }

        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        th, td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ccc;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
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
    <h1>List of Banks</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <s:iterator value="banks">
            <tr>
                <td><s:property value="bankId" /></td>
                <td><s:property value="name" /></td>
            </tr>
        </s:iterator>
    </table>
    
    <div class="top-container">
      	<a href="userLogin.action" class="back-button">Back to Welcome User</a>
  	</div>
</body>
</html>
