/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smppload;

public class ReconnectionTask implements Runnable {

	private final OutboundClient client;
	private Integer connectionFailedTimes;

	protected ReconnectionTask(OutboundClient client, Integer connectionFailedTimes) {
		this.client = client;
		this.connectionFailedTimes = connectionFailedTimes;
	}

	@Override
	public void run() {
		client.reconnect(connectionFailedTimes);
	}

}
