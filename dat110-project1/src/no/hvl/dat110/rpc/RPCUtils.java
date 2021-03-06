package no.hvl.dat110.rpc;

public class RPCUtils {

	// Utility methods for marshalling and unmarshalling of parameters and return
	// values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the
	// RPC message syntax [rpcid,parameter/return value]

	public static byte[] marshallString(byte rpcid, String str) {

		byte[] strByte = str.getBytes();

		byte[] encoded = new byte[strByte.length + 1];

		encoded[0] = rpcid;

		for (int i = 1; i <= strByte.length; i++) {
			encoded[i] = strByte[i - 1];
		}

		// TODO: marshall RPC identifier and string into byte array

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		byte[] dataWithOutRPCId = new byte[data.length - 1];

		for (int i = 0; i < dataWithOutRPCId.length; i++) {
			dataWithOutRPCId[i] = data[i + 1];
		}

		String decoded = new String(dataWithOutRPCId);

		// TODO: unmarshall String contained in data into decoded

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];
		encoded[0] = rpcid;
		// TODO: marshall RPC identifier in case of void type

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded = new byte[5];

		// TODO: marshall RPC identifier and string into byte array
		encoded[0] = rpcid;

		int j = 24; 
		for (int i = 1; i < encoded.length; i++) {
			encoded[i] = (byte)(x >> j);
			j -= 8;
		}

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}
		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded = 0;
		// TODO: unmarshall integer contained in data
		int j = 24;
		for (int i = 1; i < data.length; i++) {
			decoded += ((data[i] & 0xFF) << j);
			j -= 8;
		}

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}
		return decoded;

	}
}
