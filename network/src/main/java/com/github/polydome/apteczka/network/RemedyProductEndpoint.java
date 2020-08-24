package com.github.polydome.apteczka.network;

import com.github.polydome.apteczka.domain.model.Product;
import com.github.polydome.apteczka.domain.service.ProductEndpoint;
import com.github.polydome.apteczka.network.model.RemedyPackaging;
import com.github.polydome.apteczka.network.model.RemedyProduct;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class RemedyProductEndpoint implements ProductEndpoint {
    private final RemedyService remedyService;

    public RemedyProductEndpoint(RemedyService remedyService) {
        this.remedyService = remedyService;
    }

    @Override
    public Maybe<Product> fetchMedicineDetails(final String ean) {
        Maybe<RemedyPackaging> packaging =
                remedyService.getPackaging(ean).cache();

        return packaging.flatMap(new Function<RemedyPackaging, MaybeSource<?>>() {
                @Override
                public MaybeSource<?> apply(RemedyPackaging packaging) {
                    return remedyService.getProduct(packaging.getProductId());
                }
            })
            .zipWith(packaging, new BiFunction<Object, RemedyPackaging, Product>() {
                @Override
                public Product apply(Object product, RemedyPackaging packaging) {
                    return createProduct(packaging, ((RemedyProduct) product));
                }
            });
    }

    private Product createProduct(RemedyPackaging packaging, RemedyProduct product) {
        return Product.builder()
                .commonName(product.getCommonName())
                .form(product.getForm())
                .name(product.getName())
                .packagingSize(packaging.getSize())
                .packagingUnit(packaging.getUnit())
                .potency(product.getPotency())
                .build();
    }
}
