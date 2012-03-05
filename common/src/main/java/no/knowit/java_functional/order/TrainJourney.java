package no.knowit.java_functional.order;

import org.joda.time.DateTime;

public class TrainJourney {

	private final String from, to;

	private final DateTime departureTime;

	private final DateTime arrivalTime;

	public TrainJourney(String from, String to, DateTime departureTime,
			DateTime arrivalTime) {
		super();
		this.from = from;
		this.to = to;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public DateTime getDepartureTime() {
		return departureTime;
	}

	public DateTime getArrivalTime() {
		return arrivalTime;
	}

}
