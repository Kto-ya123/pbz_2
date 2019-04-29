<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>
    <div class="container">
        <div id="carouselExampleControls " class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="d-block w-100" src="https://res.cloudinary.com/itr/image/upload/v1556465771/main3_ux5dmu.png" >
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="https://res.cloudinary.com/itr/image/upload/v1556465771/main1_rv1grd.png" alt="First slide">
                </div>
                <div class="carousel-item">
                    <img class="d-block w-100" src="https://res.cloudinary.com/itr/image/upload/v1556465771/main2_bzfb2o.png" alt="Second slide">
                </div>
            </div>

        </div>
    </div>
    <div class="container">
        <div class="row"><div class="col-12 btn">
                <div class="center-block">
                    <canvas id="tagcanvas1" height="300" ></canvas>
                </div>
            </div>
        <div id="ptTags">
            <#list tags as tag>
                <a href="/TShirts?inputtag=%23${tag.tagName}">${tag.tagName}</a>
            </#list>
        </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm">
                <h3 class="line" class=" font-weight-normal">New<br></h3>
            </div>
        </div>
    </div>
    <br>

    <div class="container ">
        <div class="row">
            <#list tShirts as tShirt>
                <div class="col hover">
                    <div  class=" col d-flex flex-column  align-items-center  border-bottom shadow-sm border" >
                        <a href="/TShirts/${tShirt.id}">
                            <img class="d-block w-100" src="${tShirt.urlShirt}" >
                        </a>
                        <h5>18$ Rate:${tShirt.rating}</h5>
                        <button class="btn btn-outline-primary margin" type="button">Buy</button>
                    </div>
                </div>
            </#list>
        </div>
    </div>

    <br>
    <div class="container" >
        <div class="row">
            <div class="col-sm">
                <h3 class="line" class=" font-weight-normal">High Rating</h3>
            </div>
        </div>
    </div>
    <br>

    <div class="container ">
        <div class="row">
            <#list popular as tShirt>
                <div class="col hover">
                    <div  class=" col d-flex flex-column  align-items-center  border-bottom shadow-sm border" >
                        <a href="/TShirts/${tShirt.id}">
                            <img class="d-block w-100" src="${tShirt.urlShirt}" >
                        </a>
                        <h5>18$ Rate:${tShirt.rating}</h5>
                        <button class="btn btn-outline-primary margin" type="button">Buy</button>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@c.page>
<script>
    function getTagName(string) {
        return string.substring(1);
    }
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="http://bootstraptema.ru/plugins/2015/tagcanvas/jquery.tagcanvas.min.js" type="text/javascript"></script>
<script type="text/javascript">
    //<![CDATA[
    window.onload = function() {
        TagCanvas.textFont = 'Impact,"Arial Black",sans-serif';
        TagCanvas.textColour = '#694198';
        TagCanvas.textHeight = 25;
        TagCanvas.outlineColour = '#f6f';
        TagCanvas.outlineThickness = 3;
        TagCanvas.outlineOffset = 5;
        TagCanvas.outlineMethod = 'outline';
        TagCanvas.maxSpeed = 0.06;
        TagCanvas.minBrightness = 0.2;
        TagCanvas.depth = 0.95;
        TagCanvas.pulsateTo = 0.2;
        TagCanvas.pulsateTime = 0.75;
        TagCanvas.decel = 0.9;
        TagCanvas.reverse = true;
        TagCanvas.shadow = '#336';
        TagCanvas.shadowBlur = 3;
        TagCanvas.shadowOffset = [1,1];
        TagCanvas.wheelZoom = false;
        TagCanvas.fadeIn = 80;
        try {


            TagCanvas.Start('tagcanvas1','ptTags', {
                shape:'vring(0.5)',
                offsetY: -60,
                lock: 'y'
            });

        } catch(e) {
        }
    };</script>