import java.util.Date // Java Library
import java.text.SimpleDateFormat // Java Library
import mypackage.{MyClassX, MyClassY, Z} //  mypackage._ == mypackage.*

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
    val milliseconds = 250 // val defines a fixed value
    var times = 3 // var defines a variable
    times += 2
    countdown(times, milliseconds, printFunction)
    countdown(times, milliseconds, () => println("printing anonymous function"))
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

} // object MainObject
