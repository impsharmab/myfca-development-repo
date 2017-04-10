
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="robots" content="noindex, nofollow">
<meta name="googlebot" content="noindex, nofollow">


<script type="text/javascript" language="javascript"
	src="http://code.jquery.com/jquery-1.8.3.js"></script>

<style type="text/css">
body {
	font-size: 14px;
	font-family: Helvetica, Arial, sans-serif;
	color: #666;
}

h4 {
	font-size: 16px;
	color: #dd007d;
}

table {
	font-size: 14px;
	font-family: Helvetica, Arial, sans-serif;
	color: #666;
}
</style>

<title></title>


<script type='text/javascript'>//<![CDATA[
$(window).load(function(){
	window.history.pushState("string", "Title", "datatable");
	$.ajax({
		url: "/myfcarewards/services/data/${chartId}/${territory}",
			//data: { signature: authHeader },
				type: "GET",
					beforeSend: function(xhr){xhr.setRequestHeader('Authorization', '${token}');},
						success: function(data) { alert('Success!' + data); }
});
	
	//$.get("/myfcarewards/services/data/${chartId}/${territory}", function(data, status){
		//alert("Data: " + data + "\nStatus: " + status);
		//});
	
	
function fnFormatDetails(table_id, html) {
    var sOut = "<table id=\"exampleTable_" + table_id + "\">";
    sOut += html;
    sOut += "</table>";
    return sOut;
}

//////////////////////////////////////////////////////////// EXTERNAL DATA - Array of Objects 



// DETAILS ROW A 
var detailsRowAPlayer1 = { name: "Ralph Ellison", uc:"$10", mp:"$10", wa:"$10", el:"$10", pc:"$10", mm:"$10", mv:"$10", reward: "$34,000"};
var detailsRowAPlayer2 = { name: "Roger Ailes", uc:"$10", mp:"$10", wa:"$10", el:"$10", pc:"$10", mm:"$10", mv:"$10", reward: "$34,000"};
var detailsRowAPlayer3 = { name: "Brian DiStephano", uc:"$10", mp:"$10", wa:"$10", el:"$10", pc:"$10", mm:"$10", mv:"$10", reward: "$34,000"};
                         
var detailsRowA = [ detailsRowAPlayer1, detailsRowAPlayer2, detailsRowAPlayer3 ];

// DETAILS ROW B 
var detailsRowBPlayer1 = { name: "Ralph Ellison", uc:"$10", mp:"$10", wa:"$10", el:"$10", pc:"$10", mm:"$10", mv:"$10", reward: "$34,000"};
var detailsRowB = [ detailsRowBPlayer1 ];
                    
// DETAILS ROW C 
var detailsRowCPlayer1 = { name: "Ralph Ellison", uc:"$10", mp:"$10", wa:"$10", el:"$10", pc:"$10", mm:"$10", mv:"$10", reward: "$34,000"};
                         
var detailsRowC = [ detailsRowCPlayer1 ];

var rowA = { dealer: "Dealer ABC",  uc:"$10", mp:"$10", wa:"$10", el:"$10", pc:"$10", mm:"$10", mv:"$10", total: "$350,000", details: detailsRowA};
var rowB = { dealer: "Dealer XYZ", uc:"$10", mp:"$10", wa:"$10", el:"$10", pc:"$10", mm:"$10", mv:"$10",  total: "$1,000,000", details: detailsRowB};


var newRowData = [rowA, rowB,rowA, rowB,rowA, rowB,rowA, rowB,rowA, 
rowB,rowA, rowB,rowA, rowB,rowA, rowB,rowA, rowB,rowA, rowB,
rowA, rowB,rowA, rowB,rowA, rowB,rowA, rowB,rowA, rowB,rowA, rowB,rowA, rowB] ;

////////////////////////////////////////////////////////////

var iTableCounter = 1;
    var oTable;
    var oInnerTable;
    var detailsTableHtml;

    //Run On HTML Build
    $(document).ready(function () {
        
        // you would probably be using templates here
        detailsTableHtml = $("#detailsTable").html();

        //Insert a 'details' column to the table
        var nCloneTh = document.createElement('th');
        var nCloneTd = document.createElement('td');
        nCloneTd.innerHTML = '<img src="http://i.imgur.com/SD7Dz.png">';
        nCloneTd.className = "center";

        $('#exampleTable thead tr').each(function () {
            this.insertBefore(nCloneTh, this.childNodes[0]);
        });

        $('#exampleTable tbody tr').each(function () {
            this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
        });
        
       
        //Initialse DataTables, with no sorting on the 'details' column
        var oTable = $('#exampleTable').dataTable({
		
		"scrollY": "600px",
        "scrollX": true,
		"pagingType": "full_numbers",
        
            "bJQueryUI": true,
            "aaData": newRowData,
            
            "aoColumns": [
                {
                   "mDataProp": null,
                   "sClass": "control center",
                   "sDefaultContent": '<img src="http://i.imgur.com/SD7Dz.png">'
                },
                { "mDataProp": "dealer" },
				
				{ "mDataProp": "uc" },
				{ "mDataProp": "mp" },
				{ "mDataProp": "wa" },
				{ "mDataProp": "el" },
				{ "mDataProp": "pc" },
				{ "mDataProp": "mm" },
				{ "mDataProp": "mv" },

				
                { "mDataProp": "total" }
            ],
            "oLanguage": {
			    "sInfo": "_TOTAL_ entries"
			},
            "aaSorting": [[1, 'asc']]
        });

        /* Add event listener for opening and closing details
        * Note that the indicator for showing which row is open is not controlled by DataTables,
        * rather it is done here
        */
        $('#exampleTable tbody td img').live('click', function () {
            var nTr = $(this).parents('tr')[0];
            var nTds = this;
            
            if (oTable.fnIsOpen(nTr)) {
                /* This row is already open - close it */
                this.src = "http://i.imgur.com/SD7Dz.png";
                oTable.fnClose(nTr);
            }
            else {
                /* Open this row */
                var rowIndex = oTable.fnGetPosition( $(nTds).closest('tr')[0] ); 
	            var detailsRowData = newRowData[rowIndex].details;
               
                this.src = "http://i.imgur.com/d4ICC.png";
				
                oTable.fnOpen(nTr, fnFormatDetails(iTableCounter, detailsTableHtml), 'details');
                oInnerTable = $("#exampleTable_" + iTableCounter).dataTable({
                    "bJQueryUI": true,
                    "bFilter": false,
                    "aaData": detailsRowData,
                    "bSort" : true, // disables sorting
                    "aoColumns": [
	                { "mDataProp": "name" },
									{ "mDataProp": "uc" },
				{ "mDataProp": "mp" },
				{ "mDataProp": "wa" },
				{ "mDataProp": "el" },
				{ "mDataProp": "pc" },
				{ "mDataProp": "mm" },
				{ "mDataProp": "mv" },
	                { "mDataProp": "reward" }
	            ],
                    "bPaginate": false,
                    "oLanguage": {
						"sInfo": "_TOTAL_ entries"
			        },
                    "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
						/*
                      var imgLink = aData['pic']; 
                      var imgTag = '<img width="100px" src="' + imgLink + '"/>';
                      $('td:eq(0)', nRow).html(imgTag);  
                     return nRow;
					 */
                    }
                });
                iTableCounter = iTableCounter + 1;
            }
        });


    });
});

//]]> 

</script>


</head>

<body>
	${data}
	<link rel="stylesheet" type="text/css"
		href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables_themeroller.css">

	<script type="text/javascript" language="javascript"
		src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>



	<table id="exampleTable" class="display " cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Dealer</th>
				<th>uConnect</th>
				<th>Mopar Parts</th>
				<th>WiAdvisor</th>
				<th>Express Lane</th>
				<th>Parts Coutner</th>
				<th>Magnetti Marelli</th>
				<th>MVP</th>
				<th>Reward Total</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>

	<div style="display: none">
		<table id="detailsTable" class="display " cellspacing="0" width="100%">
			<thead>
				<tr>

					<th>Particpant</th>

					<th>uConnect</th>
					<th>Mopar Parts</th>
					<th>WiAdvisor</th>
					<th>Express Lane</th>
					<th>Parts Coutner</th>
					<th>Magnetti Marelli</th>
					<th>MVP</th>


					<th>Reward Total</th>

				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>


</body>

</html>

