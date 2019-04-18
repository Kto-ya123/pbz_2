<#import "parts/common.ftl" as c>


<@c.page>

    <body>

    <div class="d-flex flex-column flex-md-row align-items-center col-9  p-3 mb-3  container bg-white border-bottom shadow-sm border">
        <nav class="col-sm ">
            <a class="p-2 text-dark" href="#">T-shirts</a>
            <a class="p-2 text-dark" href="#">Woman</a>
            <a class="p-2 text-dark" href="#">Man</a>
        </nav>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="btn btn-outline-primary" href="#login_form" id="join_pop">Log In</a>
        </nav>
        <nav class="my-2 my-md-0 mr-md-3">
            <div class="input-group ">
                <input type="text" class="form-control searchcolor" placeholder="Search" aria-label="Recipient's username" aria-describedby="basic-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button">Search</button>
                </div>
            </div>
        </nav>
    </div>

    <div class=" container col-9 " >
        <div class="row " >
            <div class="col-6 " >
                <img class="" src="https://res.cloudinary.com/itr/image/upload/v1555539924/main500x500_upyegq.png" style="width: 400px;height: 400px;">
            </div>
            <div class="сontainer col-6 text-right " >
                <h3 class="display-4">Product name</h3>
                <h2 class="mb-5 line" >$18</h4>
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


    <div class="container shadow-sm col-9 p-3 mb-3 comments ">
        <ul class="">
            <li class="media">
                <div class="line">Name:</div>
                <div><br><span>Each year fewer people speak English as their mother tongue — but more people speak it as a second or foreign language. In 1950 nearly 9% of the world’s population spoke English as their first language.</span>
                </div>
            </li>
        </ul>
    </div>


    <div class="container shadow-sm col-9 p-3 mb-3 comments">
        <ul class="">
            <li class="media">
                <div class="line" >Name:</div>
                <div><br><span>Each year fewer people speak English as their mother tongue — but more people speak it as a second or foreign language. In 1950 nearly 9% of the world’s population spoke English as their first language.</span>
                </div>
            </li>
        </ul>
    </div>





    <div id="printBlock" class="mb-3">

    </div>


    <form name="search">
        <div class="container shadow-sm col-9 p-3 mb-3 "	>
            <div class="input-group">
                <textarea name="key" class="form-control searchcolor" aria-label="With textarea"></textarea>
                <div class="input-group-prepend">
                    <button name="print" class=" btn btn-outline-primary " style="width: 200px" type="button">Review</button>
                </div>
            </div>
        </div>
    </form>


    <div class="null"></div>
    <div class="null"></div>




    </body>



    <script>
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
    </script>




</@c.page>