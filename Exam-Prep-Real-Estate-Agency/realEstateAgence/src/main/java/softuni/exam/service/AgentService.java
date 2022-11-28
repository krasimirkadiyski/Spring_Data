package softuni.exam.service;


import softuni.exam.repository.entity.Agent;
import softuni.exam.repository.entity.Town;

import java.io.IOException;
import java.util.Set;

// TODO: Implement all methods

public interface AgentService {

    boolean areImported();

    String readAgentsFromFile() throws IOException;
	
	String importAgents() throws IOException;

    Agent getByFirstName(String name);

}
