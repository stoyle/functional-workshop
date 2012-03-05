package no.knowit.java_functional.order;

public class Order {

	private final TrainJourney trainJourney;

	private final double price;

	public Order(TrainJourney trainJourney, double price) {
		super();
		this.trainJourney = trainJourney;
		this.price = price;
	}

	public TrainJourney getTrainJourney() {
		return trainJourney;
	}

	public double getPrice() {
		return price;
	}

}
