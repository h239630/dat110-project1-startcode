package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload; // TODO: check for length within boundary
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];
		
		// TODO
		// encapulate/encode the payload of this message in the
		// encoded byte array according to message format
		
		encoded[0] = (byte) payload.length;
		
		int i = 1;
		
		while(i < encoded.length) {
			if (i <= payload.length) {
				encoded[i] = payload[i-1];
			} else {
				encoded[i] = (byte) i;
			}
			i++;
		}
		
		/*
		 * if (true) throw new UnsupportedOperationException(TODO.method());
		 */

		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate the data contained in the received byte array and store it 
		// in the payload of this message
		
		payload = new byte[received[0]];
		
		for (int i = 1; i <= received[0]; i++) {
			payload[i-1] = received[i];
		}
		
		
		//throw new UnsupportedOperationException(TODO.method());
		
	}
}
