/*
  Scala testing class
 */
import java.util.Date // Java Library
import java.text.SimpleDateFormat // Java Library
import mypackage.{ // multi-import
  MyClassX,
  MyClassY,
  Z
} //  mypackage._ == mypackage.* [_ wildcard for select all elements]

object MainObject {

  def main(args: Array[String]) {
    val sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy")
    val date = sdf format new Date // sdf format new Date == sdf.format(new Date);
    println(">>>>>>>>>>>>>>> [ " + date + " ] <<<<<<<<<<<<<<<\n")
    println("[ HELLO SCALA ]\n")
    work
    println("\n-------------------------------------------------------")
  } // def main(args: Array[String])

  def work() { // def defines a method
    import TestingObject._
    println("__________ pattern_matching __________ ")
    matchTesting
    println("\n__________ wildcard _________________")
    List(1, 2, 3).foreach(println(_)) // List(1, 2, 3).foreach(a => println(a)) [_ wildcard for iterate over all elements]
    println("\n__________ methods __________________")
    methodsTesting
    println("\n__________ classes __________________")
    classesTesting
    println("\n__________ case_class _______________")
    caseClassTesting
    println("\n__________ generic_type _____________")
    genericTypeTesting
    println("\n__________ trait ____________________")
    traitTesting
  } // def work()

} // object MainObject

object TestingObject {

  def evolvedSwitch(x: Any): String = {
    x match { // match pattern evolve switch statement
      case 1 | 2 | 3 => "OK, one or two or three."
      case "abc"     => "OK, abc."
      case List(_, 5, _) => // [_ wildcard for match any element]
        "OK, a list with three element whose central element is 5."
      case MyCaseObject => "Ok, it's MyCaseObject type."
      case _            => "Unknown."
    }
  } // def evolvedSwitch(x: Any): String

  def printFunction() {
    println("printing function")
  } // def printFunction()

  def countdown(ticks: Int, delay: Int, callback: () => Unit) { // Unit == void
    var counter = 0
    while (counter < ticks) {
      callback()
      counter += 1
      Thread sleep delay
    }
  } // def countdown(ticks: Int, delay: Int, callback: () => Unit)

  def matchTesting() {
    println("match 1: " + evolvedSwitch(1))
    println("match abc: " + evolvedSwitch("abc"))
    println("match List(1, 5, \"a\"): " + evolvedSwitch(List(1, 5, "a")))
    val myCaseObject = MyCaseObject
    println("match myCaseObject: " + evolvedSwitch(myCaseObject))
    println("match List(1, 5, 3, 2): " + evolvedSwitch(List(1, 5, 3, 2)))
  } // def matchTesting()

  def methodsTesting() {
    val milliseconds = 250 // val defines a fixed value
    var times = 3 // var defines a variable
    times += 2
    countdown(times, milliseconds, printFunction)
    countdown(times, milliseconds, () => println("printing anonymous function"))
  } // def methodsTesting()

  def classesTesting() {
    val myObjectX = new MyClassX(5)
    val myObjectY = new MyClassY(10)
    val zeta = new Z()
    println("" + zeta.toString()) // method overloaded
    println(
      "sum Int from companion class:  (" + myObjectX.x + "+" + myObjectY.y + ")=" + zeta
        .sum(myObjectX.x, myObjectY.y))
    val x = 10
    println("sum Int from companion class:  (" + myObjectX
      .x(x) + "+" + myObjectY.y + ")=" + zeta.sum(myObjectX.x(x), myObjectY.y))
    println("sum Str from companion object: " + Z.sum("foo", "bar"))
    println("companion class:  " + zeta.fb)
    println("companion object: " + Z.fb)
  } // def classesTesting()

  def caseClassTesting() { // case class can be used with matching pattern and its serializable by default
    case class Person(firstname: String, lastname: String, birthYear: Int) // case class: auto getters (attributes are val)
    val mp = Person("John", "Doe", 2000) // case class can ignore new
    println(mp.firstname + " " + mp.lastname + ", " + mp.birthYear + ".")
    val fp = mp.copy(firstname = "Jane")
    println(fp.firstname + " " + fp.lastname + ", " + fp.birthYear + ".")
    val personCreator: (String, String, Int) => Person = Person.apply _
    val bp = personCreator("Baby", "Doe", 2018)
    println(bp.firstname + " " + bp.lastname + ", " + bp.birthYear + ".")
  } // def caseClassTesting()

  def genericTypeTesting() {
    val myInt = new GenericTypeClass[Int]
    myInt.set(5)
    println("" + myInt.get * 5)
    val myString = new GenericTypeClass[String]
    myString.set("A")
    println("" + myString.get * 3)
  } // def genericTypeTesting()

  def traitTesting() {
    val p1 = new Point(1, 2)
    val p2 = new Point(1, 2)
    val p3 = new Point(2, 1)
    println("p1.isEqual(p2):    " + p1.isEqual(p2))
    println("p1.isNotEqual(p2): " + p1.isNotEqual(p2))
    println("p1.isEqual(p3):    " + p1.isEqual(p3))
  } // def genericTypeTesting()

} // object TestingObject

case object MyCaseObject {
  // case objects can be used with matching pattern and its serializable by default
} // case object MyCaseObject

class GenericTypeClass[T] { // T is generic Type, can be any other [A]... [+T] covariant, [-T] contravariant
  private var genericTypeVar: T = _ // [_ wildcard for any element]
  def set(value: T) { genericTypeVar = value }
  def get: T = genericTypeVar
} // class GenericTypeClass[T]

trait Compare { // Trait: like Java interfaces, but can implements of methods
  def isEqual(obj: Any): Boolean // abstract method
  def isNotEqual(obj: Any): Boolean = !isEqual(obj)
} // trait Compare

class Point(xCoord: Int, yCoord: Int) extends Compare {
  val x: Int = xCoord
  val y: Int = yCoord
  /*
  isInstanceOf: check if the object comes from a class
  asInstanceOf: its return the corresponding object instance
   */
  def isEqual(obj: Any) =
    obj.isInstanceOf[Point] &&
      obj.asInstanceOf[Point].x == x &&
      obj.asInstanceOf[Point].y == y
} // class Point(xCoord: Int, yCoord: Int) extends Compare
