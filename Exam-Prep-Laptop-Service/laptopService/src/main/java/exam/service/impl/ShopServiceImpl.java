package exam.service.impl;

import exam.model.dtos.ImportShopDto;
import exam.model.dtos.ImportShopWrapper;
import exam.model.entities.Shop;
import exam.model.entities.Town;
import exam.repository.ShopRepository;
import exam.service.ShopService;
import exam.service.TownService;
import exam.util.ValidChecker;
import exam.util.ValidCheckerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static exam.util.Paths.IMPORT_SHOPS_PATH;
import static exam.util.outputMessages.INVALID_SHOP;
import static exam.util.outputMessages.SUCCESSFULLY_IMPORTED_SHOP;

@Service
public class ShopServiceImpl implements ShopService {
    private  final ShopRepository shopRepository;
    private  final ModelMapper mapper;
    private  final TownService townService;

    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper mapper, TownService townService) {
        this.shopRepository = shopRepository;
        this.mapper = mapper;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(IMPORT_SHOPS_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ValidChecker validator = new ValidCheckerImpl();
        StringBuilder sb = new StringBuilder();
        File file = new File(IMPORT_SHOPS_PATH);
        JAXBContext context = JAXBContext.newInstance(ImportShopWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportShopWrapper wrapper = (ImportShopWrapper) unmarshaller.unmarshal(file);
        List<ImportShopDto> dtos = wrapper.getShops();
        for (ImportShopDto dto : dtos) {
            if (!validator.validate(dto) || isDuplicated(dto.getName())){
                sb.append(INVALID_SHOP)
                        .append(System.lineSeparator());
                continue;
            }
            Shop shop = mapper.map(dto, Shop.class);
            Town town = townService.getByName(dto.getTown().getName());
            shop.setTown(town);
            this.shopRepository.saveAndFlush(shop);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_SHOP,shop.getName(),shop.getIncome()))
                    .append(System.lineSeparator());

        }
        return sb.toString();
    }

    @Override
    public Boolean isDuplicated(String name) {
       return shopRepository.findByName(name) != null;
    }

    @Override
    public Shop getByName(String name) {
        return this.shopRepository.findByName(name);
    }
}
