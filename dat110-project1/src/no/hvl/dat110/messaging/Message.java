package no.hvl.dat110.messaging;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload; // TODO: check for length within boundary
		if (payload.length > MessageConfig.SEGMENTSIZE) {
			throw new ArrayIndexOutOfBoundsException("Message size cannot exceed 128 byte");
		}
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

		for (int i = 0; i < payload.length; i++) {
			encoded[i+1] = payload[i];
		}
		
//		int i = 1;
//
//		while (i < payload.length) {
//			if (i <= payload.length) {
//				encoded[i] = payload[i - 1];
//			} else {
//				encoded[i] = (byte) i;
//			}
//			i++;
//		}

		/*
		 * if (true) throw new UnsupportedOperationException(TODO.method());
		 */

		return encoded;

	}

	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate the data contained in the received byte array and store it
		// in the payload of this message
		int size = received[0];
		byte[] decapsulate = new byte[size];

		for (int i = 0; i < size; i++) {
			decapsulate[i] = received[i+1];
		}
		payload = decapsulate;
		// throw new UnsupportedOperationException(TODO.method());

	}
}
