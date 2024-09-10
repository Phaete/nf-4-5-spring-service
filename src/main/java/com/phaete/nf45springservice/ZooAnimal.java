package com.phaete.nf45springservice;

import java.math.BigDecimal;

public record ZooAnimal(
        String id,
        String name,
        int age,
        BigDecimal purchasePrice
) {
}
