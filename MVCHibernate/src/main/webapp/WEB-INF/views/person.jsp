<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Product Page</title>

	<script  src=
    "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
            </script>
            <script src=
    "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js">
            </script>
             <link href=
            "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css"
                          rel="stylesheet">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

     <style>
     .pager-nav {
         margin: 16px 0;
     }
     .pager-nav span {
         display: inline-block;
         padding: 4px 8px;
         margin: 1px;
         cursor: pointer;
         font-size: 14px;
         background-color: #FFFFFF;
         border: 1px solid #e1e1e1;
         border-radius: 3px;
         box-shadow: 0 1px 1px rgba(0,0,0,.04);
     }
     .pager-nav span:hover,
     .pager-nav .pg-selected {
         background-color:darkgrey;
         border: 1px solid #CCCCCC;
     }
     </style>

</head>
<body>
<h3>Product List</h3>
<c:if test="${!empty listPersons}">
<button><a href="addProduct">Add Product</a></button>
	<table border="5px" id="page" class="table table-striped sortable">
	<thead>
	<tr>
    		<th width="80"  id="sortable1">Product</th>
    		<th width="120" id="not-sortable">Description</th>
    		<th width="120" id="sortable2">Category</th>
    		<th width="60"  id="sortable3">Price</th>
    		<th width="60"  id="sortable4">Currency</th>
    		<th width="60"  id="sortable5">Inventory</th>
    		<th width="60"  id="sortable6">Location</th>
    		<th width="60"  id="not-sortable">Update</th>
    		<th width="60"  id="not-sortable">Delete</th>
    	</tr>
    	</thead>
    	<tbody>
    	<c:forEach items="${listPersons}" var="person">
    		<tr class="item">
    			<td>${person.product}</td>
    			<td>${person.product_description}</td>
    			<td>${person.category}</td>
    			<td>${person.price}</td>
    			<td>${person.currency}</td>
    			<td>${person.inventory}</td>
    			<td>${person.location}</td>
    			<td><a href="<c:url value='/edit/${person.id}'/>">Update</a></td>
                <td><a href="<c:url value='/remove/${person.id}'/>">Delete</a></td>
    		</tr>
	</c:forEach>
	</tbody>
	</table>
	<div id="pageNavPosition" class="pager-nav"></div>
</c:if>
<script>
function Pager(tableName, itemsPerPage) {
         this.tableName = tableName;
         this.itemsPerPage = itemsPerPage;
         this.currentPage = 1;
         this.pages = 0;
         this.inited = false;

         this.showRecords = function (from, to) {
             let rows = document.getElementById(tableName).rows;

             // i starts from 1 to skip table header row
             for (let i = 1; i < rows.length; i++) {
                 if (i < from || i > to) {
                     rows[i].style.display = 'none';
                 } else {
                     rows[i].style.display = '';
                 }
             }
         };

         this.showPage = function (pageNumber) {
             if (!this.inited) {
                 // Not initialized
                 return;
             }

             let oldPageAnchor = document.getElementById('pg' + this.currentPage);
             oldPageAnchor.className = 'pg-normal';

             this.currentPage = pageNumber;
             let newPageAnchor = document.getElementById('pg' + this.currentPage);
             newPageAnchor.className = 'pg-selected';

             let from = (pageNumber - 1) * itemsPerPage + 1;
             let to = from + itemsPerPage - 1;
             this.showRecords(from, to);

             let pgNext = document.querySelector('.pg-next'),
                 pgPrev = document.querySelector('.pg-prev');

             if (this.currentPage == this.pages) {
                 pgNext.style.display = 'none';
             } else {
                 pgNext.style.display = '';
             }

             if (this.currentPage === 1) {
                 pgPrev.style.display = 'none';
             } else {
                 pgPrev.style.display = '';
             }
         };

         this.prev = function () {
             if (this.currentPage > 1) {
                 this.showPage(this.currentPage - 1);
             }
         };

         this.next = function () {
             if (this.currentPage < this.pages) {
                 this.showPage(this.currentPage + 1);
             }
         };

         this.init = function () {
             let rows = document.getElementById(tableName).rows;
             let records = (rows.length - 1);

             this.pages = Math.ceil(records / itemsPerPage);
             this.inited = true;
         };

         this.showPageNav = function (pagerName, positionId) {
             if (!this.inited) {
                 // Not initialized
                 return;
             }

             let element = document.getElementById(positionId),
                 pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal pg-prev">&#171;</span>';

             for (let page = 1; page <= this.pages; page++) {
                 pagerHtml += '<span id="pg' + page + '" class="pg-normal pg-next" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span>';
             }

             pagerHtml += '<span onclick="' + pagerName + '.next();" class="pg-normal">&#187;</span>';

             element.innerHTML = pagerHtml;
         };
     }
     let pager = new Pager('page', 10);

     pager.init();
     pager.showPageNav('pager', 'pageNavPosition');
     pager.showPage(1);

     $(document).ready(
     function() {
     $('th').click(function(){
     console.log($(this).attr('id'))
     if($(this).attr('id')!=='not-sortable'){
             var table = $(this).parents('table').eq(0)
         var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()))
         this.asc = !this.asc
         if (!this.asc){rows = rows.reverse()}
         for (var i = 0; i < rows.length; i++){
         table.append(rows[i])}
         }
     })

     function comparer(index) {
         return function(a, b) {
             var valA = getCellValue(a, index), valB = getCellValue(b, index)
             return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.toString().localeCompare(valB)
         }
     }
     function getCellValue(row, index){
      return $(row).children('td').eq(index).text() }
     })
     </script>

</body>
</html>
