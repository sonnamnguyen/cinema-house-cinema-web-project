<%-- 
    Document   : searchmembers
    Created on : Jun 24, 2024, 3:32:47 PM
    Author     : admin
--%>

<%@page import="DTO.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"/>
        <link rel="stylesheet" href="leftbar.css">
        <link rel="stylesheet" href="movietable.css">
    </head>
    <body>
       <div class="wrapper">     
            <jsp:include page="leftbar.jsp" />
            <div class="main p-3">
                <div class="title">
                    <h2>MEMBERS</h2>
                </div>
                <div class="search-container mb-3 p-3 border border-orange rounded">
                    <!-- Form to Create a New Movie -->
                  
                   
                <button type="button" class="btn c-btn mb-2" onclick="window.location.href='createuser.jsp'">+ Create user</button>
                  
                        
                  

                    <!-- Form to Search for Movies -->
                    <form action="AdminController" method="GET">
                        <div class="left">

                            <input type="text" id="searchInput" placeholder="please inform information you want to search" name="Information" class="form-control mb-2" />

                            <input type="hidden" name="btAction" value="SearchInformation" />
                            <button type="submit" class="btn btn-search c-btn mb-2">
                                <i class="lni lni-search-alt"></i> Search
                            </button>
                        </div>
                    </form>
                    <%
                      
                        List<User> list = (List<User>) request.getAttribute("UserSearch");
                    %>
                </div>
                <table id="movieTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th class="one">AccountName</th>
                            <th>Gender</th>
                            <th>Address</th>
                            <th>PhoneNumber</th>
                            <th>YearOfBirth</th>
                            <th>Email</th>
                            <th>Status</th>
                            <th>Update/Delete</th>
                        </tr>
                    </thead>
                    <tbody id="movieTableBody">
                        <% if (list != null && !list.isEmpty()) { %>
                        <% for (User user : list) {%>
                        <tr>
                            <td class="one"><%= user.getAccountName()%></td>
                            <td><%= user.getGender()%></td>
                            <td><%= user.getAddress()%></td>
                            <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(user.getYob())%></td>

                            <td><%= user.getPhoneNumber()%></td>
                            <td><%= user.getEmail()%></td>
                            <td><%= user.isIsAdmin() ? "Admin" : "User"%></td>
                            <td>
                                <form action="AdminController" method="POST" style="display:inline;">
                                    <input type="hidden" name="btAction" value="DeleteUser" />
                                    <input type="hidden" name="userID" value="<%= user.getIdAccount()%>" />
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                </form>

                                <form  action="AdminController" method="GET" style="display:inline;">
                                    <input type="hidden" name="btAction" value="UpdateUser" />
                                    <input type="hidden" name= "userID" value="<%=user.getIdAccount()%>" />
                                    <button type="submit" class="btn btn-primary btn-sm">Update</button>
                                </form>


                            </td>
                        </tr>
                        <% } %>
                        <% } else { %>
                        <tr>
                            <td colspan="7" class="text-center">EMPTY LIST</td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
        <script src="admin.js"></script>
    </body>
</html>
