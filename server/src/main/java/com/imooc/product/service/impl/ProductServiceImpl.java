package com.imooc.product.service.impl;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.enums.ProductStatusEnum;
import com.imooc.product.repository.ProductInfoRepository;
import com.imooc.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

//    @Override
//    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
//        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
//
//        //发送mq消息
//        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
//            ProductInfoOutput output = new ProductInfoOutput();
//            BeanUtils.copyProperties(e, output);
//            return output;
//        }).collect(Collectors.toList());
//        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
//
//    }
}