import { ElementRef, KeyValueDiffers } from '@angular/core';
import { Ng2HighchartsBase } from "./ng2-highcharts-base";
export declare class Ng2Highcharts extends Ng2HighchartsBase {
    options: Object;
    constructor(ele: ElementRef, _differs: KeyValueDiffers);
    draw(opt: any): void;
}
