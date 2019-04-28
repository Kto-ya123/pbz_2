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
    <form method="get" action="/TShirts">
        <select id="selectsize" class="custom-select searchcolor " style="width: 200px" name="sex">
            <option selected>&lt;none&gt;</option>
            <option value="male">Man</option>
            <option value="female">Woman</option>
        </select>

        <select class="custom-select searchcolor " style="width: 200px; " name="topic">
            <option selected>&lt;none&gt;</option>
            <#list topics as topic>
                <option value="${topic.topicName}">${topic.topicName}</option>
            </#list>

        </select>
        <input type="text" class="btn searchcolor" placeholder="Search" aria-label="Recipient's username" aria-describedby="basic-addon2" name="inputtag" id="tag">
        <button class="btn btn-outline-primary" type="submit">Search</button>
    </form>


    <div class="row">
        <#list tShirts as tShirt>
            <div class="col-4">
                <div  class=" col d-flex flex-column  align-items-center  border-bottom shadow-sm border size  hover" >
                    <a href="/TShirts/${tShirt.id}">
                        <img class="d-block w-100" src="${tShirt.urlShirt}" style="width: 285px;height: 274px;">
                    </a>
                    <h5>18$</h5>
                    <button class="btn btn-outline-primary margin" type="button">Buy</button>
                </div>
            </div>
        </#list>
    </div>
</div>
</@c.page>

<style type="text/css">
    .text-field {
        -moz-box-sizing: border-box;
        border: 1px solid #EEEEEE;
        font-family: "Source Sans Pro",Arial,sans-serif;
        font-size: 0.73684em;
        font-weight: 600;
        height: 37px;
        margin: 0;
        padding: 5px;
        width: 100%;
    }
    .autocomplete-suggestion {
        overflow: hidden;
        padding: 2px 5px;
        white-space: nowrap;
    }
    .autocomplete-suggestions strong {
        color: #3399FF;
        font-weight: normal;
    }
    .autocomplete-selected{
        background:#F0F0F0;
    }

</style>
<script>
    var v = new Array();
    <#list exists_tag as tag>
    v[v.length] = "${tag.tagName}";
    </#list>
    v[v.length] = "arti";
    a1 = $('#tag').autocomplete({
        width: 448,
        delimiter: /(,|;)\s*/,
        lookup: v
    });



</script>
