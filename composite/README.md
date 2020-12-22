---
layout: pattern
title: Composite
folder: composite
permalink: /patterns/composite/
categories: Structural
tags:
 - Gang of Four
---

## Intent

Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients 
treat individual objects and compositions of objects uniformly.

将对象组合成树状结构以表示部分-整体层次结构。Composite允许客户端统一处理单个对象和对象的组合。

## Explanation

Real world example

> Every sentence is composed of words which are in turn composed of characters. Each of these 
> objects is printable and they can have something printed before or after them like sentence always 
> ends with full stop and word always has space before it.
>
> 每一个句子都是由每一个字轮流组成的。这些对象中的每一个都是可打印的，它们可以在它们前面或后面打印一些内容，比如句子总是以句号结尾，单词前面总是有空格。

In plain words

> Composite pattern lets clients treat the individual objects in a uniform manner.
>
> 复合模式使客户能够以统一的方式对待各个对象。

Wikipedia says

> In software engineering, the composite pattern is a partitioning design pattern. The composite 
> pattern describes that a group of objects is to be treated in the same way as a single instance of 
> an object. The intent of a composite is to "compose" objects into tree structures to represent 
> part-whole hierarchies. Implementing the composite pattern lets clients treat individual objects 
> and compositions uniformly.
>
> 在软件工程中，复合模式是一种分区设计模式。复合模式描述了一组对象将以与单个对象实例相同的方式进行处理。复合的目的是将对象“组合”成树结构，以表示部分-整体层次结构。
> 实现复合模式可以让客户机统一地处理单个对象和组合。

**Programmatic Example**

Taking our sentence example from above. Here we have the base class `LetterComposite` and the 
different printable types `Letter`, `Word` and `Sentence`. 

以上面的句子为例。这里我们有基本类LetterComposite和不同的可打印类型字母、单词和句子。

```java
public abstract class LetterComposite {

  private final List<LetterComposite> children = new ArrayList<>();

  public void add(LetterComposite letter) {
    children.add(letter);
  }

  public int count() {
    return children.size();
  }

  protected void printThisBefore() {
  }

  protected void printThisAfter() {
  }

  public void print() {
    printThisBefore();
    children.forEach(LetterComposite::print);
    printThisAfter();
  }
}

public class Letter extends LetterComposite {

  private final char character;

  public Letter(char c) {
    this.character = c;
  }

  @Override
  protected void printThisBefore() {
    System.out.print(character);
  }
}

public class Word extends LetterComposite {

  public Word(List<Letter> letters) {
    letters.forEach(this::add);
  }

  public Word(char... letters) {
    for (char letter : letters) {
      this.add(new Letter(letter));
    }
  }

  @Override
  protected void printThisBefore() {
    System.out.print(" ");
  }
}

public class Sentence extends LetterComposite {

  public Sentence(List<Word> words) {
    words.forEach(this::add);
  }

  @Override
  protected void printThisAfter() {
    System.out.print(".");
  }
}
```

Then we have a messenger to carry messages:

```java
public class Messenger {

  LetterComposite messageFromOrcs() {

    var words = List.of(
        new Word('W', 'h', 'e', 'r', 'e'),
        new Word('t', 'h', 'e', 'r', 'e'),
        new Word('i', 's'),
        new Word('a'),
        new Word('w', 'h', 'i', 'p'),
        new Word('t', 'h', 'e', 'r', 'e'),
        new Word('i', 's'),
        new Word('a'),
        new Word('w', 'a', 'y')
    );

    return new Sentence(words);

  }

  LetterComposite messageFromElves() {

    var words = List.of(
        new Word('M', 'u', 'c', 'h'),
        new Word('w', 'i', 'n', 'd'),
        new Word('p', 'o', 'u', 'r', 's'),
        new Word('f', 'r', 'o', 'm'),
        new Word('y', 'o', 'u', 'r'),
        new Word('m', 'o', 'u', 't', 'h')
    );

    return new Sentence(words);

  }

}
```

And then it can be used as:

```java
var orcMessage = new Messenger().messageFromOrcs();
orcMessage.print(); // Where there is a whip there is a way.
var elfMessage = new Messenger().messageFromElves();
elfMessage.print(); // Much wind pours from your mouth.
```

## Class diagram

![alt text](./etc/composite.urm.png "Composite class diagram")

## Applicability

Use the Composite pattern when

* You want to represent part-whole hierarchies of objects.
* You want clients to be able to ignore the difference between compositions of objects and 
individual objects. Clients will treat all objects in the composite structure uniformly.

## Real world examples

* [java.awt.Container](http://docs.oracle.com/javase/8/docs/api/java/awt/Container.html) and [java.awt.Component](http://docs.oracle.com/javase/8/docs/api/java/awt/Component.html)
* [Apache Wicket](https://github.com/apache/wicket) component tree, see [Component](https://github.com/apache/wicket/blob/91e154702ab1ff3481ef6cbb04c6044814b7e130/wicket-core/src/main/java/org/apache/wicket/Component.java) and [MarkupContainer](https://github.com/apache/wicket/blob/b60ec64d0b50a611a9549809c9ab216f0ffa3ae3/wicket-core/src/main/java/org/apache/wicket/MarkupContainer.java)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
* [Refactoring to Patterns](https://www.amazon.com/gp/product/0321213351/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0321213351&linkCode=as2&tag=javadesignpat-20&linkId=2a76fcb387234bc71b1c61150b3cc3a7)
