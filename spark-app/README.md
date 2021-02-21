# Scala Knowledge

## Ebook:
- [Learning Scala Language](https://riptutorial.com/Download/scala-language.pdf)
- [Spark Action](https://drive.google.com/file/d/18CWuR4DF3hqcy6Adj5WRlE7XuMxfwN9R/view?usp=sharing)

## Naming Convention
Scala use "cammel case" naming. 
That is, each word is capitialized (upper camel case), except possibly the first word (lower camel case).
- Method, value and variable names should be in lower camel case.
- Constant names should be in upper camel case.

Examples:
- Standard cases:
  ```
  UpperCamelCase
  lowerCamelCase
  ```
- Acronyms should be treated as normal word
  ```
  xHtml instead of xHTML
  maxId instead of maxID
  ```

## Working With Data in Immutable Style
### 1. var AND val
- var (variable): referrence as reassignable as simple variable declaration in Java
- val (value): referrence as unreassignable, it means once it has been initialized. Like a `final` variable in Java
### 2. Immutable and Mutable collections
 - Mutable collection:
  ```
  scala> val mut = scala.collection.mutable.Map.empty[String, Int]
  mut: scala.collection.mutable.Map[String,Int] = Map()

  scala> mut += ("123" -> 123)
  res0: mut.type = Map(123 -> 123)

  scala> println(mut)
  Map(123 -> 123)
  ```

  - Immutable collections:
  ```
  scala> val immut = scala.collection.immutable.Map.empty[String, Int]
  immut: scala.collection.immutable.Map[String,Int] = Map()

  scala> immut += ("123" -> 123)
  <console>:13: error: value += is not a member of scala.collection.immutable.Map[String,Int]
    Expression does not convert to assignment because receiver is not assignable.
  immut += ("123" -> 123)
        ^

  scala> immut + ("123" -> 123)
  res3: scala.collection.immutable.Map[String,Int] = Map(123 -> 123)

  scala> println(immut)
  Map()
  ```
## Configurarion
- Configuration is most essential factor in any application, that makes system customisable.
- There are 3 ways to load configuration to application:
  - A file (.properties, .conf, .json, ...)
  - Command line parameters
  - Environment variables

## Scala Implicits are anywhere
** Referrence: 
  1.  [Scala Implicits are anywhere](https://arxiv.org/pdf/1908.07883.pdf)
  2.  [What is implicit](https://blog.vietnamlab.vn/tim-hieu-ve-implicit-trong-scala/)
### 1. Purpose of implicit in Scala
Implicits paramters or implicits conversion in Scala provide the ways to:
- Extends existing software
- Implement language feature outside compiler
- Allow end-user to write code with less boilerplate

### 2. Overview of Scala implicit
Scala is a statically typed programing language that bridge the gap between object-oriented and functional programming. In 2004 scala publish "Implicits conversions" to solve the late extension problems; namely, given a class C and trait T, how to have C extends T without touching or recompiling C.

#### 2.1 Implicit conversion
- Provide a way to use a type when another type is required without resorting an explicit conversion. This feature makes Scala become a flexible programming language for DSL (Domain Specific Language).
- The definition of `implicit paramater` will be show after the below example.
- In Scala, values with type Int have no operator `**` as in power operator in Python and Scala is not an opened attribute as Ruby to user extends class Int then add a custom operator
  ```
  scala> 3 ** 4
  <console>:12: error: value ** is not a member of Int
  3 ** 4
  ^
  ```
- Implicit conversion will help user implement this via a `implicit class`. Here, we have implicit class name `class Utils` will implement an implicit conversion.
  ```
  implicit class Utils (val x: Int){ def **(y: Int) = scala.math.pow(x, y) }
  defined class Utils
  ```
- In Scala runtime when a method or a field is not exist invoke an object complier will find an implicit class in context then adopt that can get the object as parameter and inside that class contains the above undefined-field or undefinded-method
  ```
  scala> 3 ** 4
  res1: Double = 81.0
  ```  
#### 2.2 Implicit parameter
- A method or a field can be defined as an implicit parameter. The arguments to these parameter will be filled by the compiler at every call site with the most suitable values in the calling context.
- We declare an implicit method because that contains at least an implicit parameter inside then call this function by pass enough arguments.
  ```
  scala> def calculateSalary(workingHour: Float)(implicit salaryPerHour: Float): Float = workingHour * salaryPerHour

  calculateSalary: (workingHour: Float)(implicit salaryPerHour: Float)Float

  scala> calculateSalary(24)(10)
  res2: Float = 240.0
  ```
- When we call the above method with 1 required argument
  ```
  scala> calculateSalary(24)
  <console>:18: error: could not find implicit value for parameter salaryPerHour: Float
  calculateSalary(24)
  ```
- We add a statement before method calling statement
  ```
  scala> implicit val salaryPerHour = 10.0F
  salaryPerHour: Float = 10.0
  ```
- Obviously, when a define an implicit parameter in method and when calling that method without argument for that parameter the compiler will find in calling context the value of a implicit variable which fix for this argument then add automatically to calling statement.
  ```
  scala> calculateSalary(24)
  res8: Float = 240.0
  ```
- Continue with an extend implicit parameter with the an implicit function the same name with parameter `salaryPerHour` in `calculateSalary`
  ```
  scala> implicit def salaryPerHour(implicit isFriend: Boolean): Float = if (isFriend) 0.5F else 1F
  salaryPerHour: (implicit isFriend: Boolean)Float
  ```

  ```
  scala> implicit val isFriend = false
  isFriend: Boolean = false
  ```

  ```
  scala> calculateSalary(24)
  res10: Float = 24.0
  ```
- Scala compiler will not only finds values declared with `val` but also finds implicit functions call return values with type fixed with  type of implicit parameters

## Scala Extractor
### 1. Tuple extractor
Particular case: 
```
scala> val (a, b) = (1, 2)
a: Int = 1
b: Int = 2

scala> a
res0: Int = 1

scala> b
res1: Int = 2
```
To ignore extracted value:
```
scala> val (_, a) = (1, "a")
a: String = a
```
To unpack an extractor
```
scala> val student_1 = ("Peter", 10)
student_1: (String, Int) = (Peter,10)

scala> student_1._1
res0: String = Peter

scala> student_1._2
res1: Int = 10

scala> student_1._3
<console>:13: error: value _3 is not a member of (String, Int)
student_1._3
```
-------
**Note that:**
- Tuple have maximum length of 22, and this ._1 and _.22 will work (assuming tuple has at least that size).
- Tuple extractor may be used to provide symbolic arguments for literal functions.
-------
Providing symbolic arguments
```
scala> val persons = List("A." -> "Lovelace", "G." -> "Hopper")
persons: List[(String, String)] = List((A.,Lovelace), (G.,Hopper))

scala> val names = List("Lovelace, A.", "Hopper, G.")
names: List[String] = List(Lovelace, A., Hopper, G.)

scala> assert {
   names == (persons.map({name => s"${name._2}, ${name._1}"}))
 }
     |      | 

scala> assert {
   names == (persons.map({
     case (given, surname) => s"${surname}, ${given}"
   }))
 }
     |      |      |      | 
```
### 2. Case class extractor
Case classes are classes with a lot of standard boilerplace code automatically included, they are good for modeling 
immutable data. 
```
scala> case class Person(name: String, age: Int)
defined class Person

scala> val p = Person("Peter", 10)
p: Person = Person(Peter,10)

scala> val Person(c, d) = p
c: String = Peter
d: Int = 10
```
At this juncture, both n and a are vals in the program and can be accessed as such: they are said
to have been 'extracted' from p.

```
scala> val p2 = Person("Tom", 173)
 
p2: Person = Person(Tom,173)

scala> val List(Person(n1, a1), Person(_, a2)) = List(p, p2)
n1: String = Peter
a1: Int = 10
a2: Int = 173
```
----
**Note that**: 
  - Extraction can happen at 'deep' level: properties of nested objects can be extracted. 
  - Not all elements need to be extracted. The wildcard `_` character indicates that that particular
    value can be anything, and is ignored. No val is created
----

### 3. Custom Extractors (Extractor Object)
```
scala> object CustomerID {
   def apply(name: String) = s"${name}--${scala.util.Random.nextLong}"
   def unapply(customerId: String): Option[String] = {
     val stringArray: Array[String] = customerId.split("--")
     if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
   } 
 }
     |      |      |      |      |      | defined object CustomerID


scala> val customer1Id = CustomerID("Max.Luong")
customer1Id: String = Max.Luong---6872945114686439161

scala> customer1Id match {
   case CustomerID(name) => println(name)
   case _ => println("Could not extract customer ID")
 }
     |      |      | Max.Luong

scala> object OrderID {
   def apply(order: String) = s"${order}__${scala.util.Random.nextLong}"
 }
     |      | defined object OrderID

scala> val order1Id = OrderID("Car")
order1Id: String = Car__-6309088696101765621

scala> order1Id match {
   case CustomerID(name) => println(name)
   case _ => println("Could not extract customer ID")
 }
     |      |      | Could not extract customer ID

```