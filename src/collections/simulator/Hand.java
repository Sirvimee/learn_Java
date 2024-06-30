package collections.simulator;

import java.util.*;
import java.util.stream.Collectors;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
//        Collections.sort(cards);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {

        if (isStraightFlush()) {
            return HandType.STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            return HandType.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return HandType.FULL_HOUSE;
        } else if (isFlush()) {
            return HandType.FLUSH;
        } else if (isStraight()) {
            return HandType.STRAIGHT;
        } else if (isTrips()) {
            return HandType.TRIPS;
        } else if (isTwoPairs()) {
            return HandType.TWO_PAIRS;
        } else if (isOnePair()) {
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

    private boolean isOnePair() {
        Map<Card.CardValue, Long> valueCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        return valueCount.containsValue(2L);
    }

    private boolean isTwoPairs() {
        Map<Card.CardValue, Long> valueCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        return valueCount.values().stream().filter(v -> v == 2L).count() == 2;
    }

    private boolean isTrips() {
        Map<Card.CardValue, Long> valueCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        return valueCount.containsValue(3L);
    }

    private boolean isFullHouse() {
        Map<Card.CardValue, Long> valueCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        return valueCount.containsValue(3L) && valueCount.containsValue(2L);
    }

    private boolean isFourOfAKind() {
        Map<Card.CardValue, Long> valueCount = cards.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        return valueCount.containsValue(4L);
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    private boolean isStraight() {
        List<Integer> values = cards.stream()
                .map(card -> card.getValue().ordinal())
                .sorted()
                .toList();

        if (values.size() == 4) {
            return false;
        }

        if (new HashSet<>(values).containsAll(Arrays.asList(Card.CardValue.A.ordinal(),
                Card.CardValue.S2.ordinal(),
                Card.CardValue.S3.ordinal(),
                Card.CardValue.S4.ordinal(),
                Card.CardValue.S5.ordinal()))) {
            return true;
        }

        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i + 1) - values.get(i) != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isFlush() {
        return cards.stream().allMatch(card -> card.getSuit() == cards.getFirst().getSuit());
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {
        return 0;
    }
}
