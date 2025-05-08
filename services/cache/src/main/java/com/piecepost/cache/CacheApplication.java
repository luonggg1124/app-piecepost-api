package com.piecepost.cache;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import io.grpc.Server;

@SpringBootApplication
public class CacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner startGrpcServer(Server grpcServer) {
//		return args -> {
//			grpcServer.awaitTermination();
//		};
//	}
}
