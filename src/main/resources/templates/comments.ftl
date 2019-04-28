<#list comments as comment>
    <div class="container shadow-sm col-9 p-3 mb-3 comments">
        <ul class="">
            <li class="media">
                <div class="line" >${comment.author.username}</div>
                <div><br><span>${comment.message}</span>
                </div>
            </li>
        </ul>
    </div>
</#list>