package no.hvl.dat110.rpc;

import java.io.IOException;
import java.util.HashMap;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private Connection connection;

	// hashmap to register RPC methods which are required to implement RPCImpl

	private HashMap<Integer, RPCImpl> services;

	public RPCServer(int port) {

		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Integer, RPCImpl>();

		// the stop RPC methods is built into the server
		services.put((int) RPCCommon.RPIDSTOP, new RPCServerStopImpl());
	}

	public void run() {
		
		System.out.println("RPC SERVER RUN - Services: " + services.size());
		
		try {
			connection = msgserver.accept();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println("RPC SERVER ACCEPTED");
		
		boolean stop = false;
		
		while (!stop) {
			
			byte[] rpcrequest = connection.receive().getData();
			
			int rpcid = rpcrequest[0];
			
			RPCImpl RPCMethod = services.get(rpcid);
			
			if (RPCMethod != null) {
				Message message = new Message(RPCMethod.invoke(rpcrequest));
				
				connection.send(message);
			}
			
		   	// TODO
			// - receive message containing RPC request
			// - find the identifier for the RPC methods to invoke
			// - lookup the method to be invoked
			// - invoke the method
			// - send back message containing RPC reply
		   
	//		if (true) {
	//		   throw new UnsupportedOperationException(TODO.method());
	//		}
		   
			if (rpcid == RPCCommon.RPIDSTOP) {
			   stop = true;
			}
		}
	
	}

	public void register(int rpcid, RPCImpl impl) {
		services.put(rpcid, impl);
	}

	public void stop() {
		connection.close();
		msgserver.stop();

	}
}
