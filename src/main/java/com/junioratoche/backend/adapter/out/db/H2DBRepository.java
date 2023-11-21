package com.junioratoche.backend.adapter.out.db;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.junioratoche.backend.adapter.out.db.entity.PriceEntity;
import com.junioratoche.backend.adapter.out.db.mapper.PriceEntityMapper;
import com.junioratoche.backend.domain.Price;
import com.junioratoche.backend.port.out.db.EntityRepositoryOutputPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class H2DBRepository implements EntityRepositoryOutputPort {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private PriceEntityMapper priceEntityMapper;

    @Override
    public List<Price> getAll() {
        TypedQuery<PriceEntity> query = entityManager.createQuery("SELECT p FROM PriceEntity p", PriceEntity.class);
        List<PriceEntity> priceEntityList = query.getResultList();
        return priceEntityList.stream()
                .map(priceEntityMapper::priceEntityToPrice)
                .collect(Collectors.toList());
    }

    @Override
    public Price getPriceByBrandAndProductInApplicationDate(Date applicationDate, int productId, int brandId) {
        TypedQuery<PriceEntity> query = entityManager.createQuery(
            "SELECT p FROM PriceEntity p " +
            "WHERE p.startDate <= :applicationDate " +
            "AND p.endDate >= :applicationDate " +
            "AND p.productId = :productId " +
            "AND p.brand.id = :brandId", PriceEntity.class
        );
        query.setParameter("applicationDate", applicationDate);
        query.setParameter("productId", productId);
        query.setParameter("brandId", brandId);

        List<PriceEntity> resultList = query.getResultList();
        return resultList.isEmpty() ? null : priceEntityMapper.priceEntityToPrice(resultList.get(0));
    }
}