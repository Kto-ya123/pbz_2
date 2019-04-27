<#import "parts/common.ftl" as c>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>

<body>

<div class="container  ">
    <div class="container d-flex flex-column  align-items-start  mb-5">
        <div class="row">
            <div class="col-sm">
                <h3 class="display-4 ">All styles</h3>
            </div>
        </div>
    </div>
    <select id="selectsize" class="custom-select searchcolor " style="width: 200px">
        <option selected>Man</option>
        <option value="1">Woman</option>
    </select>

    <select class="custom-select searchcolor " style="width: 200px; ">
        <option selected>humor</option>
        <option value="1">sex</option>
        <option value="2">IT</option>
        <option value="3">girl</option>
        <option value="4">man</option>
        <option value="5">anime</option>
        <option value="6">children</option>
        <option value="7">sport</option>
        <option value="8">game</option>
        <option value="9">music</option>
    </select>

    <button class="btn btn-outline-primary" type="button">Del</button>
    <button class="btn btn-outline-primary" type="button">But</button>
    <div class="row">
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
    </div>
</div>
</@c.page>
