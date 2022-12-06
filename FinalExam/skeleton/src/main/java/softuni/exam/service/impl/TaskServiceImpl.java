package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ExportDto;
import softuni.exam.models.dto.ImportTaskWrapperDto;
import softuni.exam.models.dto.ImportTaskXmlDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.MechanicService;
import softuni.exam.service.PartService;
import softuni.exam.service.TaskService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.exam.util.OutputMessages.INVALID_TASK;
import static softuni.exam.util.OutputMessages.SUCCESSFULLY_IMPORTED_TASK;
import static softuni.exam.util.Paths.TASKS_XML;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final MechanicService mechanicService;
    private final PartService partService;
    private final CarService carService;
    private final ModelMapper mapper;

    public TaskServiceImpl(TaskRepository taskRepository, MechanicService mechanicService, PartService partService, CarService carService, ModelMapper mapper) {
        this.taskRepository = taskRepository;
        this.mechanicService = mechanicService;
        this.partService = partService;
        this.carService = carService;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASKS_XML));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        ValidationUtil validator = new ValidationUtilImpl();
        File file = new File(TASKS_XML);
        JAXBContext context = JAXBContext.newInstance(ImportTaskWrapperDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportTaskWrapperDto wrapper = (ImportTaskWrapperDto) unmarshaller.unmarshal(file);
        List<ImportTaskXmlDto> dtos = wrapper.getTasks();

        for (ImportTaskXmlDto dto : dtos) {
            if (!validator.validate(dto) || mechanicService.getByFirstName(dto.getMechanic().getFirstName()) == null){
                sb.append(INVALID_TASK)
                        .append(System.lineSeparator());
                continue;
            }
            Task task = mapper.map(dto, Task.class);
            Car car = carService.getById(dto.getCar().getId());
            Part part = partService.getById(dto.getPart().getId());
            Mechanic mechanic = mechanicService.getByFirstName(dto.getMechanic().getFirstName());
            task.setCar(car);
            task.setMechanic(mechanic);
            task.setPart(part);

            this.taskRepository.saveAndFlush(task);

            sb.append(String.format(SUCCESSFULLY_IMPORTED_TASK,task.getPrice()))
                    .append(System.lineSeparator());

        }
        return sb.toString();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        StringBuilder sb = new StringBuilder();
        List<Task> tasks = taskRepository.getMostExpensiveTasks();
        LinkedList<ExportDto> dtos = tasks.stream().map(t -> mapper.map(t, ExportDto.class)).collect(Collectors.toCollection(LinkedList::new));
        for (ExportDto dto : dtos) {
            sb.append(dto)
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
