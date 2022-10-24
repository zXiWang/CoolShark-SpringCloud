package com.xiwang.csmall.stock.service;

import com.xiwang.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IStockService {

    void reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO);
}
