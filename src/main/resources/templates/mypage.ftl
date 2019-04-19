<!--<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>My page</title>

  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet"  href="css/style.css">
  </head>
-->
<#import "parts/common.ftl" as c>

<@c.page>

<body>


  <div class="d-flex flex-column flex-md-row align-items-center   p-3 mb-3   bg-white border-bottom shadow-sm border">
    <nav class="col-sm ">
      <a class="p-2 text-dark" href="#">T-shirts</a>
      <a class="p-2 text-dark" href="#">Woman</a>
      <a class="p-2 text-dark" href="#">Man</a>
    </nav>
   
   
    <nav class="my-2 my-md-0 mr-md-3">
      <a class="btn btn-outline-primary" href="#login_form" id="join_pop">Log In</a>
    </nav>                     
     <div class="btn-group my-2 my-md-0 mr-md-3">
      <button type="button" class="btn  btn-outline-primary">Action</button>
      <button type="button" class="btn  btn-outline-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="sr-only ">Toggle Dropdown</span>
      </button>
        <div class="dropdown-menu ">
          <a class="dropdown-item " href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <a class="dropdown-item" href="#">Something else here</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Separated link</a>
        </div>
      </div>
 <nav class="my-2 my-md-0 mr-md-3">
      <div class="input-group ">
        <input type="text" class="form-control searchcolor" placeholder="Search" aria-label="Recipient's username" aria-describedby="basic-addon2">
          <div class="input-group-append">
            <button class="btn btn-outline-primary" type="button">Search</button>
          </div>
      </div>
  </nav>
</div>









<div class="container  ">
 <div class="container d-flex flex-column  align-items-start  mb-5">
  <div class="row">
    <div class="col-sm">
       <h3 class="display-4 ">My style</h3>
    </div>
  </div>
</div>
  <div class="row">


      <!--<div class="col hover ">
          <div  class=" col d-flex flex-column  align-items-center  border-bottom shadow-sm border size" >
              <a href="file:///C:/KP%20itr/web/product.html">
                  <img class="d-block w-100" src="/style/main1.png" >
              </a>
              <h5>Price</h5>
              <button class="btn btn-outline-primary margin" type="button">Buy</button>
          </div>
      </div>-->





      <#list tShirts as tShirt>
            <div class="col-4 hover  ">
              <div  class=" col d-flex flex-column  align-items-center  border-bottom shadow-sm border size" >
                  <a href="/TShirts/${tShirt.id}">
                <img class="d-block w-100" src="${tShirt.urlShirt}" style="width: 285px;height: 274px;">
                   </a>
                  <h5>Price</h5>
                  <button class="btn btn-outline-primary margin" type="button">Buy</button>
              </div>
            </div>
      </#list>


      <div class="col-4 hover ">
          <div class=" col d-flex flex-column align-items-center size" style="margin-top: 35%;">
              <a href="file:///C:/KP%20itr/web/product.html">
                  <img class="rounded d-block w-100" src="/style/plus.jpg">
              </a>
          </div>
      </div>


    
  </div>
</div>

  <form method="post" enctype="multipart/form-data">
      <input type="file" name="file">
      <input type="hidden" name="_csrf" value="${_csrf.token}"/>
      <button type="submit">Добавить</button>
  </form>

<div class="null"></div>
  </@c.page>
