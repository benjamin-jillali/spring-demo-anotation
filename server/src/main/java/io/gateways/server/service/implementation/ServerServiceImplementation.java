package io.gateways.server.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.gateways.server.Enumeration.Status;
import io.gateways.server.Repo.ServerRepo;
import io.gateways.server.models.Server;
import io.gateways.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//creates lombok constructor that requires final and notnull to be filled at creation
//serverRepo field will be injected as required
@RequiredArgsConstructor
/*
	indicates the class belongs to the service layer
	This annotation serves as a specialization of @Component, 
	allowing for implementation classes to be autodetected 
	through classpath scanning. 
*/
@Service
/*
 * with @Transactional the entity goes into a managed state which means its updated
 * if transaction succeeds
 * The javax.transaction.Transactional annotation provides the application 
 * the ability to declaratively control transaction boundaries on CDI managed 
 * beans, as well as classes defined as managed beans by the Java EE 
 * specification, at both the class and method level where method 
 * level annotations override those at the class level.
 */
@Transactional
//lombok for printing logs
@Slf4j
public class ServerServiceImplementation implements ServerService{
	
	private final ServerRepo serverRepo;

	@Override
	public Server create(Server server) {
		log.info("Registering new Server: {}", server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepo.save(server);
	}

	@Override
	public Server ping(String ipAddress) throws IOException {
		log.info("Pinging server IP: {}", ipAddress);
		Server server = serverRepo.findByIpAddress(ipAddress);
		//getting the InetAddress of the server
		InetAddress address = InetAddress.getByName(ipAddress);
		//here we set the status of the server if reachable is true with max10000 timeout ping
		//then set status if true up if false down
		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		serverRepo.save(server);
		return server;
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers.");
		//using a page limit to limit the number of servers recieved for a page
		//and convert to list.
		return serverRepo.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server get(long id) {
		log.info("Fetching server by id: {}", id);
		return serverRepo.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating server: {}", server);
		//jpa knows its an update attempt so will do it if id exists otherwise create a new one
		return serverRepo.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by ID: {}", id);
		serverRepo.deleteById(id);
		return Boolean.TRUE;		
	}
	
	private String setServerImageUrl() {
		String[] imageNames = {"server1.png", "server2.png" ,"server3.png" ,"server4.png"};
		//this will get uri string for an image from image list chosen at random .path sets the path to get for the context
		//4 is max so it will stay from 0 - 3
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();

	}

}




