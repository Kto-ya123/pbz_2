<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 container bg-white border-bottom shadow-sm border">
        <nav class="col-sm ">
            <a class="p-2 text-dark" href="#">T-shirts</a>
            <a class="p-2 text-dark" href="#">Woman</a>
            <a class="p-2 text-dark" href="#">Man</a>
        </nav>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="btn btn-outline-primary" href="#login_form" id="join_pop">Log In</a>
        </nav>
        <nav class="my-2 my-md-0 mr-md-3">
            <div class="input-group ">
                <input type="text" class="form-control searchcolor" placeholder="Search" aria-label="Recipient's username" aria-describedby="basic-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button">Search</button>
                </div>
            </div>
        </nav>
    </div>

    <div class="container">
        <div id="carouselExampleControls " class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="style/main.png" >
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="style/main.png" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="style/main.png" alt="Second slide">
                </div>
            </div>

        </div>
    </div>



    <div class="container">
        <div class="row">
            <div class="col-sm">
                <h3 class=" font-weight-normal">New</h3>
            </div>
        </div>
    </div>



    <div class="container ">
        <div class="row">
            <div class="col">
                <div class="col d-flex flex-column  align-items-center  border-bottom shadow-sm border" >
                    <img class="d-block w-100" src="style/main1.png" >
                    <h5>Price</h5>
                    <button class="btn btn-outline-primary margin" type="button">Buy</button>
                </div>
            </div>

            <div class="col" >
                <div class="col d-flex flex-column  align-items-center  border-bottom shadow-sm border" >
                    <img class="d-block w-100" src="style/main2.png" >
                    <h5>Price</h5>
                    <button class="btn btn-outline-primary margin" type="button">Buy</button>
                </div>
            </div>
            <div class="col" >
                <div class="col d-flex flex-column  align-items-center  border-bottom shadow-sm border" >
                    <img class="d-block w-100" src="style/main3.png" >
                    <h5>Price</h5>
                    <button class="btn btn-outline-primary margin" type="button">Buy</button>
                </div>
            </div>

            <div class="col" >
                <div class="col d-flex flex-column  align-items-center  border-bottom shadow-sm border" >
                    <img class="d-block w-100" src="style/main1.png" >
                    <h5>Price</h5>
                    <button class="btn btn-outline-primary margin" type="button">Buy</button>
                </div>
            </div>
        </div>
    </div>





    <!--   ==========         ЛОГИН     ==========          -->
    <a href="#x" class="overlay" id="login_form"></a>
    <div class="bg-white border-bottom shadow-sm popup">
        <h2>Welcome </h2>
        <p>Please enter your login and password here</p>
        <form method="post" action="/">
            <div>
                <label for="login" >Login:</label>
                <input class="form-control searchcolor" type="text" id="login" value="" name="username" />
            </div>
            <div>
                <label for="password">Password:</label>
                <input class="form-control searchcolor" type="password" id="password" value="" name="password" />
            </div>
            <input type="submit" class="btn btn-outline-primary" value="Sign In" />
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>

        <a href="/reg" id="join_pop">Sign Up</a>
        <a class="close" href="#close"></a>
    </div>
</@c.page>