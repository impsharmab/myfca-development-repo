# myfca-development-repo

#This application is build by using angular-2 framework technology in front-end and spring-boot framework to services.

#webapp-precompiled folder contains all the raw- uncompiled code for angular 2. using "ng build --prod --aot" command, angular2-codes were compiled. the compiled code can be found inside the dist folder. all the content of the dist folder are copied and replaced inside the webapp folder, leaving (datatable.jsp, favicon.ico, healthcheck.html and loginerror.html). 

#Once we have all angular compiled code inside webapp, we do our app build using maven(mvn clean install).

#The war file results after maven build is used for the deployment.
