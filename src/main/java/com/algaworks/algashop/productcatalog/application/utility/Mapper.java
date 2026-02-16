package com.algaworks.algashop.productcatalog.application.utility;

public interface Mapper {
    <T> T convert(Object o, Class<T> destinationClass);
}
