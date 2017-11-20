package boopickle.perftests

import java.nio.charset.StandardCharsets

import io.circe._
import io.circe.syntax._
import io.circe.parser._
import io.circe.generic.semiauto._
import org.openjdk.jmh.annotations.Benchmark

abstract class CirceRunner[A](data: A) extends TestRunner[A](data) {
  override def name = "Circe"
}

object CirceRunners {

  def encodeRunner[A](data: A)(implicit p: Encoder[A], u: Decoder[A]): CirceRunner[A] = new CirceRunner[A](data) {
    var testData: A = _

    override def initialize = {
      testData = data
      val res = testData.asJson.noSpaces
      // println(res)
      res.getBytes(StandardCharsets.UTF_8)
    }

    override def run(): Any = {
      testData.asJson.noSpaces
      ()
    }
  }

  def decodeRunner[A](data: A)(implicit p: Encoder[A], u: Decoder[A]): CirceRunner[A] = new CirceRunner[A](data) {
    var testData: A = _
    var s: String   = _

    override def initialize = {
      testData = data
      s = testData.asJson.noSpaces
      s.getBytes(StandardCharsets.UTF_8)
    }

    override def run(): Any = {
      u.decodeJson(parser.parse(s).toOption.get)
      ()
    }
  }
}

trait CirceCoding { self: TestData =>
  private implicit val eventEncoder: Encoder[Event] = deriveEncoder[Event]
  private lazy val eventDecoder = deriveDecoder[Event]

  private lazy val eventJson = event.asJson.noSpaces

  @Benchmark
  def circeEventDecode: Event = {
    val event = eventDecoder.decodeJson(parse(eventJson).right.get).right.get
    event
  }

  @Benchmark
  def circeEventEncode: String = {
    eventEncoder(event).noSpaces
  }
}
