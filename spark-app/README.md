# Scala Knowledge

## Ebook:
- [Learning Scala Language](https://riptutorial.com/Download/scala-language.pdf)
- [Spark Action](https://drive.google.com/file/d/18CWuR4DF3hqcy6Adj5WRlE7XuMxfwN9R/view?usp=sharing)
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