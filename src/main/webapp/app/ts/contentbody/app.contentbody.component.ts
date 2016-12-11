import {Component, OnInit} from '@angular/core';
import {MyFcaService} from '../../app.component.service';
const Highcharts = require ('highcharts');

const Highcharts3d = require('highcharts/highcharts-3d.src');
Highcharts3d(Highcharts)
//require('/../../app/ts/contentbody/exporting.js')(Highcharts);
//require('/../../app/ts/contentbody/drilldown.js')(Highcharts);
Highcharts.setOptions({
    colors: ['#058DC7', '#50B432', '#ED561B']
});

var loc2=window.location.pathname;
var nloc2 = loc2.slice(0,-10);

@Component({
    moduleId:module.id,
    selector:"app-content",
  // npm url 
//  templateUrl:"/app/ts/contentbody/newContent.html"
  templateUrl:  "./newDesign.html"
  // tomcat url  templateUrl:"/MyFcaWebApp/MyFcaDashboard/app/ts/contentbody/newContent.html"
})
export class ContentSection implements OnInit{
    loc=window.location.pathname;
	
	//loc = "/MyFcaWebApp/MyFcaDashboard/";
	
	//console.log(nloc);
    private tilesArray:any;
    private colOneArray:any=new Array();
    private colTwoArray:any=new Array();   

    ngOnInit() {
        // this.getContentData();
         //this.getHighchartData();
         // this.getHighchartDataThroughJson();
         //   this.getTileDataThroughService();
         // this.getUserProfileTestData();
         this.getTileDataThroughLocalJsonService();
           }
    constructor(private service:MyFcaService){
        console.log("zero "+ this.tilesArray);
        console.log("one "+ this.colOneArray);
        console.log("two "+ this.colTwoArray);
       
       
     }        
    
//    getContentData() {
//         this.service.getContentTileData()
//             .subscribe(
//                 (resUserData) => {this.tilesArray = resUserData 
//                     var colOne=0,colTwo = 0;

//                 for(var i=0;i<this.tilesArray.length;i++){
//                     if(i%2==0){
//                         this.colOneArray[colOne] = this.tilesArray[i];
//                         colOne++;
//                     }else{
//                         this.colTwoArray[colTwo] = this.tilesArray[i];
//                         colTwo++;
//                     }
//                 }   
//             }
//         ) 
//     }  
  
    // getTileDataThroughService() {
    //      this.service.getTileDataThroughService()
    //          .subscribe(
    //              (resUserData) => {this.tilesArray = resUserData 
    //                  var colOne=0,colTwo = 0;

    //              for(var i=0;i<this.tilesArray.length;i++){
    //                  if(i%2==0){
    //                      this.colOneArray[colOne] = this.tilesArray[i];
    //                      colOne++;
    //                  }else{
    //                      this.colTwoArray[colTwo] = this.tilesArray[i];
    //                      colTwo++;
    //                  }
    //              }   
    //          }
    //      ) 
    //  }


    getTileDataThroughLocalJsonService() {
        this.service.getTileDataThroughLocalJsonService()
            .subscribe(
                (resUserData) => {this.tilesArray = resUserData 
                    var colOne=0,colTwo = 0;

                for(var i=0;i<this.tilesArray.length;i++){
                    if(i%2==0){
                        this.colOneArray[colOne] = this.tilesArray[i];
                        colOne++;
                    }else{
                        this.colTwoArray[colTwo] = this.tilesArray[i];
                        colTwo++;
                    }
                }   
            }
        ) 
    } 

//getHighchartDataThroughJson(){
//    this.service.getHighchartDataThroughJson()
//            .subscribe(
//                (resUserData) => {this.tilesArray = resUserData 
//                   Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, (color:any)=> {
//                                                return {
//                                                    radialGradient: {
//                                                        cx: 0.5,
//                                                        cy: 0.3,
//                                                        r: 0.7
//                                                    },
//                                                    stops: [
//                                                        [0, color],
//                                                        [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
//                                                    ]
//                                                };
//                                            });
//                    var colOne=0,colTwo = 0;
//
//                for(var i=0;i<this.tilesArray.length;i++){
//                    if(i%2==0){
//                        this.colOneArray[colOne] = this.tilesArray[i];
//                        colOne++;
//                    }else{
//                        this.colTwoArray[colTwo] = this.tilesArray[i];
//                        colTwo++;
//                    }
//                }   
//            }
//        ) 
//    
//
//}
//}
//     getHighchartData(){
//         Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, (color:any)=> {
//                                                 return {
//                                                     radialGradient: {
//                                                         cx: 0.5,
//                                                         cy: 0.3,
//                                                         r: 0.7
//                                                     },
//                                                     stops: [
//                                                         [0, color],
//                                                         [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
//                                                     ]
//                                                 };
//                                             });
// this.tilesArray = this.service.getHighChartData();
// var colOne=0,colTwo = 0;

// for(var i=0;i<this.tilesArray.length;i++){
//     if(i%2==0)
//     {
//         this.colOneArray[colOne] = this.tilesArray[i];
//         colOne++;
//     }else{
//         this.colTwoArray[colTwo] = this.tilesArray[i];
//         colTwo++;
//     }
// }

//     } 
}
