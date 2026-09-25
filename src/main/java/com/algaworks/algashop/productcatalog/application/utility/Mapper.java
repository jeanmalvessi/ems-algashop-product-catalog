package com.algaworks.algashop.productcatalog.application.utility;

public interface Mapper {
    <T> T convert(Object source, Class<T> destination);
}
