package com.example.jsonAndXmlProcessing.services.seed;

import com.example.jsonAndXmlProcessing.DTOsJSON.category.ImportCategoryDto;
import com.example.jsonAndXmlProcessing.DTOsJSON.product.ImportProductDto;
import com.example.jsonAndXmlProcessing.DTOsXML.category.CategoryXmlImportWrapper;
import com.example.jsonAndXmlProcessing.DTOsXML.product.ProductXmlImportWrapper;
import com.example.jsonAndXmlProcessing.DTOsXML.user.UserXmlImportDto;
import com.example.jsonAndXmlProcessing.DTOsXML.user.UserXmlImportWrapper;
import com.example.jsonAndXmlProcessing.entities.Category;
import com.example.jsonAndXmlProcessing.entities.Product;
import com.example.jsonAndXmlProcessing.entities.User;
import com.example.jsonAndXmlProcessing.repos.CategoryRepository;
import com.example.jsonAndXmlProcessing.repos.ProductRepository;
import com.example.jsonAndXmlProcessing.repos.UserRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.jsonAndXmlProcessing.constants.Paths.*;
import static com.example.jsonAndXmlProcessing.constants.Utils.GSON;
import static com.example.jsonAndXmlProcessing.constants.Utils.MAPPER;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUser() throws IOException, JAXBException {
        if (userRepository.count() == 0) {
            FileReader fileReader = new FileReader(PATH_OF_USER_XML.toFile());
            JAXBContext context = JAXBContext.newInstance(UserXmlImportWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserXmlImportWrapper usersDto = (UserXmlImportWrapper) unmarshaller.unmarshal(fileReader);
            List<User> users = usersDto.getUsers().stream().
                    map(u -> MAPPER.map(u, User.class)).toList();
            System.out.println();


            //JSON --->
//            final FileReader fileReader = new FileReader(PATH_OF_USER_JSON.toFile());
//
//            List<User> users = Arrays.stream(GSON.fromJson(fileReader, ImportUserDto[].class))
//                    .map(importUserDto -> MAPPER.map(importUserDto, User.class))
//                    .toList();
            userRepository.saveAllAndFlush(users);
            fileReader.close();
        }

    }

    @Override
    public void seedCategory() throws IOException, JAXBException {
        if (categoryRepository.count() == 0) {

            FileReader fileReader = new FileReader(PATH_OF_CATEGORY_XML.toFile());
            JAXBContext context = JAXBContext.newInstance(CategoryXmlImportWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CategoryXmlImportWrapper categoryXmlImportWrapper = (CategoryXmlImportWrapper) unmarshaller.unmarshal(fileReader);
            List<Category> categories = categoryXmlImportWrapper.getCategories().stream()
                    .map(c -> MAPPER.map(c, Category.class)).toList();
            //Json --->
//            final FileReader fileReader = new FileReader(PATH_OF_CATEGORY_JSON.toFile());
//            List<Category> categories = Arrays.stream(GSON.fromJson(fileReader, ImportCategoryDto[].class))
//                    .map(importCategoryDto -> MAPPER.map(importCategoryDto, Category.class))
//                    .collect(Collectors.toList());

            categoryRepository.saveAllAndFlush(categories);
            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        if (productRepository.count() == 0) {
            FileReader fileReader = new FileReader(PATH_OF_PRODUCTS_XML.toFile());
            JAXBContext context = JAXBContext.newInstance(ProductXmlImportWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProductXmlImportWrapper productXmlImportWrapper = (ProductXmlImportWrapper) unmarshaller.unmarshal(fileReader);
            List<Product> products = productXmlImportWrapper.getProducts().stream().map(p -> MAPPER.map(p, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomCategory)
                    .map(this::setRandomBuyer)
                    .toList();

            //JSON ---->
//            FileReader fileReader = new FileReader(PATH_OF_PRODUCT_JSON.toFile());
//            List<Product> products = Arrays.stream(GSON.fromJson(fileReader, ImportProductDto[].class))
//                    .map(importProductDto -> MAPPER.map(importProductDto, Product.class))
//                    .map(this::setRandomSeller)
//                    .map(this::setRandomCategory)
//                    .map(this::setRandomBuyer)
//                    .toList();
            productRepository.saveAllAndFlush(products);
            fileReader.close();
        }
    }

    public Product setRandomCategory(Product product) {
        Random random = new Random();
        long high = categoryRepository.count();
        long numberOfCategories = random.nextLong(high);
        Set<Category> categories = new HashSet<>();
        for (int i = 1; i <= numberOfCategories; i++) {
            Category randomCategory = categoryRepository.getRandomCategory();
            categories.add(randomCategory);;
        }
        product.setCategories(categories);

        return product;
    }
    public Product setRandomSeller(Product product){
        User randomUser = userRepository.getRandomUser();
        product.setSeller(randomUser);
        return product;
    }

    public Product setRandomBuyer(Product product) {
        if(product.getPrice().compareTo(BigDecimal.valueOf(700)) > 0) {
            User buyer = userRepository.getRandomUser();

            while (buyer.equals(product.getSeller())) {
                buyer = userRepository.getRandomUser();
            }
            product.setBuyer(buyer);
        }
        return product;
    }
}
