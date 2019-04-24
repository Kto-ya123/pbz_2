<#import "parts/common.ftl" as c>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>

<div class=" container col-9 " >
    <div class="row " >
        <div class="col-6 " >
            <canvas id="canvas" width="500px" height="500px"  ></canvas>


        </div>
        <div class="сontainer col-6 text-right " >
            <div class="null"></div>
            <div class="null"></div>
            <div class="null"></div>


            <button onclick="v()"  class="btn mb-5" style="background-color:#fff124 ;margin-left: 13px" type="button"></button>



            <button onclick="red()"  class="btn mb-5" style="background-color:red ;margin-left: 13px" type="button"></button>
            <button onclick="skyblue()" class="btn mb-5"style="background-color:skyblue ;margin-left: 13px"  type="button"></button>
            <button onclick="green()" class="btn mb-5" style="background-color:green;margin-left: 13px"  type="button"></button>
            <button onclick="black()" class="btn mb-5" style="background-color:black;margin-left: 13px"  type="button"></button>
            <button onclick="white()" class="btn  mb-5"style="background-color:white; border:1px solid black ;margin-left: 13px" type="button"></button><br>

            <button class="btn btn-outline-primary" onclick="text()" style="width: 200px;">Add text</button>
            <input type="file" id="file" />
            <label for="file" class="btn btn-outline-primary btn-2" style="width: 200px;">Add images</label>
            <button class="btn btn-outline-primary" onclick="v()" style="width: 200px;  margin-right: 5px;">Save</button>

            <button class="btn btn-outline-primary" onclick="f()" style="width: 200px;">Delete selected object</button>

            <!--<form method="post" action="/TShirts/add">
                <input type="hidden" value="v();" name="svg"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="text" name="text">
                <button type="submit">Click</button>
            </form>-->
            <input type="hidden" name="_csrf" value="${_csrf.token}" id="csrf"/>
</@c.page>





<script>



    var canvas = new fabric.Canvas('canvas');
    document.getElementById('file').addEventListener("change", function (e) {
        var file = e.target.files[0];
        var reader = new FileReader();
        reader.onload = function (f) {
            var data = f.target.result;
            fabric.Image.fromURL(data, function (img) {
                var oImg = img.set({left: 0, top: 0, angle: 0}).scale(0.9);
                canvas.add(oImg).renderAll();
                var a = canvas.setActiveObject(oImg);
                var dataURL = canvas.toDataURL({format: 'png', quality: 0.8});
            });
        };
        reader.readAsDataURL(file);
    });


</script>

<script type="text/javascript">
    canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868664/white_zkntjk.png', canvas.renderAll.bind(canvas));
    canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1555868663/fon_qkezpa.png', canvas.renderAll.bind(canvas));


</script>


<script type="text/javascript">
    var inp=document.querySelector("#file")
    inp.value="";

    function text(){
        var textbox = new fabric.Textbox('This is a Textbox', {
            left: 250,
            top: 250,
            fill: '#000',
            strokeWidth: 2,
            stroke: "#000",
        });
        canvas.add(textbox);

    }
    function f(){
        canvas.isDrawingMode = false;
        deleteObjects();
    }

</script>





<script type="text/javascript">




    // select all objects
    function deleteObjects(){
        var activeObject = canvas.getActiveObject();

        if (activeObject) {
            if (confirm('Are you sure?')) {
                canvas.remove(activeObject);
            }
        }
    }


</script>

<script >


    function v(){
        var svg = canvas.toSVG();
        document.write('<form method="post" action="/TShirts/add">');
        document.write(" <input type='hidden' name='svg' value='"+ svg +"'/>");
        document.write('<input type="hidden" name="_csrf" value="${_csrf.token}"/>');
        document.write('<h1>Загрузка...</h1>');
        document.write(' <button type="submit"  id  = "to_svg" style="display:none;"></button>');
        document.write('</form>');
        var but = document.getElementById('to_svg');
        but.click();
    }

    function create(text, name, type) {
        //var dlbtn = document.getElementById("dlbtn");
        var file = new Blob([text], {type: type});
        //dlbtn.href = URL.createObjectURL(file);
        //dlbtn.download = name;
        return file;
    }

    function red() {
        canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868663/red_wmcbi8.png', canvas.renderAll.bind(canvas));

    }
    function skyblue() {
        canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868663/blue_sqpvb5.png', canvas.renderAll.bind(canvas));
    }
    function green() {
        canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868664/green_z8bhxi.png', canvas.renderAll.bind(canvas));
    }

    function black() {
        canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868663/black_e5i52l.png', canvas.renderAll.bind(canvas));
    }
    function white() {

        canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868664/white_zkntjk.png', canvas.renderAll.bind(canvas));

    }
</script>





