package io.gateways.server.service;

import java.io.IOException;
import java.util.Collection;

import io.gateways.server.models.Server;

//defines the features and functionalities the application will have

public interface ServerService {
	//creates a server
	Server create(Server server);
	//will look for server from ipAddress and return the ping
	Server ping(String ipAddress) throws IOException;
	//gets servers but limits number of servers per list eg list(10) limits 10 servers
	Collection<Server> list(int limit);
	Server get(long id);
	Server update(Server server);
	Boolean delete(Long id);

}
