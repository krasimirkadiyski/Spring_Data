package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmlDto.ImportOfferDto;
import softuni.exam.models.dto.xmlDto.ImportOfferWrapper;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.entity.Agent;
import softuni.exam.repository.entity.Apartment;
import softuni.exam.repository.entity.Offer;
import softuni.exam.service.AgentService;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private  final AgentService agentService;
    private final ApartmentService apartmentService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, AgentService agentService, ApartmentService apartmentService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.agentService = agentService;
        this.apartmentService = apartmentService;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/xml/offers.xml"));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        ValidationUtil validator = new ValidationUtilImpl();
        File file = new File("src/main/resources/files/xml/offers.xml");
        JAXBContext context = JAXBContext.newInstance(ImportOfferWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportOfferWrapper offerWrapper = (ImportOfferWrapper) unmarshaller.unmarshal(file);
        List<ImportOfferDto> offerDtos = offerWrapper.getOffers();
        for (ImportOfferDto dto : offerDtos) {
            if (validator.validate(dto)){
                sb.append(String.format("Successfully imported offer %.2f",dto.getPrice()))
                        .append(System.lineSeparator());
                Offer offer = modelMapper.map(dto, Offer.class);
                Apartment apartment = apartmentService.getById(dto.getApartment().getId());
                Agent agent = agentService.getByFirstName(dto.getAgent().getName());
                offer.setAgent(agent);
                offer.setApartment(apartment);
                offerRepository.saveAndFlush(offer);
            }else{
                sb.append("Invalid offer")
                        .append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    @Override
    public String exportOffers() {
        StringBuilder sb = new StringBuilder();
        Set<Offer> offers = offerRepository.findAllWithTreeRoomsApartments();
        for (Offer offer : offers) {
            sb.append(String.format("""
                    "Agent %s %s with offer №%d:
                    -Apartment area: %.2f
                    --Town: %s
                    ---Price: %.2f$"""
                            ,offer.getAgent().getFirstName(),offer.getAgent().getLastName(),offer.getId(), offer.getApartment().getArea(),offer.getApartment().getTown().getTownName(),offer.getPrice()))
                    .append(System.lineSeparator());

        }
        return sb.toString();
    }


}
