<div style="text-align: justify">
<h1 align="center">Final project of the 4 module JavaRush.</h1>
<h1 align="center">Country</h1>

#### Vladimir Krivko.

#### GitHub: *https://github.com/VladimirKrivko/ru.javarush.krivko.country* &emsp;&emsp;&emsp;*(branch dev)*

#### Mentors: @Andrii Shylin (UA, Kyiv), @Yuriy Syrovatko.

---

## Task:
&emsp; There is a relational MySQL database with a schema (country, city, countryLanguage). And there is a frequent request from the city, which slows down.
The solution is to move all the data that is requested frequently to Redis (in memory storage of the key–value type).

### Instructions for launching:
- to run mysql in docker, the command was used: _docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart unless-stopped -v mysql:/var/lib/mysql mysql:8_
- to run redis in docker, the command was used: _docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest_
- Version: Java 18.
- launching the program: _src/main/java/ru/javarush/country/SpeedTestConsoleApp.java_
- the number of test cycles to compare the sampling frequency of data from databases is set in the NUMBER_OF_TEST_ITERATIONS field SpeedTestConsoleApp.java. (by default, it costs 30 cycles) 

---

---

<h3 align="center"> &#128511; Thanks for your attention! &#128511; </h3>

</div>