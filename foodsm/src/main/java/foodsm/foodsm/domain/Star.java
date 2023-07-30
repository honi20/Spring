package foodsm.foodsm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Star {
    ONE_STAR("1"),
    TWO_STAR("2"),
    THREE_STAR("3"),
    FOUR_STAR("4"),
    FIVE_STAR("5");

    private String value;
}
