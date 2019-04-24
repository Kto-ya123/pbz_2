<#import "parts/common.ftl" as c>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>
    ${string?if_exists}
    ${messages?if_exists}
    <#if messages??>
        ${messages}
        <#else>
        <strong>Нихуя</strong>
    </#if>
</@c.page>
