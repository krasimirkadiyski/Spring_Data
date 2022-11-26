package com.example.jsonAndXmlProcessing.services.product;

import com.example.jsonAndXmlProcessing.DTOsJSON.product.ProductPriceAndSellerFullNameDto;
import com.example.jsonAndXmlProcessing.DTOsXML.product.ProductsInRangeWrapper;
import com.example.jsonAndXmlProcessing.entities.Product;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

public interface ProductService {


   Product getRandomProduct();
   Set<ProductPriceAndSellerFullNameDto> getAllUnSoldProducts(BigDecimal low, BigDecimal high) throws IOException;

  ProductsInRangeWrapper getAllUnSoldProductsXml(BigDecimal low,BigDecimal high) throws JAXBException;
}
