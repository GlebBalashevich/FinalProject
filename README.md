
## Сервис по прокату автомобилей "CarBook"<a name="русский"></a>
---
### Учебный проект по курсу Java Web Development
### Автор: Балашевич Глеб
#### [Go to EN](#english)
---
### Оглавление<a name="оглавление"></a> 
* [Общее описание](#общее_описание)
* [Пользователи](#пользователи)
* [Автомобили](#автомобили)
* [Заказы](#заказы)
### Общее описание<a name="общее_описание"></a> 
  Веб приложение предоставляет возможность осуществлять онлайн бронирование автомобилей для целей аренды. 
  Клиент, с помощью каталога, может подобрать подходящий для себя вариант по параметрам и цене, и забронировать авто на определенную дату. 
  После подтверждения заказа администратором, клиенту необходимо заказ оплатить. 
  Произведя оплату заказа, клиент может воспользоваться услугами сервиса без соблюдения каких-либо дополнительных формальностей.
  
[⬆️Оглавление](#оглавление)
____
### Пользователи<a name="пользователи"></a> 
  Для разграничения уровней доступа и возможностей пользователей онлайн сервисом в приложении были введены роли: 
  * **Гость**  
    Неавторизированный пользователь
  
    Функциональные возможности:
      * Просмотр домашней страницы
      * Просмотр доступных к заказу автомобилей
      * Смена языка сайта
      * Авторизация/Регистрация
  * **Клиент**  
    У клиента в свою очередь определены три статуса: **Ожидающий активации, Активный, Заблокированный**.  
    Гостю, прошедшему процедуру регистрации присваивается роль **Клиент** и статус **Ожидающий активации**. На электронную почту клиента, указанную при регистрации, отправляется
    письмо со ссылкой перейдя по которой статус клиента меняется на **Активный**. В случае нарушения клиентом правил пользования сервисом администратор может его заблокировать,
    статус с **Активный** сменится на **Заблокированный**.  
    
    Функциональные возможности:
      * **Ожидающий активации**  
        * Просмотр домашней страницы
        * Просмотр доступных к заказу автомобилей
        * Смена языка сайта
      * **Активный** 
        * Просмотр домашней страницы
        * Просмотр доступных к заказу автомобилей
        * Смена языка сайта
        * Заказ автомобиля
        * Оплата заказа
        * Просмотр всех своих заказов
      * **Заблокированный** 
        * Просмотр домашней страницы
        * Смена языка сайта 
  * **Администратор**  
    Управляет работой сервиса. В его обязанности входит добавление новых автомобилей в каталог, изменение их параметров,
    управление заказами клиентов, управление статусом клиентов.
    
    Функциональные возможности:
    * Просмотр домашней страницы
    * Смена языка сайта
    * Просмотр заказов клиентов
    * Подтверждение/отмена заказа
    * Просмотр каталога автомобилей
    * Изменение стоимости аренды, статуса автомобиля (является ли он доступным для заказа)
    * Добавление нового автомобиля в каталог
    * Просмотр зарегистрированных клиентов
    * Изменение статуса клиента Активный/Заблокированный
    
[⬆️Оглавление](#оглавление)
____
### Автомобили<a name="автомобили"></a>   
  Являются предметной областью онлайн сервиса. Обладают различными параметрами, такими как название модели, тип, количество мест, тип двигателя, расход топлива.
  Так же каждому автомобилю присвоена стоимость его аренды в сутки и флаг - доступен он для заказа клиентами или нет.  
  
[⬆️Оглавление](#оглавление)
___
### Заказы<a name="заказы"></a>   
  Результатом бронирования автомобиля клиентом на определенный интервал дат является заказ. 
  Заказ содержит информацию об автомобиле и клиенте, заказавшем его, интервале дат в течении которых клиент будет пользоваться автомобилем, общей сумме аренды.  
  
  Заказу присваивается один из четырех статусов:
  * **Ожидающий**  
  Присваивается новому заказу после его оформления клиентом
  * **Ожидающий оплаты**  
  Присваивается заказу после его подтверждения администратором. В случае отмены заказа, заказ удаляется.
  * **Активный**  
  Присваивается заказу после осуществления оплаты клиентом подтвержденного администратором заказа
  * **Выполненный**
  По окончанию срока аренды статус заказа с Активный меняется на Выполненный
  
  В случаях, когда:
  * Заказ не был подтвержден администратором до срока наступления аренды 
  * Заказ был подтвержден администратором, но не был оплачен
  * Заказ был отменен администратором 
  
  такой заказ считается недействительным и удаляется, при этом клиенту направляется письмо-уведомление об отмене заказа, с указанием причины.  
  
[⬆️Оглавление](#оглавление)
____

## Car rental service "CarBook"<a name="english"></a>
---
### Study project for the course Java Web Development
### Author: Balashevich Gleb
#### [Перейти на русский](#русский)
---
### Table of contents<a name="contents"></a> 
* [General description](#description)
* [Users](#users)
* [Cars](#cars)
* [Orders](#orders)
### General description<a name="description"></a> 
  The web application provides the ability to make online car booking for rental purposes.
  The client, using the catalog, can choose a suitable option for himself in terms of parameters and price, and book a car for a specific date.
  After the order is confirmed by the administrator, the client needs to pay for the order.
  Having made the payment for the order, the client can use the services of the service without any additional formalities.
  
[⬆️Table of contents](#contents)
____
### Users<a name="users"></a> 
  To differentiate the access levels and capabilities of users of the online service, roles were introduced in the app:
  * **Guest**  
    Unauthorized user
  
    Functionality:
      * Home page view
      * View available cars for order
      * Changing the site language
      * Login / Register
  * **Client**  
    The client, in turn, has three statuses: ** Pending, Active, Blocked **. 
    The guest who has passed the registration procedure is assigned the role ** Client ** and the status ** Pending**. The client's email address specified during registration is sent
     a letter with a link by clicking on which the client's status changes to ** Active **. If the client violates the rules for using the service, the administrator can block it,
     status from ** Active ** will change to ** Blocked **.
    
    Functionality:
      * **Pending**  
        * Home page view
        * View available cars for order
        * Changing the site language
      * **Active** 
        * Home page view
        * View available cars for order
        * Changing the site language
        * Car order
        * Order payment
        * View all your orders
      * **Blocked** 
        * Home page view
        * Changing the site language 
  * **Admin**  
    Manages the online service. His responsibilities include adding new cars to the catalog, changing their parameters,
     client order management, client status management.
    
    Functionality:
    * Home page view
    * Changing the site language
    * View clients orders
    * Order confirmation/cancellation
    * View car catalog
    * Change in the rental price, car status (whether it is available for ordering)
    * Adding a new car to the catalog
    * Viewing registered clients
    * Client status change Active / Blocked
    
[⬆️Table of contents](#contents)
____
### Cars<a name="cars"></a>   
  Are the subject area of an online service. They have various parameters such as model name, type, number of seats, engine type, fuel consumption.
   Also, each car is assigned a rental price per day and a flag - whether it is available for ordering by clients or not.
  
[⬆️Table of contents](#contents)
___
### Orders<a name="orders"></a>   
  The result of the client booking a car for a certain date interval is an order.
   The order contains information about the car and the client who ordered it, the range of dates during which the client will use the car, the total rental amount.  
  
  An order is assigned one of four statuses:
  * **Pending**  
  Assigned to a new order after the client has placed it
  * **Awaiting Payment**  
  Assigned to an order after its confirmation by the administrator. If the order is canceled, the order is deleted.
  * **Active**  
  Assigned to an order after the client has made a payment for the order confirmed by the administrator
  * **Completed**
  At the end of the lease term, the status of the order from Active changes to Completed
  
  In cases when:
  * The order was not confirmed by the administrator before the rental period
  * The order was confirmed by the administrator but was not paid
  * The order was canceled by the administrator
  
  such an order is considered invalid and deleted, and the customer is sent a notification letter about the cancellation of the order, indicating the reason.  
  
[⬆️Table of contents](#contents)
____
