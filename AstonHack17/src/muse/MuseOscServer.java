package muse;

import oscP5.*;

public class MuseOscServer {

	static MuseOscServer museOscServer;

	OscP5 museServer;
	static int recvPort = 5000;

	public MuseOscServer() {
		museServer = new OscP5(museOscServer, recvPort);
	}

	void oscEvent(OscMessage msg) {
		if (msg.checkAddrPattern("/muse/eeg") == true) {
			int i = 0;
			// System.out.print("EEG on channel " + i + ": " + msg.get(i).floatValue() +
			// "\n");
			if (msg.get(i).floatValue() > 1000) {
				System.out.println("BLINK");
			} else {
				System.out.println("NO BLINK");
			}
		}
	}

	public boolean isBlinking() {
		return false;
	}

}