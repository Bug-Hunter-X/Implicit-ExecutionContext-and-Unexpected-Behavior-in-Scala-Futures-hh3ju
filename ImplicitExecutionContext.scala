```scala
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ImplicitExecutionContext extends App {
  val future1 = Future {
    println("Future 1 started")
    Thread.sleep(1000)
    println("Future 1 finished")
    1
  }

  val future2 = Future {
    println("Future 2 started")
    Thread.sleep(1000)
    println("Future 2 finished")
    2
  }

  val combinedFuture = for {
    result1 <- future1
    result2 <- future2
  } yield result1 + result2

  combinedFuture.onComplete(println(_))

  Thread.sleep(2000)
  println("Main thread finished")
}
```