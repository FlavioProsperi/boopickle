package external

// removed until Nyaya is compatible with Scala.js 1.0

/*
import boopickle.Default._
import boopickle.{CompositePickler, Pickler}
import nyaya.gen._
import nyaya.prop.Prop
import nyaya.test.PropTest._
import nyaya.test._
import utest._

import scalaz.Equal
import scalaz.std.AllInstances._

object PropTests extends TestSuite {

  def prop[A: Equal: Pickler] =
    Prop.equalSelf[A]("decode.encode = id", a => {
      val b = Pickle.intoBytes(a)
      Unpickle[A].fromBytes(b)
    })

  sealed trait ADT
  object ADT {
    case class A(i: Int)                            extends ADT
    case class B(a: Char, b: Long, c: Option[Char]) extends ADT
    case object C                                   extends ADT
    case class D()                                  extends ADT
    case class R(child: ADT)                        extends ADT
    implicit val equality: Equal[ADT] = Equal.equalA
    implicit val adtCodec             = CompositePickler[ADT]
    adtCodec.addConcreteType[A].addConcreteType[B].addConcreteType[C.type].addConcreteType[D].addConcreteType[R]
  }
  val genADT: Gen[ADT] = {
    import ADT._

    val ga               = Gen.int map A
    val gb               = Gen.apply3(B)(Gen.char, Gen.long, Gen.char.option)
    val gc               = Gen.pure[C.type](C)
    val gd               = Gen.pure(D())
    val gr: Gen[ADT.R]   = Gen.pure(R(A(0)))
    lazy val g: Gen[ADT] = Gen.chooseGen(ga, gb, gc, gd, gr)
    g
  }

  def crazy =
    for {
      a <- Gen.int.list.option
      b <- Gen.char.triple.mapBy(Gen.alphaNumeric.string1)
      c <- Gen.boolean.option either Gen.char.set
      d <- Gen.long.vector
    } yield (a, b, c, d)

  override def tests = TestSuite {
    'boolean - Domain.boolean.mustProve(prop)
    'byte - Domain.byte.mustProve(prop)
    'int - Gen.int.mustSatisfy(prop)
    'long - Gen.long.mustSatisfy(prop)
    'char - Gen.char.mustSatisfy(prop)
    'string - Gen.unicode.string.mustSatisfy(prop)
    'float - Gen.float.mustSatisfy(prop)
    'double - Gen.double.mustSatisfy(prop)
    'short - Gen.short.mustSatisfy(prop)

    'adt - genADT.mustSatisfy(prop)

    'option - Domain.byte.option.mustProve(prop)
    'list - Gen.int.list.mustSatisfy(prop)
    'stream - Gen.int.stream.mustSatisfy(prop)
    'vector - Gen.int.vector.mustSatisfy(prop)
    'set - Gen.int.set.mustSatisfy(prop)
    'map - Gen.int.mapTo(Gen.char).mustSatisfy(prop)
    'either - Gen.int.either(Gen.char).mustSatisfy(prop)
    'pair - Gen.int.pair.mustSatisfy(prop)
    'triple - Gen.int.triple.mustSatisfy(prop)

    'crazy - crazy.mustSatisfy(prop)
  }
}
*/