# HandlerFramework

1. Download tomcat web server from https://downloads.apache.org/tomcat/tomcat-9/v9.0.37/bin/apache-tomcat-9.0.37-windows-x64.zip (for windows).
2. Unzip it (for simplicity rename it as tomcat and place it at root of C drive)
3. Edit catalina batch file (in bin folder) add -
   set JAVA_HOME=<path to jdk>(form me it was set JAVA_HOME=C:\jdk1.8 ) in next line to @echo off.
4. Place HandlerFrameWork in webapps folder.
5. Move in <tomcat_root>\bin and run startup.bat .
6. In postman set request body as - 
 {
  "movieArr":[
  {
	  "movieName": "Movie1",
	  "startDate": "yyyy/mm/dd",
	  "endDate": "yyyy/mm/dd"
  },
  {
	  "movieName": "Movie2",
	  "startDate": "yyyy/mm/dd",
	  "endDate": "yyyy/mm/dd"
  },
  {
	  "movieName": "Movie3",
	  "startDate": "yyyy/mm/dd",
	  "endDate": "yyyy/mm/dd"
  }
  ]
}
  Put movie name, start date and end date in the above given format(you can add/remove json objects)
6. To send get request for localhost:8080/HandlerFramework/service/controller/moviesForMaxProfit (replace 8080 with the port number your tomcat server is hosted on)
7. In response we will get the optimized json of suggested movies and earnings in the below json format.
  
  {
    "moviesSuggested": [
        {
            "movieName": "A",
            "startDate": "2020/1/1",
            "endDate": "2020/1/30"
        },
        {
            "movieName": "C",
            "startDate": "2020/2/5",
            "endDate": "2020/2/15"
        },
        {
            "movieName": "D",
            "startDate": "2020/2/16",
            "endDate": "2020/2/26"
        }
    ],
    "earning": "Rs. X cr."
}
