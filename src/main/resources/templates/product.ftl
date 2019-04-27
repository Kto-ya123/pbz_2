<#import "parts/common.ftl" as c>

<#if user??><#assign login = true></#if>
<@c.page login!false user!false>

    <body>
    <div class=" container col-9 " >
        <div class="row " >
            <div class="col-6 " >
                <img class="" src="${tShirt.urlShirt}" style="width: 400px;height: 400px;">
            </div>
            <div class="сontainer col-6 text-right " >
                <h3 class="display-4">Product name</h3>
                <h4 class="mb-5 line" >Name:${tShirt.name}</h4>
                <h4 class="mb-5 line" >Description:${tShirt.description}</h4>
                <h4 class="mb-5 line" >Sex:${tShirt.sex}</h4>
                <h4 class="mb-5 line" >Author:${tShirt.author.username}</h4>
                <h4 class="mb-5 line" >Id:${tShirt.id}</h4>

                    Size<br>
                    <select class="custom-select searchcolor  mb-3" style="width: 200px">
                        <option selected>M</option>
                        <option value="1">S</option>
                        <option value="2">X</option>
                    </select><br>
                    Sex<br>
                    <select class="custom-select searchcolor mb-5" style="width: 200px">
                        <option selected>Man</option>
                        <option value="1">Woman</option>
                    </select><br>

                    <button class="btn btn-outline-primary " style="width: 200px" type="button">Buy It Now</button>
                    <button class="btn btn-outline-primary " style="width: 200px" type="button">Add to cart</button>

            </div>

        </div>
    </div>
    </div>

    <div class="null"></div>

    <h3 class="container md-2">Comments</h3>

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

    <div id="printBlock" class="mb-3">

    </div>

 <#if user??>
    <form action="/TShirts/addComment" method="post">
        <div class="container shadow-sm col-9 p-3 mb-3 "	>
            <div class="input-group">
                <input  type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" value="${tShirt.id}" name="tShirtId"/>
                <textarea name="text" class="form-control searchcolor" aria-label="With textarea"></textarea>
                <div class="input-group-prepend">
                    <button name="print" class=" btn btn-outline-primary " style="width: 200px" type="submit">Review</button>
                </div>
            </div>
        </div>
    </form>
 </#if>


    <div class="null"></div>
    <div class="null"></div>




    </body>



   <!--<script>
        function printForm(e){

            var keyBox = document.search.key;
            var val = keyBox.value;

            var printBlock = document.getElementById("printBlock");

            var pElement = document.createElement("div");

            pElement.className="container shadow-sm col-9 p-3 mb-3";


            pElement.textContent = "Name:"+val;
            if(val){
                printBlock.appendChild(pElement);
            }else{
                alert('Пустое поле');
            }
        }

        var printButton = document.search.print;
        printButton.addEventListener("click", printForm);
    </script>-->




</@c.page>