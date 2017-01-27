import { Component } from '@angular/core';

@Component({
    selector: "spinner-component",
    template: `<div style="display: flex; justify-content: center;">
<img class="refreshGlyphImg" src="../../../app/resources/spinner.gif" />
</div>`,
    //styleUrls: ["../../resources/css/spinner.css"]
})

export class SpinnerComponent { }