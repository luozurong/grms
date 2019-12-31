call dd E:\workspaces-mpp\mmp\src\main\webapp\WEB-INF\classes
call dd E:\workspaces-mpp\mmp\src\main\webapp\WEB-INF\lib\
call md E:\workspaces-mpp\mmp\src\main\webapp\WEB-INF\classes 
cd E:\workspaces-mpp\mmp
call mvn clean compile war:inplace
