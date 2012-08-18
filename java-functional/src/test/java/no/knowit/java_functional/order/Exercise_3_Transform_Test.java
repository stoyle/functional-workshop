package no.knowit.java_functional.order;

import static fj.data.List.iterableList;
import fj.F;
import fj.control.parallel.Strategy;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.OrderingComparisons.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.*;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import ch.lambdaj.function.matcher.LambdaJMatcher;

public class Exercise_3_Transform_Test {

	/**
	 * Exercise 3: Create orders from train journeys. Use the createOrder
	 * method below to convert individual journeys, to simulate work.
	 * 
	 * Hint: Check Functional Java imports at the top of the source file.
	 * Solve this using Functional Java, not regular Java for loops etc.
	 */
	public Collection<Order> createOrderAlternatives(List<TrainJourney> itineraries) {
		return Collections.emptyList();
	}

	/**
	 * Bonus: Transform train journeys to orders in parallel, to reduce running time.
	 */
	public Collection<Order> createOrdersParallel(List<TrainJourney> itineraries) {
		return createOrderAlternatives(itineraries);
	}

	/**
	 * Method to convert a single train journey to order, simulating
	 * time-consuming work.
	 */
	private Order createOrder(TrainJourney journey) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		return new Order(journey, 123.0);
	}

	/**
	 * Plain Java for-loop solution, for reference
	 */
	public Collection<Order> createOrdersLoop(List<TrainJourney> itineraries) {
		List<Order> alternatives = new LinkedList<Order>();
		for (TrainJourney trainJourney : itineraries) {
			alternatives.add(createOrder(trainJourney));
		}
		return alternatives;
	}

	/*
	 * Tests below here
	 */

	@Test
    @Ignore
	public void convert_trainjourney_to_order() {
		List<TrainJourney> itineraries = createItineraries("OSLO S", "BERGEN", new DateTime());

		Collection<Order> orderAlternatives = createOrderAlternatives(itineraries);

		assertThat(orderAlternatives.size(), is(equalTo(5)));
		assertThat("Order missing price", orderAlternatives, everyItem(hasPrice()));
	}

	@Test
    @Ignore
	public void convert_trainjourney_to_order_loop() {
		List<TrainJourney> itineraries = createItineraries("OSLO S", "BERGEN", new DateTime());

		Collection<Order> orderAlternatives = createOrdersLoop(itineraries);

		assertThat(orderAlternatives.size(), is(equalTo(5)));
		assertThat("Order missing price", orderAlternatives, everyItem(hasPrice()));
	}

	@Test
    @Ignore
	public void create_orders_within_timelimit() {
		List<TrainJourney> itineraries = createItineraries("OSLO S", "BERGEN", new DateTime());

		long startTime = System.currentTimeMillis();
		Collection<Order> orderAlternatives = createOrdersParallel(itineraries);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;

		assertThat(orderAlternatives.size(), is(equalTo(5)));
		assertThat("Order missing price", orderAlternatives, everyItem(hasPrice()));
		assertThat("Create order alternatives too fast", duration, is(greaterThan(200L)));
		assertThat("Create order alternatives took too long", duration, is(lessThan(600L)));
	}

	private LambdaJMatcher<Order> hasPrice() {
		return having(on(Order.class).getPrice(), greaterThan(0.0));
	}

	private List<TrainJourney> createItineraries(String from, String to, DateTime departureTime) {
		List<TrainJourney> itineraries = new LinkedList<TrainJourney>();
		for (int i = 0; i < 5; i++) {
			itineraries.add(new TrainJourney(from, to, departureTime, departureTime.plusHours(2)));
			departureTime = departureTime.plusHours(1);
		}
		return itineraries;
	}

}
