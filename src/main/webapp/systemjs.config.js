/**
 * System configuration for Angular samples
 * Adjust as necessary for your application needs.
 */
(function (global) {
  // var loc=window.location.pathname;
  // console.log(loc);
  //loc = "/MyFcaWebApp/MyFcaDashboard/index.html";
  //var nloc = loc.slice(0,-10);
  //console.log(nloc);
  //nloc=MyFcaWebApp/MyFcaDashboard
  System.config({
    paths: {
      // paths serve as alias
      // npm url     
      'npm:': 'node_modules/'
      //tomcat url   'npm:': 'imiservices/node_modules/'
    },
    // map tells the System loader where to look for things
    map: {
      // our app is within the app folder
      //npm url    
      app: 'app',
      // tomacat url      app: 'imiservices/app',
      // angular bundles
      '@angular/core': 'npm:@angular/core/bundles/core.umd.js',
      '@angular/common': 'npm:@angular/common/bundles/common.umd.js',
      '@angular/compiler': 'npm:@angular/compiler/bundles/compiler.umd.js',
      '@angular/platform-browser': 'npm:@angular/platform-browser/bundles/platform-browser.umd.js',
      '@angular/platform-browser-dynamic': 'npm:@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js',
      '@angular/http': 'npm:@angular/http/bundles/http.umd.js',
      '@angular/router': 'npm:@angular/router/bundles/router.umd.js',
      '@angular/router/upgrade': 'npm:@angular/router/bundles/router-upgrade.umd.js',
      '@angular/forms': 'npm:@angular/forms/bundles/forms.umd.js',
      '@angular/upgrade': 'npm:@angular/upgrade/bundles/upgrade.umd.js',
      '@angular/upgrade/static': 'npm:@angular/upgrade/bundles/upgrade-static.umd.js',
      // other libraries
      'angular2-highcharts': 'npm:angular2-highcharts',
      'angular-datatables':'npm:angular-datatables',
      'highcharts': 'npm:highcharts',
      'rxjs': 'npm:rxjs',
      'angular-in-memory-web-api': 'npm:angular-in-memory-web-api/bundles/in-memory-web-api.umd.js',
      '@ng-bootstrap/ng-bootstrap': 'node_modules/@ng-bootstrap/ng-bootstrap/bundles/ng-bootstrap.js',
    },
    // packages tells the System loader how to load when no filename and/or no extension
    packages: {
      app: {
      	main: './main.js',
        defaultExtension: 'js',
           "styles": [
        "../node_modules/datatables.net-dt/css/jquery.dataTables.css"
      ],
      "scripts": [
        "../node_modules/jquery/dist/jquery.js",
        "../node_modules/datatables.net/js/jquery.dataTables.js"
      ]
      },
      rxjs: {
        defaultExtension: 'js'
      },
      'angular2-highcharts': {
        main: './index.js',
        defaultExtension: 'js'
      },
      'highcharts': {
        main: './highstock.src.js', //paid? lisence

         defaultExtension: 'js'
      }
    }
  });
})(this);