<#import "parts/common.ftl" as c>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>
<div class=" container col-9 " >
	<div class="row " >
        <div class="col-6 " >
            <img class="" src="${tShirt.urlShirt}" style="width: 400px;height: 400px;">
        </div>
   
          

			<div class="сontainer col-6 text-right " >		
				<h3 class="display-4"><b>Name:</b> ${tShirt.name}</h3>
                <h5><b>Author:</b>  ${tShirt.author.username}</h5>
                <h5><b>Topic:</b>  ${tShirt.topic.topicName?if_exists}</h5>
	  			<h2 class="mb-5 line" >$18</h2>
	  			Size<br>		
	  			<select id="selectsize" class="custom-select searchcolor  mb-3" style="width: 200px"> 				
				  	<option selected>M</option>
				  	<option value="1">S</option>
				  	<option value="2">X</option> 
				</select><br>
        Sex<br>
        <select id="select" onclick="sex()"  class="custom-select searchcolor mb-3" style="width: 200px">               
                    <option id="q" selected>Man</option>
                    <option id="q" value="1">Woman</option>
                </select><br>   
		


       
        <button onclick="red()"  class="btn mb-5" style="background-color:#FF0000 ;margin-left: 13px" type="button"></button>
        <button onclick="skyblue()" class="btn mb-5"style="background-color:skyblue ;margin-left: 13px"  type="button"></button>
        <button onclick="green()" class="btn mb-5" style="background-color:#39E639;margin-left: 13px"  type="button"></button>
        <button onclick="black()" class="btn mb-5" style="background-color:black;margin-left: 13px"  type="button"></button>
        <button onclick="white()" class="btn  mb-5"style="background-color:white; border:1px solid black ;margin-left: 13px" type="button"></button><br>
      


        <button class="btn btn-outline-primary mb-3" style="width: 200px" type="button">Buy It Now</button>
        <button class="btn btn-outline-primary mb-3" style="width: 200px" type="button">Add to cart</button>
        <#if access??>
            <button class="btn btn-outline-primary mb-3" style="width: 200px" type="button">Editing T-shirt</button>
            <form action="/TShirts/${tShirt.id}/delete" method="post">
                <input type="hidden" value="${tShirt.id}" name="tShirtId"/>
                <input  type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-outline-primary mb-3" style="width: 200px" type="submit">Delete</button>
            </form>
        </#if>


				</div>

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
           
            <div><br><span class="btn searchcolor" style="margin-right: 15px">#Each</span>
              <span class="btn searchcolor" style="margin-right: 15px">#Each</span>
            </div>   

    </li>
  </ul>
</div>

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
</@c.page>
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