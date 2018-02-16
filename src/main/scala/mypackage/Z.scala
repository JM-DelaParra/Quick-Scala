package mypackage

class Z() { // Companion class
  import Z._ // import companion object components
  override def toString = ("class Z") // overwrite parent class method
  def sum(a: Int, b: Int) = a + b // public by default
  private val foo = "foo"
  def fb = foo + "-" + bar
} // class Z()

object Z { // (Named same as class) Companion object: not receive arguments, its like a singleton and has the static behavior from Java
  val z = new Z() // instance of companion class
  def sum(a: String, b: String) = a + " " + b
  private val bar = "bar"
  def fb = z.foo + "-" + bar
} // object Z
