package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsonDto.ImportAgentDto;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.repository.entity.Agent;
import softuni.exam.repository.entity.Town;
import softuni.exam.service.AgentService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;

    private final TownService townService;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository, TownService townService, ModelMapper modelMapper, Gson gson) {
        this.agentRepository = agentRepository;

        this.townService = townService;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }



    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(Path.of("src/main/resources/files/json/agents.json"));
    }


    @Override
    public String importAgents() throws IOException {
        ValidationUtil validator = new ValidationUtilImpl();
        StringBuilder sb = new StringBuilder();
        ImportAgentDto[] dtos = gson.fromJson(readAgentsFromFile(), ImportAgentDto[].class);

        for (ImportAgentDto dto : dtos) {
            if (agentRepository.findByFirstName(dto.getFirstName()) != null){
                continue;
            }
            if (validator.validate(dto)) {
                Agent agent = modelMapper.map(dto, Agent.class);
                Town town = townService.getByName(dto.getTown());
                agent.setTown(town);
                agentRepository.saveAndFlush(agent);
                sb.append(String.format("Successfully imported agent - %s %s",dto.getFirstName(),dto.getLastName()))
                        .append(System.lineSeparator());
            }else {
                sb.append("Invalid agent")
                        .append(System.lineSeparator());
            }
        }


        return sb.toString();

    }

    @Override
    public Agent getByFirstName(String name) {
       return agentRepository.findByFirstName(name);
    }



}
