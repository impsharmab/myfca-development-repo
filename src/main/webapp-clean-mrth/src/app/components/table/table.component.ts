import { Component, OnInit, EventEmitter, Output, ViewChild, ElementRef, Renderer, OnChanges, Input, OnDestroy } from '@angular/core';

declare var $: any;
@Component({
    moduleId: module.id,
    selector: 'datatable',
    template: ``
})

export class TableComponent implements OnChanges, OnDestroy {
    private otable: any;
    private randomId: any = new Date().valueOf();
    private tableObj: any;
    @Input("tableData") tableData: any;
    @Input("columnsHeader") columns: any;
    @Output("deleteClick") deleteClick: EventEmitter<any> = new EventEmitter<any>();
    @Input("columnDefs") columnDefs: any;
    constructor(private element: ElementRef, private renderer: Renderer) {
        var elementHtml = element.nativeElement;
        elementHtml.innerHTML = `<table id="` + this.randomId + `"></table>`
    }
    ngOnDestroy() {

    }
    ngOnChanges(): void {

        var data = this.tableData;
        var columns = this.columns;
        var columnDefs = this.columnDefs;
        if ($.fn.dataTable.isDataTable('#' + this.randomId)) {
            this.tableObj.clear().draw();
            this.tableObj.rows.add(data); // Add new data
            this.tableObj.columns.adjust().draw(); // Redraw the DataTable
            return;
            //  $('#'+this.randomId).dataTable().fnDestroy()
        }
        var table = $('#' + this.randomId).DataTable({
            // 'bSort': false,
            "data": data,
            "columns": columns,
            "columnDefs":columnDefs
        });
        this.tableObj = table;
        
        var __this = this;
        $('#' + this.randomId + ' tbody').on('click', 'td.details-control', function () {
            var tr = $(this).closest('tr');
            var row = table.row(tr);
            __this.deleteClick.emit(row.data());
            //
            // alert(JSON.stringify(row.data()));
        });
        // this.element.nativeElement.querySelector('button').addEventListener();
    }
}
