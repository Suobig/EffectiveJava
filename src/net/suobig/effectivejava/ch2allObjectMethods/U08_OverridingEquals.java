/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.suobig.effectivejava.ch2allObjectMethods;

import java.awt.Color;


public class U08_OverridingEquals {
/*  Неправильное переопределение метода equals может привести к серьезным 
 проблемам. Самое простое - не переопределять этот метод вообще. Это подходит
 в следующих случаях:
    1) каждый экземпляр класса уникален и "==" эквивалентно equals
    2) нет смысла проверять равенство экземпляров класса 
    3) родитель уже переопределил equals и его поведение подходит для потомка
    4) класс является private или package private и есть уверенность, что его
метод equals никогда не будет вызван. В таких случях возможно стоит 
переопределить метод с возвращением new AssertionError()
    Для метода equals существует общее соглашение (general contract) о том, 
что любая его реализация должна обладать следующими свойствами:
    1) рефлективность. Для любого ненулевого x x.equals(x) = true 
    2) симметричность. Для любых ненулевых x и y x.equals(y) = true тогда и 
только тогда, когда y.equals(x) = true
    3) транзитивность. Для любых ненулевых x, y, z, если  x.equals(y) = true
y.equals(z) = true, то x.equals(z) = true
    4) постоянство. Для любых ненулевых x, y повторные вызовы equals() должны
возвращать одинаковый результат, если никакй из используемых сравнении элементов
этих объектов не был изменен
    5) для любого ненулевого x: x.equals(null) = false
    
    Нарушение этого соглашения может привести к ошибкам, падениям программы и
прочим малоприятным последствиям, источник которых будет крайне непросто 
обнаружить

    Пример нарушения симметричности:*/
}
class CaseInsensitiveString {  
    String s;
    
    public CaseInsensitiveString(String s) {
        if (s == null) 
            throw new NullPointerException();
        this.s = s;
    }
    
    @Override
    //Нарушает симметричность!
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString) 
            return s.equalsIgnoreCase(
                    ((CaseInsensitiveString)o).s);
        if (o instanceof String) //работает только в одну сторону!
            return s.equalsIgnoreCase((String)o); //Нарушение симметричности!
        return false;
    }
    
    public static void run() {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";
        System.out.println(s.equals(cis)); //false
        System.out.println(cis.equals(s)); //true
    }
/*  В данной ситуации проблема заключается в том, что невозможно предсказать,
 как в той или иной ситуации поведут себя другие объекты, использующие сравнение
 с данным.*/
}
/*  Решение заключается в том, чтобы убрать в equals попытку сравнить 
 несравнимое:*/
class CorrectCaseInsensitiveString {  
    String s;
    
    public CorrectCaseInsensitiveString(String s) {
        if (s == null) 
            throw new NullPointerException();
        this.s = s;
    }
    
    @Override
    public boolean equals(Object o) {
        return (o instanceof CorrectCaseInsensitiveString) &&
                s.equalsIgnoreCase(
                    ((CorrectCaseInsensitiveString)o).s);
    }
}
/*  Пример нарушения транзитивности:*/

class Point {
    final int x;
    final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;
        Point p = (Point)o;
        return p.x == x && p.y == y;
    }
    //Остальное пропущено
}

class ColorPointNonSymmetry extends Point {
    private final Color color;
    
    public ColorPointNonSymmetry(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
    
    //Нарушает требование симметричности!
    @Override 
    public boolean equals(Object o) {
        if (!(o instanceof ColorPointNonSymmetry)) {
            return false;
        }
        return super.equals(o) && ((ColorPointNonSymmetry) o).color == color;
    }
    
    public static void run(String args[]) {
        Point p = new Point(1, 2);
        ColorPointNonSymmetry cp = new ColorPointNonSymmetry(
                1, 2, Color.yellow);
        System.out.println(p.equals(cp)); //true
        System.out.println(cp.equals(p)); //false
    }
}

class ColorPointNonTransitive extends Point {
    private final Color color;
    
    public ColorPointNonTransitive(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
    
    //Нарушает требование транзитивности!
    @Override 
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        
        //Сравниваем без учета цвета, если это обычная точка 
        if (!(o instanceof ColorPointNonTransitive)) {
            return o.equals(this);
        }
        
        return super.equals(o) && ((ColorPointNonTransitive) o).color == color;
    }
    
    
    public static void main(String args[]) {
        Point p = new Point(1, 2);
        ColorPointNonTransitive cp1 = new ColorPointNonTransitive(
                1, 2, Color.yellow);
        ColorPointNonTransitive cp2 = new ColorPointNonTransitive(
                1, 2, Color.RED);
        
        System.out.println(p.equals(cp1)); //true
        System.out.println(p.equals(cp2)); //true
        System.out.println(cp1.equals(cp2)); //false
    }
}

/*  Как это исправить? Никак. Нельзя создать корректную операцию проверки 
 эквивалентности наследника и потомка, если у потомка больше полей для 
 сравнения. Это фундаментальная проблема объектно-ориентированных языков
 
    Эту проблему нельзя решить, проверяя в equals с помощью метода getClass()
так как подобное решение нарушает принцип подстановки Барбары Лисков (функция,
использующая базовый тип, должна быть способна использовать любой из его
подтипов не зная об этом.

    Если мы создадим класс-наследник, который имеет тот же набор полей, то
подобный метод equals будет возвращать false при сравнении с базовым классом
что противоречит логике.

    Лучший способ обойти подобное ограничение - не наследовать от класса
а включать его экземпляр в качестве поля в классе с большим функционалом:*/

// 
class ColorPointInculded {
    private final Point point;
    private final Color color;
    
    public ColorPointInculded(int x, int y, Color color) {
        if (color == null)
        throw new NullPointerException();
        point = new Point(x, y);
        this.color = color;
    }
    /**
    * Returns the point-view of this color point.
    */
    public Point asPoint() {
        return point;
    }
    
    @Override 
    public boolean equals(Object o) {
        if (!(o instanceof ColorPointInculded))
            return false;
        ColorPointInculded cp = (ColorPointInculded) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }
    // Остальноео опущено
}

/*  В стандартных библиотеках Java существуют классы, которые нарушают принцип
транзитивности. Например java.util.Data и java.sql.Timestamp. Timestamp является
наследником Date с добавленным полем nanoseconds. Таким образом могут возникнуть
серьзные проблемы, если попытаться использовать Timestamp и Date в одной 
коллекции.

    При этом, наследование от абстрактного класса не нарушает принцип
транзитивности.

    Принпци постоянства говорит о том, что в любые два момента времени сравнение
объектов должно возвращать один и тот же результат, если состояние объектов
не изменилось между этими моментами. Ярким примером нарушения принципа 
постоянства является java.net.URL. Метод equals основан на сравнении IP адересов
серверов, полученных по указанному URL. Программист не может гарантировать, что
у одного и того же URL данный сетевой запрос в любой момент времени вернет одни 
и те же адреса.

    Рецепт высококачественного метода equals:
    1) Использовать оператор "==" для проверки, не имеют ли сравниваемый и 
сравнивающий объект один и тот же адрес. Особенно эффективно, если сравнение
занимает много ресурсов.
    2) Использовать оператор instanceof для проверки, явялется ли сравниваемый
объект подходящего для сравнения типа
    3) Привести аргумент к нужному типу
    4) сравнить все логически существенные для сравнения поля объекта. Для 
примитивов кроме float и double использовать "==", для float и double 
использовать compare, для остальных использовать equals. Если поле может
содержать в себе null - включить проверку на null, чтобы не поймать 
NullPointerException. Часто поля, хранящие объекты, стоит сначала проверить 
через "==" и только затем через equals - это может сократить время проверки.
Провеку стоит начинать с полей, которые с максимальной вероятностью различаются
и при этом имеют наименьшую стоимость проверки. Если объект сложно проверять, 
возможно у него есть каноническая форма, которую удобно хранить внутри объекта
(например, каноническая форма String у объекта CaseInsensitiveString) и
проверять именно ее. Особенно это актуально для неизменяемых объектов, так как
не нужно заморачиваться с обновлением канонической формы при изменении объекта.
Если у объекта есть поля, которые явно выводятся из прочих его полей, то, не 
смотря на их кажущуюся бесполезность в процессе проверки объекта, начать стоит
именно с них - ведь если подобное поле у объектов отличается, то и проверять
дальше бессмысленно!
    5) после написания метода, проверить его на симметричность, транзитивность
и постоянство. При этом не только в уме, но и с помощью тестов.

    В заключении:
    1) всегда переоперделяйте hashCode, если переопределяете equals
    2 чем проще - тем лучше. 
    3) не пытайтесь передать в equals агрумент другого типа, нежели Object. 
Это банально не работает, так как не идет переопределение метода в Object.
Используйте аннотацию @Override, явно указывая свое намерение переопределить 
метод - это позволит избежать неприятностей уже на этапе компиляции.*/