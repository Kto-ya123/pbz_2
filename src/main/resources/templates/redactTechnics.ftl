<style>
    .brd {
        border: 4px double black; /* Параметры границы */
        background: #fc3; /* Цвет фона */
        padding: 10px; /* Поля вокруг текста */
    }
</style>
    <br/>
    <div class="brd">
        <div>
            <b>IdNumber: ${technics.idNumber}</b>
        </div>
        <div>
            Title: ${technics.title}
        </div>
        <div>
            Model: ${technics.model}
        </div>
        <div>
            Date: ${technics.datePurchase}
        </div>
        <div>
            Cash: ${technics.cash}
        </div>
        <div>
            Subdivision: ${technics.flat.subdivision.name}
        </div>
        <br/>
    </div>
    <div>
        <form action="/tech/redact/${technics.idNumber}" method="post">
            <div><label> Title: <input type="text" name="title"/> </label></div>
            <div><label> Model: <input type="text" name="model"/> </label></div>
            <div><label> Date: <input type="date" name="date"/> </label></div>
            <div><label> Cash: <input type="number" name="cash"/> </label></div>

            <div>
                Flat
                <select name="flatId"  style="width: 200px">
                    <#list flats as flat>
                        <option value="${flat.id}">${flat.id}</option>
                    </#list>
                </select>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div><input type="submit" value="Обновить"/></div>
        </form>
    </div>
<div>
    <a href="/tech">Назад</a>
</div>

