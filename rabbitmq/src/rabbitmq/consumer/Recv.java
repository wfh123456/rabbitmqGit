package rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Recv {

	   private final static String QUEUE_NAME = "hello";
	    public static void main(String[] args) throws java.io.IOException, java.lang.InterruptedException  {
	        
	        // 创建连接工厂
	         ConnectionFactory factory = new ConnectionFactory();  
	            factory.setHost("192.168.134.20");  
	            factory.setPort(5672);
	            factory.setVirtualHost("jl");
	            factory.setUsername("admin");
	            factory.setPassword("admin");
	            // 打开连接和创建频道，与发送端一样  
	            Connection connection = factory.newConnection();  
	            Channel channel = connection.createChannel();  
	      
	            // 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。  
	            channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
	            // 创建队列消费者  

	            System.out.println(" 开始创建消息队列消费者 ------------->>>");
	            // 指定消费队列 
	            
	            QueueingConsumer consumer = new QueueingConsumer(channel);  
	            channel.basicConsume(QUEUE_NAME, true, consumer);  
	            while(true){  
	                QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
	                String message = new String(delivery.getBody());  
	                System.out.println(" 接收到的rabbitmq 消息内容：" + message + "'");  
	            }  

	    }
}
