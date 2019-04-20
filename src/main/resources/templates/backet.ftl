<#import "parts/common.ftl" as c>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>

<div class="null"> </div>



<div class="container">
    <div class="d-flex flex-column flex-md-row align-items-center p-3 mb-3 bg-white  shadow-sm border-top border-bottom border-left border-right row">
        <div class="col">Product name</div>
        <div class="col "><img class="" src="style/main1.png" style="width: 50px;height: 50px;">  </div>
        <div class="col ">18$</div>

    </div>

    <div class="d-flex flex-column flex-md-row align-items-center p-3 mb-3 bg-white  shadow-sm border-top border-bottom border-left border-right row">
        <div class="col">Product name</div>
        <div class="col "><img class="" src="style/main.png" style="width: 50px;height: 50px;">  </div>
        <div class="col ">18$</div>

    </div>
</div>

<div class="container basket">
    <div class="form-row ">
        <div class="form-group col-md-6">
            <label for="inputEmail4">Email</label>
            <input type="email" class=" searchcolor  form-control basket" id="inputEmail4" placeholder="Email">
        </div>

    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputAddress">Address</label>
            <input type="text" class=" searchcolor form-control basket" id="inputAddress">
        </div>
    </div>

    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputCity">City</label>
            <input type="text" class="searchcolor  form-control basket" id="inputCity">
        </div>
    </div>

    <div class="form-row ">
        <div class="form-group col-md-6">
            <button class=" btn btn-outline-primary mb-3 basket"type="button">Buy</button>
        </div>
    </div>
</div>
</div>
</body>
</@c.page>



