<#import "parts/justPage.ftl" as j>
<#import  "parts/login.ftl" as l>
<#import  "parts/login.ftl" as l>
<@j.page>
    <div>
        ${string?if_exists}
    </div>
<div>
<@l.logout/>
    <span><a href="/user">User list</a></span>
</div>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="text" placeholder="Введите сообщение"/>
        <input type="text" name="tag" placeholder="Тэг"/>
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Добавить</button>
    </form>
<div>Список сообщений</div>
</div>
    <form method="get" action="/main">
        <input type="text" name="filter" />
        <button type="submit">Найти</button>
    </form>

<#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
        <div>
            <#if message.filename??>
                <img src="/img/${message.filename}">
            </#if>
        </div>
    </div>
    <#else>
    No messages
</#list>
</@j.page>