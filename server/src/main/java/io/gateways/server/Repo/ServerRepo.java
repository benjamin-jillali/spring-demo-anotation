package io.gateways.server.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gateways.server.models.Server;

//give it our entity and the primary key(ID) type
public interface ServerRepo extends JpaRepository<Server, Long>{
	//tells jpa to select a server and compare if it matches the ipaddress input
	Server findByIpAddress(String ipAddress);

}
