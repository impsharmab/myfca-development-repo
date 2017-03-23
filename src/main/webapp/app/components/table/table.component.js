"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var TableComponent = (function () {
    function TableComponent(element, renderer) {
        this.element = element;
        this.renderer = renderer;
        this.randomId = new Date().valueOf();
        var elementHtml = element.nativeElement;
        elementHtml.innerHTML = "<table id=\"" + this.randomId + "\"></table>\n                <div style=\"display:none\">\n                    <table id=\"detailsTable\">\n                    </table>\n                </div>";
    }
    TableComponent.prototype.ngOnDestroy = function () {
    };
    TableComponent.prototype.ngOnChanges = function () {
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
        $('#' + this.randomId + ' thead tr').each(function () {
            this.insertBefore(nCloneTh, this.childNodes[0]);
        });
        $('#' + this.randomId + ' tbody tr').each(function () {
            this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
        });
        //Initialse DataTables, with no sorting on the 'details' column
        var data = this.tableData.data === undefined ? [] : this.tableData.data;
        var head = this.tableData.headers === undefined ? [] : this.tableData.headers;
        var cloumns = [{ "title": "" }];
        var dataset = [];
        for (var i = 0; i < head.length; i++) {
            var obj = { "title": head[i] };
            cloumns.push(obj);
        }
        var innerData = {};
        for (var i = 0; i < data.length; i++) {
            var dataObj = data[i];
            innerData[i] = dataObj.innerData;
            dataset.push(dataObj.data);
        }
        var oTable = $('#' + this.randomId + '').dataTable({
            data: dataset,
            columns: cloumns,
            "bJQueryUI": true,
            // "aaData": newRowData,
            // "bPaginate": false,
            "destroy": true,
            "oLanguage": {
                "sInfo": "_TOTAL_ entries"
            },
        });
        $('#' + this.randomId + ' tbody td img').live('click', function () {
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
                    cloumns.push(obj);
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
                    "bSort": true,
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
    };
    return TableComponent;
}());
__decorate([
    core_1.Input("tableData"),
    __metadata("design:type", Object)
], TableComponent.prototype, "tableData", void 0);
TableComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'datatable',
        template: ""
    }),
    __metadata("design:paramtypes", [core_1.ElementRef, core_1.Renderer])
], TableComponent);
exports.TableComponent = TableComponent;
//# sourceMappingURL=table.component.js.map