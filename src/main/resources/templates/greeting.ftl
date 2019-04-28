<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>
    <div class="container">
        <div id="carouselExampleControls " class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="https://res.cloudinary.com/itr/image/upload/v1556465771/main3_ux5dmu.png" >
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="https://res.cloudinary.com/itr/image/upload/v1556465771/main1_rv1grd.png" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="https://res.cloudinary.com/itr/image/upload/v1556465771/main2_bzfb2o.png" alt="Second slide">
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
            <div class="col hover ">
                <div  class=" col d-flex flex-column  align-items-center  border-bottom shadow-sm border" >
                    <a href="/">
                        <img class="d-block w-100" src="style/main1.png" >
                    </a>
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
</@c.page>