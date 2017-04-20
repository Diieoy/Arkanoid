# Арканоид

* Основные фичи:
	* Из сбитых фигур могут падать различные бонусы.
	* После прохождения уровня будет показана картинка.
	* Уровней будет несколько.
	* Будет "доска почёта" которая разместится на сайте игры;
	   в ней будет отражена статистика:
		имя игрока;	общее кол-во очков;	общее время прохождения;
	* В начале игры можно выбрать 1 из 3 уровней сложности (скорость передвижения шарика).
	* Управление на стрелках клавиатуры, либо мышью.

* Описание действий:

		  Нажал любую стрелку либо ЛКМ - шарик полетел, раунд начался.
		  Сбивать фигуры будет шарик, который отбивается от всего, во что попадает, кроме низа игрового поля.
		  Уронил шарик - минус жизнь. 
		  Потерял все жизни - игра закончена.
***
  1. Описание возможных фигур (src/main/resources/Фигуры.jpg).
  2. Описание БД (src/main/resources/Схема БД.jpg).
  3. Сайт для игры (src/main/resources/site).
  
***
Запросы:
	1. Топ по очкам.
	2. Топ по наиграному времени.
