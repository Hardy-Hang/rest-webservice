package cn.fu.interceptor;
 
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
 

public class MessageInterceptor extends AbstractPhaseInterceptor<Message> {
    
    //����Ҫһ�����εĹ��캯��
    public MessageInterceptor(String phase) {
        super(phase);
    }
 
    public void handleMessage(Message message) throws Fault {
        System.out.println("############handleMessage##########");
        System.out.println(message);
        if (message.getDestination() != null) {
            System.out.println(message.getId() + "#" + message.getDestination().getMessageObserver());
        }
        if (message.getExchange() != null) {
            System.out.println(message.getExchange().getInMessage() + "#" + message.getExchange().getInFaultMessage());
            System.out.println(message.getExchange().getOutMessage() + "#" + message.getExchange().getOutFaultMessage());
        }
    }
}