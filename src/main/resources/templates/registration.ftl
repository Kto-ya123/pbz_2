<#import "parts/common.ftl" as c>
<#if user??><#assign login = true></#if>
<@c.page login!false user!false>
    <div class="container shadow-sm col-3 p-3 mb-3" style="margin: 20px auto;">
        <div>
            <h2>Sign Up</h2>
            <p>Please enter your details here</p>
            <p><#if errormessage??>
                <div style="color:red;border-color: red;width: 258px;" class="btn mb-">${errormessage}</div>
            </#if></p>
            <form action="/registration" method="post">
                <div class="md-5">
                    <label for="login_form">Login</label>
                    <input class="form-control searchcolor" type="login" id="login_form" value="" name="username" />
                </div>
                <div>
                    <label for="email">Email</label>
                    <input class="form-control searchcolor" type="Email" id="email" value="" name="email" />
                </div>
                <div>
                    <label for="password">Password</label>
                    <input class="form-control searchcolor"  type="password" id="password" value="" name="password" />
                </div>
                <div class="">
                    <label for="password">Password</label>
                    <input class="form-control searchcolor" type="password" id="password2" value="" name="passwordrepeat" />
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="sign">
                    <input class="btn btn-outline-primary" type="submit" value="Sign Up" />&nbsp;&nbsp;&nbsp;or&nbsp;&nbsp;&nbsp;<a href="/#login_form" id="login_pop">Log In</a>
                </div>
            </form>
        </div>
    </div>
</@c.page>
