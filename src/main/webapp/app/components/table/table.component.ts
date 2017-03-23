import { Component, OnInit, ViewChild, ElementRef, Renderer, OnChanges, Input,OnDestroy } from '@angular/core';

declare var $: any;
@Component({
    moduleId: module.id,
    selector: 'datatable',
    template:  ``
})

export class TableComponent implements OnChanges ,OnDestroy{
    private otable: any;
    private randomId:any = new Date().valueOf();
    @Input("tableData") tableData: any;
    constructor(private element: ElementRef, private renderer: Renderer) {
        var elementHtml = element.nativeElement;
        elementHtml.innerHTML = `<table id="`+this.randomId+`"></table>
                <div style="display:none">
                    <table id="detailsTable">
                    </table>
                </div>`
    }

    
ngOnDestroy(){
   
}
    ngOnChanges(): void {        

        function fnFormatDetails(table_id, html) {
            var sOut = "<table id=\"exampleTable_" + table_id + "\">";
            sOut += html;
            sOut += "</table>";
            return sOut;
        }
        

        var iTableCounter = 1;
        // var oTable;
        var oInnerTable;
        var detailsTableHtml;



        // you would probably be using templates here
        detailsTableHtml = $("#detailsTable").html();

        //Insert a 'details' column to the table
        var nCloneTh = document.createElement('th');
        var nCloneTd = document.createElement('td');
        nCloneTd.innerHTML = '<img src="http://i.imgur.com/SD7Dz.png">';
        nCloneTd.className = "center";

        $('#'+this.randomId+' thead tr').each(function () {
            this.insertBefore(nCloneTh, this.childNodes[0]);
        });

        $('#'+this.randomId+' tbody tr').each(function () {
            this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
        });


        //Initialse DataTables, with no sorting on the 'details' column
        var data = this.tableData.data === undefined ? [] : this.tableData.data;
        var head = this.tableData.headers === undefined ? [] : this.tableData.headers;
        var cloumns = [{ "title": "" }];
        var dataset = [];
        for (var i = 0; i < head.length; i++) {
            var obj = { "title": head[i] };
            cloumns.push(obj)
        }
        var innerData = {};
        for (var i = 0; i < data.length; i++) {
            var dataObj: any = data[i];
            innerData[i] = dataObj.innerData;
            dataset.push(dataObj.data)
        }
        var oTable = $('#'+this.randomId+'').dataTable({
            data: dataset,
            columns: cloumns,
            "bJQueryUI": true,
            // "aaData": newRowData,
            // "bPaginate": false,
            "destroy": true,
            
            "oLanguage": {
                "sInfo": "_TOTAL_ entries"
            },
            // "aaSorting": [[1, 'asc']]
        });

        
        $('#'+this.randomId+' tbody td img').live('click', function () {
            var nTr = $(this).parents('tr')[0];
            var nTds = this;

            if (oTable.fnIsOpen(nTr)) {
                /* This row is already open - close it */
                this.src = "http://i.imgur.com/SD7Dz.png";
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

                //  var detailsRowData = newRowData[rowIndex].details;
                this.src = "http://i.imgur.com/d4ICC.png";
                
                oTable.fnOpen(nTr, fnFormatDetails(iTableCounter, detailsTableHtml), 'details');
                oInnerTable = $("#exampleTable_" + iTableCounter).dataTable({
                    data: data,
                    columns: cloumns,
                    "bJQueryUI": true,
                    "bFilter": false,
                    //     "aaData": detailsRowData,
                    "bSort": true, // disables sorting
                    
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

        this.otable = oTable;

    }
}
