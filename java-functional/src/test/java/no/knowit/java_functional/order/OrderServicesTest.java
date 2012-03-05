package no.knowit.java_functional.order;

import static ch.lambdaj.Lambda.*;
import static fj.data.List.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.OrderingComparisons.*;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import ch.lambdaj.function.convert.Converter;

import fj.F;
import fj.P1;
import fj.control.parallel.Strategy;

public class OrderServicesTest implements OrderService {

	/*
	 * Create orders from train journeys using the createOrder method below
	 */
	@Override
	public Collection<Order> createOrderAlternatives(
			List<TrainJourney> itineraries) {
		// return createOrdersLoop(itineraries);
		// return createOrdersLambaJMap(itineraries);
		// return createOrdersMap(itineraries);
		return createOrdersParMap(itineraries);
	}

	/*
	 * Method to simulate time-consuming work
	 */
	private Order createOrder(TrainJourney journey) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		return new Order(journey, 123.0);
	}

	/*
	 * Plain Java
	 */

	private Collection<Order> createOrdersLoop(List<TrainJourney> itineraries) {
		List<Order> alternatives = new LinkedList<Order>();
		for (TrainJourney trainJourney : itineraries) {
			alternatives.add(createOrder(trainJourney));
		}
		return alternatives;
	}

	/*
	 * LambdaJ
	 */

	private Collection<Order> createOrdersLambaJMap(
			List<TrainJourney> itineraries) {
		return convert(itineraries, journeyToOrderConverter);
	}

	Converter<TrainJourney, Order> journeyToOrderConverter = new Converter<TrainJourney, Order>() {
		@Override
		public Order convert(TrainJourney journey) {
			return createOrder(journey);
		}
	};

	/*
	 * Functional Java
	 */

	private Collection<Order> createOrdersMap(List<TrainJourney> itineraries) {
		return iterableList(itineraries).map(journeyToOrder).toCollection();
	}

	private Collection<Order> createOrdersParMap(List<TrainJourney> itineraries) {
		Strategy<Order> s = Strategy.simpleThreadStrategy();
		return s.parMap(journeyToOrder, iterableList(itineraries))._1()
				.toCollection();
	}

	private F<TrainJourney, Order> journeyToOrder = new F<TrainJourney, Order>() {
		@Override
		public Order f(TrainJourney journey) {
			return createOrder(journey);
		}
	};

	/*
	 * Tests below here
	 */

	@Test
	public void testCreateOrders() {
		List<TrainJourney> itineraries = createItineraries("OSLO S", "BERGEN",
				new DateTime());

		long startTime = System.currentTimeMillis();
		Collection<Order> orderAlternatives = createOrderAlternatives(itineraries);
		long endTime = System.currentTimeMillis();
		int duration = (int) (endTime - startTime);

		assertThat(orderAlternatives.size(), is(equalTo(5)));
		assertThat("Create order alternatives took too long", duration,
				is(lessThan(400)));
	}

	private List<TrainJourney> createItineraries(String from, String to,
			DateTime departureTime) {
		List<TrainJourney> itineraries = new LinkedList<TrainJourney>();
		for (int i = 0; i < 5; i++) {
			itineraries.add(new TrainJourney(from, to, departureTime,
					departureTime.plusHours(2)));
			departureTime = departureTime.plusHours(1);
		}
		return itineraries;
	}

}
