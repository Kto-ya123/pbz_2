<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <#if errormessage??>errormessage</#if>
    Login page
    <@l.login1 "/login" />
    <a href="/registration">Add new user</a>
</@c.page>