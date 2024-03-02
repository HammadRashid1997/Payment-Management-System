<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>List of Payments</title>
    <style>
    
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
            background-color: #e6f7ff; /* Light blue background on hover */
        }
        
        tr:nth-child(even) {
            background-color: #f5f5f5; /* Light gray background for even rows */
        }
        
        td {
            color: #333; /* Dark text color for better contrast */
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
    <h1>List of Payments</h1>
    
    <table>
        <tr>
            <th>Payment ID</th>
            <th>Sender Account ID</th>
            <th>Receiver Account ID</th>
            <th>Amount</th>
            <th>Status</th>
        </tr>
        <s:iterator value="paymentList">
            <tr>
                <td><s:property value="paymentId"/></td>
                <td><s:property value="senderAccountId"/></td>
                <td><s:property value="receiverAccountId"/></td>
                <td><s:property value="amount"/></td>
                <td><s:property value="status"/></td>
            </tr>
        </s:iterator>
    </table>
    
    <div class="top-container">
      	<a href="adminLogin.action" class="back-button">Back to Welcome Admin</a>
  	</div>
    
</body>
</html>
