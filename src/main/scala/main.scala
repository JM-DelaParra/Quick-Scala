import java.util.Date // Java Library
import java.text.SimpleDateFormat // Java Library

object MainObject {

  def main(args: Array[String]) {
    var sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy")
    var date = sdf format new Date // sdf format new Date == sdf.format(new Date);
    println(">>>>>>>>>>>>>>> [ " + date + " ] <<<<<<<<<<<<<<<\n")
    println("Hello Scala\n")
    work()
    println("\n-------------------------------------------------------")
  } // def main(args: Array[String])

  def work() {
    countdown(5, 250, printFunction)
    countdown(5, 250, () => println("printing anonymous function"))
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
