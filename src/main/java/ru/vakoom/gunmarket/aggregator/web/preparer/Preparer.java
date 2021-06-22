package ru.vakoom.gunmarket.aggregator.web.preparer;

import ru.vakoom.querybuilder.BasicEntity;

public interface Preparer {
    void prepare(FilterAndPageable filterAndPageable, Class<? extends BasicEntity> entityClass);
}
