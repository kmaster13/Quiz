# Quiz
Проект написан с использованием Spring Boot. В качестве БД используется Spring H2. Для соединения с бд используется тонкая обёртка JDBC API - JDBCTemplate.

Frontend первоначально делался на Vue.js, но в процессе был заменен шаблонизатором Thymeleaf, засчёт чего нет необходимости докачивать Node.js и Yarn, как и с H2 DB.

В БД находятся 4 таблицы. 

Question - таблица с двумя колонками id,title , где title-название вопроса.

Answer - таблица с колонками id, question_id,name,flag, где flag-правильность вопроса. Отношение с Question ManyToOne.

Person -  таблица с двумя колонками id,email.

PersonAnswer - связывающая таблица отношения ManyToMany.

После установки к себе на компьютер, необходимо указать в application.properties путь до файлов БД:

![image](https://user-images.githubusercontent.com/81090666/224555110-7cf6a227-6ba2-4558-9b65-f90d50e8f726.png)

Скрипты создания базы данных:

![image](https://user-images.githubusercontent.com/81090666/225129757-92a5c9a1-eedb-4bbd-9c03-8a87fa8a185b.png)

Диаграмма базы данных:

![image](https://user-images.githubusercontent.com/81090666/225134496-bb66d58e-33ea-408b-844a-646f0d23c5ba.png)

Планируется добавить Spring Security, JDBCTemplate заменить на Hibernate. Также необходимо доработать некоторые методы и добавить тестирование. 
