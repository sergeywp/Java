import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
 
public class GetMac{
	public static String GetMacAdr(){
		String s="";
		try {
            //InetAddress address = InetAddress.getLocalHost();
            //InetAddress address = InetAddress.getByName("192.168.46.53");
            InetAddress address = InetAddress.getByName("10.143.1.70");
            
            /*
             * Get NetworkInterface for the current host and then read
             * the hardware address.
             */
            NetworkInterface ni =
                    NetworkInterface.getByInetAddress(address);
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    /*
                     * Extract each array of mac address and convert it
                     * to hexa with the following format
                     * 08-00-27-DC-4A-9E.
                     */
                    for (int i = 0; i < mac.length; i++) {
                        //System.out.format("%02X%s",
                          //      mac[i], (i < mac.length - 1) ? "-" : "");
                       s = s + String.format("%02X%s",
                               mac[i], (i < mac.length - 1) ? "-" : "");
                       //String.format("%02X%s",
                         //  mac[i], (i < mac.length - 1) ? "-" : "");
                    }
                } else {
                	
                }
            } else {
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
		return s;
    }
    public static void main(String[] args) {
    	String s = GetMacAdr();
    	
    	System.out.format(s);
       /* try {
            InetAddress address = InetAddress.getLocalHost();
            //InetAddress address = InetAddress.getByName("192.168.46.53");
 
            /*
             * Get NetworkInterface for the current host and then read
             * the hardware address.
             */
            /*NetworkInterface ni =
                    NetworkInterface.getByInetAddress(address);
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    /*
                     * Extract each array of mac address and convert it
                     * to hexa with the following format
                     * 08-00-27-DC-4A-9E.
                     */
                    /*for (int i = 0; i < mac.length; i++) {
                        System.out.format("%02X%s",
                                mac[i], (i < mac.length - 1) ? "-" : "");
                    }
                } else {
                    System.out.println("Address doesn't exist or is not " +
                            "accessible.");
                }
            } else {
                System.out.println("Network Interface for the specified " +
                        "address is not found.");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }*/
    }
}
