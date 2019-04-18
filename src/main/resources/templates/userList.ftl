<#import "parts/common.ftl" as c>
<@c.page>

    <div class="d-flex flex-column flex-md-row align-items-center col-9  p-3 mb-3  container bg-white border-bottom shadow-sm border">

    <table  class="table table-hover ">
        <thead>
        <tr >
            <th scope="col">Name</th>
            <th scope="col">E-Mail</th>
            <th scope="col">Role</th>

        </tr>
        </thead>
    <tbody>
<#list users as user>

    <tr scope="row">

        <td scope="col"> <a href="/user/${user.id}">${user.username}</a></td>
        <td scope="col"><#if user.email ??>${user.email}</#if></td>
        <td scope="col"><#list user.roles as role>${role}<#sep> , </#list></td>
    </tr>

</#list>
    </tbody>
</table>
    </div>
</@c.page>