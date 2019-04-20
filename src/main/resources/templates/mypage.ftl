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
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>

<body>

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
  </@c.page>
