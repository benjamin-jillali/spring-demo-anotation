package io.gateways.server.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gateways.server.Enumeration.Status;
import io.gateways.server.models.Response;
import io.gateways.server.models.Server;
import io.gateways.server.service.implementation.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;

//tells the program this is a rest controller
@RestController
//maps web requests onto methods
@RequestMapping(path = "/server")
//generates constructor with required args
@RequiredArgsConstructor
public class ServerResourceController {
	// need to create dependency injection
	private final ServerServiceImplementation serverService;

	// puts get mapping for /server/list
	@GetMapping(path = "/list")
	/*
	 * returns a response first set response entity then call response builder add
	 * different components and message return response.
	 */
	private ResponseEntity<Response> getServers() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		
		  return ResponseEntity.ok( Response.builder() .timeStamp(LocalDateTime.now())
		  .data(Map.of("servers", serverService.list(20)))
		  .message("Server retrieved.") .status(HttpStatus.OK)
		  .statusCode(HttpStatus.OK.value()) .build() );
		 

	}

	// puts get server for /server/ping{address}
	@GetMapping("/ping/{ipAddress}")
	private ResponseEntity<Response> getServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
		// creates server from address
		Server server = serverService.ping(ipAddress);
		return ResponseEntity.ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("server", server))
				// checks if server status is up or down and sends message accordingly
				.message(server.getStatus() == Status.SERVER_UP ? "Ping successful" : "Ping failed")
				.status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

	}

	// saves a server @RequestBody bounds the field to the web request in this case
	// server
	// @Valid checks if the field validity checks are positive in this case if
	// server is a valid server object
	@PostMapping("/save")
	private ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
		return ResponseEntity.ok(Response.builder().timeStamp(LocalDateTime.now())
				// creates the server and puts it in data
				.data(Map.of("server", serverService.create(server))).message("Server created").status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value()).build());

	}

	// gets server by id after recieving id from path variable
	@GetMapping("/get/{id}")
	private ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
		return ResponseEntity
				.ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("server", serverService.get(id)))
						// checks if server status is up or down and sends message accordingly
						.message("Server retrieved").status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());

	}

	// deletes server from id retrieved from path variable
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
		return ResponseEntity
				.ok(Response.builder().timeStamp(LocalDateTime.now()).data(Map.of("deleted", serverService.delete(id)))
						.message("Server deleted").status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
	}

	/*
	 * Method to get the bytes of an image from path path is declared in get mapping
	 * since we are talking about 2 values(path, produces) produces tells the type
	 * the method should produces in response the browser by default will send the
	 * get request when we ask for server image
	 */
	@GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	private byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
		// System.getProperty("user.home") usees a local system variable to find the
		// username
		// return Files.readAllBytes(Paths.get(System.getProperty("user.home") +
		// "Downloads/images/" + fileName));
		// retrieves an image from the uri and returns the file in byte form
		return Files.readAllBytes(Paths.get("/mirror/part1/spring_angular/spring/server/" + fileName));
	}

}
