
 <link rel="stylesheet" href="css/stylescss.css">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <aside id="sidebar">
              
            <div class="d-flex">
                <button class="toggle-btn" type="button">
                    <i class="lni lni-grid-alt"></i>
                </button>
                <div class="sidebar-logo">
                    <a href="#">Admin</a>
                </div>
            </div>
            <ul class="sidebar-nav">  
                <li class="sidebar-item">
                     <a href="index.jsp" class="sidebar-link">
                        <i class="lni lni-home"></i>
                        <span>User View</span>
                        </font>
                    </a>               
                </li>
                <li class="sidebar-item">
                    <a href="admin.jsp" class="sidebar-link">
                        <font color="yellow">
                        <i class="lni lni-agenda"></i>
                        <span>Dashboard</span>
                        </font>
                    </a>               
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link collapsed has-dropdown" data-bs-toggle="collapse"
                        data-bs-target="#auth" aria-expanded="false" aria-controls="auth">
                        <i class="lni lni-video"></i>
                        <span>Movie</span>
                    </a>
                    <ul id="auth" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a href="movieAdmin.jsp" class="sidebar-link">Movie List</a>
                        </li>
                    
                    </ul>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link collapsed has-dropdown" data-bs-toggle="collapse"
                        data-bs-target="#multi" aria-expanded="false" aria-controls="multi">
                        <i class="lni lni-ticket"></i>
                        <span>Members</span>
                    </a>
                    <ul id="multi" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a href="ticketmanagement.jsp" class="sidebar-link">Tickets Management</a>
                        </li>
                    </ul>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link collapsed has-dropdown" data-bs-toggle="collapse"
                        data-bs-target="#com" aria-expanded="false" aria-controls="com">
                        <i class="lni lni-popup"></i>
                        <span>Comment</span>
                    </a>
                    <ul id="com" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a href="commentmanagement.jsp" class="sidebar-link">All Comments</a>
                        </li>
                    </ul>
                </li>
                <li class="sidebar-item">
                    <a href="#" class="sidebar-link collapsed has-dropdown" data-bs-toggle="collapse"
                        data-bs-target="#user" aria-expanded="false" aria-controls="user">
                         <i class="lni lni-user"></i>
                        <span>Booking Ticket</span>
                    </a>
                    <ul id="user" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a href="managementmembers.jsp" class="sidebar-link">Members Management</a>
                        </li>
                       
                    </ul>
                </li>
                <li class="sidebar-item">
                         <a href="#" class="sidebar-link collapsed has-dropdown" data-bs-toggle="collapse"
                        data-bs-target="#MC" aria-expanded="false" aria-controls="MC">
                        <i class="lni lni-pencil-alt"></i>
                        <span>Screening Rate</span>
                        </a>
                        <ul id="MC" class="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                        <li class="sidebar-item">
                            <a href="screeningratemanagement.jsp" class="sidebar-link">Screening Rate Management</a>
                        </li>
                    </ul>
                </li>
                 
            </ul>
        </aside>
   <script src="js/scripts.js"></script>
        <!-- /#sidebar-wrapper -->
