<#import "style.ftl" as s>
<#macro page login user>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <@s.style/>
    </head>
    <body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm border">
        <nav class="col-sm ">
            <a class="p-2 text-dark" href="#">T-shirts</a>
            <a class="p-2 text-dark" href="#">Woman</a>
            <a class="p-2 text-dark" href="#">Man</a>
        </nav>
        <#if login>
            <div class="btn-group my-2 my-md-0 mr-md-3">
                <button type="button" onclick="location.href= '/${user.username}'" class="btn btn-outline-primary">${user.username}</button>
                <button type="button" class="btn btn-outline-primary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="sr-only ">Toggle Dropdown</span>
                </button>
                <div class="dropdown-menu ">
                    <a class="dropdown-item " href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                    <div class="dropdown-divider"></div>
                    <form action="/logout" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input class="btn col-3" type="submit" value="Sign Out"/>
                    </form>
                </div>
            </div>
        <#else>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="btn btn-outline-primary" href="#login_form" id="join_pop">Log In</a>
            </nav>
        </#if>


        <nav class="my-2 my-md-0 mr-md-3">
            <div class="input-group ">
                <input type="text" class="form-control searchcolor" placeholder="Search" aria-label="Recipient's username" aria-describedby="basic-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button">Search</button>
                </div>
            </div>
        </nav>
    </div>
    <#nested>
    </body>
</html>
</#macro>