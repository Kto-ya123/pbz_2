<#import "parts/common.ftl" as c>
<@c.page>

    <div class="container shadow-sm col-2 p-3 mb-3" style="margin: 20px auto;">
    <div>

    <form action="/user" method="post">
        User Editor
        <br>
        <input class="btn  searchcolor" type="text" name="username" value="${user.username}">
        <#list roles as role>
            <div class="md-5">

                <label><input class="form-check-label" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <br>
        <input type="hidden" value="${user.id}" name="userId">
        <button class="btn btn-outline-primary" style="width: 100px;" type="submit">Save</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
    <form action="/user/delete" method="post">
        <br>
        <input type="hidden" value="${user.id}" name="userId"/>
        <button class="btn btn-outline-primary" style="width: 100px;" type="submit">Delete</button>
        <input  type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
        <form action="/user/delete" method="post">
            <br>
            <input type="hidden" value="${user.id}" name="userId"/>
            <button class="btn btn-outline-primary" style="width: 100px;" type="submit">Block</button>
            <input  type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>

        </div>
    </div>
</@c.page>