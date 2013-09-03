package net.suobig.effectivejava.ch4_ClassesAndInterfaces;

/*  Инкапсуляция - ограничение доступа к деталям реализации объектов - является
 важным элементом программирования. 
 
    Одно из основных достоинств инкапсуляции состоит в возможности отделить
API от реализации, тем самым, оставляя первое неизменным, свободно менять
второе, не влияя остальную часть системы. Это также позволяет вести параллельную
разработку модулей, ускоряя тем самым процесс разработки. Кроме того, 
изолированные друг от друга модули можно также изолированно тестировать на баги.
Не смотря на то, что сама по себе инкапсуляция скорее ухудшает 
производительность, она ускоряет процесс повышения производительности: можно 
выявлять и оптимизировать систему отдельными модулями.

    Кроме того, при написании изолированных модулей, они могут использоваться
многократно в разных сисетмах без малейших изменений.

    И, наконец, изолированные модули могут быть полезны при создании крупных 
систем - даже если система в целом будет признана неудачной, отдельные ее части
могут получить самостоятельную жизнь.

    Java обладает множеством способов сокрытия информации. Основное правило: 
делайте информацию настолько скрытой, насколько это возможно. 

    Для классов и интерфейсов верхнего уровня (не вложенных) есть всего два 
уровня доступа: публичный и пакетный. Если есть возможность оставить у класса
пакетный уровень доступа - это нужно сделать. В таком случае это будет часть
реализации, иначе - часть API. Отсюда очевидная выгода - пользователи
рассчитывают на то, что API останется неизменным, при этом о деталях реализации
они ничего не знают и вы в праве менять их как вам заблагорасудится. */