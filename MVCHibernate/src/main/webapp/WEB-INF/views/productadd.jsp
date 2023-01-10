<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
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
		<td colspan="2">
                  <input type="submit" value="${value}"/>
	</tr>
</table>
</form:form>