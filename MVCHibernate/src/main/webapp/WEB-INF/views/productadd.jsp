<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<style>
#wrap {
    text-align:center;
}
h2 {
    margin:20px;
    padding:30px;
    font-size:200%;
}
form {
    border:2px solid black;
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
    margin-left:40px;
    margin-right:40px;
    text-align:left;
}
form input{
    border:2px solid black;
    margin:5px;
    margin-left:30%;
    width:85%;
    padding:5px;
}

form label {
    margin-left:35%;
    display:block;
    font-size:105%;
    font-weight: bold;
}
.button {
    padding:10px;
    font-size:100%;
}
.submit {
    background-color:#90EE90;
}
.back-button {
    background-color:#FFCCCB;
}
</style>
</head>
<body>
<h2> ${value} Product</h2>
<form:form action="/SpringMVCHibernate/addPerson" modelAttribute="person" method="post">
<table>
	<tr>
		<td>
			<form:label path="product">
				Product
			</form:label>
		</td>
		<td>
			<form:input path="product" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="product_description">
				Product Description
			</form:label>
		</td>
		<td>
			<form:input path="product_description" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="category">
				Category
			</form:label>
		</td>
		<td>
			<form:input path="category" />
		</td>
	</tr>
	<tr>
    		<td>
    			<form:label path="price">
    				Price
    			</form:label>
    		</td>
    		<td>
    			<form:input path="price" />
    		</td>
    	</tr>
    	<tr>
        		<td>
        			<form:label path="currency">
        				Currency
        			</form:label>
        		</td>
        		<td>
        			<form:input path="currency" />
        		</td>
        	</tr>
        	<tr>
            		<td>
            			<form:label path="inventory">
            				Inventory
            			</form:label>
            		</td>
            		<td>
            			<form:input path="inventory" />
            		</td>
            	</tr>
            	<tr>
                     <td>
                        <form:label path="location">
                         Location
                        </form:label>
                        </td>
                        <td>
                        <form:input path="location" />
                     </td>
                </tr>
	<tr>
	        <td>
                    <input class="button back-button" type="button" value="Go Back" onClick="history.go(-1)">
            </td>
		     <td>
                  <input type="submit" class="button submit" value="${value}"/>
              </td>
	</tr>
</table>
</form:form>