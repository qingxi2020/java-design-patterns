---
layout: pattern
title: Abstract Factory
folder: abstract-factory
permalink: /patterns/abstract-factory/
categories: Creational
tags:
 - Gang of Four
---

## Also known as

Kit

## Intent

- Provide an interface for creating families of related or dependent
objects without specifying their concrete classes.

- 提供一个接口，用于创建相关或依赖对象的族，而无需指定其具体类。

## Explanation

Real world example

> To create a kingdom we need objects with a common theme. Elven kingdom needs an Elven king, Elven castle and Elven army whereas Orcish kingdom needs an Orcish king, Orcish castle and Orcish army. There is a dependency between the objects in the kingdom.

> 为了创造一个王国，我们需要具有共同主题的物体。精灵王国需要精灵王、精灵城堡和精灵军队，而兽人王国需要兽人国王、兽人城堡和兽人军队。王国中的物体之间有一种依赖关系。


In plain words

> A factory of factories; a factory that groups the individual but related/dependent factories together without specifying their concrete classes.

> 工厂的工厂；一个将单个但相关/依赖的工厂组合在一起而不指定其具体类别的工厂。

Wikipedia says

> The abstract factory pattern provides a way to encapsulate a group of individual factories that have a common theme without specifying their concrete classes

> 抽象工厂模式提供了一种方法来封装一组具有共同主题的独立工厂，而无需指定它们的具体类

**Programmatic Example**

Translating the kingdom example above. First of all we have some interfaces and implementation for the objects in the 
kingdom.

翻译上面的王国例子。首先，我们为王国中的对象提供了一些接口和实现。

```java
public interface Castle {
  String getDescription();
}

public interface King {
  String getDescription();
}

public interface Army {
  String getDescription();
}

// Elven implementations ->
public class ElfCastle implements Castle {
  static final String DESCRIPTION = "This is the Elven castle!";
  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
public class ElfKing implements King {
  static final String DESCRIPTION = "This is the Elven king!";
  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}
public class ElfArmy implements Army {
  static final String DESCRIPTION = "This is the Elven Army!";
  @Override
  public String getDescription() {
    return DESCRIPTION;
  }
}

// Orcish implementations similarly -> ...

```

Then we have the abstraction and implementations for the kingdom factory

然后我们有了王国工厂的抽象和实现

```java
public interface KingdomFactory {
  Castle createCastle();
  King createKing();
  Army createArmy();
}

public class ElfKingdomFactory implements KingdomFactory {
  public Castle createCastle() {
    return new ElfCastle();
  }
  public King createKing() {
    return new ElfKing();
  }
  public Army createArmy() {
    return new ElfArmy();
  }
}

public class OrcKingdomFactory implements KingdomFactory {
  public Castle createCastle() {
    return new OrcCastle();
  }
  public King createKing() {
    return new OrcKing();
  }
  public Army createArmy() {
    return new OrcArmy();
  }
}
```

Now we have our abstract factory that lets us make family of related objects i.e. Elven kingdom factory creates Elven castle, king and army etc.

现在我们有了一个抽象的工厂，可以让我们把相关的对象组成一个家庭，也就是说，精灵王国工厂创造了精灵城堡、国王和军队等。

```java
var factory = new ElfKingdomFactory();
var castle = factory.createCastle();
var king = factory.createKing();
var army = factory.createArmy();

castle.getDescription();
king.getDescription();
army.getDescription();
```

Program output:

```java
This is the Elven castle!
This is the Elven king!
This is the Elven Army!
```

Now, we can design a factory for our different kingdom factories. In this example, we created FactoryMaker, responsible for returning an instance of either ElfKingdomFactory or OrcKingdomFactory.  
The client can use FactoryMaker to create the desired concrete factory which, in turn, will produce different concrete objects (Army, King, Castle).  
In this example, we also used an enum to parameterize which type of kingdom factory the client will ask for.

现在，我们可以为我们不同的王国工厂设计一个工厂。在本例中，我们创建了FactoryMaker，负责返回ElfKingdomFactory或orkingdomfactory的实例。

客户可以使用FactoryMaker创建所需的混凝土工厂，进而生产不同的混凝土对象（军队、国王、城堡）

在这个例子中，我们还使用了一个enum参数化客户端将请求的kingdom工厂的类型。

```java
public static class FactoryMaker {

  public enum KingdomType {
    ELF, ORC
  }

  public static KingdomFactory makeFactory(KingdomType type) {
    switch (type) {
      case ELF:
        return new ElfKingdomFactory();
      case ORC:
        return new OrcKingdomFactory();
      default:
        throw new IllegalArgumentException("KingdomType not supported.");
    }
  }
}

public static void main(String[] args) {
  var app = new App();

  LOGGER.info("Elf Kingdom");
  app.createKingdom(FactoryMaker.makeFactory(KingdomType.ELF));
  LOGGER.info(app.getArmy().getDescription());
  LOGGER.info(app.getCastle().getDescription());
  LOGGER.info(app.getKing().getDescription());

  LOGGER.info("Orc Kingdom");
  app.createKingdom(FactoryMaker.makeFactory(KingdomType.ORC));
  -- similar use of the orc factory
}
```

## Class diagram

![alt text](./etc/abstract-factory.urm.png "Abstract Factory class diagram")


## Applicability

Use the Abstract Factory pattern when

* The system should be independent of how its products are created, composed and represented
* 系统应该独立于它的产品是如何被创建、组成和表现的
* The system should be configured with one of multiple families of products
* 系统应配置多个产品系列中的一个
* The family of related product objects is designed to be used together, and you need to enforce this constraint
* 相关产品对象的系列设计为一起使用，您需要强制执行此约束
* You want to provide a class library of products, and you want to reveal just their interfaces, not their implementations
* 你想要提供一个产品的类库，你只想展示它们的接口，而不是它们的实现
* The lifetime of the dependency is conceptually shorter than the lifetime of the consumer.
* 从概念上讲，依赖项的生存期比使用者的生存期短。
* You need a run-time value to construct a particular dependency
* 您需要一个运行时值来构造特定的依赖项
* You want to decide which product to call from a family at runtime.
* 您需要决定在运行时从族中调用哪个产品。
* You need to supply one or more parameters only known at run-time before you can resolve a dependency.
* 在解析依赖项之前，需要提供一个或多个仅在运行时已知的参数。
* When you need consistency among products
* 当您需要产品之间的一致性时
* You don’t want to change existing code when adding new products or families of products to the program.
* 在向程序中添加新产品或产品系列时，不希望更改现有代码。

Example use cases	

* Selecting to call to the appropriate implementation of FileSystemAcmeService or DatabaseAcmeService or NetworkAcmeService at runtime.
* Unit test case writing becomes much easier
* UI tools for different OS

## Consequences:

* Dependency injection in java hides the service class dependencies that can lead to runtime errors that would have been caught at compile time.
* While the pattern is great when creating predefined objects, adding the new ones might be challenging.
* The code becomes more complicated than it should be, since a lot of new interfaces and classes are introduced along with the pattern.

## Tutorial

* [Abstract Factory Pattern Tutorial](https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java) 

## Known uses

* [javax.xml.parsers.DocumentBuilderFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/parsers/DocumentBuilderFactory.html)
* [javax.xml.transform.TransformerFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/transform/TransformerFactory.html#newInstance--)
* [javax.xml.xpath.XPathFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/xpath/XPathFactory.html#newInstance--)

## Related patterns

[Factory Method](https://java-design-patterns.com/patterns/factory-method/)
[Factory Kit](https://java-design-patterns.com/patterns/factory-kit/)

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/gp/product/0201633612/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0201633612&linkCode=as2&tag=javadesignpat-20&linkId=675d49790ce11db99d90bde47f1aeb59)
* [Head First Design Patterns: A Brain-Friendly Guide](https://www.amazon.com/gp/product/0596007124/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0596007124&linkCode=as2&tag=javadesignpat-20&linkId=6b8b6eea86021af6c8e3cd3fc382cb5b)
