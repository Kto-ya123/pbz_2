<#import "parts/common.ftl" as c>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>

<div class=" container col-9 ">
    <div class="row " >
        <div class="col-6 " >
            <canvas id="canvas" width="500px" height="500px"  ></canvas>
        </div>
        <div class="col-6 text-right " >
            <#if message??>
                <div style="color:red;border-color: red;width: 258px;" class="btn mb-">${message}</div>
            </#if>
            <br>
            <select class="custom-select searchcolor  mb-3" id="topic" style="width: 258px;margin-top: 50px;">
                <#list topics as topic>
                    <option value="${topic.topicName}">${topic.topicName}</option>
                </#list>
            </select><br>
            <input  type="text" class="form-control searchcolor btn mb-3" id="name_product"  style="width: 258px;"placeholder="Name product"></input>

            <form name="search">
                <input name="key"  type="text" class="form-control searchcolor btn mb-3" id="comment_data" style="width: 200px;"placeholder="#tags"></input>
                <button class="btn btn-outline-primary addField add mb-3" type="button" name="print" style="margin-right: -3px;" >Add</button>
            </form>

            <div style="height: 100px;" class="inputs" id="tag" name="tag">
                <div class="inputs" name="dynamic" id="tags" class="field"></div>
            </div>

            <select id="sex" onclick="sex()"  class="custom-select searchcolor mb-3" style="width: 200px">
                <option value="male">Man</option>
                <option value="female">Woman</option>
            </select><br>


            <h6>Color object   <input type="color" class="btn searchcolor btn-outline-primary form-control " id="favcolor" style="width: 50px;margin-right:0px;margin-top: 0px"></h6>
            <h6 class="">T color <input type="color" class="btn searchcolor btn-outline-primary form-control " id="tcolor" style="width: 50px;;margin-right: 0px;margin-top: 0px"></h6>



            <span>
                <button class="btn btn-outline-primary " onclick="text()" style="width: 200px;">Add text</button>
                <input type="file" id="file" />
                <label for="file" class="btn btn-outline-primary btn-2" style="width: 200px;">Add images</label><br>
                <button class="btn btn-outline-primary" onclick="save()" style="width: 200px; margin-right: 5px;">Save</button>
                <button class="btn btn-outline-primary" onclick="f()" style="width: 200px;">Delete selected object</button>
            </span>
        </div>
    </div>
</div>


<div class="container col-9 p-3 mb-3 " >
    <div class="input-group"> <br>
        <textarea name="key" id="description" class="form-control searchcolor" aria-label="With textarea" placeholder="Add description..."></textarea>

    </div>
</div>
</@c.page>


<script>
    var canvas = new fabric.Canvas('canvas');
    document.getElementById('file').addEventListener("change", function (e) {
        var file = e.target.files[0];
        var reader = new FileReader();
        reader.onload = function (f) {
            var data = f.target.result;
            fabric.Image.fromURL(data, function (img) {
                var oImg = img.set({left: 0, top: 0, angle: 0, opacity: 0.85}).scale(0.9);
                canvas.add(oImg).renderAll();
                var a = canvas.setActiveObject(oImg);
                var dataURL = canvas.toDataURL({format: 'png', quality: 0.8});
            });
        };
        reader.readAsDataURL(file);
    });
</script>
<script >
    function save(){
        var load = '<div class="d-flex align-items-center flex-column justify-content-center h-100 loader" id="header"> \n' +
            '<a href=""><img class="anim" src="https://res.cloudinary.com/itr/image/upload/v1556293613/404_akspcp.png" style="width: 256px;height: 224px;margin-left: 30px;"></a> \n' +
            '</div>';
        var style = '<style type="text/css"> \n' +
            '.anim{ \n' +
            '-webkit-animation: pulsing 5s infinite; \n' +
            'margin: 16% 50%; \n' +
            '} \n' +
            '\n' +
            '@-webkit-keyframes pulsing { \n' +
            '0% { \n' +
            '-webkit-transform: rotate(0deg); \n' +
            'transform: rotate(0deg); \n' +
            '} \n' +
            '50% { \n' +
            '-webkit-transform: rotate(0deg); \n' +
            'transform: rotate(380deg); \n' +
            '} \n' +
            '</style>';

        var nameProduct = document.getElementById('name_product').value;
        var sex = document.getElementById('sex').value;
        var description = document.getElementById('description').value;
        var topic = document.getElementById('topic').value;
        var tags = getTags();

        var tag="";
        for(i = 0; i < tags.length; i++){
            tag+=tags[i];
        }

        var svg = canvas.toSVG();
        document.write(style);
        document.write('<form method="post" action="/TShirts/add">');
        document.write(" <input type='hidden' name='svg' value='"+ svg +"'/>");
        document.write(" <input type='hidden' name='nameProduct' value='"+ nameProduct +"'/>");
        document.write(" <input type='hidden' name='topic' value='"+ topic +"'/>");
        document.write(" <input type='hidden' name='discription' value='"+ description +"'/>");
        document.write(" <input type='hidden' name='sex' value='"+ sex +"'/>");
        document.write(" <input type='hidden' name='tags' value='"+ tags +"'/>");
        document.write('<input type="hidden" name="username" value="${userpage}"/>');
        document.write('<input type="hidden" name="_csrf" value="${_csrf.token}"/>');
        document.write(load);
        document.write(' <button type="submit"  id  = "to_svg" style="display:none;"></button>');
        document.write('</form>');
        var but = document.getElementById('to_svg');
        but.click();
    }
</script>
<script >
    var tags = new Array(0);
    $(document).ready(function () {
        kol=0;
        $('html').on('click','.add',function () {
            d=($("#comment_data").text($(".searchcolor")).val());
            console.log(d);
            if (kol<4 && d.length<15){
                $('<i class="btn searchcolor"style="margin-left:15px;margin-top:30px;font-style:normal; "><input type="file"  " class="field mb-5 " name="dynamic[]" /><span class="remove">'+d+' <i class="fa fa-remove"></i> </span></i> ').fadeIn('slow').appendTo('.inputs');
                kol++;
                tags[tags.length] = d;
            }
        });
        $('html').on('click','.remove', function () {
            var position = tags.indexOf($(this).parent());
            tags.splice(position,1);

            $(this).parent().remove();
            kol--;
        });

    });
    function getTags() {
        return tags;

    }
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
            opacity: 0.85,
            stroke: "#000"
        });
        canvas.add(textbox);

    }

    function f(){
        var activeObject = canvas.getActiveObject();
        canvas.remove(activeObject);
    }
    var red = new fabric.Rect({
        top: 0,
        left: 0,
        width: 500,
        height: 500,
        opacity: 0.70,
        fill: 'white' });
    canvas.add(red);
    canvas.item(0).selectable = false;
</script>

<script >
    function sex(){

        if($('#sex').val()=='male'){
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1555868663/fon_qkezpa.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868664/white_zkntjk.png', canvas.renderAll.bind(canvas));
        }else{
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wfon_rmnrqt.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wwhite_uzpgsm.png', canvas.renderAll.bind(canvas));
        }


    }


    function red() {
        if($('#select').val()=='Man'){
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1555868663/fon_qkezpa.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868663/red_wmcbi8.png', canvas.renderAll.bind(canvas))
        }else{
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wred_xugdzs.png', canvas.renderAll.bind(canvas))
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wfon_rmnrqt.png', canvas.renderAll.bind(canvas));
        }
    }

    function skyblue() {
        if($('#select').val()=='Man'){
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1555868663/fon_qkezpa.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868663/blue_sqpvb5.png', canvas.renderAll.bind(canvas));
        }else{
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wblue_kgnztk.png', canvas.renderAll.bind(canvas))
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wfon_rmnrqt.png', canvas.renderAll.bind(canvas));
        }
    }

    function green() {
        if($('#select').val()=='Man'){
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1555868663/fon_qkezpa.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868664/green_z8bhxi.png', canvas.renderAll.bind(canvas));
        }else{
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wfon_rmnrqt.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wgreen_l5fig8.png', canvas.renderAll.bind(canvas));
        }
    }

    function black() {
        if($('#select').val()=='Man'){
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1555868663/fon_qkezpa.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868663/black_e5i52l.png', canvas.renderAll.bind(canvas));
        }else{
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wfon_rmnrqt.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wblack_d42cqx.png', canvas.renderAll.bind(canvas));
        }
    }

    function white() {
        if($('#select').val()=='Man'){
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1555868663/fon_qkezpa.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1555868664/white_zkntjk.png', canvas.renderAll.bind(canvas));
        }else{
            canvas.setOverlayImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wfon_rmnrqt.png', canvas.renderAll.bind(canvas));
            canvas.setBackgroundImage('https://res.cloudinary.com/itr/image/upload/v1556117619/Wwhite_uzpgsm.png', canvas.renderAll.bind(canvas));
        }
    }
</script>

<script type="text/javascript">

    var tscolor = document.getElementById("tcolor");
    var theInput = document.getElementById("favcolor");

    tscolor.addEventListener("input", function() {
        var theColor = tscolor.value;
        var activeObject = canvas.getActiveObject();
        var d=canvas.item(0);
        d.set({
            fill: theColor,
            stroke:theColor
        });
        canvas.renderAll();
    }, true);

    theInput.addEventListener("input", function() {
        var theColor = theInput.value;
        var activeObject = canvas.getActiveObject();
        activeObject.set({
            fill: theColor,
            stroke:theColor
        });
        canvas.renderAll();
    }, true);

</script>



