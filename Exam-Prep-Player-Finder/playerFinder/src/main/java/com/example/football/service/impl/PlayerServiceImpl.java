package com.example.football.service.impl;

import com.example.football.models.dto.ExportPlayerDto;
import com.example.football.models.dto.xml.ImportPlayerDto;
import com.example.football.models.dto.xml.ImportPlayerWrapper;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.example.football.Paths.ImportUrls.IMPORT_PLAYER_URL;
import static com.example.football.util.OutputMessage.INVALID_PLAYER;
import static com.example.football.util.OutputMessage.SUCCESSFULLY_IMPORTED_PLAYER;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final TownService townService;
    private final StatService statService;
    private final TeamService teamService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, TownService townService, StatService statService, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.townService = townService;

        this.statService = statService;
        this.teamService = teamService;
    }


    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(IMPORT_PLAYER_URL));
    }

    @Override
    public String importPlayers() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        ValidationUtil validator = new ValidationUtilImpl();
        File file = new File(IMPORT_PLAYER_URL);
        JAXBContext context = JAXBContext.newInstance(ImportPlayerWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportPlayerWrapper wrapper  = (ImportPlayerWrapper) unmarshaller.unmarshal(file);
        List<ImportPlayerDto> dtos = wrapper.getPlayers();
        for (ImportPlayerDto dto : dtos) {
                if (playerRepository.findByEmail(dto.getEmail()) != null || !validator.validate(dto)){
                    sb.append(INVALID_PLAYER)
                            .append(System.lineSeparator());
                    continue;
                }
            Player player = modelMapper.map(dto, Player.class);
            Town town = townService.getByName(dto.getTown().getName());
            Team team = teamService.getByName(dto.getTeam().getName());
            Stat stat = statService.getById(dto.getStat().getId());
            player.setStat(stat);
            player.setTeam(team);
            player.setTown(town);
            sb.append(String.format(SUCCESSFULLY_IMPORTED_PLAYER,player.getFirstName(),player.getLastName(),player.getPosition()))
                    .append(System.lineSeparator());
            playerRepository.saveAndFlush(player);
        }
        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb = new StringBuilder();
        Set<ExportPlayerDto> dtos = playerRepository.findAllWithBirthdayIn(LocalDate.of(1995, 01, 01), LocalDate.of(2003, 01, 01));
        for (ExportPlayerDto dto : dtos) {
            sb.append(dto.toString())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
