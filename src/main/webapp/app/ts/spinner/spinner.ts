import {Component} from '@angular/core';

@Component({
    selector:"spinner-component",
    template:`<div style="display: flex; justify-content: center;">
            <img class ="refreshGlyphImg" src="../../../app/resources/spinner.gif" />
</div>`,

styles:[` 
            .refreshGlyphImg{
                width: 200px; 
                height:200px;
                }`
                
        ]
    
        // templateUrl:"./spinner.html",
    // styleUrls:["spinner.css"]
})

export class SpinnerComponent{}