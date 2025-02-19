# Implicit ExecutionContext and Unexpected Behavior in Scala Futures

This example demonstrates a potential issue when using implicit `ExecutionContext.Implicits.global` in Scala's `Future` operations. The global execution context, while convenient, can mask subtle problems related to thread management and asynchronous task completion. 

The problem is that the main thread may exit before some long-running futures finish, leading to unexpected results and potential resource leaks. 