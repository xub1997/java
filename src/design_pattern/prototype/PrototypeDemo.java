package design_pattern.prototype;

import java.io.*;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-06 11:53
 * <p>
 * 浅拷贝：对值类型的成员变量进行值的复制,对引用类型的成员变量仅仅复制引用,不复制引用的对象
 * <p>
 * 深拷贝：对值类型的成员变量进行值的复制,对引用类型的成员变量也进行引用对象的复制
 * CopyRight &copy; All rights reserved.
 **/
public class PrototypeDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("-----浅拷贝------");
        Prototype prototype1 = new Prototype();
        prototype1.setBookId(1);
        prototype1.setBookName("Java入门到放弃");
        BookType1 bookType1 = new BookType1();
        bookType1.setTypeCode("aaaaa");
        bookType1.setTypeDesc("bbbbbb");
        prototype1.setType1(bookType1);
        System.out.println("克隆前: prototype1.bookId=" + prototype1.getBookId());
        System.out.println("克隆前: prototype1.bookName=" + prototype1.getBookName());
        System.out.println("克隆前: prototype1.type1=" + prototype1.getType1());
        System.out.println("-----------");
        //克隆出对象prototype2,并对prototype2的属性进行改动
        Prototype prototype2 = (Prototype) prototype1.clone();
        System.out.println(String.format("prototype1的hasCode： %s，prototype2的hasCode： %s", prototype1.hashCode(), prototype2.hashCode()));
        System.out.println(String.format("prototype1.type1的hasCode： %s，prototype2.type1的hasCode： %s", prototype1.getType1().hashCode(), prototype2.getType1().hashCode()));
        //操作引用对象（对引用对象进行引用传递，并修改值）
        BookType1 bookType2 = prototype2.getType1();
        bookType2.setTypeCode("123");
        bookType2.setTypeDesc("456");
        System.out.println("修改引用对象值： typeCode: aaaaa  ->  123，typeDesc: bbbbbb  -> 456");
        prototype2.setBookId(2);
        prototype2.setBookName("Java入门到直接放弃");
//        prototype2.setType1(bookType2);

        System.out.println("克隆后: prototype1.bookId=" + prototype1.getBookId());
        System.out.println("克隆后: prototype1.bookName=" + prototype1.getBookName());
        System.out.println("克隆后: prototype1.type1=" + prototype1.getType1());
        System.out.println("-----------");
        System.out.println("克隆后: prototype2.bookId=" + prototype2.getBookId());
        System.out.println("克隆后: prototype2.bookName=" + prototype2.getBookName());
        System.out.println("克隆后: prototype2.type1=" + prototype2.getType1());
        System.out.println(String.format("prototype1的hasCode： %s，prototype2的hasCode： %s", prototype1.hashCode(), prototype2.hashCode()));
        System.out.println(String.format("prototype1.type1的hasCode： %s，prototype2.type1的hasCode： %s", prototype1.getType1().hashCode(), prototype2.getType1().hashCode()));
        System.out.println("\n\n\n\n\n\n");
        System.out.println("-----深拷贝------");
        Prototype prototype3 = new Prototype();
        prototype3.setBookId(123);
        prototype3.setBookName("php入门到放弃");
        BookType2 bookType3 = new BookType2();
        bookType3.setTypeCode("aaaaa");
        bookType3.setTypeDesc("bbbbbb");
        prototype3.setType2(bookType3);
        System.out.println("克隆前: prototype3.bookId=" + prototype3.getBookId());
        System.out.println("克隆前: prototype3.bookName=" + prototype3.getBookName());
        System.out.println("克隆前: prototype3.type2=" + prototype3.getType2());
        System.out.println("-----------");
        //克隆出对象prototype4,并对prototype4的属性进行改动
        Prototype prototype4 = (Prototype) prototype3.deepClone1();
        System.out.println(String.format("prototype3的hasCode： %s，prototype4的hasCode： %s", prototype3.hashCode(), prototype4.hashCode()));
        System.out.println(String.format("prototype3.type2的hasCode： %s，prototype4.type2的hasCode： %s", prototype3.getType2().hashCode(), prototype4.getType2().hashCode()));
        prototype4.setBookId(111);
        prototype4.setBookName("php入门到直接放弃");
        BookType2 bookType4 = prototype4.getType2();
        bookType4.setTypeCode("123");
        bookType4.setTypeDesc("456");
        System.out.println("修改引用对象值： typeCode: aaaaa  ->  123，typeDesc: bbbbbb -> 456");
        System.out.println("克隆后: prototype3.bookId=" + prototype3.getBookId());
        System.out.println("克隆后: prototype3.bookName=" + prototype3.getBookName());
        System.out.println("克隆后: prototype3.type2=" + prototype3.getType2());
        System.out.println("-----------");
        System.out.println("克隆后: prototype4.bookId=" + prototype4.getBookId());
        System.out.println("克隆后: prototype4.bookName=" + prototype4.getBookName());
        System.out.println("克隆后: prototype3.type2=" + prototype4.getType2());
        System.out.println(String.format("prototype3的hasCode： %s，prototype4的hasCode： %s", prototype3.hashCode(), prototype4.hashCode()));
        System.out.println(String.format("prototype3.type2的hasCode： %s，prototype4.type2的hasCode： %s", prototype3.getType2().hashCode(), prototype4.getType2().hashCode()));
        System.out.println("-----------");
    }
}

/**
 * 实现Cloneable接口，浅拷贝
 */
class Prototype implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    //基本类型以及String类可实现
    private int bookId;
    private String bookName;
    //对于引用类型无法进行深度克隆
    private BookType1 type1;

    private BookType2 type2;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BookType1 getType1() {
        return type1;
    }

    public void setType1(BookType1 type1) {
        this.type1 = type1;
    }

    public BookType2 getType2() {
        return type2;
    }

    public void setType2(BookType2 type2) {
        this.type2 = type2;
    }

    public Prototype() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Prototype prototype1 = (Prototype) super.clone();
        return prototype1;
    }

    /**
     * 深拷贝：第一种方式：对对象里面的引用也实现Cloneable接口，并在clone方法里面进行调用引用对象具体的clone方法
     *
     * @return
     */
    public Object deepClone1() throws CloneNotSupportedException {
        Prototype prototype1 = (Prototype) super.clone();
        prototype1.type2 = (BookType2) type2.clone();
        return prototype1;
    }

    /**
     * 深拷贝：第一种方式：实现Serializable接口
     *
     * @return
     */
    public Object deepClone2() throws IOException, ClassNotFoundException {
        /**
         *  写入当前对象的二进制流
         */
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);

        /**
         * 写出当前对象二进制流
         */
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream;
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();

    }
}

class BookType1 {
    private String typeCode;
    private String typeDesc;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Override
    public String toString() {
        return "BookType1{" +
                "typeCode='" + typeCode + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                '}';
    }
}

class BookType2 implements Cloneable {
    private String typeCode;
    private String typeDesc;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        BookType2 type = (BookType2) super.clone();
        return type;
    }

    @Override
    public String toString() {
        return "BookType2{" +
                "typeCode='" + typeCode + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                '}';
    }
}
