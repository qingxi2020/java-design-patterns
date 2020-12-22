---
layout: pattern
title: Prototype
folder: prototype
permalink: /patterns/prototype/
categories: Creational
tags: 
 - Gang Of Four
 - Instantiation
---

## Intent

Specify the kinds of objects to create using a prototypical instance, and create new objects by 
copying this prototype.

指定要使用原型实例创建的对象的种类，并通过复制此原型来创建新对象。
## Explanation

First it should be noted that Prototype pattern is not used to gain performance benefits. It's only 
used for creating new objects from prototype instance.

首先应该注意，原型模式不用于获得性能优势。 它仅用于从原型实例创建新对象。

Real world example

> Remember Dolly? The sheep that was cloned! Lets not get into the details but the key point here is 
> that it is all about cloning.


In plain words

> Create object based on an existing object through cloning.
>
>通过克隆基于现有对象创建对象。

Wikipedia says

> The prototype pattern is a creational design pattern in software development. It is used when the 
> type of objects to create is determined by a prototypical instance, which is cloned to produce new 
> objects.
>
>原型模式是软件开发中的一种创新设计模式。 当要创建的对象类型由原型实例确定时使用，该实例实例被克隆以生成新对象。

In short, it allows you to create a copy of an existing object and modify it to your needs, instead 
of going through the trouble of creating an object from scratch and setting it up.

简而言之，它使您可以创建现有对象的副本并根据需要对其进行修改，而不必麻烦地从头创建对象并进行设置。

**Programmatic Example**

In Java, it can be easily done by implementing `Cloneable` and overriding `clone` from `Object`

```java
class Sheep implements Cloneable {
  private String name;
  public Sheep(String name) { this.name = name; }
  public void setName(String name) { this.name = name; }
  public String getName() { return name; }
  @Override
  public Sheep clone() {
    try {
      return (Sheep)super.clone();
    } catch(CloneNotSuportedException) {
      throw new InternalError();
    }
  }
}
```

Then it can be cloned like below:

```java
var original = new Sheep("Jolly");
System.out.println(original.getName()); // Jolly

// Clone and modify what is required
var cloned = original.clone();
cloned.setName("Dolly");
System.out.println(cloned.getName()); // Dolly
```

## Class diagram

![alt text](./etc/prototype.urm.png "Prototype pattern class diagram")

## Applicability

Use the Prototype pattern when a system should be independent of how its products are created, 
composed, represented and

* When the classes to instantiate are specified at run-time, for example, by dynamic loading.
* To avoid building a class hierarchy of factories that parallels the class hierarchy of products.
* When instances of a class can have one of only a few different combinations of state. It may be 
more convenient to install a corresponding number of prototypes and clone them rather than 
instantiating the class manually, each time with the appropriate state.
* When object creation is expensive compared to cloning.

## Real world examples

* [java.lang.Object#clone()](http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#clone%28%29)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
