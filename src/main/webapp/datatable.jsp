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
					case '9': return getChart9(data);
					case '10': return getChart10(data);
					case '11': return getChart11(data);
					case '12': return getChart12(data);
					case '13': return getChart13(data);
					case '19': return getChart19(data);
					case '20': return getChart20(data);
					case '22': return getChart22(data);
					case '23': return getChart23(data);
					case '31': return getChart31(data);
					case '32': return getChart32(data);
					case '33': return getChart33(data);
					case '34': return getChart34(data);
					case '35': return getChart35(data);
					case '36': return getChart36(data);
				}
			}

			//$.get("/myfcarewards/services/data/${chartId}/${territory}", function(data, status){
			//alert("Data: " + data + "\nStatus: " + status);
			//});


		});
		function getChart9(jsonData) {
			var tableData = {};
			tableData.headers = ["DEALER", "TOTAL WINNERS"];
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
				var ytdWinnersTotal = 0;

				innerDataObj.headers = ["Participant"]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					// innerDataObj.data.push([innerData[0].name, innerData[0].winners]);
					innerDataObj.data.push([innerData[0].name]);
					ytdWinnersTotal = ytdWinnersTotal + innerData[0].winners;
					// ramTotal = ramTotal + (innerData[1] == undefined ? 0 : innerData[1].winners);
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], ytdWinnersTotal], "innerData": innerDataObj })

			}
			return tableData;

		}
		function getChart10(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer", "Jeep", "Ram", "Total Points Earned"];
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

					"Total Points Earned"
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
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], jeepTotal, ramTotal, jeepTotal + ramTotal], "innerData": innerDataObj })

			}
			return tableData;

		}
		function getChart11(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer", "Jeep Expert Completed", "Ram Expert Completed", "Total"];
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
					"Participant", "Jeep Expert Completed", "Ram Expert Completed"]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					//innerDataObj.data.push([innerData[0].name, innerData[0].points, innerData[1] == undefined ? "" : innerData[1].points, innerData[0].points + (innerData[1] == undefined ? 0 : innerData[1].points)]);
					innerDataObj.data.push([innerData[0].name, innerData[0].cert, innerData[1] == undefined ? "" : innerData[1].cert]);
					jeepTotal = jeepTotal + innerData[0].cert;
					ramTotal = ramTotal + (innerData[1] == undefined ? 0 : innerData[1].cert);
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], jeepTotal, ramTotal, jeepTotal + ramTotal], "innerData": innerDataObj })

			}
			return tableData;

		}
		function getChart12(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer", "Award Points", "Excellence Card Awards"];
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
				var awardsPointTotal = 0;
				var earningsTotal = 0;

				innerDataObj.headers = ["Participant", "Award Points", "Excellence Card Awards"]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					innerDataObj.data.push([innerData[0].name, innerData[0].points, "$" + innerData[0].earnings]);
					awardsPointTotal = awardsPointTotal + innerData[0].points;
					earningsTotal = earningsTotal + innerData[0].earnings;
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], awardsPointTotal, "$" + earningsTotal], "innerData": innerDataObj })

			}
			return tableData;



		}
		function getChart13(jsonData) {
			var tableData = {};
			tableData.headers = ["DEALER", "Certified", "Certified Specialist", "Master Certified", "Total Certification"];
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
				var totalCertified = 0;
				var totalCertifiedSpecialist = 0;
				var totalMasterCertified = 0;



				innerDataObj.headers = ["Participant", "Certified", "Certified Specialist", "Master Certified"]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					// innerDataObj.data.push([innerData[0].name, innerData[0].certified, innerData[0].certifiedSpecalist, innerData[0].masterCertified, innerData[0].certified + innerData[0].certifiedSpecalist + innerData[0].masterCertified]);
					innerDataObj.data.push([innerData[0].name, innerData[0].certified, innerData[0].certifiedSpecalist, innerData[0].masterCertified]);
					totalCertified = totalCertified + innerData[0].certified;
					totalCertifiedSpecialist = totalCertifiedSpecialist + innerData[0].certifiedSpecalist;
					totalMasterCertified = totalMasterCertified + innerData[0].masterCertified;

				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], totalCertified, totalCertifiedSpecialist, totalMasterCertified, totalCertified + totalCertifiedSpecialist + totalMasterCertified], "innerData": innerDataObj })

			}
			return tableData;

		}
		function getChart19(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer", "Express Lane", "Magneti Marelli", "Mopar Parts", "MVP", "Parts Counter", "UConnect", "wiAdvisor", "Total"];
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
				var expressLaneTotal = 0;
				var magnettiMarelliTotal = 0;
				var moparPartsTotal = 0;
				var mvpTotal = 0;
				var partsCounter = 0;
				var uConnectTotal = 0;
				var wiAdvisor = 0;
				var total = 0;

				innerDataObj.headers = ["Participant",
					"Express Lane",
					"Magneti Marelli",
					"Mopar Parts",
					"MVP",
					"Parts Counter",
					"UConnect",
					"wiAdvisor",
					"Total"]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					console.log(innerData)
					var rowData = [innerData[0].name];
					var total = 0;
					var programName = ["Express Lane", "Magneti Marelli", "Mopar Parts", "MVP", "Parts Counter", "Uconnect", "wiAdvisor"]
					for (var j = 0; j < programName.length; j++) {
						var obj = innerData.filter(function (ele, index, array) {
							console.log(programName[j] + " === " + ele.program)
							return programName[j] === ele.program;

						});
						console.log(obj)
						var value = obj[0] === undefined ? 0 : obj[0].earningsYTD;
						rowData.push(value)

						total = total + value;
					}
					rowData.push(total)
					innerDataObj.data.push(rowData)


					expressLaneTotal = expressLaneTotal + rowData[1];
					magnettiMarelliTotal = magnettiMarelliTotal + rowData[2];
					moparPartsTotal = moparPartsTotal + rowData[3];
					mvpTotal = mvpTotal + rowData[4];
					partsCounter = partsCounter + rowData[5];
					uConnectTotal = uConnectTotal + rowData[6];
					wiAdvisor = wiAdvisor + rowData[7];

					total = expressLaneTotal + magnettiMarelliTotal + moparPartsTotal + mvpTotal + partsCounter + uConnectTotal + wiAdvisor;

				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], expressLaneTotal, magnettiMarelliTotal, moparPartsTotal, mvpTotal, partsCounter, uConnectTotal, wiAdvisor, total], "innerData": innerDataObj })

			}
			return tableData;

		}
		function getChart20(jsonData) {
			//sir
		}
		function getChart22(jsonData) {
			//sir
		}
		function getChart23(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer", "TOTAL Points Earned"];
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
				var totalEarnedPoints = 0;

				innerDataObj.headers = [
					"Participant",
					"TOTAL Points Earned"
				]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					innerDataObj.data.push([innerData[0].name, innerData[0].earnedPoints]);
					totalEarnedPoints = totalEarnedPoints + innerData[0].earnedPoints;
					// ramTotal = ramTotal + (innerData[1] == undefined ? 0 : innerData[1].winners);
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], totalEarnedPoints], "innerData": innerDataObj })

			}
			return tableData;


		}
		function getChart31(jsonData) {

		}
		function getChart32(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer",
				"Level 0",
				"Performance",
				"Process",
				"Voice of Employee",
				"Training",
				"Facility",
				"CFAFE Award Certification",
				"Total"];
			tableData.data = [];

			var delarName = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];

				tableData.data.push({
					"data": [" ", obj.dealerName, obj.noCertification,
						obj.performance, obj.process, obj.voiceofEmployee, obj.training,
						obj.facility, obj.cfafeawardCertification,
						obj.noCertification + obj.performance + obj.process + obj.voiceofEmployee + obj.training+
						obj.facility + obj.cfafeawardCertification], "innerData": []
				})

			}
			return tableData;
		}
		function getChart33(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer",
				"Level 0",
				"Performance",
				"Process",
				"Voice of Employee",
				"Training",
				"Facility",
				"CFAFE Award Certification",
				"Total"];
			tableData.data = [];

			var delarName = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];

				tableData.data.push({
					"data": [" ", obj.dealerName, obj.noCertification,
						obj.performance, obj.process, obj.voiceofEmployee, obj.training,
						obj.facility, obj.cfafeawardCertification,
						obj.noCertification + obj.performance + obj.process + obj.voiceofEmployee + obj.training+
						obj.facility + obj.cfafeawardCertification], "innerData": []
				})

			}
			return tableData;

		}
		function getChart34(jsonData) {
			//total dealers enrolled
		}
		function getChart35(jsonData) {
			//total dealers not- enrolled
		}
		function getChart36(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer", "TOTAL Dollard Earned"];
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
				var totalEarnedDollars = 0;

				innerDataObj.headers = [
					"Participant",
					"TOTAL Dollard Earned"
				]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					innerDataObj.data.push([innerData[0].name, "$" + Math.round(innerData[0].earnings)]);
					totalEarnedDollars = totalEarnedDollars + Math.round(innerData[0].earnings);
					// ramTotal = ramTotal + (innerData[1] == undefined ? 0 : innerData[1].winners);
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], "$" + totalEarnedDollars], "innerData": innerDataObj })

			}
			return tableData;

		}
		// function getJEEPRAMJSON(jsonData) {

		// 	var tableData = {};
		// 	tableData.headers = ["DEALER", "JEEP", "RAM", "TOTAL"];
		// 	tableData.data = [];

		// 	var delarName = {};
		// 	for (var i = 0; i < jsonData.length; i++) {
		// 		var obj = jsonData[i];
		// 		delarName[obj.dealerName] = obj.dealerName;
		// 	}
		// 	for (var key in delarName) {
		// 		var outerObj = {}

		// 		var outerData = jsonData.filter(function (ele, index, array) {
		// 			return delarName[key] === ele.dealerName;
		// 		});
		// 		var sid = {};
		// 		for (var k = 0; k < outerData.length; k++) {
		// 			var obj = outerData[k];
		// 			sid[obj.sid] = obj.sid;
		// 		}

		// 		var innerDataObj = {};
		// 		var jeepTotal = 0;
		// 		var ramTotal = 0;

		// 		innerDataObj.headers = [
		// 			"Participant",
		// 			"Jeep",
		// 			"Ram",
		// 			"Total"
		// 		]
		// 		innerDataObj.data = [];
		// 		for (var key1 in sid) {
		// 			var innerData = outerData.filter(function (ele, index, array) {
		// 				return sid[key1] === ele.sid;
		// 			});
		// 			innerDataObj.data.push([innerData[0].name, innerData[0].points, innerData[1] == undefined ? "" : innerData[1].points, innerData[0].points + (innerData[1] == undefined ? 0 : innerData[1].points)]);
		// 			jeepTotal = jeepTotal + innerData[0].points;
		// 			ramTotal = ramTotal + (innerData[1] == undefined ? 0 : innerData[1].points);
		// 		}
		// 		tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], jeepTotal, ramTotal, jeepTotal + ramTotal], "innerData": innerDataObj })

		// 	}
		// 	return tableData;
		// }

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