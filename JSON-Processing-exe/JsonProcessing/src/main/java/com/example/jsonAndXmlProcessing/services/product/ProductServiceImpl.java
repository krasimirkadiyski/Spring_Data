package com.example.jsonAndXmlProcessing.services.product;

import com.example.jsonAndXmlProcessing.DTOsJSON.product.ProductPriceAndSellerFullNameDto;
import com.example.jsonAndXmlProcessing.DTOsXML.product.ProductXmlWithSellerDto;
import com.example.jsonAndXmlProcessing.DTOsXML.product.ProductsInRangeWrapper;
import com.example.jsonAndXmlProcessing.entities.Product;
import com.example.jsonAndXmlProcessing.repos.ProductRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jsonAndXmlProcessing.constants.Paths.PATH_OF_OUTPUT_PRODUCTS_IN_RANGE_XML;
import static com.example.jsonAndXmlProcessing.constants.Paths.PATH_OF_OUTPUT_PRODUCTS_WITH_SELLER_AND_PRICE_JSON;
import static com.example.jsonAndXmlProcessing.constants.Utils.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public Product getRandomProduct() {
       return productRepository.getRandomProduct();
    }

    @Override
    public Set<ProductPriceAndSellerFullNameDto> getAllUnSoldProducts(BigDecimal low, BigDecimal high) throws IOException {
        Set<Product> products = productRepository.findAllByBuyerIsNullAndPriceBetweenOrderByPrice(low, high);
        Set<ProductPriceAndSellerFullNameDto> resultSet = products.stream().map(product -> MAPPER.map(product, ProductPriceAndSellerFullNameDto.class))
                .collect(Collectors.toSet());
        writeJsonIntoFile(resultSet,PATH_OF_OUTPUT_PRODUCTS_WITH_SELLER_AND_PRICE_JSON);
        System.out.println();
        return  resultSet;

    }

    @Override
    public ProductsInRangeWrapper getAllUnSoldProductsXml(BigDecimal low, BigDecimal high) throws JAXBException {
        Set<Product> products = productRepository.findAllByBuyerIsNullAndPriceBetweenOrderByPrice(low, high);
        Set<ProductXmlWithSellerDto> productXmlWithSellerDtos = products.stream().map(p -> MAPPER.map(p, ProductXmlWithSellerDto.class))
                .collect(Collectors.toSet());
        ProductsInRangeWrapper productsInRangeWrapper = new ProductsInRangeWrapper(productXmlWithSellerDtos);
        JAXBContext context = JAXBContext.newInstance(ProductsInRangeWrapper.class);
        File file = PATH_OF_OUTPUT_PRODUCTS_IN_RANGE_XML.toFile();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(productsInRangeWrapper,file);

        System.out.println();
        return productsInRangeWrapper;
    }
}
