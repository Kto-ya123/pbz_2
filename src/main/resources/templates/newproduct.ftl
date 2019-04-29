<#import "parts/common.ftl" as c>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>
<div class=" container col-9 " >
	<div class="row " >
        <div class="col-6 " >
            <img class="" src="${tShirt.urlShirt}" style="width: 400px;height: 400px;">
        </div>
   
          

			<div class="сontainer col-6 text-right " >		
				<h3 class="display-4">${tShirt.name}</h3>
                <div style="width: 120px; margin-left:420px;">
                    <h6>Rating:${tShirt.rating?if_exists} Amount:${quantity} </h6>
                    <#if user??>
                    <input class="star-rating__input" id="star-rating-5" type="radio" name="rating" value="5" onclick="rat()">
                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5" title="5 out of 5 stars"></label>

                    <input class="star-rating__input" id="star-rating-4" type="radio" name="rating" value="4" onclick="rat()">
                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4" title="4 out of 5 stars"></label>

                    <input class="star-rating__input" id="star-rating-3" type="radio" name="rating" value="3" onclick="rat()">
                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3" title="3 out of 5 stars"></label>


                    <input class="star-rating__input" id="star-rating-2" type="radio" name="rating" value="2" onclick="rat()">
                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2" title="2 out of 5 stars"></label>


                    <input class="star-rating__input" id="star-rating-1" type="radio" name="rating" value="1" onclick="rat()">
                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1" title="1 out of 5 stars" ></label>
                    </#if>
                </div>
                <br>
	  			<h2 class="line" >$18</h2>
                <h4><b>Author:</b>  ${tShirt.author.username}</h4>
                <h4><b>Topic:</b>  ${tShirt.topic.topicName?if_exists}</h4>
                <br>
	  			Size<br>		
	  			<select id="selectsize" class="custom-select searchcolor  mb-3" style="width: 200px"> 				
				  	<option selected>M</option>
				  	<option value="1">S</option>
				  	<option value="2">X</option> 
				</select><br>
        Sex<br>
                <h5><#if tShirt.sex == "MALE">Man<#else>Woman</#if></h5>


        <button class="btn btn-outline-primary mb-3" style="width: 200px" type="button"  onclick="location.href= '/TShirts/${tShirt.id}/backet'">Buy It Now</button>
        <#if access??>
            <form action="/TShirts/${tShirt.id}/delete" method="post">
                <input type="hidden" value="${tShirt.id}" name="tShirtId"/>
                <input  type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-outline-primary mb-3" style="width: 200px" type="submit">Delete</button>
            </form>
        </#if>
            </div>


        <div class="input-group"> <br>
	  		</div>							
	</div>
</div>




<h3 class="container">Description</h3> 

<div class="container">
  <ul class="">
      <li class="media ">
           
            <div><br><span>${tShirt.description}</span>
            </div>          
    </li>
  </ul>
</div>


<div class="container mb-5">
  <ul class="">
      <li class="media ">
          <#if tShirt.tags??>
            <div><br>
                <#list tShirt.tags as tag>
                    <span class="btn searchcolor" style="margin-right: 15px">${tag.tagName}</span>
                </#list>
            </div>
          </#if>

    </li>
  </ul>
</div>

<h3 onload="comments();" class="container md-2">Comments</h3>


<div id="comm">
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
</div>

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
</@c.page>
<script type="text/javascript">
    function rat() {
        var q=document.querySelector('input[name="rating"]:checked').value;
        setRat(q);
    };
    function setRat(q) {
        $.ajax({
            type: "GET",
            url:"/TShirts/${tShirt.id}/rating?value="+q,
            data: ${tShirt.id},
            success: function (rezult) {

            }
        })

    }



</script>

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

<script>
    window.onload = function () {
        comments();
        <#if user??>
            setRating();
        </#if>
    }
    function setRating() {
        var rate = document.getElementById('star-rating-${rating}');
        rate.checked = rate;
    }

    function comments() {
        setInterval(loadComment, 2000);
    }
    function loadComment() {
        $.ajax({
            type: "GET",
            url:"/comment/getcomment/${tShirt.id}",
            data: ${tShirt.id},
            success: function (rezult) {
                $('#comm').empty();
                $(rezult).appendTo('#comm');
            }
        })
    }
</script>


<script type="text/javascript">
    function sex(){
  
        if($('#select').val()=='Man'){
             var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1555868664/white_zkntjk.png'  ;  
        }else{
              var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1556117619/Wwhite_uzpgsm.png';  
         }


    }

  
    function red() {
        if($('#select').val()=='Man'){
             var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1555868663/red_wmcbi8.png'  ;  
        }else{
              var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1556117619/Wred_xugdzs.png';  
         }
    }

   function skyblue() {
         if($('#select').val()=='Man'){
             var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1555868663/blue_sqpvb5.png'  ;  
        }else{
              var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1556117619/Wblue_kgnztk.png';  
         }
    }
   
   function green() {
         if($('#select').val()=='Man'){
             var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1555868664/green_z8bhxi.png'  ;  
        }else{
              var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1556117619/Wgreen_l5fig8.png';  
         }
  }

   function black() {
        if($('#select').val()=='Man'){
             var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1555868663/black_e5i52l.png'  ;  
        }else{
              var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1556117619/Wblack_d42cqx.png';  
         }
    }

   function white() {
         if($('#select').val()=='Man'){
             var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1555868664/white_zkntjk.png'  ;  
        }else{
              var image=document.getElementById("foto");
              image.src = 'https://res.cloudinary.com/itr/image/upload/v1556117619/Wwhite_uzpgsm.png';  
         }
    }
</script>



<script type="text/javascript">
  function theme(){
    var theme = document.getElementById("Theme");
    if(theme.getAttribute("href")=="css/style.css"){
    theme.setAttribute("href", "css/style2.css");
  }else{
     theme.setAttribute("href", "css/style.css");
  }
  }
</script>