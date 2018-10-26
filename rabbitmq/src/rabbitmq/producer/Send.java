package rabbitmq.producer;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	  private final static String QUEUE_NAME = "hello";
      public static void main(String[] args) throws java.io.IOException, InterruptedException {  
            /** 
             * 创建连接连接到MabbitMQ 
             */  
            ConnectionFactory factory = new ConnectionFactory();  
            // 设置MabbitMQ所在主机ip或者主机名  
            factory.setHost("192.168.134.20");  
            //factory.setHost("localhost"); 
            factory.setVirtualHost("jl");
            factory.setUsername("admin");
            factory.setPassword("admin");
            factory.setPort(5672);
            // 创建一个连接  
            Connection connection = factory.newConnection();  
            // 创建一个频道  
            Channel channel = connection.createChannel();  
            // 指定一个队列  
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
            //时间格式化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
           
            // 往队列中发出一条消息  
            int i = 0;
            while(true){
            	
            	Thread.sleep(5000);
            	i++;
            	String exeTime = sdf.format(new Date());
            	 // 发送的消息  
                String message = " 生产者发送的消息 ： 哈哈。。。。。。。" + exeTime ; 
            	channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  
            	System.out.println(" producer send message info '" + message + "'");  
            	if(i > 10){
            		break;
            	}
            }
            // 关闭频道和连接  
           channel.close();  
           connection.close();  
        }  
}
