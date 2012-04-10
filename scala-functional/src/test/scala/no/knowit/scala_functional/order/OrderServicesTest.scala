package no.knowit.scala_functional.order

import org.hamcrest.CoreMatchers._
import org.hamcrest.number.OrderingComparisons._
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Test
import org.junit.Assert._
import org.junit.matchers.JUnitMatchers._
import no.knowit.java_functional.order.Order
import no.knowit.java_functional.order.TrainJourney
import org.junit.Ignore

@RunWith(classOf[JUnit4])
class OrderServicesTest {

  /**
   * Exercise 3: Create orders from train journeys. Use the createOrder
   * method below to convert individual journeys, to simulate work.
   */
  def createOrderAlternatives(itineraries: Seq[TrainJourney]): Seq[Order] = Nil

  /**
   * Bonus: Transform train journeys to orders in parallel, to reduce running time.
   */
  def createOrdersParallel(itineraries: Seq[TrainJourney]): Seq[Order] = Nil

  private def createOrder(journey: TrainJourney): Order = {
    try {
      Thread.sleep(200)
    } catch {
      case e: InterruptedException => // Do nothing
    }
    new Order(journey, 123.0)
  }

  @Test
  @Ignore
  def convert_trainjourney_to_order() {
    val itineraries = createItineraries("OSLO S", "BERGEN", new DateTime())

    val orderAlternatives = createOrderAlternatives(itineraries)

    assertThat(orderAlternatives.size, is(equalTo(5)))
    assertTrue("Order missing price", orderAlternatives forall { _.getPrice > 0.0 })
  }

  @Test
  @Ignore
  def create_orders_within_timelimit() {
    val itineraries = createItineraries("OSLO S", "BERGEN", new DateTime());

    val startTime = System.currentTimeMillis();
    val orderAlternatives = createOrdersParallel(itineraries);
    val endTime = System.currentTimeMillis();
    val duration = endTime - startTime;

    assertThat(orderAlternatives.size, is(equalTo(5)));
    assertTrue("Order missing price", orderAlternatives forall { _.getPrice > 0.0 })
    assertTrue("Create order alternatives too fast", duration >= 200);
    assertTrue("Create order alternatives took too long", duration < 800);
  }

  private def createItineraries(from: String, to: String, departureTime: DateTime): Seq[TrainJourney] =
    for (i <- 1 to 5)
      yield new TrainJourney(from, to, departureTime.plusHours(i), departureTime.plusHours(i + 2))

}