import java.util.Date // Java Library
import java.text.SimpleDateFormat // Java Library
import mypackage.{
  MyClassX,
  MyClassY,
  Z
} //  mypackage._ == mypackage.* [_ wildcard for select all elements]

object MainObject {

  def main(args: Array[String]) {
    val sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy")
    val date = sdf format new Date // sdf format new Date == sdf.format(new Date);
    println(">>>>>>>>>>>>>>> [ " + date + " ] <<<<<<<<<<<<<<<\n")
    println("Hello Scala\n")
    work()
    println("\n-------------------------------------------------------")
  } // def main(args: Array[String])

  def work() { // def defines a method
    println("match 1: " + evolvedSwitch(1))
    println("match 2: " + evolvedSwitch(2))
    println("match abc: " + evolvedSwitch("abc"))
    println("match List(1, 5, \"a\"): " + evolvedSwitch(List(1, 5, "a")))
    println("match List(1, 5, 3, 2): " + evolvedSwitch(List(1, 5, 3, 2)))
    println()
    List(1, 2, 3).foreach(println(_)) // List(1, 2, 3).foreach(a => println(a)) [_ wildcard for iterate over all elements]
    println()
    val milliseconds = 250 // val defines a fixed value
    var times = 3 // var defines a variable
    times += 2
    countdown(times, milliseconds, printFunction)
    println()
    countdown(times, milliseconds, () => println("printing anonymous function"))
    println()
    val myObjectX = new MyClassX(5)
    val myObjectY = new MyClassY(10)
    val zeta = new Z()
    println(zeta.toString())
    println(
      "sum Int from companion class:  (" + myObjectX.x + "+" + myObjectY.y + ")=" + zeta
        .sum(myObjectX.x, myObjectY.y))
    val x = 10
    println("sum Int from companion class:  (" + myObjectX
      .x(x) + "+" + myObjectY.y + ")=" + zeta.sum(myObjectX.x(x), myObjectY.y))
    println("sum Str from companion object: " + Z.sum("foo", "bar"))
    println("companion class:  " + zeta.fb)
    println("companion object: " + Z.fb)
    println()
    case class Person(firstname: String, lastname: String, birthYear: Int) // case class: magic getters (attributes are val)
    val mp = Person("John", "Doe", 2000) // case class can ignore new
    println(mp.firstname + " " + mp.lastname + ", " + mp.birthYear + ".")
    val fp = mp.copy(firstname = "Jane")
    println(fp.firstname + " " + fp.lastname + ", " + fp.birthYear + ".")
    val personCreator: (String, String, Int) => Person = Person.apply _
    val bp = personCreator("Baby", "Doe", 2018)
    println(bp.firstname + " " + bp.lastname + ", " + bp.birthYear + ".")
  } // def work()

  def countdown(ticks: Int, delay: Int, callback: () => Unit) { // Unit == void
    var counter = 0
    while (counter < ticks) {
      callback()
      counter += 1
      Thread sleep delay
    }
  } // def countdown(ticks: Int, delay: Int, callback: () => Unit)

  def printFunction() {
    println("printing function")
  } // def printFunction()

  def evolvedSwitch(x: Any): String =
    x match { // match pattern evolve switch statement
      case 1     => "OK, one."
      case "abc" => "OK, abc."
    case List(_, 5, _) => // [_ wildcard for match any element]
        "OK, list with three element whose central element is 5."
      case _ => "Unknown"
    } // def evolvedSwitch(x: Any): String

} // object MainObject
