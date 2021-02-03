package io.adana.infinite.common.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * @author simon
 * @email merin@outlook.com
 * @date 2019/2/27
 * @description get the ip address and mac address
 */
@Slf4j
public class IpUtil {

    /**
     * 获取当前网络ip
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String unknownAdd = "unknown";
        String localIpv4 = "127.0.0.1";
        String localIpv6 = "0:0:0:0:0:0:0:1";
        int maxNum = 15;
        String ipAddress = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ipAddress) || ipAddress.length() == 0 || unknownAdd.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || ipAddress.length() == 0 || unknownAdd.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || unknownAdd.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ipAddress) || ipAddress.length() == 0 || unknownAdd.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (localIpv4.equals(ipAddress) || localIpv6.equals(ipAddress)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    log.error("UnknownHost," + e);
                }
                ipAddress = Objects.requireNonNull(inet).getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (StringUtils.isEmpty(ipAddress) && ipAddress.length() > maxNum) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 获得MAC地址
     *
     * @param ip ip address
     * @return mac address
     */
    public static String getMACAddress(String ip) {
        String str;
        String macAddress = "";
        int count = 100;
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < count; i++) {
                str = input.readLine();
                if (str != null) {
                    if (str.indexOf("MAC Address") > 1) {
                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }

}
