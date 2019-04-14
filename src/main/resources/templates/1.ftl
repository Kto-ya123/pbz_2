<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
Add new user
    <#if errormessage??>${errormessage}</#if>
    <form action="/registration" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Email: <input type="email" name="email"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><label> Password: <input type="password" name="passwordrepeat"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Sign In"/></div>
    </form>
</@c.page>