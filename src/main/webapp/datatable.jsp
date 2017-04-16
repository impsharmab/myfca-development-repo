<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="robots" content="noindex, nofollow">
	<meta name="googlebot" content="noindex, nofollow">


	<script type="text/javascript" language="javascript" src="https://code.jquery.com/jquery-1.8.3.js"></script>

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


	<script type='text/javascript'>
		//<![CDATA[
		//var chartId = ${chartId };
		$(window).load(function () {

			$.ajax({
				url: "/myfcarewards/services/data/${chartId}/${territory}",
				//data: { signature: authHeader },
				type: "GET",
				beforeSend: function (xhr) { xhr.setRequestHeader('Authorization', '${token}'); },
				success: function (data) {
					window.history.pushState("string", "Title", "datatable");
					//alert('Success!' + data);

					var tableData = getParsedJSON('${chartId}', data);

					//a var tableData = getJEEPRAMJSON(data);
					 loadTableData(tableData);
				}
			});
			function getParsedJSON(id, data) {
				switch (id) {
					case '11' : return getJEEPRAMJSON(data)
					case '12' : return getJEEPRAMJSON(data)
				}
			}

			//$.get("/myfcarewards/services/data/${chartId}/${territory}", function(data, status){
			//alert("Data: " + data + "\nStatus: " + status);
			//});


		});
		function getJEEPRAMJSON(jsonData) {

			var tableData = {};
			tableData.headers = ["DEALER", "JEEP", "RAM", "TOTAL"];
			tableData.data = [];

			var delarName = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				delarName[obj.dealerName] = obj.dealerName;
			}
			for (var key in delarName) {
				var outerObj = {}

				var outerData = jsonData.filter(function (ele, index, array) {
					return delarName[key] === ele.dealerName;
				});
				var sid = {};
				for (var k = 0; k < outerData.length; k++) {
					var obj = outerData[k];
					sid[obj.sid] = obj.sid;
				}

				var innerDataObj = {};
				var jeepTotal = 0;
				var ramTotal = 0;

				innerDataObj.headers = [
					"Participant",
					"Jeep",
					"Ram",
					"Total"
				]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					innerDataObj.data.push([innerData[0].name, innerData[0].points, innerData[1] == undefined ? "" : innerData[1].points, innerData[0].points + (innerData[1] == undefined ? 0 : innerData[1].points)]);
					jeepTotal = jeepTotal + innerData[0].points;
					ramTotal = ramTotal + (innerData[1] == undefined ? 0 : innerData[1].points);
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], jeepTotal, ramTotal, jeepTotal+ramTotal], "innerData": innerDataObj })

			}
			return tableData;
		}

		function loadTableData(tableData) {
			function fnFormatDetails(table_id, html) {
				var sOut = "<table id=\"exampleTable_" + table_id + "\">";
				sOut += html;
				sOut += "</table>";
				return sOut;
			}

			//////////////////////////////////////////////////////////// EXTERNAL DATA - Array of Objects 


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
				nCloneTd.innerHTML = '<img src="https://i.imgur.com/SD7Dz.png">';
				nCloneTd.className = "center";

				$('#exampleTable thead tr').each(function () {
					this.insertBefore(nCloneTh, this.childNodes[0]);
				});

				$('#exampleTable tbody tr').each(function () {
					this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
				});

				var data = tableData.data === undefined ? [] : tableData.data;
				var head = tableData.headers === undefined ? [] : tableData.headers;
				var cloumns = [{ "title": "" }];
				var dataset = [];
				for (var i = 0; i < head.length; i++) {
					var obj = { "title": head[i] };
					cloumns.push(obj)
				}
				var innerData = {};
				for (var i = 0; i < data.length; i++) {
					var dataObj = data[i];
					innerData[i] = dataObj.innerData;
					dataset.push(dataObj.data)
				}


				//Initialse DataTables, with no sorting on the 'details' column
				var oTable = $('#exampleTable').dataTable({

					data: dataset,
					columns: cloumns,
					"bJQueryUI": true,
					// "aaData": newRowData,
					// "bPaginate": false,
					"destroy": true,
					// "aoColumns": [
					//     {
					//        "mDataProp": null,
					//        "sClass": "control center",
					//        "sDefaultContent": '<img src="http://i.imgur.com/SD7Dz.png">'
					//     },
					//     { "mDataProp": "dealer" },

					// 	{ "mDataProp": "uc" },
					// 	{ "mDataProp": "mp" },
					// 	{ "mDataProp": "wa" },
					// 	{ "mDataProp": "el" },
					// 	{ "mDataProp": "pc" },
					// 	{ "mDataProp": "mm" },
					// 	{ "mDataProp": "mv" },


					//     { "mDataProp": "total" }
					// ],
					"oLanguage": {
						"sInfo": "_TOTAL_ entries"
					},
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
						this.src = "https://i.imgur.com/SD7Dz.png";
						oTable.fnClose(nTr);
					}
					else {
						/* Open this row */
						var rowIndex = oTable.fnGetPosition($(nTds).closest('tr')[0]);
						var data = innerData[rowIndex].data === undefined ? [] : innerData[rowIndex].data;
						var head = innerData[rowIndex].headers === undefined ? [] : innerData[rowIndex].headers;
						var cloumns = [];
						var dataset = [];
						for (var i = 0; i < head.length; i++) {
							var obj = { "title": head[i] };
							cloumns.push(obj)
						}


						//var detailsRowData = newRowData[rowIndex].details;

						this.src = "https://i.imgur.com/d4ICC.png";

						oTable.fnOpen(nTr, fnFormatDetails(iTableCounter, detailsTableHtml), 'details');
						oInnerTable = $("#exampleTable_" + iTableCounter).dataTable({
							data: data,
							columns: cloumns,
							"bJQueryUI": true,
							"bFilter": false,
							//     "aaData": detailsRowData,
							"bSort": true, // disables sorting
							//     "aoColumns": [
							//     { "mDataProp": "name" },
							// 					{ "mDataProp": "uc" },
							// { "mDataProp": "mp" },
							// { "mDataProp": "wa" },
							// { "mDataProp": "el" },
							// { "mDataProp": "pc" },
							// { "mDataProp": "mm" },
							// { "mDataProp": "mv" },
							//     { "mDataProp": "reward" }
							// ],
							"bPaginate": false,
							"oLanguage": {
								"sInfo": "_TOTAL_ entries"
							},
							"fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
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
		}

//]]>
	</script>


</head>

<body>




	<link rel="stylesheet" type="text/css" href="https://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables_themeroller.css">

	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>



	<table id="exampleTable" class="display " cellspacing="0" width="100%">

	</table>

	<div style="display: none">
		<table id="detailsTable" class="display " cellspacing="0" width="100%">

		</table>
	</div>
</body>

</html>