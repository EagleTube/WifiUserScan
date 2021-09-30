package WifiUser;
        
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

        public class scan{
public static void main(String[] arg) throws UnknownHostException, IOException, InterruptedException{
   ExecutorService es = Executors.newCachedThreadPool();
   InetAddress subnet = InetAddress.getLocalHost();
   String hostIP = subnet.getHostAddress();
   Scanner sc = new Scanner(System.in);
   Pattern p = Pattern.compile("([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3}).*");
   Matcher m = p.matcher(hostIP);
   int timeout=200;
   es.awaitTermination(200L, TimeUnit.MILLISECONDS);
   System.out.println("Your local ip : "+subnet);
    if(m.matches()) {
    String s0 = m.group(1);
    String s1 = m.group(2);
    String s2 = m.group(3);
    String ip = s0 + "." + s1 + "." + s2;
   es.submit(() -> {
   try {
   for (int i=1;i<255;i++){
       String host=ip + "." + i;
       if (InetAddress.getByName(host).isReachable(timeout)){
           System.out.println(host + " is reachable --> " + InetAddress.getByName(host).getHostName()
           + "("  +timeout+ "ms)");
       }
   }
}
catch(IOException e){
    System.out.println("No such host is known "+subnet);  
        }
        });
           }
    else {
        System.out.println("Error");
    }
    es.shutdown();
        }
        }
