package top.moma.m64.core.helper;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import lombok.extern.slf4j.Slf4j;
import top.moma.m64.core.exceptions.M64Exception;

@Slf4j
public class IpHelper {

  private IpHelper() {}

  public static InetAddress getLocalIp() {
    try {
      for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
          e.hasMoreElements(); ) {
        NetworkInterface item = e.nextElement();
        for (InterfaceAddress address : item.getInterfaceAddresses()) {
          if (item.isLoopback() || !item.isUp()) {
            continue;
          }
          if (address.getAddress() instanceof Inet4Address) {
            return address.getAddress();
          }
        }
      }
      return InetAddress.getLocalHost();
    } catch (SocketException | UnknownHostException e) {
      throw new M64Exception(e);
    }
  }
}
