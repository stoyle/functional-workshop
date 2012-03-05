package no.knowit.java_functional.order;

import java.util.Collection;
import java.util.List;

public interface OrderService {

	Collection<Order> createOrderAlternatives(List<TrainJourney> itineraries);
}
