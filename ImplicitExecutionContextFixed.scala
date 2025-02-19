```scala
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object ExplicitExecutionContext extends App {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

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

  combinedFuture.onComplete {
    case Success(value) => println(s"Combined result: $value")
    case Failure(exception) => println(s"Error: ${exception.getMessage}")
  }

  // Keep main thread alive until futures complete
  Await.result(combinedFuture, 3.seconds)
  println("Main thread finished")
}
```