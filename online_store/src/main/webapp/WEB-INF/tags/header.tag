
<header>
    <!-- Check if the user is logged in by looking for the "username" attribute in the session -->           
            <nav class="navbar bg-dark navbar-expand-lg bg-body-tertiary p-4 justify-content-center" data-bs-theme="dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">
                        <img src="images/diff_logo_enhanced-removebg.png" width="160"   alt="No_IMG_Found" class="d-inline-block align-text-top">
                        
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            
                            <li class="nav-item">
                                <a class="nav-link" href="Login">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Cart</a>
                            </li>
                        </ul>
                        <!--Logout button is the user is connected -->
                        <c: if test = "(currentUser != null) " >
                        <form action="Logout" method="post">
                        <input type="submit" value="Logout">
                        </form>
                         <form class="d-flex" role="search">
                            <input class="form-control me-2" type="password" placeholder="Secret Code" aria-label="Password">
                            <button class="btn btn-outline-success" type="submit">Enter</button>
                        </form>   
                        </c:if >
                        
                    </div>
                </div>
            </nav>
        </header>