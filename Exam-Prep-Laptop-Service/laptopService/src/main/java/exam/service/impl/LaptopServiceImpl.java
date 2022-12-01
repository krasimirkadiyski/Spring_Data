package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dtos.ExportLaptopDto;
import exam.model.dtos.ImportLaptopDto;
import exam.model.entities.Laptop;
import exam.model.entities.Shop;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.util.ValidChecker;
import exam.util.ValidCheckerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static exam.util.Paths.IMPORT_LAPTOPS_PATH;
import static exam.util.outputMessages.INVALID_LAPTOP;
import static exam.util.outputMessages.SUCCESSFULLY_IMPORTED_LAPTOP;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;
    private final ModelMapper mapper;
    private  final Gson gson;
    private final ShopService shopService;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ModelMapper mapper, Gson gson, ShopService shopService) {
        this.laptopRepository = laptopRepository;
        this.mapper = mapper;
        this.gson = gson;
        this.shopService = shopService;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(IMPORT_LAPTOPS_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder sb = new StringBuilder();
        ValidChecker validator = new ValidCheckerImpl();
        ImportLaptopDto[] dtos = gson.fromJson(readLaptopsFileContent(), ImportLaptopDto[].class);
        for (ImportLaptopDto dto : dtos) {
            if (!validator.validate(dto) || isDuplicated(dto.getMacAddress())){
                sb.append(INVALID_LAPTOP)
                        .append(System.lineSeparator());
                continue;
            }
            Laptop laptop = mapper.map(dto, Laptop.class);
            Shop shop = shopService.getByName(dto.getShop().getName());
            laptop.setShop(shop);
            this.laptopRepository.saveAndFlush(laptop);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_LAPTOP,laptop.getMacAddress(),laptop.getCpuSpeed(),laptop.getRam(),laptop.getStorage()))
                    .append(System.lineSeparator());

        }
        return sb.toString();
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder sb = new StringBuilder();
        List<Laptop> laptops = this.laptopRepository.getTheBestLaptops();
        for (Laptop laptop : laptops) {
            ExportLaptopDto dto = mapper.map(laptop, ExportLaptopDto.class);
            sb.append(dto)
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Boolean isDuplicated(String macAddress) {
        return laptopRepository.findByMacAddress(macAddress) != null;
    }
}
