<h2>StockAccountingService</h2> </br>
<p>Веб приложение на Spring Boot, предназначенное для учета товара на складе.</p>
<h3>Приложение должно содержать список товаров и форму товара. Товар имеет поля, представленные в таблице:</h3>
<table>
    <tr>
        <td>Поле</td>
        <td>Тип</td>
        <td>Описание</td>
    </tr>
    <tr>
        <td>id</td>
        <td>long</td>
        <td>Уникальный идентификатор.<div>Заполняется автоматически. На форме доступен только для чтения.</td>
    </tr>
    <tr>
        <td>name</td>
        <td>string (512 символов)</td>
        <td>Название товара. На форме представлено в виде однострочного поля.</td>
    </tr>
    <tr>
        <td>description</td>
        <td>string (1024 символа)</td>
        <td>Описание товара. На форме представлено в виде многострочного поля.</td>
    </tr>
    <tr>
        <td>create_date</td>
        <td>date</td>
        <td>Дата добавления товара в базу. На форме выбор из календаря или текстовое поле с маской.</td>
    </tr>
    <tr>
        <td>place_storage</td>
        <td>integer</td>
        <td>Номер ячейки хранения. На форме представлено в виде текстового однострочного поля с проверкой, что введено число.</td>
    </tr>
    <tr>
        <td>reserved</td>
        <td>boolean</td>
        <td>Флаг резервирования товара.</td>
    </tr>
</table>
<h4><strong>Требования предъвляемые к созданию приложения:</strong></h4>
<p>1. Приложение должно работать под управлением сервера приложений <strong><span style="text-decoration: underline;">Apache Tomcat</span></strong>. Данные необходимо хранить в базе данных под управленем СУБД <strong><span style="text-decoration: underline;">HSQLDB</span></strong>. Обращение к базе данных осуществлять посредством <strong><span style="text-decoration: underline;">spring-jdbc</span></strong>.<br />2. При обращении на первую страницу приложения должна отображаться страница со списком товаров, с возможностью создания, редактирования и удаления каждой позиции в списке. При создании и рекдактировании товара должна открываться форма товара с возможностью сохранения изменений и отмены сохранения.<br />3. Должен быть предусмотрен <strong><span style="text-decoration: underline;">AJAX</span></strong> подход: обновление списка, отрисовка формы товара должно происходить без полной перерисовки страницы.<br />
    4. Страница должна общаться к web-сервису, используя <strong><span style="text-decoration: underline;">REST</span></strong> протокол.</br></p>

 Index.html доступен по url http://localhost:63342/StockAccountingService/StockAccountingService.main/static/index.html </br>
 Для взаимодействия с сервером Tomcat клиент должен обращаться к нему с помощью следующих запросов. 
http://localhost:8080/product/save – GET </br>
http://localhost:8080/product/remove/{id} - DELETE </br>
, где id – идентификатор удаляемого товара </br>
http://localhost:8080/product/all - GET </br>
http://localhost:8080/product/find/{id} – GET </br>
, где id – идентификатор искомого товара;</br>
http://localhost:8080/product/update/{id} - PUT </br>
где id – идентификатор изменяемого товара товара </br>
