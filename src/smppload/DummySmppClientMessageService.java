/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smppload;

import com.cloudhopper.smpp.pdu.DeliverSm;
import com.cloudhopper.smpp.pdu.PduResponse;

public class DummySmppClientMessageService implements SmppClientMessageService {

    @Override
    public PduResponse received(OutboundClient client, DeliverSm deliverSm) {
        return deliverSm.createResponse();
    }


}
