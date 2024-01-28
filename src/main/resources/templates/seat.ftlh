<html>
  <head>
     <link href="/Css/hello.css" rel="stylesheet"></link>
   </head>
  <body>
     <table class="lists">
      <tr>
        <th> Employee No</th>
        <th> Employee Name</th>
        <th> Salary</th>
       </tr>
        <#if lists?has_content>
	         <#list lists as list>
		       <tr>
		          <td>${list.empno}</td>
		          <td>${list.empname}</td>
		          <td>${list.salary}</td>
		       </tr> 
		    </#list> 
	     <#else>
	        <tr>
	          <td>No Records</td>
	       </tr> 
	     </#if> 
     </table>
  <body>
</html>