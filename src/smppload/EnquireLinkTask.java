/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smppload;

import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.pdu.EnquireLink;
import com.cloudhopper.smpp.pdu.EnquireLinkResp;
import com.cloudhopper.smpp.type.SmppChannelException;
import com.cloudhopper.smpp.type.SmppTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EnquireLinkTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(EnquireLinkTask.class);
	private OutboundClient client;
	private Integer enquireLinkTimeout;

	public EnquireLinkTask(OutboundClient client, Integer enquireLinkTimeout) {
		this.client = client;
		this.enquireLinkTimeout = enquireLinkTimeout;
	}

	@Override
	public void run() {
		SmppSession smppSession = client.getSession();
		if (smppSession != null && smppSession.isBound()) {
			try {
				//logger.debug("sending enquire_link");
				EnquireLinkResp enquireLinkResp = smppSession.enquireLink(new EnquireLink(), enquireLinkTimeout);
				//logger.debug("enquire_link_resp: {}", enquireLinkResp);
			} catch (SmppTimeoutException e) {
				logger.warn("Enquire link failed, executing reconnect; " + e);
				logger.debug("", e);
				client.scheduleReconnect();
			} catch (SmppChannelException e) {
				logger.warn("Enquire link failed, executing reconnect; " + e);
				logger.debug("", e);
				client.scheduleReconnect();
			} catch (InterruptedException e) {
				logger.info("Enquire link interrupted, probably killed by reconnecting");
			} catch (Exception e) {
				logger.error("Enquire link failed, executing reconnect", e);
				client.scheduleReconnect();
			}
		} else {
			logger.error("enquire link running while session is not connected");
		}
	}
}