/*
 * Copyright 2013 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package context

import java.util.concurrent.{Executors, ArrayBlockingQueue, TimeUnit, ThreadPoolExecutor}
import scala.concurrent.ExecutionContext

/**
 * Execution contexts execute tasks submitted to them, and you can think of execution contexts as thread pools.
 * They are essential for the future method because they handle how and when the asynchronous computation is executed.
 * <pre/>
 * We are defining here one ExecutionContext which can be used by other classes by simply defining it as an implicit variable:
 * <pre><code>
 * implicit val executionContext = StormpathExecutionContext.executionContext
 * </code></pre>
 *
 */
object StormpathExecutionContext {

  private val corePoolSize: Int = 32
  private val maximumPoolSize: Int = 64
  private val maxBlockingBacklog: Int = 100
  private val keepAlive: Long = 120

  private val service = new ThreadPoolExecutor(corePoolSize,
    maximumPoolSize,
    keepAlive, TimeUnit.SECONDS,
    new ArrayBlockingQueue[Runnable](
      maxBlockingBacklog,
      false /* we don't care about FIFO */
    ), /* workQueue */
    Executors.defaultThreadFactory())

  // allow the pool to shrink below corePoolSize after keepAliveTime
  service.allowCoreThreadTimeOut(true)

  val executionContext = ExecutionContext.fromExecutorService(service)

}
