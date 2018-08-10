var action = "";
var updateObj = {};
var addObj = {};
var d = new Date();
var dd = d.getDate() < 10? '0' + d.getDate() : d.getDate();
var mm = (d.getMonth() + 1);
mm = mm < 10? '0' + mm : mm;
var yyyy = d.getFullYear();

function updateSuppliesStocksRecord(){
	var isNull=false;
	
	$w("itemName txtQuantity txtReferenceNo txtDateAdded txtPurchaseDate").each(function(c){
		if ($F(c) == null || $F(c) == ""){
			isNull = true;
		}
	})
	
	if(isNull){
		alert("Fields cannot be null");
		return false;
	}
	
	if(checkdate($F("txtDateAdded")) == false || checkdate($F("txtPurchaseDate")) == false) {
		return false;
	}

	updateObj.stockId = $F("stockId");
	updateObj.supplyId = $F("itemName");
	updateObj.purchaseDate = $F("txtPurchaseDate");
	updateObj.referenceNo = $F("txtReferenceNo");
	
	new Ajax.Request(contextPath + "/updateSuppliesStocksRecord", {
		method: "POST",
		parameters: {
			action: "updateRecord",
			stockId: updateObj.stockId,
			supplyId: updateObj.supplyId,
			purchaseDate: updateObj.purchaseDate,
			referenceNo: updateObj.referenceNo,
		},
		onComplete: function(){
			alert("Record Updated!");
			window.location.reload();
		}
	});
}

function checkdate(input){
	var validformat1=/^\d{2}\/\d{2}\/\d{4}$/;
	var validformat2=/^\d{1}\/\d{2}\/\d{4}$/;
	var returnval=false;
	
	if (!validformat1.test(input) && !validformat2.test(input))
		alert("Invalid Date Format. Please correct and submit again.");
	else{
		var monthfield=input.split("/")[0];
		var dayfield=input.split("/")[1];
		var yearfield=input.split("/")[2];
		var dayobj = new Date(yearfield, monthfield-1, dayfield);
		
		if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
			alert("Invalid Day, Month, or Year range detected. Please correct and submit again.");
		else{
			returnval=true;
		}
	}
		return returnval;
}

function checkQuantity(input) {
	if (isNaN(parseInt(input)) || input < 1) {
		alert("Please input a valid Quantity.");
		return false;
	}
	return true;
}

function addSuppliesStocksRecord(){
	var isNull=false;
	
	$w("itemName txtQuantity txtReferenceNo txtDateAdded txtPurchaseDate").each(function(c){
		if ($F(c) == null || $F(c) == ""){
			isNull = true;
		}
	})
	
	if(isNull){
		alert("Fields cannot be null");
		return false;
	}
	
	if(checkdate($F("txtDateAdded")) == false || checkdate($F("txtPurchaseDate")) == false) {
		return false;
	}
	
	if(checkQuantity($F("txtQuantity")) == false) {
		return false;
	}
	
	addObj.supplyId = $F("itemName");
	addObj.itemName = itemName.options[itemName.selectedIndex].innerHTML;
	addObj.dateAdded = $F("txtDateAdded");
	addObj.purchaseDate = $F("txtPurchaseDate");
	addObj.referenceNo = $F("txtReferenceNo");
	addObj.quantity = $F("txtQuantity");
	
	$("txtQuantity").value = "";
	$("txtReferenceNo").value = "";
	$("txtDateAdded").value = "";
	$("txtPurchaseDate").value = "";
}

function saveSuppliesStocksRecord(){
	new Ajax.Request(contextPath + "/addSuppliesStocksRecord", {
		method: "POST",
		parameters: {
				action: "insertRecord",
				supplyId: addObj.supplyId,
				dateAdded: addObj.dateAdded,
				purchaseDate: addObj.purchaseDate,
				referenceNo: addObj.referenceNo,
				quantity: addObj.quantity,
		},
		onComplete: function(){
			 alert("Record Inserted!");
			 window.location.reload();
		}
	});
}

function searchSuppliesStocksRecord() {
	new Ajax.Request(contextPath + "/searchSuppliesStocksRecord", {
		method: "POST",
		parameters: {
				action: "searchRecord",
				searchTxt: $F("txtSearch")
		},
		onComplete: function(response){
			 $("mainContents").update(response.responseText);
			 $("itemName").value = "";
		}
	}); 
}

function cancelSuppliesStocksRecord() {
	window.location.reload();
}