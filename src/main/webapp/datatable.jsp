<!DOCTYPE html>
<html>

<head>
	<title>Details Page</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta name="robots" content="noindex, nofollow">
	<meta name="googlebot" content="noindex, nofollow">
	<link rel="shortcut icon" href="app/resources/images/favicon.ico" />

	<script type="text/javascript" language="javascript" src="https://code.jquery.com/jquery-1.8.3.js"></script>

	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js"></script>
	<script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js"></script>



	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="app/resources/css/styles-datatables.css">

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
		function numberWithCommasNoDecimals(x) {
			return Math.floor(x).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
		function numberWithCommasDecimals(x) {
			return (x).toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
		function getChart9(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer Code", "Dealership", "Total Winners"];
			tableData.data = [];
			var delarName = {};
			var dealerCode = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				delarName[obj.dealerCode] = obj.dealerName;
				dealerCode[obj.dealerCode] = obj.dealerCode;
			}
			for (var key in dealerCode) {
				var outerObj = {}
				var outerData = jsonData.filter(function (ele, index, array) {
					return dealerCode[key] === ele.dealerCode;
				});
				var sid = {};
				for (var k = 0; k < outerData.length; k++) {
					var obj = outerData[k];
					sid[obj.sid] = obj.sid;
				}
				var innerDataObj = {};
				var ytdWinnersTotal = 0;
				innerDataObj.headers = ["", "Participant"]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					// innerDataObj.data.push([innerData[0].name, innerData[0].winners]);
					innerDataObj.data.push(["", innerData[0].name]);
					ytdWinnersTotal = ytdWinnersTotal + innerData[0].winners;
					// ramTotal = ramTotal + (innerData[1] == undefined ? 0 : innerData[1].winners);
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", dealerCode[key], delarName[key], numberWithCommasDecimals(ytdWinnersTotal)], "innerData": innerDataObj })
			}
			return tableData;
		}
		function getChart10(jsonData) {
			var tableData = {};
			//tableData.headers = ["Dealer", "Jeep", "Ram", "Total Points Earned"];
			tableData.headers = ["DealerCode", "Dealer", "Total Points Earned"];
			tableData.data = [];
			var delarName = {};
			var dealerCode = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				delarName[obj.dealerCode] = obj.dealerName;
				dealerCode[obj.dealerCode] = obj.dealerCode;

			}
			for (var key in dealerCode) {
				var outerObj = {}
				var outerData = jsonData.filter(function (ele, index, array) {
					return dealerCode[key] === ele.dealerCode;
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
					"",
					"Participant",
					"Total Points Earned"
				]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					// innerDataObj.data.push([innerData[0].name, innerData[0].points, innerData[1] == undefined ? "" : innerData[1].points, innerData[0].points + (innerData[1] == undefined ? 0 : innerData[1].points)]);
					for (var y = 0; y < innerData.length; y++) {
						innerDataObj.data.push(["", innerData[y].name, numberWithCommasDecimals(innerData[y].points)]);
						jeepTotal = jeepTotal + innerData[y].points;
						//ramTotal = ramTotal + (innerData[y] == undefined ? 0 : innerData[1].points);
					}
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", dealerCode[key], delarName[key], numberWithCommasDecimals(jeepTotal)], "innerData": innerDataObj })
			}
			return tableData;
		}
		function getChart11(jsonData) {
			var tableData = {};
			// tableData.headers = ["Dealer", "Jeep Expert Completed", "Ram Expert Completed", "Total"];
			tableData.headers = ["Dealer Code", "Dealer", "Ram Expert Completed"];

			//tableData.headers = ["Dealer", "Jeep Expert Completed", "Ram Expert Completed", "Tech Expert Completed", "Total"];

			tableData.data = [];
			var delarName = {};
			var dealerCode = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				delarName[obj.dealerCode] = obj.dealerName;
				dealerCode[obj.dealerCode] = obj.dealerCode;
			}
			for (var key in dealerCode) {
				var outerObj = {}
				var outerData = jsonData.filter(function (ele, index, array) {
					return dealerCode[key] === ele.dealerCode;
				});
				var sid = {};
				for (var k = 0; k < outerData.length; k++) {
					var obj = outerData[k];
					sid[obj.sid] = obj.sid;
				}
				var innerDataObj = {};
				var jeepTotal = 0;
				var ramTotal = 0;
				//innerDataObj.headers = ["", "Participant", "Jeep Expert Completed", "Ram Expert Completed", "Tech Expert Completed"]
				// innerDataObj.headers = [" ", "Participant", "Jeep Expert Completed", "Ram Expert Completed"]
				innerDataObj.headers = [" ", "Participant", "Ram Expert Completed"]

				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					//innerDataObj.data.push([innerData[0].name, innerData[0].points, innerData[1] == undefined ? "" : innerData[1].points, innerData[0].points + (innerData[1] == undefined ? 0 : innerData[1].points)]);

					var rowData = [""]
					rowData.push(innerData[0].name)

					var programsArray = ["RAM"]
					for (var r = 0; r < programsArray.length; r++) {
						var programObj = innerData.filter(function (ele, index, array) {
							console.log(programsArray[r] === ele.certType.trim())
							return programsArray[r] === ele.certType.trim();

						});
						var value = 0;

						for (var p = 0; p < programObj.length; p++) {
							value = value + programObj[p].cert
						}
						rowData.push(value)
						//console.log(programObj)
					}
					console.log(rowData)
					innerDataObj.data.push(rowData);
					//jeepTotal = jeepTotal + (rowData[1] == undefined ? 0 : rowData[1]);
					ramTotal = ramTotal + (rowData[2] == undefined ? 0 : rowData[2]);
					//ramTotal = ramTotal + (rowData[2] == undefined ? 0 : rowData[2]);
				}
				// tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", delarName[key], numberWithCommasDecimals(jeepTotal), numberWithCommasDecimals(ramTotal), numberWithCommasDecimals(jeepTotal + ramTotal)], "innerData": innerDataObj })
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", dealerCode[key], delarName[key], numberWithCommasDecimals(ramTotal)], "innerData": innerDataObj })
			}
			return tableData;
		}
		function getChart12(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer Code", "Dealer", "Award Points"];
			tableData.data = [];
			var delarName = {};
			var dealerCode = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				delarName[obj.dealerCode] = obj.dealerName;
				dealerCode[obj.dealerCode] = obj.dealerCode;
			}
			for (var key in dealerCode) {
				var outerObj = {}
				var outerData = jsonData.filter(function (ele, index, array) {
					return dealerCode[key] === ele.dealerCode;
				});
				var sid = {};
				for (var k = 0; k < outerData.length; k++) {
					var obj = outerData[k];
					sid[obj.sid] = obj.sid;
				}
				var innerDataObj = {};
				var awardsPointTotal = 0;
				var earningsTotal = 0;
				innerDataObj.headers = ["", "Participant", "Award Points"]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					innerDataObj.data.push(["", innerData[0].name, numberWithCommasDecimals(innerData[0].points)]);
					awardsPointTotal = awardsPointTotal + innerData[0].points;
					earningsTotal = earningsTotal + innerData[0].earnings;
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", dealerCode[key], delarName[key], numberWithCommasDecimals(awardsPointTotal)], "innerData": innerDataObj })
			}
			return tableData;
		}
		function getChart13(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer Code", "Dealer", "Certified", "Certified Specialist", "Master Certified", "Total Certifications"];
			tableData.data = [];
			var delarName = {};
			var dealerCode = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				delarName[obj.dealerCode] = obj.dealerName;
				dealerCode[obj.dealerCode] = obj.dealerCode;

			}
			for (var key in dealerCode) {
				var outerObj = {}
				var outerData = jsonData.filter(function (ele, index, array) {
					return dealerCode[key] === ele.dealerCode;
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
				innerDataObj.headers = [" ", "Participant", "Certified", "Certified Specialist", "Master Certified"]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					// innerDataObj.data.push([innerData[0].name, innerData[0].certified, innerData[0].certifiedSpecalist, innerData[0].masterCertified, innerData[0].certified + innerData[0].certifiedSpecalist + innerData[0].masterCertified]);
					for (var x = 0; x < innerData.length; x++) {
						innerDataObj.data.push(["", innerData[x].name,
							numberWithCommasDecimals(innerData[x].certified),
							numberWithCommasDecimals(innerData[x].certifiedSpecialist),
							numberWithCommasDecimals(innerData[x].masterCertified)]);
						totalCertified = totalCertified + innerData[x].certified;
						totalCertifiedSpecialist = totalCertifiedSpecialist + innerData[x].certifiedSpecialist;
						totalMasterCertified = totalMasterCertified + innerData[x].masterCertified;
					}

				}
				tableData.data.push({
					"data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", dealerCode[key],
						delarName[key],
						numberWithCommasDecimals(totalCertified),
						numberWithCommasDecimals(totalCertifiedSpecialist),
						numberWithCommasDecimals(totalMasterCertified),
						numberWithCommasDecimals(totalCertified + totalCertifiedSpecialist + totalMasterCertified)], "innerData": innerDataObj
				})
			}
			return tableData;
		}
		function getChart19(jsonData) {
			var tableData = {};
			tableData.headers = ["Dealer Code", "Dealer",  "Express Lane", "Magneti Marelli", "Mopar Parts", "MVP", "Parts Counter", "Uconnect", "wiAdvisor", "Total"];
			tableData.data = [];
			var dealerCode = {};
			var dealerName = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				dealerCode[obj.dealerCode] = obj.dealerCode;
				dealerName[obj.dealerCode] = obj.dealerName;
			}
			for (var key in dealerCode) {
				var outerObj = {}
				var outerData = jsonData.filter(function (ele, index, array) {
					return dealerCode[key] === ele.dealerCode;
				});
				var sid = {};
				for (var k = 0; k < outerData.length; k++) {
					var obj = outerData[k];
					sid[obj.sid.trim()] = obj.sid.trim();
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
				innerDataObj.headers = ["Participant","Express Lane","Magneti Marelli","Mopar Parts","MVP","Parts Counter","Uconnect","wiAdvisor","Total"]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid.trim();
					});
					//console.log(innerData)
					var rowData = [innerData[0].name];
					var total = 0;
					var programName = ["Express Lane", "Magneti Marelli", "Mopar Parts", "MVP", "Parts Counter", "Uconnect", "wiAdvisor"]
					for (var j = 0; j < programName.length; j++) {
						var obj = innerData.filter(function (ele, index, array) {
							//console.log(programName[j] + " === " + ele.program)
							return programName[j] === ele.program;
						});

						var value = 0;
						//console.log(obj)
						for (var t = 0; t < obj.length; t++) {
							value = value + (obj[t] === undefined ? 0 : (obj[t].earningsYTD));
						}

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
					for (var m = 1; m < rowData.length; m++) {
						rowData[m] = "$" + numberWithCommasDecimals(rowData[m]);
					}
				}
				tableData.data.push({
					"data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">",
						dealerCode[key],
						dealerName[key],						
						"$" + numberWithCommasDecimals(expressLaneTotal),
						"$" + numberWithCommasDecimals(magnettiMarelliTotal),
						"$" + numberWithCommasDecimals(moparPartsTotal),
						"$" + numberWithCommasDecimals(mvpTotal),
						"$" + numberWithCommasDecimals(partsCounter),
						"$" + numberWithCommasDecimals(uConnectTotal),
						"$" + numberWithCommasDecimals(wiAdvisor),
						"$" + numberWithCommasDecimals(total)], "innerData": innerDataObj
				})
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
			tableData.headers = ["DealerCode", "Dealer",  "Total Points Earned"];
			tableData.data = [];
			var delarName = {};
			var dealerCode = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				delarName[obj.dealerCode] = obj.dealerName;
				dealerCode[obj.dealerCode] = obj.dealerCode;
			}
			for (var key in dealerCode) {
				var outerObj = {}
				var outerData = jsonData.filter(function (ele, index, array) {
					return dealerCode[key] === ele.dealerCode;
				});
				var sid = {};
				for (var k = 0; k < outerData.length; k++) {
					var obj = outerData[k];
					sid[obj.sid] = obj.sid;
				}
				var innerDataObj = {};
				var totalEarnedPoints = 0;
				innerDataObj.headers = [
					"",
					"Participant",
					"Total Points Earned"
				]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});

					for (var z = 0; z < innerData.length; z++) {
						innerDataObj.data.push(["", innerData[0].name, numberWithCommasDecimals(innerData[z].earnedPoints)]);
						totalEarnedPoints = totalEarnedPoints + innerData[z].earnedPoints;
					}
					// innerDataObj.data.push(["", innerData[0].name, numberWithCommasDecimals(innerData[0].earnedPoints)]);
					// totalEarnedPoints = totalEarnedPoints + innerData[0].earnedPoints;
					// ramTotal = ramTotal + (innerData[1] == undefined ? 0 : innerData[1].winners);
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">",dealerCode[key], delarName[key],  numberWithCommasDecimals(totalEarnedPoints)], "innerData": innerDataObj })
			}
			return tableData;
		}

		function getChart31(jsonData) {
			var tableData = {};
			tableData.headers = [
				"Dealer",
				"Service Managers",
				"Service Advisors",
				"Service Technicians",
				"Parts Managers",
				"Parts Advisors",
				"Sales Managers",
				"Sales Consultants"];
			tableData.data = [];
			var delarName = {};

			var delarName = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				delarName[obj.dealerCode] = obj.dealerCode;
			}
			for (var key in delarName) {
				var outerObj = {}
				var outerData = jsonData.filter(function (ele, index, array) {
					return delarName[key] === ele.dealerCode;
				});
				var positionCodeArray = ["09", "13", "23", "08", "14", "04", "42"]
				var rowData = [" "];

				rowData.push(outerData[0].dealerName);
				var total = 0;
				for (var n = 0; n < positionCodeArray.length; n++) {
					var data = outerData.filter(function (ele, index, array) {
						return positionCodeArray[n] === ele.positionCode;
					});

					rowData.push((data[0].percentage) + "%")
					//total = Math.floor(total + data[0].percentage);
				}

				//rowData.push(total+"%");
				tableData.data.push({
					"data": rowData, "innerData": []
				})

			}
			return tableData;
		}
		function getChart32(jsonData) {
			var tableData = {};
			tableData.headers = [
				"Dealer Code",
				"Dealer",				
				"Level 0",
				"Performance",
				"Process",
				"Voice of Employee",
				"Training",
				"Facility",
				"CFAFE Award Certification"
			];
			tableData.data = [];
			var delarName = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				tableData.data.push({
					"data": [" ", obj.dealerCode, obj.dealerName, obj.noCertification,
						obj.performance, obj.process, obj.voiceofEmployee, obj.training,
						obj.facility, obj.cfafeawardCertification,
					], "innerData": []
				})
			}
			return tableData;
		}
		function getChart33(jsonData) {
			var tableData = {};
			tableData.headers = [
				"Dealer Code",
				"Dealer",
				"Level 0",
				"Performance",
				"Process",
				"Voice of Employee",
				"Training",
				"Facility",
				"CFAFE Award Certification"
			];
			tableData.data = [];
			var delarName = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				tableData.data.push({
					"data": [" ",
						obj.dealerCode,
						obj.dealerName,
						obj.noCertification + "%",
						obj.performance + "%",
						obj.process + "%",
						obj.voiceofEmployee + "%",
						obj.training + "%",
						obj.facility + "%",
						obj.cfafeawardCertification + "%"
					], "innerData": []
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
			tableData.headers = ["Dealer Code", "Dealer", "Total Dollars Earned"];
			tableData.data = [];
			var delarName = {};
			var dealerCode = {};
			for (var i = 0; i < jsonData.length; i++) {
				var obj = jsonData[i];
				delarName[obj.dealerCode] = obj.dealerName;
				dealerCode[obj.dealerCode] = obj.dealerCode;

			}
			for (var key in dealerCode) {
				var outerObj = {}
				var outerData = jsonData.filter(function (ele, index, array) {
					return dealerCode[key] === ele.dealerCode;
				});
				var sid = {};
				for (var k = 0; k < outerData.length; k++) {
					var obj = outerData[k];
					sid[obj.sid] = obj.sid;
				}
				var innerDataObj = {};
				var totalEarnedDollars = 0;
				innerDataObj.headers = [
					"", "Participant",
					"Total Dollars Earned"
				]
				innerDataObj.data = [];
				for (var key1 in sid) {
					var innerData = outerData.filter(function (ele, index, array) {
						return sid[key1] === ele.sid;
					});
					innerDataObj.data.push(["", innerData[0].name, "$" + numberWithCommasDecimals(innerData[0].earnings)]);
					totalEarnedDollars = totalEarnedDollars + (innerData[0].earnings);
					// ramTotal = ramTotal + (innerData[1] == undefined ? 0 : innerData[1].winners);
				}
				tableData.data.push({ "data": ["<img src=\"https://i.imgur.com/SD7Dz.png\">", dealerCode[key], delarName[key], "$" + numberWithCommasDecimals(totalEarnedDollars)], "innerData": innerDataObj })
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
					dom: 'Bfrtip',
					buttons: [
						'pageLength',


						{
							extend: 'excelHtml5'

						}

					],

					"scrollY": "600px",
					"scrollX": true,
					"pagingType": "full_numbers",
					"bLengthChange": true,
					"bInfo": true,
					data: dataset,
					columns: cloumns,
					"bPaginate": true,
					"destroy": true,

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

						this.src = "https://i.imgur.com/d4ICC.png";
						oTable.fnOpen(nTr, fnFormatDetails(iTableCounter, detailsTableHtml), 'details');
						oInnerTable = $("#exampleTable_" + iTableCounter).dataTable({
							dom: 'Bfrtip',
							buttons: [
								'pageLength',

								{
									extend: 'excelHtml5'
								}
							],
							"pagingType": "full_numbers",
							"bInfo": true,
							"bLengthChange": true,
							"bPaginate": true,

							data: data,
							columns: cloumns,
							"bFilter": false,
							"bSort": true,
							"bPaginate": false,
							"oLanguage": {
								"sInfo": "_TOTAL_ entries"
							},
							"fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
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

	<style>
		.datatable-margin-class {
			margin: 20px;
		}
	</style>

	<fieldset class="datatable-margin-class">
		<table id="exampleTable" class="display  datatable-margin-class" cellspacing="0" width="100%">
		</table>

		<div style="display: none">
			<table id="detailsTable" class="display " cellspacing="0" width="100%">
			</table>
		</div>
	</fieldset>
</body>

</html>