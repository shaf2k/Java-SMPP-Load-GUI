/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smppload;

import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;

public abstract class Client {

	protected volatile SmppSession smppSession;

	public SmppSessionConfiguration getConfiguration() {
		return smppSession.getConfiguration();
	}

	public boolean isConnected() {
		SmppSession session = smppSession;
		if (session != null) {
			return session.isBound();
		}
		return false;
	}

	public SmppSession getSession() {
		return smppSession;
	}
}
