<!--<!DOCTYPE html>
<html>
<head>
	<title>Example</title>
</head>
<body>
	<form name="loandata">
		<input type="text" name="text" id="text" onchange="calculate();">
		<br>
		<input type="text" id="text1">
		<br>
		<span class="result" id="payment"></span>
	</form>
<script language="JavaScript">
function calculate(){
	var textInput = document.loandata.text.value;
	var textOutput = document.getElementById("payment");
	textOutput.innerHTML = textInput
}
</script>
</body>
</html>-->
<html>
<head>
    <title></title>
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
</head>
<body>
<div class="panel panel-default">
    <div class="panel-body">Пример простой панели</div>
    <button type="button">but</button>
</div>
<script>

</script>

<input type="text" id="query" class="text-field valid" autocomplete="off" placeholder="Search here">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.2.24/jquery.autocomplete.min.js"></script>
<script>
    var v = new Array();
    <#list users as usere>
        v[v.length] = "${usere.username}";
    </#list>
    v[v.length] = "arti";
    //a1 = $('#query').autocomplete({
    //    width: 448,
    //    delimiter: /(,|;)\s*/,
    //    lookup: 'Andorra,Azerbaijan,Bahamas,Bahrain,Benin,Bhutan,Bolivia,Bosnia Herzegovina,Botswana,Brazil,Brunei,Bulgaria,Burkina, Burundi,Cambodia,Cameroon,Canada,Cape Verde,Central African Rep,Chile,China,Colombia,Comoros,Congo,Congo {Democratic Rep},Costa Rica,Croatia,Cuba,Cyprus,Czech Republic,Denmark,Djibouti,East Timor,Ecuador,Egypt,El Salvador,Equatorial Guinea,Eritrea,Fiji,France,Georgia,Germany,Ghana,Greece,Grenada,Guatemala,Guinea,Guinea-Bissau,Guyana,Haiti,Honduras,Hungary,India,Iraq,Ireland {Republic},Ivory Coast,Jamaica,Japan,Kazakhstan,Kiribati,Korea North,'.split(',')
    //});
    a1 = $('#query').autocomplete({
        width: 448,
        delimiter: /(,|;)\s*/,
        lookup: v
    });



</script>
</body>
</html>



