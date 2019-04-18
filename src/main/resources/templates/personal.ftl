<#import "parts/common.ftl" as c>
<@c.page>
    <H2>${message?if_exists}</H2>

    <div class="container ">
        <#list tShirts as tShirt>
            <div class="row">
                <div class="col hover ">
                    <div  class=" col d-flex flex-column  align-items-center  border-bottom shadow-sm border" >
                        <a href="/mypage/${tShirt.id}">
                            <img class="d-block w-100" src="${tShirt.urlShirt}" >
                        </a>
                        <h5>Price</h5>
                        <button class="btn btn-outline-primary margin" type="button">Buy</button>
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
</@c.page>