package ru.vakoom.gunmarket.aggregator.web.preparer;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.vakoom.gunmarket.commondatalayer.model.Product;
import ru.vakoom.querybuilder.BasicEntity;

@Component
public class SortPreparer implements Preparer {
    @Override
    public void prepare(FilterAndPageable filterAndPageable, Class<? extends BasicEntity> entityClass) {
        Pageable pageable = filterAndPageable.getPageable();
        String sortParam = filterAndPageable.getFilter().remove("sort");
        if (sortParam == null) {
            filterAndPageable.setPageable(getDefaultSortingProperty(pageable, entityClass));
        } else {
            String sortProp = sortParam.substring(0, sortParam.indexOf(","));
            Sort sort = Sort.by(pageable.getSort().getOrderFor(sortProp));
            filterAndPageable.setPageable(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));
        }
    }

    private static PageRequest getDefaultSortingProperty(Pageable pageable, Class<? extends BasicEntity> entityClass) {
        String sortingProperty = "";
        Sort.Direction dir = Sort.Direction.DESC;
        if (entityClass.isAssignableFrom(Product.class)) {
            // Temp solution WHILE rating not implemented
            // sortingProperty = Rating.RATING_VALUE_SORT;
            sortingProperty = Product.PRODUCT_MIN_PRICE;
        }
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(dir, sortingProperty));
    }
}
