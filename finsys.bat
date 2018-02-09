set projectpath=H:\MavenProject\Finsys
echo %projectpath%
set classpath=%projectpath%\bin;%projectpath%\lib\lib\*;%projectpath%\lib\selenium-2.53.0\*;%projectpath%\lib\*
echo %classpath%
java org.testng.TestNG -d test-outputs %projectpath%\testng.xml
echo %projectpath%
pause