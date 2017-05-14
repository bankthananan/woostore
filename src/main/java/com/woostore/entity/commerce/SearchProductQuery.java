package com.woostore.entity.commerce;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class SearchProductQuery {

    String text;

    @NonNull
    boolean name;

    @NonNull
    boolean description;

    @NonNull
    double firstPrice;

    @NonNull
    double secondPrice;

    public boolean isPrice() {
        return (firstPrice == 0 && secondPrice == 0) ? false : secondPrice >= firstPrice;
    }

}
