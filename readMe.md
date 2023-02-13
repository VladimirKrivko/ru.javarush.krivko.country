<div style="text-align: justify">
<h1 align="center">Итоговый проект четвертого модуля JavaRush.</h1>
<h1 align="center">Country</h1>

#### Владимир Кривко.

#### GitHub: *https://github.com/VladimirKrivko/ru.javarush.krivko.country* &emsp;&emsp;&emsp;*(ветка dev)*

#### Менторы: @Andrii Shylin (UA, Kyiv), @Yuriy Syrovatko.

---

## Задача:
&emsp; Есть реляционная БД MySQL с схемой (страна-город, язык по стране). И есть частый запрос города, который тормозит.
Решение – вынести все данные, которые запрашиваются часто, в Redis (in memory storage типа ключ-значение).

### Инструкция по запуску:
- для запуска mysql в докере использовалась команда: _docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart unless-stopped -v mysql:/var/lib/mysql mysql:8_
- для запуска redis в докере использовалась команда: _docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest_
- Версия: Java 18.
- Запуск программы: _src/main/java/ru/javarush/country/Main.java_
- Количество циклов вычислений средней скорости доступа к данным в разных бд можно изменить в Main.java поле NUMBER_OF_TEST_ITERATIONS. По умолчанию стоит 30 циклов. 

---

---

<h3 align="center"> &#128511; Спасибо за внимание! &#128511; </h3>

</div>