/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.suobig.effectivejava.ch2allObjectMethods;

/*  В классе Object метод toString реализован следующим образом:
    [Имя класса]@[hashCode в 16-ричном представлении]
    
    Чаще всего такое строковое представление экземпляра совершенно 
неинформативно, и поэтому, хорошим тоном является переопределение метода
toString. В отличие от методов equals и hashCode, это не влияет на работу 
программы, однако делает использование класса удобнее в использовании. 
    
    Рекомендуется выводить в методе toSting всю полезную информацию, 
содержащуюся в объекте. Иногда целесообразно предоставить некоторые общие 
сведения. В идеале, получившаяся строка должна быть понятна без доп.пояснений.

    Важный выбор - определять ли формат вывода. Если вы определите формат вывода
в своем классе - вы привязаны к нему навсегда. Пользователи класса будут 
расчитывать, что результат toString будет всегда в этом же формате, они будут
парсить вывод, будут создавать экземпляры по этому формату и т.д. 

    Вне зависимости от вашего выбора, надо четко прописать его в документации.
Если формат есть, надо подробно и точно описать его. 

    Например, метод toString для класса PhoneNumber*/

final class PhoneNumberToString {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;
    private volatile int hashCode;
    
    public PhoneNumberToString(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }
    
    private static void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max)
            throw new IllegalArgumentException(name +": " + arg);
    }
    
    @Override 
    public boolean equals(Object o) {
        if (o == this)
            return true;
        
        if (!(o instanceof PhoneNumberToString))
            return false;
        
        PhoneNumberToString pn = (PhoneNumberToString)o;
        return pn.lineNumber == lineNumber 
                && pn.prefix == prefix 
                && pn.areaCode == areaCode;
    }
    
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = 17;
            result = 31 * result + areaCode;
            result = 31 * result + prefix;
            result = 31 * result + lineNumber;
            hashCode = result;
        }
        return result;
    }
    
/**
    * Returns the string representation of this phone number.
    * The string consists of fourteen characters whose format
    * is "(XXX) YYY-ZZZZ", where XXX is the area code, YYY is
    * the prefix, and ZZZZ is the line number. (Each of the
    * capital letters represents a single decimal digit.)
    *
    * If any of the three parts of this phone number is too small
    * to fill up its field, the field is padded with leading zeros.
    * For example, if the value of the line number is 123, the last
    * four characters of the string representation will be "0123".
    *
    * Note that there is a single space separating the closing
    * parenthesis after the area code from the first digit of the
    * prefix.
*/
    @Override 
    public String toString() {
        return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
    }
    
    public static void main(String args[]) {
        PhoneNumberToString o = new PhoneNumberToString(916, 472, 1039);
        System.out.println(o);
    }
}